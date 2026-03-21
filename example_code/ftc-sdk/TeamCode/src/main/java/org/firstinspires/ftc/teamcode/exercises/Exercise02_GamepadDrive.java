/**
 * Exercise 2: Motor Control
 *
 * OBJECTIVE: Control a motor with gamepad joystick input (tank drive).
 *
 * INSTRUCTIONS:
 *   1. In init(): Get "leftMotor" and "rightMotor" from hardwareMap
 *   2. In loop():
 *      - Set left motor power = gamepad1.left_stick_y
 *      - Set right motor power = gamepad1.right_stick_y
 *      - Display both motor powers on telemetry
 *
 * WHAT SUCCESS LOOKS LIKE:
 *   Push left stick forward → left motor spins forward
 *   Push right stick forward → right motor spins forward
 *   Motor powers displayed on Driver Station
 */
package org.firstinspires.ftc.teamcode.exercises;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Exercise 2 - Motor Drive", group = "Exercises")
public class Exercise02_GamepadDrive extends OpMode {

    // TODO: Declare DcMotor leftMotor and DcMotor rightMotor

    @Override
    public void init() {
        // TODO: Map leftMotor using hardwareMap.get(DcMotor.class, "leftMotor")
        // TODO: Map rightMotor using hardwareMap.get(DcMotor.class, "rightMotor")
    }

    @Override
    public void loop() {
        // TODO: Get joystick values from gamepad1.left_stick_y and gamepad1.right_stick_y

        // TODO: Set motor powers using setPower()

        // TODO: Display motor powers on telemetry
        // TODO: Call telemetry.update()
    }
}
