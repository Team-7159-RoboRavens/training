/**
 * Example 3: Hardware Mapping Introduction
 *
 * Demonstrates how to get hardware devices from the hardware map.
 * Hardware map names must match exactly with those in the robot configuration XML.
 *
 * What you'll see: Motor power values displayed on Driver Station.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Example 3 - Hardware Mapping", group = "Examples")
public class Ch06_FirstHardwareOpMode extends OpMode {

    // Declare motor objects
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        // Get motors from hardware map
        // Names MUST match the config file exactly!
        try {
            leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
            rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
            telemetry.addData("Status", "Hardware initialized successfully");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Hardware not found! Check config.");
            telemetry.addData("Error", e.getMessage());
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        // Set motor power to joystick values
        if (leftMotor != null && rightMotor != null) {
            leftMotor.setPower(gamepad1.left_stick_y);
            rightMotor.setPower(gamepad1.right_stick_y);

            // Display power
            telemetry.addData("Left Motor Power", leftMotor.getPower());
            telemetry.addData("Right Motor Power", rightMotor.getPower());
        }
        telemetry.update();
    }
}
