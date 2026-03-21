/**
 * Exercise 3: Servo Control
 *
 * OBJECTIVE: Control a servo (grabber/arm) with gamepad buttons.
 *
 * INSTRUCTIONS:
 *   1. In init(): Get "servo" from hardwareMap, set initial position to 0.5
 *   2. In loop():
 *      - If A button pressed: set servo position to 1.0 (open)
 *      - If B button pressed: set servo position to 0.0 (closed)
 *      - Display current servo position on telemetry
 *
 * WHAT SUCCESS LOOKS LIKE:
 *   Press A button → servo moves to max position (1.0)
 *   Press B button → servo moves to min position (0.0)
 *   Current position displayed on Driver Station
 */
package org.firstinspires.ftc.teamcode.exercises;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Exercise 3 - Servo Toggle", group = "Exercises")
public class Exercise03_ServoToggle extends OpMode {

    // TODO: Declare Servo servo variable

    @Override
    public void init() {
        // TODO: Map servo using hardwareMap.get(Servo.class, "servo")
        // TODO: Set initial position to 0.5
    }

    @Override
    public void loop() {
        // TODO: If gamepad1.a is true, set servo position to 1.0 (open)

        // TODO: If gamepad1.b is true, set servo position to 0.0 (closed)

        // TODO: Display servo position on telemetry
        // TODO: Add label "A = Open (1.0), B = Closed (0.0)"
        // TODO: Call telemetry.update()
    }
}
