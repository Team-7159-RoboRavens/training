# Robot Math - Field-Oriented Drive, Odometry & Testable Autonomous

Learn the math and design patterns behind field-oriented drive, odometry pods, and
autonomous path-following - **without needing a robot**. Every exercise here has an
automated JUnit test suite: no more comparing console output to an "Expected Output"
comment by eye. Run `./gradlew test` and the computer tells you exactly what's still
wrong.

---

## 🎯 What You'll Learn

- **Mecanum kinematics** - turning stick input into 4 wheel powers
- **Field-oriented drive math** - rotating stick input by the robot's heading
- **Two-wheel odometry math** - what a GoBilda Pinpoint automates for you
- **The testable hardware-adapter pattern** - depend on an interface, not a sensor,
  so you can test sensor-consuming code with a fake
- **Testable autonomous state machines** - the same trick applied to Pedro Pathing's
  `Follower`

All of this feeds directly into the `ftc-sdk` module's `Ch13`-`Ch16` examples, which
wrap these exact classes around real FTC hardware and the real Pedro Pathing library.

---

## 🚀 Getting Started

### Way 1: GitHub Codespaces (Easiest ⭐)
Click the green "Code" button → "Codespaces" → "Create codespace on main". Java 17 and
the Gradle/Java Test extensions are pre-configured.

### Way 2: Local Development
```bash
# Install Java 17 (if not already installed)
brew install openjdk@17   # macOS with Homebrew

cd training/example_code/robot-math
./gradlew test
```

---

## 📂 Folder Structure

```
robot-math/
├── src/
│   ├── examples/     ← Complete, worked answer-key classes + runnable demos
│   │   ├── Calculator.java, Ex01_JUnitBasics.java
│   │   ├── MotorPowers.java, MecanumKinematics.java, Ex02_MecanumKinematicsDemo.java
│   │   ├── FieldOrientedTransform.java, Ex03_FieldOrientedTransformDemo.java
│   │   ├── PoseDelta.java, TwoWheelOdometry.java, Ex04_TwoWheelOdometryDemo.java
│   │   ├── HeadingSource.java, FakeHeadingSource.java, HeadingDriftDetector.java, Ex05_HeadingDriftDemo.java
│   │   ├── PathFollower.java, FakeFollower.java, AutoRoutine.java, Ex06_AutoRoutineDemo.java
│   │   └── Pose.java, PointToPointController.java, Ex07_PointToPointDemo.java   (stretch)
│   │
│   ├── exercises/    ← Your work goes here (same class names, package exercises)
│   │   ├── MecanumKinematics.java
│   │   ├── FieldOrientedTransform.java
│   │   ├── TwoWheelOdometry.java
│   │   ├── HeadingDriftDetector.java
│   │   ├── AutoRoutine.java
│   │   └── PointToPointController.java   (stretch)
│   │
│   └── test/         ← Pre-written JUnit suites - these are what you're aiming for
│       ├── JUnitBasicsTest.java
│       ├── MecanumKinematicsTest.java
│       ├── FieldOrientedTransformTest.java
│       ├── TwoWheelOdometryTest.java
│       ├── HeadingDriftDetectorTest.java
│       ├── AutoRoutineTest.java
│       └── PointToPointControllerTest.java   (stretch)
│
├── build.gradle
└── README.md          ← You are here
```

`examples/` and `exercises/` deliberately have matching class names in different Java
packages (`examples.MecanumKinematics` vs. `exercises.MecanumKinematics`) - the example
is the worked answer key, the exercise is what you edit, and every test suite targets
the `exercises` version.

---

## 📖 Recommended Learning Path (Weeks 5-6)

### Day 1: JUnit Basics
1. Read `src/examples/Ex01_JUnitBasics.java`, run its `main()`
2. Read `src/test/JUnitBasicsTest.java` - same checks, automated
3. Run `./gradlew test` and confirm it passes (nothing to build yet)

