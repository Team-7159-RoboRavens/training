# FTC SDK - Android Robot Control

Write code to control your FTC robot's motors, servos, sensors, and more. All exercises are tested patterns from championship-winning teams.

---

## 🤖 Prerequisites

Before starting this section, make sure you:
- ✅ Completed **Java Foundations** module
- ✅ Have **Android Studio** installed (version 2021.2 or newer)
- ✅ Have a **REV Control Hub** OR **Android phone** with ADB enabled
- ✅ Installed FTC Driver Station app

---

## 🚀 Initial Setup

### 1. Clone with Submodule
```bash
git clone <repo-url>
cd ftc-training/ftc-sdk
git submodule init
git submodule update
```

### 2. Open in Android Studio
- Launch Android Studio
- File → Open → select `ftc-training/ftc-sdk/` folder
- Wait for Gradle sync to complete
- Build → Make Project (should complete with no errors)

### 3. Connect Your Robot
**For REV Control Hub:**
```bash
# Connect USB, then:
adb devices  # should show your device
```

**For Android Phone:**
- Enable USB debugging in Settings
- Connect via USB
- Trust computer when prompted

### 4. Deploy Your First OpMode
- Select `TeamCode` from module dropdown
- Run → Run on device (or `Shift+F10`)
- On driver station: find your OpMode, press INIT then PLAY

---

## 📂 Folder Structure

```
ftc-sdk/
├── FtcRobotController/          ← Official SDK (git submodule)
│   └── (read-only reference)
│
└── TeamCode/
    ├── src/main/java/org/firstinspires/ftc/teamcode/
    │   ├── examples/            ← Complete, working OpModes
    │   │   ├── Ch01_HelloWorldOpMode.java
    │   │   ├── Ch03_GamepadBasicMath.java
    │   │   ├── Ch06_FirstHardwareOpMode.java
    │   │   ├── Ch07_MotorControlOpMode.java
    │   │   ├── Ch08_ServoControlOpMode.java
    │   │   └── Ch12_StateMachineAuto.java
    │   │
    │   └── exercises/           ← Your work goes here
    │       ├── Exercise01_Telemetry.java
    │       ├── Exercise02_GamepadDrive.java
    │       ├── Exercise03_ServoToggle.java
    │       ├── Exercise04_TeleOpFull.java
    │       └── Exercise05_SimpleAuto.java
    │
    └── build.gradle
```

---

## 📖 Learning Path (Weeks 3-4)

### Understanding OpModes First

**What is an OpMode?**
- Android app that runs on the Control Hub
- Has lifecycle: init() → loop() (or runOpMode() for Autonomous)
- Gets sensor data, outputs motor commands
- Used for both TeleOp and Autonomous

### Week 3: TeleOp (Driver Control)

**Day 1-2: Basics**
1. Read `examples/Ch01_HelloWorldOpMode.java` - Understanding lifecycle
2. Deploy it and watch telemetry
3. Complete `exercises/Exercise01_Telemetry.java`

**Day 3-4: Input & Output**
1. Read `examples/Ch03_GamepadBasicMath.java` - Gamepad input
2. Read `examples/Ch06_FirstHardwareOpMode.java` - Hardware mapping
3. Complete `exercises/Exercise02_GamepadDrive.java`

**Day 5: Advanced Control**
1. Read `examples/Ch07_MotorControlOpMode.java` - Motor details
2. Read `examples/Ch08_ServoControlOpMode.java` - Servo control
3. Complete `exercises/Exercise03_ServoToggle.java`
4. Complete `exercises/Exercise04_TeleOpFull.java` - Put it together

### Week 4: Autonomous

**Day 1-3: State Machines**
1. Read `examples/Ch12_StateMachineAuto.java` - State machine pattern
2. Understand why state machines are essential for auto
3. Complete `exercises/Exercise05_SimpleAuto.java`

**Day 4-5: Your Own Auto**
1. Modify Exercise05 to add more states
2. Test timing for your robot
3. Deploy and tune

---

## 🔧 Hardware Configuration

Before your OpModes work, you need to configure hardware in the Control Hub:

1. Open FTC Driver Station app on Control Hub/phone
2. Menu → Configure → **Robot Controller**
3. Add your hardware:
   - Motors: name them exactly as in your code
   - Servos: same naming
   - Sensors: I2C, analog, digital as needed

**Important:** Your OpMode hardware names MUST match config exactly!

```java
// If you name it "leftMotor" in config, use:
leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");

// If you name it "clawServo", use:
servo = hardwareMap.get(Servo.class, "clawServo");
```

---

## 💾 Running/Deploying

### Compile Check (No Robot Needed)
```bash
./gradlew build
# If no errors, it will run on a robot
```

### Deploy to Robot
**Option 1: Android Studio (Recommended)**
- Click green Run button
- Select your device
- OpMode appears on Driver Station

**Option 2: Command Line**
```bash
./gradlew build
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

### Troubleshooting Deployment

| Problem | Solution |
|---------|----------|
| Device not found | Check `adb devices`, restart Android Studio |
| Build fails | Delete `.gradle/` folder, rebuild |
| OpMode not visible | Check `@TeleOp` or `@Autonomous` annotation |
| Hardware error | Double-check hardware names match config |

---

## 📊 Telemetry (Debugging Output)

Display info on the Driver Station screen:

```java
telemetry.addData("Motor Power", motorPower);
telemetry.addData("Servo Position", 0.5);
telemetry.addData("Status", "Running");
telemetry.update();  // REQUIRED! Don't forget this
```

**Common telemetry patterns:**
```java
// Label and value
telemetry.addData("Left Motor", leftMotor.getPower());

