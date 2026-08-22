/**
 * Example 14: GoBilda Pinpoint Odometry Computer
 *
 * Demonstrates configuring a GoBilda Pinpoint Odometry Computer (dead-wheel pods +
 * built-in IMU fusion) and reading the robot's field pose (x, y, heading) from it every
 * loop. The Pinpoint does the two-wheel-odometry + heading fusion math internally -
 * you just have to tell it your pod offsets and encoder resolution once, then remember
 * to call update() every loop before reading a fresh pose.
 *
 * NOTE: This example assumes HeadingSource.java has been copied from the robot-math
 * module's src/examples/ into this project's teamcode package (e.g.
 * org.firstinspires.ftc.teamcode.examples) before this file will compile.
 *
 * What you'll see: Live X/Y/heading telemetry that updates as you push the robot
 * around by hand (no drive motors needed to see the pose change - odometry pods spin
 * freely).
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;

@TeleOp(name = "Example 14 - GoBilda Pinpoint", group = "Examples")
public class Ch14_GoBildaPinpointOpMode extends OpMode {

    private GoBildaPinpointDriver pinpoint;

    // This adapter lets ANY code written against the HeadingSource interface (like
    // robot-math's HeadingDriftDetector, already unit-tested with a FakeHeadingSource)
    // run unmodified against the REAL Pinpoint hardware. Nothing about
    // HeadingDriftDetector's logic needs to change - only which HeadingSource
    // implementation gets passed into its constructor.
    private class PinpointHeadingSource implements HeadingSource {
        @Override
        public double getHeadingDegrees() {
            return pinpoint.getPosition().getHeading(AngleUnit.DEGREES);
        }
    }

    // Wire the real adapter up here (or pass it into a HeadingDriftDetector) once you
    // copy HeadingDriftDetector.java into this package too:
    //   HeadingDriftDetector driftDetector = new HeadingDriftDetector(new PinpointHeadingSource(), 30.0);
    private HeadingSource headingSource;

    @Override
    public void init() {
        try {
            pinpoint = hardwareMap.get(GoBildaPinpointDriver.class, "pinpoint");

            // PLACEHOLDER OFFSETS - these MUST be measured on your own robot before
            // trusting the pose! X = how far sideways the Pinpoint is from the robot's
            // center (left of center = positive), Y = how far forward of center it is.
            // Measure with a ruler from the robot's center of rotation to the Pinpoint
            // board, in millimeters.
            pinpoint.setOffsets(-84.0, -168.0, DistanceUnit.MM);

            // Must match the actual odometry pods on your robot (goBILDA sells a few
            // different pod sizes with different tick-per-mm resolutions).
            pinpoint.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);

            // Must match which direction each pod's encoder counts up when the robot
            // moves forward/right - flip FORWARD to REVERSED if the pose drifts the
            // wrong way when you push the robot in a known direction.
            pinpoint.setEncoderDirections(
                    GoBildaPinpointDriver.EncoderDirection.FORWARD,
                    GoBildaPinpointDriver.EncoderDirection.FORWARD);

            // Zeroes both the pods and the built-in IMU. Do this with the robot
            // stationary at init time.
            pinpoint.resetPosAndIMU();
            pinpoint.setPosition(new Pose2D(DistanceUnit.INCH, 0, 0, AngleUnit.DEGREES, 0));

            headingSource = new PinpointHeadingSource();

            telemetry.addData("Status", "Pinpoint initialized successfully");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Hardware not found! Check config.");
            telemetry.addData("Error", e.getMessage());
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        // IMPORTANT: update() must be called every single loop before reading a fresh
        // pose. Forget this and getPosition() silently returns STALE data - this is a
        // real bug we hit (and fixed) in a sibling project this season, so don't skip it.
        pinpoint.update();

        Pose2D pose = pinpoint.getPosition();

        telemetry.addData("X (in)", pose.getX(DistanceUnit.INCH));
        telemetry.addData("Y (in)", pose.getY(DistanceUnit.INCH));
        telemetry.addData("Heading (deg)", pose.getHeading(AngleUnit.DEGREES));
        telemetry.addData("HeadingSource adapter (deg)", headingSource.getHeadingDegrees());
        // NOTE: getDeviceStatus() is a real method on GoBildaPinpointDriver as of the
        // manufacturer's published driver, returning an enum (e.g. READY, CALCULATING,
        // NOT_READY) - verify the exact enum name/values against the version of the
        // driver jar in your project before relying on it for fault detection.
        telemetry.addData("Device Status", pinpoint.getDeviceStatus());
        telemetry.update();
    }
}
