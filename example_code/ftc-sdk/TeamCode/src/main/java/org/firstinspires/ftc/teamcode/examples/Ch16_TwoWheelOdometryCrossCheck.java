/**
 * Example 16: Two-Wheel Odometry Cross-Check Against a Real Pinpoint
 *
 * Demonstrates running the SAME simplified two-wheel-odometry math taught in the
 * robot-math module (TwoWheelOdometry.computeDelta) side by side with a real GoBilda
 * Pinpoint, so you can build intuition for what the Pinpoint is doing internally.
 *
 * NOTE: GoBildaPinpointDriver does not expose raw per-pod tick counts through a simple
 * public getter the way a plain DcMotor encoder does (at least not in any documented,
 * stable way at the time this was written) - so there is no direct way to feed the
 * Pinpoint's own raw pod readings into TwoWheelOdometry.computeDelta() for a true
 * apples-to-apples comparison. Instead, this example takes the Pinpoint's own reported
 * pose delta (current pose minus previous pose) each loop, rotates it into the robot's
 * forward/strafe axes, and feeds THAT through computeDelta() as a stand-in "raw pod
 * reading". This is a teaching approximation, not a real sensor feed.
 *
 * NOTE: This example assumes TwoWheelOdometry.java and PoseDelta.java have been copied
 * from the robot-math module's src/examples/ into this project's teamcode package
 * (e.g. org.firstinspires.ftc.teamcode.examples) before this file will compile.
 *
 * What you'll see: Two forward/strafe delta numbers side by side every loop - the
 * Pinpoint's own field-frame delta (rotated into robot axes) and the corrected result
 * of running that same delta back through the simplified textbook formula. They will
 * NOT match exactly, because the Pinpoint's real internal algorithm fuses IMU heading
 * data and uses its own (proprietary, more sophisticated) correction model - and
 * that's the point of this exercise. It's a sanity/intuition check for "does my mental
 * model of dead-wheel odometry roughly agree with what expensive hardware reports",
 * not a bit-for-bit validation of the Pinpoint.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "Example 16 - Odometry Cross-Check", group = "Examples")
public class Ch16_TwoWheelOdometryCrossCheck extends OpMode {

    private GoBildaPinpointDriver pinpoint;
    private Pose2D previousPose;

    // Same offsets that were measured and passed to pinpoint.setOffsets(...) in Ch14 -
    // reusing them here keeps the "model" side of the comparison consistent with what
    // the Pinpoint itself was configured with. Sign convention here matches
    // TwoWheelOdometry's javadoc (offset = distance of the pod from the center of
    // rotation along that pod's own axis) - re-verify the sign against your own
    // measurements if the two numbers disagree wildly in a spin-in-place test.
    private static final double FORWARD_POD_OFFSET_MM = -168.0;
    private static final double STRAFE_POD_OFFSET_MM = -84.0;

    @Override
    public void init() {
        try {
            pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");
            pinpoint.setOffsets(-84.0, -168.0, DistanceUnit.MM);
            pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
            pinpoint.setEncoderDirections(
                    GoBildaPinpointDriver.EncoderDirection.FORWARD,
                    GoBildaPinpointDriver.EncoderDirection.FORWARD);
            pinpoint.resetPosAndIMU();
            pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

            previousPose = pinpoint.getPosition();

            telemetry.addData("Status", "Pinpoint initialized successfully");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Hardware not found! Check config.");
            telemetry.addData("Error", e.getMessage());
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        // IMPORTANT: forgetting update() here means getPosition() returns stale data -
        // the same real bug called out in Ch14.
        pinpoint.update();
        Pose2D currentPose = pinpoint.getPosition();

        double previousHeadingRad = Math.toRadians(previousPose.getHeading(AngleUnit.DEGREES));
        double currentHeadingRad = Math.toRadians(currentPose.getHeading(AngleUnit.DEGREES));
        double headingDeltaRadians = currentHeadingRad - previousHeadingRad;

        double dxFieldMm = currentPose.getX(DistanceUnit.MM) - previousPose.getX(DistanceUnit.MM);
        double dyFieldMm = currentPose.getY(DistanceUnit.MM) - previousPose.getY(DistanceUnit.MM);

        // Rotate the field-frame delta into the robot's forward/strafe axes using the
        // heading at the START of this tick (good enough over one short loop interval).
        // This rotated delta stands in for what a pair of raw dead-wheel pods would have
        // reported this loop - see the class-level NOTE above for why we can't read the
        // Pinpoint's actual raw pod ticks directly.
        double cos = Math.cos(previousHeadingRad);
        double sin = Math.sin(previousHeadingRad);
        double standInForwardPodDeltaMm = dxFieldMm * cos + dyFieldMm * sin;
        double standInStrafePodDeltaMm = -dxFieldMm * sin + dyFieldMm * cos;

        // mmPerTick = 1.0 because our stand-in "raw" reading above is already in mm, not
        // encoder ticks - a real dead-wheel pod would pass its actual ticks-per-mm here.
        PoseDelta modelDelta = TwoWheelOdometry.computeDelta(
                standInForwardPodDeltaMm, standInStrafePodDeltaMm, headingDeltaRadians, 1.0,
                FORWARD_POD_OFFSET_MM, STRAFE_POD_OFFSET_MM);

        telemetry.addLine("--- Pinpoint (rotated into robot axes) ---");
        telemetry.addData("Pinpoint forward delta (mm)", standInForwardPodDeltaMm);
        telemetry.addData("Pinpoint strafe delta (mm)", standInStrafePodDeltaMm);
        telemetry.addLine("--- TwoWheelOdometry model (same input, textbook correction) ---");
        telemetry.addData("Model forward delta (mm)", modelDelta.dx);
        telemetry.addData("Model strafe delta (mm)", modelDelta.dy);
        telemetry.addData("Heading delta (deg)", Math.toDegrees(headingDeltaRadians));
        telemetry.update();

        previousPose = currentPose;
    }
}