### Day 2-3: Mecanum Kinematics + Field-Oriented Drive
1. Read `Ex02_MecanumKinematicsDemo.java`, then complete `exercises/MecanumKinematics.java`
2. Run `./gradlew test` until `MecanumKinematicsTest` is green
3. Read `Ex03_FieldOrientedTransformDemo.java`, then complete `exercises/FieldOrientedTransform.java`
4. Run `./gradlew test` until `FieldOrientedTransformTest` is green

### Day 4-5: Odometry Pods
1. Read `Ex04_TwoWheelOdometryDemo.java` carefully - the offset-correction demo is the
   whole point of this module
2. Complete `exercises/TwoWheelOdometry.java`
3. Run `./gradlew test` until `TwoWheelOdometryTest` is green (6 cases, including a
   "drive a square, return to start" integration test)

### Week 6: Testable Patterns for Hardware & Autonomous
1. Read `Ex05_HeadingDriftDemo.java`, complete `exercises/HeadingDriftDetector.java`
2. Read `Ex06_AutoRoutineDemo.java`, complete `exercises/AutoRoutine.java`
3. (Stretch) Read `Ex07_PointToPointDemo.java`, complete `exercises/PointToPointController.java`
4. Move on to the `ftc-sdk` module's `Ch13`-`Ch16` to wrap these classes around real hardware

---

## ▶️ Running Code

### Run a demo
```bash
./gradlew build
java -cp build/classes/java/main examples.Ex02_MecanumKinematicsDemo
```

### Run the tests (this is your main feedback loop)
```bash
./gradlew test
```
Open `build/reports/tests/test/index.html` in a browser for a readable pass/fail report.

---

## 💡 How to Complete an Exercise

Each exercise file has TODO comments broken into PARTS, matching the OBJECTIVE and
INSTRUCTIONS in its class Javadoc:
```java
// TODO PART A: compute raw lf, rf, lb, rb from forward/strafe/rotate
```

Steps:
1. Read the OBJECTIVE and INSTRUCTIONS at the top of the exercise file
2. Read the matching example/demo file if you want to see it working first
3. Follow each TODO, replacing the placeholder `return` statement at the end
4. Run `./gradlew test` - a red test tells you exactly which case is still wrong
5. Repeat until green, then move to the next exercise

---

## 🆘 Debugging Tips

| Problem | Likely cause |
|---|---|
| `cannot find symbol: examples.X` | Check the import - shared DTOs (`MotorPowers`, `Pose`, `PoseDelta`) live in `examples`, even when used from `exercises` |
| Test fails with a small numeric mismatch | Check you're using the exact formula from the INSTRUCTIONS - order of operations matters |
| Test fails on the wraparound/rotation cases specifically | You likely forgot the offset-correction or angle-normalization step - re-read the class Javadoc |
| `./gradlew test` shows "0 tests" | You're probably still returning the placeholder value - the code compiles but a TODO section is empty |

---

## ✅ Completion Checklist

- [ ] `MecanumKinematicsTest` green
- [ ] `FieldOrientedTransformTest` green
- [ ] `TwoWheelOdometryTest` green (all 6 cases, including the driving-a-square test)
- [ ] `HeadingDriftDetectorTest` green
- [ ] `AutoRoutineTest` green
- [ ] (Stretch) `PointToPointControllerTest` green
- [ ] You can explain, out loud, why pod offset matters for odometry accuracy
- [ ] You can explain why `HeadingSource`/`PathFollower` being interfaces (not concrete
      hardware classes) is what makes this testable at all

---

## 🎓 What's Next?

Move to the `ftc-sdk` module's `Ch13_FieldOrientedDriveOpMode.java` through
`Ch16_TwoWheelOdometryCrossCheck.java` - the same classes you just tested, now wrapped
around a real IMU, GoBilda Pinpoint, and Pedro Pathing `Follower`.

---

**These tests are your robot when the robot isn't available. Good luck! 🚀**
