/**
 * Example 6: State Machine for Autonomous
 *
 * Demonstrates using a state machine to control robot behavior in autonomous.
 * State machines are essential for reliable autonomous routines.
 *
 * What you'll see: Robot executing a sequence: forward, backward, stop.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Example 6 - State Machine Auto", group = "Examples")
public class Ch12_StateMachineAuto extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private long stateStartTime = 0;

    enum State {
        INIT,
        DRIVE_FORWARD,
        DRIVE_BACKWARD,
        STOP,
        DONE
    }

    private State currentState = State.INIT;

    @Override
    public void init() {
        try {
            leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
            rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
            telemetry.addData("Status", "Ready for autonomous");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Hardware init failed");
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        long elapsedTime = System.currentTimeMillis() - stateStartTime;

        switch (currentState) {
            case INIT:
                stateStartTime = System.currentTimeMillis();
                currentState = State.DRIVE_FORWARD;
                break;

            case DRIVE_FORWARD:
                leftMotor.setPower(0.5);
                rightMotor.setPower(0.5);
                if (elapsedTime > 2000) {  // Drive for 2 seconds
                    currentState = State.DRIVE_BACKWARD;
                    stateStartTime = System.currentTimeMillis();
                }
                break;

            case DRIVE_BACKWARD:
                leftMotor.setPower(-0.5);
                rightMotor.setPower(-0.5);
                if (elapsedTime > 2000) {  // Drive back for 2 seconds
                    currentState = State.STOP;
                    stateStartTime = System.currentTimeMillis();
                }
                break;

            case STOP:
                leftMotor.setPower(0.0);
                rightMotor.setPower(0.0);
                currentState = State.DONE;
                break;

            case DONE:
                // Keep motors off
                break;
        }

        telemetry.addData("Current State", currentState);
        telemetry.addData("Elapsed (ms)", elapsedTime);
        telemetry.update();
    }
}
