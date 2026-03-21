/**
 * Exercise 4: Full TeleOp
 *
 * OBJECTIVE: Combine motor and servo control into one complete TeleOp.
 *
 * INSTRUCTIONS:
 *   1. In init(): Map leftMotor, rightMotor, and servo
 *   2. In loop():
 *      - Left/Right joysticks → Drive motors (tank drive)
 *      - A button → Open servo (1.0)
 *      - B button → Close servo (0.0)
 *      - Display motor powers and servo position
 *
 * WHAT SUCCESS LOOKS LIKE:
 *   Joysticks drive the robot, buttons control grabber
 *   All values displayed on Driver Station
 */
package org.firstinspires.ftc.teamcode.exercises;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Exercise 4 - Full TeleOp", group = "Exercises")
public class Exercise04_TeleOpFull extends OpMode {

    // TODO: Declare DcMotor leftMotor, rightMotor
    // TODO: Declare Servo servo

    @Override
    public void init() {
        // TODO: Map motors and servo from hardwareMap
        // TODO: Set servo initial position to 0.5
    }

    @Override
    public void loop() {
        // TODO: Read joystick values for left and right motors

        // TODO: Set motor powers from joysticks

        // TODO: Control servo with A (open) and B (close) buttons

        // TODO: Display on telemetry:
        //   - Left Motor Power
        //   - Right Motor Power
        //   - Servo Position
        //   - Instructions: "Left/Right = Drive, A = Open, B = Close"

        // TODO: Call telemetry.update()
    }
}
