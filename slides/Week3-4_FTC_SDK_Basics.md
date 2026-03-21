# Week 3-4: FTC SDK Basics

## What You'll Learn This Week
- OpMode Structure and Lifecycle
- Hardware Mapping
- The FTC SDK and Android Studio
- Driver Station Telemetry
- Writing Your First Real OpMode
- Gamepad Input Handling

---

## Slide 1: What is the FTC SDK?

The **FTC Software Development Kit (SDK)** is the official framework for controlling FTC robots.

### Key Components:
- **Robot Controller** - Runs the code (Android phone or REV Control Hub)
- **Driver Station** - Used by drivers to control robot during competition
- **Hardware Map** - Connects code to physical robot parts
- **OpMode** - A program that runs on the robot

### Where Code Lives:
```
FtcRobotController/
└── TeamCode/
    └── src/main/java/org/firstinspires/ftc/teamcode/
        ├── TeleOp/         (Driver-controlled programs)
        ├── Autonomous/     (Auto programs)
        └── Hardware/       (Hardware configurations)
```

---

## Slide 2: OpMode - Your Robot Program

An **OpMode** (Operation Mode) is a single program/mode that runs on your robot.

### Analogy:
- A **video game** = FTC Robot Controller
- A **game level** = An OpMode
- You can have multiple levels (OpModes) in one game (Robot Controller)

### Types of OpModes:
1. **TeleOp** - Driver controlled (human controls robot with gamepad)
2. **Autonomous** - Robot runs on its own based on code
3. **Test** - Used for testing individual components

### Every OpMode Needs:
- To extend `OpMode` class
- Implement `init()` and `loop()` methods
- An `@TeleOp` or `@Autonomous` annotation

---

## Slide 3: OpMode Lifecycle Diagram

The robot goes through these stages:

```
┌─────────────────────────────────────────┐
│   OpMode selected on Driver Station     │
└──────────────┬──────────────────────────┘
               ↓
        ┌──────────────┐
        │  init() ×1   │  ← Runs ONCE when INIT is pressed
        └──────┬───────┘
               ↓
        ┌──────────────┐
        │  start() ×1  │  ← Runs ONCE when PLAY is pressed
        └──────┬───────┘
               ↓
        ┌──────────────────────────────┐
        │  loop() × MANY               │  ← Runs repeatedly
        │  (until STOP pressed)        │
        └──────┬───────────────────────┘
               ↓
        ┌──────────────┐
        │  stop() ×1   │  ← Runs ONCE when STOP is pressed
        └──────────────┘
```

---

## Slide 4: Required vs Optional Methods

### REQUIRED:
- **`init()`** - Runs once at startup. Initialize hardware, set initial values.
- **`loop()`** - Runs repeatedly. Main program logic goes here.

### OPTIONAL (but useful):
- **`start()`** - Runs once when PLAY is pressed (after init).
- **`stop()`** - Runs once when STOP is pressed. Good for cleanup.

### TeleOp Template:
```java
@TeleOp(name="My TeleOp")
public class MyTeleOp extends OpMode {
    DcMotor leftMotor;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
    }

    @Override
    public void loop() {
        leftMotor.setPower(gamepad1.left_stick_y);
    }
}
```

---

## Slide 5: Annotations - Telling FTC About Your OpMode

**Annotations** start with `@` and give information to FTC.

### @TeleOp - Driver Controlled Program
```java
@TeleOp(name="Basic Drive", group="Main")
public class BasicDrive extends OpMode {
    // This will show up on driver station
}
```

- `name` - What shows on the Driver Station
- `group` - Optional category (like a folder)

### @Autonomous - Self-Running Program
```java
@Autonomous(name="Red Left Auto", group="Autonomous")
public class RedLeftAuto extends OpMode {
    // Runs on its own
}
```

### @Disabled - Hide This Program
```java
@Disabled
public class OldCode extends OpMode {
    // Won't show on driver station
}
```

### Important:
If you forget the annotation (`@TeleOp()` or `@Autonomous()`), your code will compile but **won't appear on the driver station!**

---

## Slide 6: Hardware Mapping

**Hardware mapping** connects your Java code to actual robot parts.

### Step 1: Define Hardware Configuration (Phone Setup)
On the Robot Controller phone:
1. Connect motors/servos/sensors
2. Go to Robot Controller > Configure Robot
3. Create a configuration file with names for each part
4. Save it

