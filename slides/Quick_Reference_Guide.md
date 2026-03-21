# Java for FTC: Quick Reference & Hands-On Guide

## Table of Contents
1. Java Basics Quick Reference
2. FTC SDK Quick Reference
3. Complete Example: From Setup to First OpMode
4. Debugging Checklist
5. Code Snippets Library

---

## Part 1: Java Basics Quick Reference

### Data Types at a Glance

```java
// Numbers
int count = 42;           // Whole numbers
double voltage = 12.5;    // Decimal numbers
float temp = 98.6f;       // Smaller decimal (usually not used in FTC)

// Text
String name = "Team";     // Text/strings
char letter = 'A';        // Single character

// Logic
boolean moving = true;    // True or false
boolean stopped = false;

// Collections
int[] speeds = {10, 20, 30};                    // Fixed size array
ArrayList<Integer> list = new ArrayList<>();    // Dynamic size
```

### Operators

```java
// Arithmetic
5 + 3      // Add: 8
5 - 3      // Subtract: 2
5 * 3      // Multiply: 15
5 / 2      // Divide: 2 (integer division)
5 % 2      // Modulo (remainder): 1

// Comparison
5 == 5     // Equal: true
5 != 3     // Not equal: true
5 > 3      // Greater than: true
5 < 10     // Less than: true
5 >= 5     // Greater or equal: true

// Logic
true && false    // AND: false
true || false    // OR: true
!true            // NOT: false
```

### Control Flow

```java
// If-Else
if (x > 0) {
    // x is positive
} else if (x < 0) {
    // x is negative
} else {
    // x is zero
}

// For Loop
for (int i = 0; i < 10; i++) {
    // Runs 10 times: i = 0, 1, 2, ..., 9
}

// While Loop
while (condition) {
    // Runs while condition is true
}

// Enhanced For (loop through array)
for (int value : array) {
    // value is each element
}
```

### Method Definition

```java
// Simple method
public void stop() {
    motor.setPower(0);
}

// Method with parameters
public void setSpeed(double speed) {
    motor.setPower(speed);
}

// Method that returns value
public double getSpeed() {
    return motor.getPower();
}

// Method with multiple parameters and return
public int calculateDistance(int speed, int time) {
    return speed * time;
}
```

---

## Part 2: FTC SDK Quick Reference

### Imports You'll Need

```java
// Basic FTC stuff
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.*;

// Hardware
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

// OpModes
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// Other utilities
import java.util.ArrayList;
```

### Basic OpMode Template

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="My First OpMode")
public class MyFirstOpMode extends OpMode {

    // Declare hardware
    DcMotor motor;

    @Override
    public void init() {
        // Initialize hardware
        motor = hardwareMap.get(DcMotor.class, "motor");
        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Main control loop
        motor.setPower(gamepad1.left_stick_y);

        telemetry.addData("Power", motor.getPower());
        telemetry.update();
    }
}
```

### Hardware Access

```java
// Motors
DcMotor motor = hardwareMap.get(DcMotor.class, "motorName");
DcMotorEx motorEx = hardwareMap.get(DcMotorEx.class, "motorName");

// Servos
Servo servo = hardwareMap.get(Servo.class, "servoName");
CRServo crServo = hardwareMap.get(CRServo.class, "crServoName");

// Sensors
DistanceSensor distance = hardwareMap.get(DistanceSensor.class, "distanceName");
ColorSensor color = hardwareMap.get(ColorSensor.class, "colorName");

// IMU
IMUEx imu = hardwareMap.get(IMUEx.class, "imu");
```

### Common Motor Methods

```java
motor.setPower(0.5);           // Set power -1 to 1
motor.getPower();              // Get current power
motor.setDirection(FORWARD);   // Set direction
motor.setMode(RUN_USING_ENCODER);  // Use encoder
motor.resetEncoder();          // Reset encoder count
motor.getCurrentPosition();    // Get encoder position
```

### Common Servo Methods

```java
servo.setPosition(0.5);        // Set position 0 to 1
servo.getPosition();           // Get current position
servo.setDirection(FORWARD);   // Set direction
servo.scaleRange(0.2, 0.8);   // Limit range
```

### Gamepad Input

```java
// Joysticks (-1 to 1)
gamepad1.left_stick_y      // Forward/back
gamepad1.left_stick_x      // Left/right
gamepad1.right_stick_y     // Forward/back
gamepad1.right_stick_x     // Left/right

// Buttons (true/false)
gamepad1.a   gamepad1.b   gamepad1.x   gamepad1.y
gamepad1.left_bumper    gamepad1.right_bumper
gamepad1.dpad_up   gamepad1.dpad_down   gamepad1.dpad_left   gamepad1.dpad_right

