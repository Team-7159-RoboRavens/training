/**
 * Exercise 5: Simple Autonomous with State Machine
 *
 * OBJECTIVE: Create an autonomous routine that moves forward, stops.
 *
 * INSTRUCTIONS:
 *   1. In init(): Map leftMotor and rightMotor
 *   2. Implement a state machine with states: INIT, DRIVE, STOP, DONE
 *   3. DRIVE state: Set both motors to 0.5 power for 2 seconds
 *   4. STOP state: Set both motors to 0.0
 *   5. Display current state and elapsed time on telemetry
 *
 * WHAT SUCCESS LOOKS LIKE:
 *   Robot drives forward for 2 seconds, then stops.
 *   State changes displayed on Driver Station.
 */
package org.firstinspires.ftc.teamcode.exercises;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Exercise 5 - Simple Auto", group = "Exercises")
public class Exercise05_SimpleAuto extends OpMode {

    // TODO: Declare DcMotor leftMotor, rightMotor

    // TODO: Create an enum State with values: INIT, DRIVE, STOP, DONE

    // TODO: Declare State currentState and long stateStartTime

    @Override
    public void init() {
        // TODO: Map motors from hardwareMap
        // TODO: Initialize currentState to State.INIT
    }

    @Override
    public void loop() {
        // TODO: Calculate elapsed time since state started

        // TODO: Switch on currentState:
        //   INIT: Set stateStartTime, change to DRIVE
        //   DRIVE: Set motors to 0.5 power
        //           If elapsed > 2000ms, change to STOP, reset timer
        //   STOP: Set motors to 0.0, change to DONE
        //   DONE: Keep motors off

        // TODO: Display telemetry:
        //   - Current State
        //   - Elapsed time (ms)
        //   - Motor powers

        telemetry.update();
    }
}