// Multiple lines
telemetry.addLine("--- Motor Status ---");
telemetry.addData("Left", leftPower);
telemetry.addData("Right", rightPower);

// Clear and start fresh
telemetry.clear();
telemetry.addData("New", "data");
```

---

## 🎮 GamePad Reference

### Joysticks
```java
gamepad1.left_stick_y      // -1.0 to 1.0 (forward/back)
gamepad1.left_stick_x      // -1.0 to 1.0 (left/right)
gamepad1.right_stick_y     // -1.0 to 1.0
gamepad1.right_stick_x     // -1.0 to 1.0
```

### Triggers
```java
gamepad1.left_trigger      // 0.0 to 1.0
gamepad1.right_trigger     // 0.0 to 1.0
```

### Buttons (boolean - true/false)
```java
gamepad1.a              // (Y on PlayStation)
gamepad1.b              // (O on PlayStation)
gamepad1.x              // (□ on PlayStation)
gamepad1.y              // (△ on PlayStation)

gamepad1.dpad_up        // D-pad
gamepad1.dpad_down
gamepad1.dpad_left
gamepad1.dpad_right

gamepad1.left_bumper    // LB/RB
gamepad1.right_bumper
```

### Gamepad 2
Everything above works with `gamepad2.` too for second controller.

---

## 🔌 Hardware Interface Patterns

### Motors
```java
// Get motor
DcMotor motor = hardwareMap.get(DcMotor.class, "motorName");

// Set power (-1.0 to 1.0, where 0 = stop)
motor.setPower(0.5);     // half power forward
motor.setPower(-0.5);    // half power backward
motor.setPower(0);       // stop

// Read info
double power = motor.getPower();
int position = motor.getCurrentPosition();
boolean busy = motor.isBusy();

// Run modes (for autonomous)
motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
motor.setTargetPosition(1000);
```

### Servos
```java
// Get servo
Servo servo = hardwareMap.get(Servo.class, "servoName");

// Set position (0.0 = min, 1.0 = max)
servo.setPosition(0.0);    // fully closed
servo.setPosition(0.5);    // middle
servo.setPosition(1.0);    // fully open

// Read position
double position = servo.getPosition();
```

### Timing in Autonomous
```java
// Get current time since program started
double time = getRuntime();  // returns seconds

// Example: run motor for 2 seconds
motor.setPower(0.5);
while (getRuntime() < 2.0) { }  // wait
motor.setPower(0);
```

---

## 🎯 How to Complete an Exercise

1. **Read the comments** - Each exercise has clear TODO markers
2. **Look at the example** - The corresponding example shows the pattern
3. **Write code** - Replace TODO with actual code
4. **Verify syntax** - Build → Make Project (no red underlines)
5. **Test on robot** - Deploy and try it
6. **Debug with telemetry** - Add telemetry to understand what's happening

Example:
```java
// Skeleton (before):
public class Exercise02_GamepadDrive extends OpMode {
    // TODO: Declare DcMotor leftMotor, rightMotor

    @Override
    public void init() {
        // TODO: Map motors from hardwareMap
    }
}

// Completed (after):
public class Exercise02_GamepadDrive extends OpMode {
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
    }
}
```

---

## ⏱️ Timing Pattern for Autonomous

State machines are the right way to do autonomous:

```java
enum State { INIT, DRIVE_FORWARD, TURN_LEFT, DONE }
State currentState = State.INIT;
long stateStartTime = 0;

@Override
public void loop() {
    long elapsed = System.currentTimeMillis() - stateStartTime;

    switch (currentState) {
        case INIT:
            stateStartTime = System.currentTimeMillis();
            currentState = State.DRIVE_FORWARD;
            break;

        case DRIVE_FORWARD:
            motor.setPower(0.5);
            if (elapsed > 3000) {  // 3 seconds
                currentState = State.TURN_LEFT;
                stateStartTime = System.currentTimeMillis();
            }
            break;

        case TURN_LEFT:
            // Turn logic here
            currentState = State.DONE;
            break;

        case DONE:
            motor.setPower(0);
            break;
    }
}
```

---

## 🐛 Common Errors

| Error | Cause | Fix |
|-------|-------|-----|
| `HardwareFactory.getInstance()` error | Hardware not found | Check hardware config names match |
| `ClassCastException` | Wrong hardware type | Ensure `get()` uses correct class |
| OpMode doesn't appear | Missing annotation | Add `@TeleOp` or `@Autonomous` |
| Motor doesn't move | Power is 0 | Check `setPower()` called with non-zero value |
| Servo jerks | Changing position too fast | Increment by 0.01-0.05 per loop |

---

## 📞 Debugging Strategies

1. **Add telemetry everywhere** - Display values at each step
2. **Test components separately** - Get one motor working before two
3. **Check configuration** - Hardware names must match exactly
4. **Read error stack traces** - They point to the line causing the issue
5. **Compare with examples** - Examples show working patterns

---

## ✅ Completion Checklist

- [ ] Android Studio project builds without errors
- [ ] Can deploy OpMode to robot/Control Hub
- [ ] Exercise01_Telemetry displays on driver station
- [ ] Exercise02_GamepadDrive controls motors with joystick
- [ ] Exercise03_ServoToggle moves servo with buttons
- [ ] Exercise04_TeleOpFull combines motors + servo
- [ ] Exercise05_SimpleAuto runs autonomous sequence
- [ ] You understand each line of code you wrote

---

## 🎓 Next Steps

After completing all exercises:
1. Modify exercises to match your robot's actual capabilities
2. Add sensors (distance, color, gyro)
3. Implement more complex autonomous routines
4. Study championship team strategies
5. Compete with confidence!

---

**Ready to control a robot? Let's go! 🚀**
