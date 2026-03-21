/**
 * Example 4: Motor Control
 *
 * Demonstrates setting motor power and reading motor properties.
 * Shows how to set power, read current, and monitor motor behavior.
 *
 * What you'll see: Motor telemetry including power and encoder position.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Example 4 - Motor Control", group = "Examples")
public class Ch07_MotorControlOpMode extends OpMode {

    private DcMotor motor;

    @Override
    public void init() {
        try {
            motor = hardwareMap.get(DcMotor.class, "motor");
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            telemetry.addData("Status", "Motor ready");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Motor not found");
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        if (motor != null) {
            // Set motor power from gamepad trigger
            double power = gamepad1.right_trigger - gamepad1.left_trigger;
            motor.setPower(power);

            // Read motor information
            telemetry.addData("Motor Power", motor.getPower());
            telemetry.addData("Current", motor.getCurrentPosition());
            telemetry.addData("Is Busy", motor.isBusy());

            // Display control instructions
            telemetry.addData("Right Trigger", "Forward");
            telemetry.addData("Left Trigger", "Backward");
        }
        telemetry.update();
    }
}