// Triggers (0 to 1)
gamepad1.left_trigger
gamepad1.right_trigger

// Special buttons
gamepad1.guide              // Xbox button
gamepad1.start              // Start
gamepad1.back               // Back

// Stick buttons (press stick)
gamepad1.left_stick_button
gamepad1.right_stick_button
```

---

## Part 3: Complete Example - Setup to First OpMode

### Step 1: Configure Robot Hardware

1. Open Robot Controller app on the Control Hub
2. Tap "Configure Robot"
3. Create a new configuration
4. Add devices:
   - Motor Port 0 → Name: "leftMotor"
   - Motor Port 1 → Name: "rightMotor"
   - Servo Port 0 → Name: "claw"
5. Save configuration

### Step 2: Create Java File in Android Studio

```
Right-click: org.firstinspires.ftc.teamcode/TeamCode
Select: New → Java Class
Name: SimpleDrive
```

### Step 3: Write Your First OpMode

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="SimpleDrive", group="First")
public class SimpleDrive extends OpMode {

    // Hardware
    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo claw;

    @Override
    public void init() {
        // Get hardware
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        claw = hardwareMap.get(Servo.class, "claw");

        // Optional: Set initial direction
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addData("Status", "Ready to drive!");
        telemetry.update();
    }

    @Override
    public void loop() {
        // Tank Drive: left stick drives left side, right stick drives right
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);

        // Claw control: A = close, B = open
        if (gamepad1.a) {
            claw.setPosition(1.0);  // Close
        } else if (gamepad1.b) {
            claw.setPosition(0.0);  // Open
        }

        // Send debug data
        telemetry.addData("Left Motor", leftMotor.getPower());
        telemetry.addData("Right Motor", rightMotor.getPower());
        telemetry.addData("Claw Position", claw.getPosition());
        telemetry.update();
    }
}
```

### Step 4: Deploy and Test

1. Connect phone with Android Studio
2. Build → Build 'FtcRobotController'
3. Wait for build to complete
4. Run (green play button)
5. On Driver Station, select "SimpleDrive"
6. Press INIT, then PLAY
7. Use left joystick to drive!

---

## Part 4: Debugging Checklist

### Before Deploying

- [ ] Did you add `@TeleOp()` or `@Autonomous()` annotation?
- [ ] Is your class name exactly what you named the file?
- [ ] Does your class extend `OpMode` or `LinearOpMode`?
- [ ] Did you implement `init()` and `loop()` methods?
- [ ] Do all hardware names match your robot configuration?

### After OpMode Loads on Driver Station

- [ ] Does your OpMode appear in the list?
- [ ] Can you press INIT without errors?
- [ ] Does telemetry show in INIT_LOOP?
- [ ] Can you press PLAY to start?

### Testing Motor Direction

```
1. Set motor power to 0.5
2. Check which way it spins
3. If wrong way, add: motor.setDirection(DcMotor.Direction.REVERSE)
4. Retest
```

### Testing Servo Position

```
1. Set servo to 0.0 - should move to one end
2. Set servo to 1.0 - should move to other end
3. If reversed, add: servo.setDirection(Servo.Direction.REVERSE)
```

### If Nothing Happens

1. Check telemetry output - does it show values?
2. Check Logcat for errors (Android Studio)
3. Verify hardware config exists and is selected
4. Try a fresh build: Build → Clean Project, then Build

### If Motor Powers Show But Motor Doesn't Move

1. Check motor battery connection
2. Check motor port (should match config)
3. Try different port
4. Motor might be broken - try different motor

---

## Part 5: Code Snippets Library

### Tank Drive (2 motors)

```java
leftMotor.setPower(gamepad1.left_stick_y);
rightMotor.setPower(gamepad1.right_stick_y);
```

### Arcade Drive (2 motors, 2 sticks)

```java
double drive = gamepad1.left_stick_y;
double turn = gamepad1.right_stick_x;

leftMotor.setPower(drive + turn);
rightMotor.setPower(drive - turn);
```

### Toggle Servo (Press A to toggle)

```java
boolean lastA = false;
boolean servoOpen = false;

if (gamepad1.a && !lastA) {
    servoOpen = !servoOpen;
}
lastA = gamepad1.a;

if (servoOpen) {
    claw.setPosition(0.0);
} else {
    claw.setPosition(1.0);
}
```

### Smooth Servo Movement

```java
// Move servo from 0 to 1 over time
for (double pos = 0.0; pos <= 1.0; pos += 0.01) {
    servo.setPosition(pos);
    sleep(10);  // Wait 10ms

    // Can add: if (!opModeIsActive()) break;
    // to stop if STOP is pressed
}
```

### Read Motor Encoder

