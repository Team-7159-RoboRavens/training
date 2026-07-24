package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="StarterBotTankDrive")
public class TankDrive extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor intakeMotor;
    private CRServo intakeServo1;
    private CRServo intakeServo2;

    // Runs ONCE when the driver hits INIT
    @Override
    public void init() {
        // Initialize the hardware variables
        leftMotor = hardwareMap.get(DcMotor.class, "DriveMotorLeft");
        rightMotor = hardwareMap.get(DcMotor.class, "DriveMotorRight");
        intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");

        intakeServo1 = hardwareMap.get(CRServo.class, "ServoLeft");
        intakeServo2 = hardwareMap.get(CRServo.class, "ServoRight");

        // Reverse the right motor so both go forward with positive power
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addData("Status", "Initialized");
    }

    // Runs REPEATEDLY after the driver hits PLAY, until they hit STOP
    @Override
    public void loop() {
        // Read joystick inputs (inverted because the Y-axis goes negative when pushed up)
        double Power = gamepad1.left_stick_y;
        double Steer = gamepad1.right_stick_x;

        // Apply power to the motors
        leftMotor.setPower(Power+Steer);
        rightMotor.setPower(Power-Steer);

        // If X is pressed, run at full speed (1.0). Otherwise, stop (0.0).
        if (gamepad1.x) {
            intakeMotor.setPower(1.0);
            intakeServo1.setPower(-1.0);
            intakeServo2.setPower(1.0);
        }
        if (gamepad1.a) {
            intakeMotor.setPower(0.0);
            intakeServo1.setPower(0.0);
            intakeServo2.setPower(0.0);
        }


        // Telemetry to debug
        //telemetry.addData("Left Power", leftPower);
        //telemetry.addData("Right Power", rightPower);
    }
}