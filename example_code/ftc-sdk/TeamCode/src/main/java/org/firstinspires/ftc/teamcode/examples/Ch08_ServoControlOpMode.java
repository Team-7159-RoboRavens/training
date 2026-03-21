/**
 * Example 5: Servo Control
 *
 * Demonstrates controlling servo motors (0-1 position range).
 * Servos are useful for grabbers, arms, and positioning mechanisms.
 *
 * What you'll see: Servo position updated by gamepad triggers.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Example 5 - Servo Control", group = "Examples")
public class Ch08_ServoControlOpMode extends OpMode {

    private Servo servo;
    private double servoPosition = 0.5;

    @Override
    public void init() {
        try {
            servo = hardwareMap.get(Servo.class, "servo");
            servo.setPosition(0.5);  // Start at middle (0.0 = min, 1.0 = max)
            telemetry.addData("Status", "Servo ready");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Servo not found");
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        if (servo != null) {
            // Move servo with right/left triggers
            if (gamepad1.right_trigger > 0.1) {
                servoPosition += 0.02;  // Move toward max (1.0)
            }
            if (gamepad1.left_trigger > 0.1) {
                servoPosition -= 0.02;  // Move toward min (0.0)
            }

            // Clamp position between 0.0 and 1.0
            if (servoPosition > 1.0) servoPosition = 1.0;
            if (servoPosition < 0.0) servoPosition = 0.0;

            // Set servo position
            servo.setPosition(servoPosition);

            // Display
            telemetry.addData("Servo Position", servoPosition);
            telemetry.addData("0.0 = Min", "1.0 = Max");
            telemetry.addData("Right Trigger", "Increase");
            telemetry.addData("Left Trigger", "Decrease");
        }
        telemetry.update();
    }
}
