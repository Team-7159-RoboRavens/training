/**
 * Example 2: Gamepad and Basic Math
 *
 * Demonstrates reading gamepad input and performing calculations.
 * Shows how to scale raw joystick values to motor power (0-255 range).
 *
 * What you'll see: Joystick values and calculated motor power on Driver Station.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Example 2 - Gamepad Math", group = "Examples")
public class Ch03_GamepadBasicMath extends OpMode {

    @Override
    public void init() {
        telemetry.addData("Status", "Gamepad Math Example");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Read raw joystick value (-1.0 to 1.0)
        double leftStickY = gamepad1.left_stick_y;

        // Scale to motor power (0-255)
        int motorPower = (int) (leftStickY * 255);

        // Read trigger values (0.0 to 1.0)
        double rightTrigger = gamepad1.right_trigger;
        int triggerPower = (int) (rightTrigger * 255);

        // Display results
        telemetry.addData("Left Stick Y", leftStickY);
        telemetry.addData("Motor Power", motorPower);
        telemetry.addData("Right Trigger", rightTrigger);
        telemetry.addData("Trigger Power", triggerPower);

        // Button states
        telemetry.addData("A Button Pressed", gamepad1.a);
        telemetry.addData("B Button Pressed", gamepad1.b);

        telemetry.update();
    }
}