Example configuration:
```
Device Type    | Port  | Configured Name
Motor          | 0     | leftMotor
Motor          | 1     | rightMotor
Servo          | 0     | clawServo
```

### Step 2: Use Hardware in Code
```java
@Override
public void init() {
    // Get hardware by the name from configuration
    DcMotor leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
    Servo claw = hardwareMap.get(Servo.class, "clawServo");
}
```

### The Names Must Match!
- Configuration says: `"leftMotor"`
- Code must use: `"leftMotor"`
- If they don't match → Runtime error!

---

## Slide 7: Controlling Motors

**Motors** are controlled by setting their power level.

### Motor Power:
- Range: **-1.0 to +1.0**
  - `1.0` = full forward
  - `0.5` = half power forward
  - `0.0` = stopped
  - `-0.5` = half power backward
  - `-1.0` = full backward

### Basic Motor Control:
```java
DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

motor.setPower(0.75);    // Run at 75% forward
motor.setPower(-0.5);    // Run at 50% backward
motor.setPower(0.0);     // Stop

// Read current power
double power = motor.getPower();
```

### Motor as Sensor (Encoder):
```java
motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
motor.resetEncoder();
int ticks = motor.getCurrentPosition();  // How many ticks moved
```

### Common Patterns:
```java
// Simple drivetrain control
leftMotor.setPower(gamepad1.left_stick_y);
rightMotor.setPower(gamepad1.right_stick_y);

// One motor to control speed
int targetSpeed = 50;  // percent
motor.setPower(targetSpeed / 100.0);
```

---

## Slide 8: Controlling Servos

**Servos** move to a specific angle (typically 0° to 180°).

### Servo Power:
- Range: **0.0 to 1.0**
  - `0.0` = One end (usually 0°)
  - `0.5` = Middle (usually 90°)
  - `1.0` = Other end (usually 180°)

### Basic Servo Control:
```java
Servo claw = hardwareMap.get(Servo.class, "claw");

claw.setPosition(0.0);     // Move to 0°
claw.setPosition(0.5);     // Move to 90° (middle)
claw.setPosition(1.0);     // Move to 180°

// Read current position
double pos = claw.getPosition();
```

### Common Patterns:
```java
// Toggle open/closed with button press
if (gamepad1.a) {
    claw.setPosition(0.0);  // Open
}
if (gamepad1.b) {
    claw.setPosition(1.0);  // Close
}

// Smooth movement from 0 to 1
for (double i = 0; i <= 1.0; i += 0.01) {
    claw.setPosition(i);
    sleep(10);  // Wait 10ms between steps
}
```

---

## Slide 9: Reading Gamepad Input

The **gamepad** is how drivers control the robot.

### Analog Inputs (Joysticks):
```java
// Left joystick
double leftY = gamepad1.left_stick_y;   // -1.0 to 1.0 (up/down)
double leftX = gamepad1.left_stick_x;   // -1.0 to 1.0 (left/right)

// Right joystick
double rightY = gamepad1.right_stick_y;
double rightX = gamepad1.right_stick_x;

// Triggers
double leftTrigger = gamepad1.left_trigger;    // 0.0 to 1.0
double rightTrigger = gamepad1.right_trigger;
```

### Digital Inputs (Buttons):
```java
// Buttons are TRUE or FALSE
boolean aButton = gamepad1.a;
boolean bButton = gamepad1.b;
boolean xButton = gamepad1.x;
boolean yButton = gamepad1.y;

// Bumpers
boolean leftBumper = gamepad1.left_bumper;
boolean rightBumper = gamepad1.right_bumper;

// Directional pad
boolean dpadUp = gamepad1.dpad_up;
boolean dpadDown = gamepad1.dpad_down;
```

### Two Gamepads:
```java
// FTC supports 2 drivers
gamepad1  // First controller
gamepad2  // Second controller
```

---

## Slide 10: Simple TeleOp Example

```java
@TeleOp(name="Basic Drive")
public class BasicDrive extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo claw;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        claw = hardwareMap.get(Servo.class, "claw");
    }

    @Override
    public void loop() {
        // Drive with joysticks
        leftMotor.setPower(gamepad1.left_stick_y);
        rightMotor.setPower(gamepad1.right_stick_y);

        // Control claw with buttons
        if (gamepad1.a) {
            claw.setPosition(0.0);  // Open
        } else if (gamepad1.b) {
            claw.setPosition(1.0);  // Close
        }

        // Send debug info to driver station
        telemetry.addData("Left Speed", gamepad1.left_stick_y);
        telemetry.addData("Right Speed", gamepad1.right_stick_y);
        telemetry.update();
    }
}
```