```java
motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
motor.resetEncoder();

int tickCount = motor.getCurrentPosition();
telemetry.addData("Ticks", tickCount);
telemetry.update();
```

### Two-Button Control

```java
if (gamepad1.a) {
    // A pressed
    motor.setPower(0.5);
} else if (gamepad1.b) {
    // B pressed
    motor.setPower(-0.5);
} else {
    // Neither pressed
    motor.setPower(0.0);
}
```

### Analog Control with Trigger

```java
double power = gamepad1.right_trigger;  // 0 to 1
motor.setPower(power);
```

### Joystick with Dead Zone

```java
double y = gamepad1.left_stick_y;

// Ignore small movements (dead zone)
if (Math.abs(y) < 0.1) {
    y = 0.0;
}

motor.setPower(y);
```

### Auto - Drive Forward Then Stop

```java
@Autonomous(name="Drive Forward")
public class DriveForward extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
    }

    @Override
    public void loop() {
        if (getRuntime() < 2.0) {  // First 2 seconds
            leftMotor.setPower(0.5);
            rightMotor.setPower(0.5);
        } else {
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            requestOpModeStop();
        }

        telemetry.addData("Time", getRuntime());
        telemetry.update();
    }
}
```

### IMU Heading Check

```java
IMUEx imu = hardwareMap.get(IMUEx.class, "imu");

// In loop:
double heading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
telemetry.addData("Heading", heading);
telemetry.update();
```

---

## Programming Best Practices

### 1. Always Check Telemetry
Add telemetry to see what's happening:
```java
telemetry.addData("Motor Power", motor.getPower());
telemetry.addData("Gamepad A", gamepad1.a);
telemetry.update();
```

### 2. Use Meaningful Names
```java
// Good
DcMotor intakeMotor = hardwareMap.get(DcMotor.class, "intake");
Servo clawServo = hardwareMap.get(Servo.class, "claw");

// Bad
DcMotor m1 = hardwareMap.get(DcMotor.class, "m1");
Servo s = hardwareMap.get(Servo.class, "s");
```

### 3. Test Individual Components
Before combining code:
- Test motor alone
- Test servo alone
- Test gamepad input alone
- Then combine

### 4. Add Comments for Complex Code
```java
// Simple code doesn't need comments
motor.setPower(0.5);

// Complex code needs explanation
// Arcade drive formula: (drive + turn, drive - turn)
// This allows forward motion AND rotation
leftMotor.setPower(gamepad1.left_stick_y + gamepad1.right_stick_x);
```

### 5. Handle Errors Gracefully
```java
try {
    motor = hardwareMap.get(DcMotor.class, "leftMotor");
} catch (Exception e) {
    telemetry.addData("Error", "Motor not found: " + e.getMessage());
}
```

---

## Common Mistakes to Avoid

| Mistake | Problem | Fix |
|---------|---------|-----|
| Forget `@TeleOp()` | OpMode doesn't show on DS | Add annotation |
| Hardware names mismatch | "Hardware not found" error | Check config |
| Wrong motor direction | Robot turns instead of straight | Use `.setDirection(REVERSE)` |
| No telemetry.update() | Driver sees no data | Add `telemetry.update()` |
| Use `sleep()` in loop() | Robot freezes | Use `getRuntime()` instead |
| Forget to call `hardwareMap` | NullPointerException | Initialize all hardware in `init()` |
| Can't find hardware class | Import error | Check imports at top |
| Gamepad reading wrong | Control not responsive | Check dead zones |

---

## Next Topics to Explore

Once you master these basics:

1. **State Machines** - Organize complex autonomous
2. **PID Controllers** - Precise motor positioning
3. **Encoders** - Distance/speed measurement
4. **Road Runner** - Advanced path planning
5. **Computer Vision** - Camera-based detection
6. **Sensors** - Distance, color, IMU
7. **Command-based Framework** - Organize large codebases

---

## Resources

- **Official FTC Documentation**: ftc-docs.readthedocs.io
- **Learn Java for FTC**: github.com/alan412/LearnJavaForFTC
- **Game Manual 0**: gm0.org (comprehensive guide)
- **FTC Discord**: discord.gg/first-tech-challenge (community support)
- **Android Studio Guide**: developer.android.com

---

## Quick Tip: Testing Your Code

```java
// Add this to EVERY loop to verify it's running
static int loopCount = 0;
loopCount++;
telemetry.addData("Loop Count", loopCount);
telemetry.update();

// If this number keeps increasing, your loop() is running
// If it's stuck, check for errors in Logcat
```

---

**Remember**: The best way to learn programming is by DOING. Type the code yourself, break things, fix them, and learn!

Good luck building your FTC robot! 🤖
