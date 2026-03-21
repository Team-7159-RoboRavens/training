/**
 * Example 1: Hello World OpMode
 *
 * The simplest OpMode: displays text on the driver station via telemetry.
 * This demonstrates the OpMode lifecycle: init() and loop().
 *
 * What you'll see: Text on Driver Station that updates continuously.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Example 1 - Hello World", group = "Examples")
public class Ch01_HelloWorldOpMode extends OpMode {

    private int loopCount = 0;

    @Override
    public void init() {
        // Called once when INIT button is pressed
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Called repeatedly (100 Hz) while PLAY is pressed
        loopCount++;
        telemetry.addData("Loop Count", loopCount);
        telemetry.addData("Status", "Running");
        telemetry.addData("Message", "Hello from FTC!");
        telemetry.update();
    }
}
