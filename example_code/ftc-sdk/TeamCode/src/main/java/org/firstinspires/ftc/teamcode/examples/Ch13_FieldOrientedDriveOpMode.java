/**
 * Example 13: Field-Oriented Mecanum Drive with a Real IMU
 *
 * Demonstrates driving a mecanum chassis in FIELD-CENTRIC mode: pushing the left
 * stick "away from you" always drives the robot away from the driver station wall,
 * no matter which way the robot is currently facing. This is done by reading the
 * robot's heading from the REV Hub's built-in IMU and rotating the driver's stick
 * vector into the robot's own frame BEFORE handing it to the mecanum math.
 *
 * NOTE: This example assumes MecanumKinematics.java, FieldOrientedTransform.java,
 * and MotorPowers.java have been copied from the robot-math module's src/examples/
 * into this project's teamcode package (e.g. org.firstinspires.ftc.teamcode.examples)
 * before this file will compile. Those classes are pure math, already unit-tested
 * (38/38 passing) in robot-math - this OpMode just wires them to real hardware.
 *
 * What you'll see: Robot drives relative to the FIELD, not its own nose, while the
 * heading and each wheel's power are shown on telemetry. Press A to re-zero heading
 * (do this while the robot is physically facing "away from the driver" at match
 * start). Hold the left bumper to temporarily fall back to robot-centric driving
 * (useful when the IMU has drifted and you just need to limp the robot around).
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Example 13 - Field-Oriented Drive", group = "Examples")
public class Ch13_FieldOrientedDriveOpMode extends OpMode {

    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftBack;
    private DcMotor rightBack;
    private IMU imu;

    @Override
    public void init() {
        try {
            leftFront = hardwareMap.get(DcMotor.class, "leftFront");
            rightFront = hardwareMap.get(DcMotor.class, "rightFront");
            leftBack = hardwareMap.get(DcMotor.class, "leftBack");
            rightBack = hardwareMap.get(DcMotor.class, "rightBack");

            imu = hardwareMap.get(IMU.class, "imu");

            // NOTE: LogoFacingDirection/UsbFacingDirection must match how the REV
            // Control/Expansion Hub is physically mounted on YOUR robot. UP/FORWARD
            // is just the most common orientation - verify against the hub's silkscreen
            // before trusting the heading.
            RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                    RevHubOrientationOnRobot.LogoFacingDirection.UP;
            RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;
            RevHubOrientationOnRobot orientationOnRobot =
                    new RevHubOrientationOnRobot(logoDirection, usbDirection);
            imu.initialize(new IMU.Parameters(orientationOnRobot));

            telemetry.addData("Status", "Hardware initialized successfully");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Hardware not found! Check config.");
            telemetry.addData("Error", e.getMessage());
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        // A resets the IMU's yaw to zero. Do this at the START of a match while the
        // robot is physically pointed the direction you want to call "forward" on the
        // field - not necessarily the direction the robot's own chassis nose points.
        if (gamepad1.a) {
            imu.resetYaw();
        }

        double headingRadians = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Gamepad Y sticks report "up" as negative, so flip it to get a normal
        // "positive = forward" convention before doing any math with it.
        double fieldForward = -gamepad1.left_stick_y;
        double fieldStrafe = gamepad1.left_stick_x;
        double rotate = gamepad1.right_stick_x;

        boolean robotCentricOverride = gamepad1.left_bumper;

        MotorPowers powers;
        if (robotCentricOverride) {
            // Skip the field transform entirely - the stick vector is applied directly
            // in the robot's own frame, exactly like a non-field-oriented drive.
            powers = MecanumKinematics.robotCentric(fieldForward, fieldStrafe, rotate);
        } else {
            FieldOrientedTransform.FieldVector robotRelative =
                    FieldOrientedTransform.toRobotRelative(fieldForward, fieldStrafe, headingRadians);
            powers = MecanumKinematics.robotCentric(robotRelative.forward, robotRelative.strafe, rotate);
        }

        leftFront.setPower(powers.lf);
        rightFront.setPower(powers.rf);
        leftBack.setPower(powers.lb);
        rightBack.setPower(powers.rb);

        telemetry.addData("Mode", robotCentricOverride ? "ROBOT-CENTRIC (override)" : "FIELD-CENTRIC");
        telemetry.addData("Heading (deg)", Math.toDegrees(headingRadians));
        telemetry.addData("LF power", powers.lf);
        telemetry.addData("RF power", powers.rf);
        telemetry.addData("LB power", powers.lb);
        telemetry.addData("RB power", powers.rb);
        telemetry.update();
    }
}
