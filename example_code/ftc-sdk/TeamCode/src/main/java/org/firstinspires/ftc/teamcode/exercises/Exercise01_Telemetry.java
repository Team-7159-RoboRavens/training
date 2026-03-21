/**
 * Exercise 1: Telemetry
 *
 * OBJECTIVE: Display sensor data on the Driver Station using telemetry.
 *
 * INSTRUCTIONS:
 *   1. In init(): Display "Status" = "Ready"
 *   2. In loop():
 *      - Add telemetry line "Loop Count" = incremented value
 *      - Add telemetry line "Time" = current elapsed time
 *      - Call telemetry.update()
 *
 * WHAT SUCCESS LOOKS LIKE:
 *   Loop count increases each cycle, time increments in milliseconds.
 */
package org.firstinspires.ftc.teamcode.exercises;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Exercise 1 - Telemetry", group = "Exercises")
public class Exercise01_Telemetry extends OpMode {

    private int loopCount = 0;

    @Override
    public void init() {
        // TODO: Add telemetry line "Status" with value "Ready"
        // TODO: Call telemetry.update()
    }

    @Override
    public void loop() {
        // TODO: Increment loopCount
        // TODO: Add telemetry line "Loop Count" with loopCount value
        // TODO: Add telemetry line "Time (ms)" with getRuntime() * 1000
        // TODO: Call telemetry.update()
    }
}