---

## Slide 11: Telemetry - Debug Information

**Telemetry** sends text to the Driver Station for debugging.

### Displaying Data:
```java
telemetry.addData("Motor Power", leftMotor.getPower());
telemetry.addData("Position", claw.getPosition());
telemetry.addData("Button A", gamepad1.a);

telemetry.update();  // Send to driver station
```

### Multiple Lines:
```java
telemetry.addData("Left Motor", leftMotor.getPower());
telemetry.addData("Right Motor", rightMotor.getPower());
telemetry.addData("Claw", claw.getPosition());
telemetry.addData("Status", "Running");
telemetry.update();
```

### Clearing Previous Data:
```java
telemetry.clear();  // Remove old data
telemetry.addData("New Data", value);
telemetry.update();
```

### In the loop() method:
```java
@Override
public void loop() {
    // Your robot code here

    // Always update telemetry at the end
    telemetry.addData("Debug Info", someValue);
    telemetry.update();
}
```

---

## Slide 12: Handling Motor Direction

Motors might spin the wrong direction. Here's how to fix it:

### Reversing a Motor:
```java
leftMotor.setDirection(DcMotor.Direction.FORWARD);   // Normal
rightMotor.setDirection(DcMotor.Direction.REVERSE);  // Backwards
```

### Why Reverse?
On a two-motor drivetrain, if you set both to forward power:
- Left motor might spin forward
- Right motor might spin backward (due to how it's mounted)

Solution:
```java
@Override
public void init() {
    leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
    rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");

    // Make both go the same direction
    leftMotor.setDirection(DcMotor.Direction.FORWARD);
    rightMotor.setDirection(DcMotor.Direction.REVERSE);
}
```

### Test It:
Set both motors to 0.5 power. They should both move forward. If not, adjust directions.

---

## Slide 13: Mechanical Components in Code

### Summary of Common Hardware:

| Component | Class | Controls | Example |
|-----------|-------|----------|---------|
| Motor | `DcMotor` | Speed/direction | Drivetrain, intake, elevator |
| Servo | `Servo` | Angle (0-180°) | Claw, arm pivot, gate |
| Sensor | `DistanceSensor`, `ColorSensor` | Read values | Object detection |
| Encoder | Part of `DcMotor` | Position/ticks | Autonomous movement |
| IMU | `IMUEx` | Heading/rotation | Turning in autonomous |

### Hardware Map Pattern:
```java
// Motor with encoder
DcMotor motor = hardwareMap.get(DcMotor.class, "motor");

// Standard servo
Servo servo = hardwareMap.get(Servo.class, "servo");

// Distance sensor
DistanceSensor distance = hardwareMap.get(DistanceSensor.class, "distance");

// IMU (gyroscope)
IMUEx imu = hardwareMap.get(IMUEx.class, "imu");
```

---

## Slide 14: Common OpMode Patterns

### Pattern 1: Tank Drive
```java
// Two joysticks, one for each side
leftMotor.setPower(gamepad1.left_stick_y);
rightMotor.setPower(gamepad1.right_stick_y);
```

### Pattern 2: Arcade Drive
```java
// One joystick for forward/back, one for turning
double forward = gamepad1.left_stick_y;
double turn = gamepad1.right_stick_x;

leftMotor.setPower(forward + turn);
rightMotor.setPower(forward - turn);
```

### Pattern 3: Toggle Mechanism
```java
// Press button to toggle between two states
boolean lastA = false;
boolean clawOpen = false;

if (gamepad1.a && !lastA) {
    clawOpen = !clawOpen;  // Toggle
}
lastA = gamepad1.a;

if (clawOpen) {
    claw.setPosition(0.0);
} else {
    claw.setPosition(1.0);
}
```

### Pattern 4: Analog Control
```java
// Use trigger to control servo smoothly
double triggerValue = gamepad1.right_trigger;  // 0 to 1
claw.setPosition(triggerValue);  // Servo position 0 to 1
```

---

## Slide 15: First Autonomous OpMode

```java
@Autonomous(name="Simple Auto")
public class SimpleAuto extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
    }

    @Override
    public void loop() {
        // Drive forward for 2 seconds
        leftMotor.setPower(0.5);
        rightMotor.setPower(0.5);

        if (getRuntime() > 2.0) {
            leftMotor.setPower(0.0);
            rightMotor.setPower(0.0);
            requestOpModeStop();  // Stop the program
        }

        telemetry.addData("Time", getRuntime());
        telemetry.update();
    }
}
```

---

## Slide 16: Useful FTC Methods

| Method | Purpose | Example |
|--------|---------|---------|
| `sleep(ms)` | Pause for milliseconds | `sleep(1000)` - wait 1 sec |
| `getRuntime()` | Seconds since OpMode started | `if (getRuntime() > 5)` |
| `opModeIsActive()` | Is the program still running | `while (opModeIsActive())` |
| `requestOpModeStop()` | Stop the program | In autonomous when done |
| `telemetry.update()` | Send data to Driver Station | Every loop cycle |

### Note on sleep():
- **Don't use it in loop()** - blocks everything
- Use `getRuntime()` instead (non-blocking)

### opModeIsActive() - Best Practice:
```java
@Override
public void loop() {
    if (!opModeIsActive()) return;  // Stop if program is stopping

    // Rest of your code
    motor.setPower(0.5);
    telemetry.update();
}
```

---

## Slide 17: Debugging Tips

### 1. Use Telemetry for Everything
```java
telemetry.addData("Motor Power", motor.getPower());
telemetry.addData("Servo Position", servo.getPosition());
telemetry.addData("Gamepad A", gamepad1.a);
telemetry.update();
```

### 2. Check Hardware Map Names
```
Configuration file says: "leftMotor"
Code must say: "leftMotor"

If mismatched → Runtime error!
```

### 3. Motor Direction Check
```
Set power to 0.5
→ Motor should move forward
→ If backwards, add .setDirection(REVERSE)
```

### 4. Servo Range Check
```
Set to 0.0 → Should be at one extreme
Set to 1.0 → Should be at other extreme
```

### 5. Gamepad Dead Zone
```java
// Joysticks have a dead zone (small movements are 0)
// This prevents drift

double y = gamepad1.left_stick_y;
if (Math.abs(y) < 0.1) {
    y = 0;  // Ignore small movements
}
```

---

## Slide 18: Practice Exercises

### Exercise 1: Basic Motor Control
Create a TeleOp that:
- Controls left motor with left joystick Y
- Controls right motor with right joystick Y
- Prints motor powers to telemetry

### Exercise 2: Servo Control
Add a servo to your robot config, then:
- Create OpMode that opens servo on 'A' button
- Closes servo on 'B' button
- Shows servo position on telemetry

### Exercise 3: Mixed Control
Create a TeleOp with:
- Motor control via left joystick
- Servo control via triggers (left trigger = open, right trigger = close)
- Telemetry showing everything

### Exercise 4: Simple Autonomous
Create an Auto OpMode that:
- Runs both motors at 0.5 power
- Stops after 3 seconds
- Prints elapsed time to telemetry

### Exercise 5: Gamepad State Tracking
Implement a toggle mechanism:
- Press 'A' to toggle servo position
- Press 'X' to toggle motor on/off
- Shows current state on telemetry

---

## Week 3-4 Summary

You've learned how to use the **FTC SDK**:

✅ **OpMode Structure** - How programs run on robots
✅ **Hardware Mapping** - Connecting code to robot parts
✅ **Motor Control** - Setting power levels
✅ **Servo Control** - Moving to specific angles
✅ **Gamepad Input** - Reading driver commands
✅ **Telemetry** - Debugging and showing information
✅ **Writing Real OpModes** - Your first TeleOp and Autonomous

### Next Steps:
- Write a complete TeleOp for your robot
- Test each component individually
- Combine components into full control system
- Start working on autonomous routines

---

## Common Issues & Solutions

| Problem | Solution |
|---------|----------|
| OpMode not showing on DS | Add `@TeleOp()` or `@Autonomous()` |
| "Hardware not found" error | Check config name matches code |
| Motor spins wrong way | Use `.setDirection(REVERSE)` |
| Joystick jittery | Add dead zone check |
| Servo jerky | Add small delays between moves |
| Code compiles but doesn't run | Check for exceptions in Logcat |

---

## Resources

- **FTC SDK GitHub**: github.com/FIRST-Tech-Challenge/FtcRobotController
- **Official FTC Docs**: ftc-docs.readthedocs.io
- **Game Manual 0**: gm0.org (comprehensive reference)
- **Learn Java for FTC**: GitHub alan412/LearnJavaForFTC (more details)

**Next Session**: We'll dive into advanced topics like PID controllers, state machines, and autonomous path planning!
