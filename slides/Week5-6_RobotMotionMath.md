# Week 5-6: Robot Motion Math

## What You'll Learn This Week
- Mecanum Drive Kinematics (stick input → 4 wheel powers)
- Wheel Power Normalization (clamping without boosting)
- Analog Gamepad Input (sticks vs. the dpad/bumpers you already know)
- JUnit Testing (checking robot math without a robot)
- Field-Oriented Drive (rotating stick input by heading)
- Two-Wheel Odometry Math (dead wheels, pod offset correction)

---

## Slide 1: Recap - Digital vs. Analog Driving

In Week 3-4 your TeleOp drove with **digital** inputs: `gamepad1.dpad_up`, `gamepad1.a`, `gamepad1.left_bumper`. Each of those is either `true` or `false` — a button is pressed or it isn't. That was enough to nudge a servo by a fixed step or run a motor at one fixed power, but it can't give you smooth, proportional control.

This week you move to **analog** input: `gamepad1.left_stick_x`, `gamepad1.left_stick_y`, `gamepad1.right_stick_x`. Each of these is a `double` ranging continuously from `-1.0` to `1.0`. That extra precision is powerful, but it also means the math connecting "how far the driver pushed the stick" to "how fast each of the 4 wheels should spin" is no longer a one-line `if`. It's a small system of equations, and — like any math you can't watch happen on a physical robot in front of you — it deserves to be tested before you trust it.

```java
// Week 3-4 style: digital, one fixed speed
if (gamepad1.dpad_up) {
    motor.setPower(0.5);
}

// Week 5-6 style: analog, continuously variable
double forward = -gamepad1.left_stick_y;   // full range, not just pressed/not-pressed
motor.setPower(forward);
```

### Key Points:
- **Digital** input (dpad, buttons, bumpers) = `true`/`false`, good for on/off actions
- **Analog** input (sticks, triggers) = a `double` in a continuous range, good for proportional control
- A **mecanum** drivetrain (4 wheels, each with rollers at 45°) can drive forward, strafe sideways, and rotate independently — but only if the code combines all three stick axes correctly for each wheel
- This is exactly the kind of math you want to verify with a unit test *before* you ever spin a real motor

---

## Slide 2: The Mecanum Wheel Equations

**Mecanum kinematics** is the formula that turns three driver inputs — forward, strafe, and rotate — into four individual wheel powers. The module you're using this week (`training/example_code/robot-math`) implements this in `examples/MecanumKinematics.java`:

```java
public static MotorPowers robotCentric(double forward, double strafe, double rotate) {
    double lf = forward + strafe + rotate;
    double rf = forward - strafe - rotate;
    double lb = forward - strafe + rotate;
    double rb = forward + strafe - rotate;
    // ... normalization happens next (Slide 3)
}
```

### Sign Convention:
- `forward`: **+1** = full forward
- `strafe`: **+1** = full **right**
- `rotate`: **+1** = full **clockwise**, viewed from above

| Input | lf | rf | lb | rb |
|---|---|---|---|---|
| Forward `(1, 0, 0)` | 1.0 | 1.0 | 1.0 | 1.0 |
| Strafe right `(0, 1, 0)` | 1.0 | -1.0 | -1.0 | 1.0 |
| Rotate clockwise `(0, 0, 1)` | 1.0 | -1.0 | 1.0 | -1.0 |

### Key Points:
- This is a **documented convention**, not a law of physics — the `examples/MecanumKinematics.java` Javadoc literally says "bench-verify on a real robot before trusting it." If your wheels are wired or mounted differently, forward/strafe/rotate might need flipped signs to match reality.
- Each wheel's formula is just `forward ± strafe ± rotate` — the pattern of `+`/`-` signs is what makes the four wheels cooperate instead of fight each other
- `lf`/`rf`/`lb`/`rb` (left-front, right-front, left-back, right-back) are the four fields of the `MotorPowers` class you'll return
- Try tracing the rotate row by hand: if only `rotate=1`, the left wheels spin forward (`+1`) and the right wheels spin backward (`-1`) — that's exactly what makes a robot spin in place

---

## Slide 3: Normalizing Wheel Powers

Adding three numbers together can easily produce a wheel power outside the valid range. `MecanumKinematics.robotCentric(1, 1, 0)` gives raw values of `lf=2, rf=0, lb=0, rb=2` — but `setPower()` only accepts `-1.0` to `1.0`. That's what **normalization** fixes.

```java
double max = Math.max(1.0, Math.max(Math.max(Math.abs(lf), Math.abs(rf)),
                                     Math.max(Math.abs(lb), Math.abs(rb))));

return new MotorPowers(lf / max, rf / max, lb / max, rb / max);
```

### Why `Math.max(1.0, ...)` and not just the largest wheel value?

If the largest raw wheel power is already ≤ 1.0 — say the driver only pushed the stick halfway — dividing by anything less than 1.0 would *boost* that small input back up to full power, which is not what the driver asked for. Wrapping the largest-magnitude value in `Math.max(1.0, ...)` guarantees the divisor is **never less than 1.0**, so:
- If every wheel is already within range, dividing by `1.0` changes nothing
- If any wheel exceeds `1.0`, dividing by that wheel's magnitude scales *all four* wheels down proportionally, preserving the shape of the turn while keeping every wheel legal

### Key Points:
- Normalize using the **largest magnitude among all four wheels**, not each wheel independently — otherwise you'd distort the intended direction of travel
- `Math.max(1.0, largestMagnitude)` is the trick: it only ever scales *down*, never up
- `MecanumKinematics.robotCentric(1, 1, 0)` → `lf=1.0, rf=0.0, lb=0.0, rb=1.0` after this correction — that's the exact "combined case needing clamping" you'll test in Slide 6
- This is exactly the boundary case worth double-checking with a test: what happens right at `1.0` versus just over it?

---

## Slide 4: Reading Analog Sticks

Recall from Week 3-4's Gamepad Reference table (`Quick_Reference_Guide.md`) that `left_stick_x`, `left_stick_y`, and `right_stick_x`/`right_stick_y` are all analog, ranging `-1.0` to `1.0`. The mecanum equations from Slide 2 expect exactly three numbers — `forward`, `strafe`, `rotate` — so the job of your TeleOp `loop()` is to map gamepad axes onto those three parameters.

```java
@Override
public void loop() {
    double forward = -gamepad1.left_stick_y;   // see note below on the minus sign
    double strafe  = gamepad1.left_stick_x;
    double rotate  = gamepad1.right_stick_x;

    MotorPowers powers = MecanumKinematics.robotCentric(forward, strafe, rotate);

    frontLeft.setPower(powers.lf);
    frontRight.setPower(powers.rf);
    backLeft.setPower(powers.lb);
    backRight.setPower(powers.rb);
}
```

### Why the minus sign on `left_stick_y`?

FTC gamepads report a **negative** `y` value when the stick is pushed forward (up) and a positive value when pulled back — the opposite of what you'd intuitively expect. You already saw this in Week 3-4's DC Motor Test slide, which noted the same fix (`bench.setMotorSpeed(-gamepad1.left_stick_y)`). Negating it once here means `forward` in your code matches the `+1 = full forward` convention from Slide 2.

### Key Points:
- Three stick axes map to three kinematics parameters: `left_stick_y` → `forward` (negated), `left_stick_x` → `strafe`, `right_stick_x` → `rotate`
- See `Quick_Reference_Guide.md`'s "Gamepad Input" section for the complete list of analog and digital gamepad fields
- Getting the sign wrong on any one axis doesn't crash your code — it just makes the robot drive backward, strafe the wrong way, or spin opposite of what the driver expects. This is exactly the kind of subtle bug that's hard to catch by eye and easy to catch with a test (next slide)

---

## Slide 5: Your First Unit Test - Why and How

So far, every exercise in this curriculum has been checked by eye: run the code, read the console output, compare it to an "EXPECTED OUTPUT" comment. That works for small examples, but robot math has a lot of moving parts (pun intended) and it's easy to fool yourself. This week introduces **JUnit**, a testing framework that lets the computer do the comparing for you.

`examples/Ex01_JUnitBasics.java` shows the old, manual way:
```java
Calculator calc = new Calculator();
double sum = calc.add(2, 3);
System.out.println("2 + 3 = " + sum + " (expected 5.0)");
```

`test/JUnitBasicsTest.java` shows the exact same check, automated:
```java
import static org.junit.Assert.assertEquals;

public class JUnitBasicsTest {
    private static final double DELTA = 1e-9;

    @Test
    public void addsTwoPositiveNumbers() {
        Calculator calc = new Calculator();
        assertEquals(5.0, calc.add(2, 3), DELTA);
    }
}
```

### Key Concepts:
- **`@Test`** marks a method as a test case that JUnit should run automatically
- **`assertEquals(expected, actual, delta)`** — for `double` values, you must supply a small tolerance (`DELTA`), because floating-point math is rarely bit-for-bit exact. `1e-9` here means "close enough to be considered equal"
- Run `./gradlew test` from `training/example_code/robot-math` — JUnit runs every `@Test` method and reports pass/fail directly, no eyeballing required
- **This matters most when the robot isn't available.** You can't always bench-test a drivetrain — the robot might be mid-build, checked out by another subteam, or it's 11pm the night before an event. A test suite lets you verify your math is correct on a laptop, right now, with zero hardware

### Key Points:
- `Calculator.add()`/`.subtract()` are deliberately trivial — the point of this slide isn't the math, it's the *pattern* you'll reuse on real robot math starting next slide
- A red (failing) test tells you exactly which case broke — far more specific than "the robot drove weird at practice"

---

## Slide 6: Testing Your Mecanum Math

`test/MecanumKinematicsTest.java` applies the exact same pattern to the mecanum equations from Slides 2-3. Here are three representative cases:

```java
@Test
public void pureForward() {
    MotorPowers mp = MecanumKinematics.robotCentric(1, 0, 0);
    assertEquals(1.0, mp.lf, DELTA);
    assertEquals(1.0, mp.rf, DELTA);
    assertEquals(1.0, mp.lb, DELTA);
    assertEquals(1.0, mp.rb, DELTA);
}

@Test
public void pureStrafeRight() {
    MotorPowers mp = MecanumKinematics.robotCentric(0, 1, 0);
    assertEquals(1.0, mp.lf, DELTA);
    assertEquals(-1.0, mp.rf, DELTA);
    assertEquals(-1.0, mp.lb, DELTA);
    assertEquals(1.0, mp.rb, DELTA);
}

@Test
public void combinedRequiringClamp() {
    MotorPowers mp = MecanumKinematics.robotCentric(1, 1, 0);
    assertEquals(1.0, mp.lf, DELTA);
    assertEquals(0.0, mp.rf, DELTA);
    assertEquals(0.0, mp.lb, DELTA);
    assertEquals(1.0, mp.rb, DELTA);
}
```

### Key Points:
- **`pureForward`** and **`pureStrafeRight`** each isolate a single input axis — if one of these fails, you know exactly which term of the equation (Slide 2) has a sign or arithmetic error
- **`combinedRequiringClamp`** is the case from Slide 3 where `forward + strafe` overflows `1.0` on two of the four wheels — this test only passes if your normalization step is implemented correctly, not just the raw equations
- The full suite in `MecanumKinematicsTest.java` has 12 cases in total — pure forward/backward, pure strafe left/right, pure rotate both directions, combined cases with and without clamping, and a boundary check that `1.0` doesn't get needlessly shrunk
- This suite targets `exercises.MecanumKinematics` (not the answer key in `examples`) — it's currently failing on the unmodified skeleton, and will turn green as you fill in the `TODO PART A/B/C` sections (Slide 15)

---

## Slide 7: What Is Field-Oriented Drive?

Everything so far has been **robot-centric**: "forward" means "whichever way the chassis is currently pointed." That's fine until the robot spins during a match — after a 180° turn, pushing the stick "forward" now drives the robot backward relative to the field, which is disorienting for the driver.

**Field-oriented drive** fixes this by re-interpreting the stick before it ever reaches the mecanum equations. The mental model:

> The stick always means forward on the **FIELD**, not forward on the **ROBOT**.

| | Robot-Centric | Field-Oriented |
|---|---|---|
| "Push stick forward" means | Forward relative to the chassis | Forward relative to the field, always |
| Driver must account for robot's rotation? | Yes — confusing after turns | No — stick always points the same field direction |
| Extra input needed | None | The robot's current **heading** |

### Key Points:
- Field-oriented drive doesn't change the mecanum equations from Slide 2 — it adds a **pre-processing step** that rotates the driver's stick vector before handing it to `MecanumKinematics.robotCentric(...)`
- The pre-processing step needs one more piece of information: the robot's current heading (rotation angle), which Slide 9 explains
- This is exactly the kind of feature that's hard to trust without a test — you can't easily verify "does this feel right while spinning" by eye, but you absolutely can verify "does the math produce the right numbers at heading 0°, 90°, 180°" (Slide 10)

---

## Slide 8: The Rotation Math

The transform lives in `examples/FieldOrientedTransform.java`. It rotates a field-relative stick vector by the *negative* of the robot's heading, producing the equivalent robot-relative vector:

```java
public static FieldVector toRobotRelative(double fieldForward, double fieldStrafe, double headingRadians) {
    double cos = Math.cos(headingRadians);
    double sin = Math.sin(headingRadians);

    double robotForward = fieldForward * cos + fieldStrafe * sin;
    double robotStrafe = -fieldForward * sin + fieldStrafe * cos;

    return new FieldVector(robotForward, robotStrafe);
}
```

### Why "rotate by -heading"?

If the robot has physically rotated by `+heading` (counter-clockwise) relative to the field, then a fixed field-direction vector appears to have rotated by `-heading` *relative to the robot*. Rotating the field-frame vector by `-heading` is what maps it correctly into the robot's current frame — that's precisely what the `cos`/`sin` combination above computes (this is the standard 2D rotation matrix, applied with a negated angle).

### Heading Convention:
- **Heading increases counter-clockwise**, matching the convention used by devices like the GoBilda Pinpoint (`AngleUnit.RADIANS`)
- Like the mecanum sign convention in Slide 2, this is a **documented convention that must be bench-verified**, not an absolute law — the class Javadoc says so explicitly

### Key Points:
- `fieldForward`/`fieldStrafe` are what the driver's stick means *on the field*; `robotForward`/`robotStrafe` are what you actually feed into `MecanumKinematics.robotCentric(...)`
- At `heading = 0`, `cos = 1` and `sin = 0`, so the formula reduces to `robotForward = fieldForward`, `robotStrafe = fieldStrafe` — the transform is the identity when the robot hasn't rotated at all (you'll verify this exact case in Slide 10)
- Field-oriented drive is just: **read heading → rotate the stick vector → feed the result into the mecanum equations you already tested**

---

## Slide 9: Where Does Heading Come From?

The rotation math on Slide 8 needs a `headingRadians` value from *somewhere*. In a real robot, that comes from a sensor that tracks orientation over time — most commonly the Control Hub's built-in **IMU** (which you already used in Week 3-4's IMU Test, via `bench.getHeading()`) or a dedicated device like the **GoBilda Pinpoint**, which fuses odometry and IMU data into a more stable heading estimate.

That sensor integration is **next module's material** — Week 7-8 covers the GoBilda Pinpoint and Pedro Pathing in depth. For this module, `toRobotRelative(fieldForward, fieldStrafe, headingRadians)` deliberately treats heading as **just a `double` parameter you pass in**, not something it reads from hardware itself.

### Why keep heading as a plain parameter for now?

- It lets you **test the rotation math in complete isolation**, with zero dependency on an IMU, a Pinpoint, or any physical robot — exactly the same "test without deploying" idea from Slide 5
- It's a preview of a pattern you'll formalize soon: depending on an **interface** (something that can supply a heading) rather than a **concrete sensor class**, so the same math can be exercised with a real IMU on the robot or a fake value in a test
- Right now, you supply the heading directly in your test cases (`Math.PI / 2`, `Math.PI`, etc.) — Slide 10 walks through exactly those cases

### Key Points:
- Heading source (IMU vs. Pinpoint vs. a test's hardcoded value) is a separate concern from the rotation math itself — that separation is *why* this math is testable at all
- Keep this question in the back of your mind for Week 7-8: "how do I get a trustworthy, drift-corrected heading onto the robot?"

---

## Slide 10: Testing the Field-Oriented Transform

`test/FieldOrientedTransformTest.java` checks the rotation formula at several headings. Four cases worth walking through:

```java
@Test
public void headingZeroIsIdentity() {
    FieldVector v1 = FieldOrientedTransform.toRobotRelative(1, 0, 0);
    assertEquals(1.0, v1.forward, DELTA);
    assertEquals(0.0, v1.strafe, DELTA);
}

@Test
public void heading90Degrees() {
    FieldVector v = FieldOrientedTransform.toRobotRelative(1, 0, Math.PI / 2);
    assertEquals(0.0, v.forward, DELTA);
    assertEquals(-1.0, v.strafe, DELTA);
}

@Test
public void heading180Degrees() {
    FieldVector v = FieldOrientedTransform.toRobotRelative(1, 0, Math.PI);
    assertEquals(-1.0, v.forward, DELTA);
    assertEquals(0.0, v.strafe, DELTA);
}

@Test
public void roundTripRecoversOriginalVector() {
    double heading = 0.73;
    FieldVector rotated = FieldOrientedTransform.toRobotRelative(1, 0.5, heading);
    FieldVector back = FieldOrientedTransform.toRobotRelative(rotated.forward, rotated.strafe, -heading);
    assertEquals(1.0, back.forward, DELTA);
    assertEquals(0.5, back.strafe, DELTA);
}
```

### Key Points:
- **`headingZeroIsIdentity`** confirms the reduction from Slide 8: no rotation means the output equals the input exactly
- **`heading90Degrees`** and **`heading180Degrees`** are easy to sanity-check by hand (`cos`/`sin` of 90° and 180° are simple numbers like `0`, `1`, `-1`), which makes them great "does my formula even resemble the right shape" tests
- **`roundTripRecoversOriginalVector`** rotates a vector by `heading` and then by `-heading`, and expects to land back exactly where it started — a powerful test because it doesn't require you to hand-compute the expected numbers, only that "undo" truly undoes "do"
- `FieldOrientedTransformTest` also includes a case checking that `-90°` and `270°` (`3 * Math.PI / 2`) produce identical results — a reminder that angles wrap around, which matters once real sensors report headings outside a single clean range

---

## Slide 11: How Odometry Pods Work

An **odometry pod** ("dead wheel") is a small unpowered wheel with its own encoder, dragged along the floor purely to measure distance traveled — unlike your drive wheels, it never receives motor power, so it isn't affected by wheel slip the same way. This module's two-pod setup, in `examples/TwoWheelOdometry.java`, uses:

- One pod aligned to sense **forward** distance
- One pod aligned to sense **strafe** (sideways) distance

Each pod reports its movement as a raw **tick count** from its encoder. To turn ticks into a real-world distance, you multiply by a calibration constant:

```java
double rawForwardMm = forwardPodDeltaTicks * mmPerTick;
double rawStrafeMm = strafePodDeltaTicks * mmPerTick;
```

### Key Points:
- `mmPerTick` (in this module's tests, `0.5`) is a calibration constant specific to your pod's wheel diameter and encoder resolution — you'd measure or look this up for a real pod
- `PoseDelta` (`examples/PoseDelta.java`) is the tiny result type this module returns: just two `double` fields, `dx` and `dy`, representing the robot-relative pose change for one update tick
- This two-pod design is a simplified model of what a device like the **GoBilda Pinpoint** automates in hardware — Week 7-8 picks this up again with the real sensor
- Raw tick-to-millimeter conversion alone isn't quite enough for an accurate position estimate — Slide 12 explains why

---

## Slide 12: Why Pod Offset Matters

Here's the subtlety that makes odometry pods tricky: **neither pod sits exactly on the robot's center of rotation.** When the robot spins in place — with zero real translation — a pod that's offset from the center still physically sweeps through space, so its encoder reports ticks even though the robot never actually moved.

The `TwoWheelOdometry` class Javadoc describes the correction model this module uses:

> A pod offset by `offsetMm` from the center of rotation sweeps an arc of length `offsetMm * headingDeltaRadians` purely from rotation. Subtracting that arc length from the pod's raw reading leaves only the translation-caused distance.

Picture the pod tracing a small arc as the whole robot rotates around its center — that arc's length is exactly `offset × angle` (in radians), the same arc-length formula you'd use for any point at a fixed radius from a rotation center.

### Key Points:
- If you set a pod's offset to `0` when it isn't actually `0`, a pure in-place spin will look like the robot slid sideways or forward — a **spurious drift** that compounds over a match
- The correction is a subtraction: `rawDistance - offsetMm * headingDeltaRadians` — Slide 13 shows this applied to both pods together
- This is exactly the "measure something before you trust it" theme from Slide 2's sign convention — pod offset is a physical measurement (in mm) you'd take off your real robot's CAD or by careful measurement, not a number you can guess

---

## Slide 13: Computing Pose Deltas from Raw Encoder Ticks

Putting Slides 11-12 together, here is the complete `computeDelta` method from `examples/TwoWheelOdometry.java`:

```java
public static PoseDelta computeDelta(double forwardPodDeltaTicks, double strafePodDeltaTicks,
                                      double headingDeltaRadians, double mmPerTick,
                                      double forwardPodOffsetMm, double strafePodOffsetMm) {
    double rawForwardMm = forwardPodDeltaTicks * mmPerTick;
    double rawStrafeMm = strafePodDeltaTicks * mmPerTick;

    double correctedForwardMm = rawForwardMm - forwardPodOffsetMm * headingDeltaRadians;
    double correctedStrafeMm = rawStrafeMm - strafePodOffsetMm * headingDeltaRadians;

    return new PoseDelta(correctedForwardMm, correctedStrafeMm);
}
```

### Step by Step:
1. **Convert ticks to millimeters** for each pod: `ticks * mmPerTick` (Slide 11)
2. **Compute the rotation-induced arc length** for each pod: `offsetMm * headingDeltaRadians` (Slide 12)
3. **Subtract** the arc length from the raw distance — what's left is the distance caused by real translation, not rotation
4. **Package the two corrected values** into a `PoseDelta(dx, dy)` — this is the robot-relative pose change for one update tick

### Key Points:
- Both pods go through the *same* three steps independently — the forward pod uses `forwardPodOffsetMm`, the strafe pod uses `strafePodOffsetMm`
- Setting both offsets to `0` collapses this back to the simple "ticks × mmPerTick" conversion from Slide 11 — offset correction is additive, it doesn't change the basic conversion
- `exercises/TwoWheelOdometry.java` has this exact method broken into `TODO PART A/B/C`, matching these three steps — see Slide 15 for the exercise itself

---

## Slide 14: Testing Your Odometry Math

`test/TwoWheelOdometryTest.java` has 6 cases. Two are worth walking through in detail because they exercise the offset-correction logic most directly:

```java
@Test
public void pureRotationNonzeroOffsetCancelsOutAfterCorrection() {
    double forwardOffsetMm = 40;
    double strafeOffsetMm = 60;
    double headingDelta = 0.2;

    // Raw ticks a real pod WOULD report from rotation-induced arc motion alone.
    double forwardTicks = (forwardOffsetMm * headingDelta) / MM_PER_TICK; // 16 ticks
    double strafeTicks = (strafeOffsetMm * headingDelta) / MM_PER_TICK;  // 24 ticks

    PoseDelta d = TwoWheelOdometry.computeDelta(forwardTicks, strafeTicks, headingDelta,
            MM_PER_TICK, forwardOffsetMm, strafeOffsetMm);
    assertEquals(0.0, d.dx, LOOSE_DELTA);
    assertEquals(0.0, d.dy, LOOSE_DELTA);
}
```

This test *manufactures* the exact tick counts a real, offset pod would report purely from spinning (no real translation), then checks that the correction from Slide 13 cancels them back out to (0, 0) — precisely the scenario Slide 12 warned about.

The last case in the suite is a larger integration test:

```java
@Test
public void drivingASquareReturnsToStart() {
    // Drive four 100mm sides, turning 90 degrees CCW after each one,
    // accumulating field-frame position using FieldOrientedTransform.
    ...
    assertEquals(0.0, fieldX, LOOSE_DELTA);
    assertEquals(0.0, fieldY, LOOSE_DELTA);
}
```

### Why "drive a square, check you're back at (0,0)" is such a good sanity test:

- It combines **two** pieces of math you built this week — `TwoWheelOdometry.computeDelta` for each side, and `FieldOrientedTransform.toRobotRelative` to rotate each robot-relative move into the field frame before accumulating position
- It doesn't require hand-computing intermediate expected values the way `arbitraryAngle30Degrees`-style tests do — geometry guarantees that four equal 90° turns bring you home, so any nonzero final position is a real bug, not a rounding quirk
- This is the pure-software equivalent of the classic "drive a square on the practice field and see if you drift" test that FTC teams have always used to sanity-check dead-reckoning code — except this version runs in milliseconds and never needs a charged battery

### Key Points:
- `pureForwardNoOffsetsNoRotation` and `pureStrafeNoOffsetsNoRotation` isolate each pod's basic tick-to-mm conversion, with offsets at `0`
- `pureRotationZeroOffsetProducesNoTranslation` confirms that a pod sitting exactly on the rotation axis reports (correctly) zero movement during a spin
- `combinedTranslationAndRotation` is the most realistic case: real translation *and* rotation happening at the same time, both corrected together

---

## Slide 15: Practice Exercises

### Exercise 1: Mecanum Kinematics
Complete `src/exercises/MecanumKinematics.java` in the `robot-math` module:
- Fill in **PART A**: the raw `lf`/`rf`/`lb`/`rb` formulas from Slide 2
- Fill in **PART B**: the `Math.max(1.0, ...)` normalization divisor from Slide 3
- Fill in **PART C**: return the normalized `MotorPowers`
- Run `./gradlew test` and get `MecanumKinematicsTest` fully green (12 cases)

### Exercise 2: Field-Oriented Transform
Complete `src/exercises/FieldOrientedTransform.java`:
- Fill in **PART A**: compute `cos`/`sin` of `headingRadians`
- Fill in **PART B**: the `robotForward`/`robotStrafe` rotation formulas from Slide 8
- Fill in **PART C**: return the resulting `FieldVector`
- Run `./gradlew test` and get `FieldOrientedTransformTest` fully green (7 cases)

### Exercise 3: Two-Wheel Odometry
Complete `src/exercises/TwoWheelOdometry.java`:
- Fill in **PART A**: raw tick-to-millimeter conversion for each pod
- Fill in **PART B**: subtract each pod's rotation-induced arc length (`offsetMm * headingDeltaRadians`)
- Fill in **PART C**: return the corrected `PoseDelta`
- Run `./gradlew test` and get `TwoWheelOdometryTest` fully green (6 cases, including `drivingASquareReturnsToStart`)

### Your Feedback Loop
For all three exercises: `cd training/example_code/robot-math && ./gradlew test`. A red test tells you exactly which case is still wrong — no robot required. If you see "0 tests" ran, you're probably still returning the placeholder value at the bottom of the method.

### Stretch Goal: Beyond This Module
Once all three suites above are green, you've built the same core math that goes into a competition-grade holonomic drive. As an optional "going beyond" idea (not required, and not something you need any external repo to attempt): a more advanced production version of this same mecanum math — with squared/cubic input response curves for finer low-speed control, and the ability to toggle between multiple drive schemes — exists in a file called `HolonomicDrive.java` on a sibling FTC team's robot code repository, `First_Age-25-26-`. If you're curious what "going further" looks like, it's worth searching for and reading that class for inspiration on shaping stick input beyond the linear mapping you built this week — but treat it purely as inspiration, not a dependency for finishing this module.

---

## Week 5-6 Summary

You've learned the **math behind analog, field-aware robot motion** — and how to test that math without a robot:

✅ **Digital vs. Analog Input** - Why proportional stick control needs real math, not just `if` statements
✅ **Mecanum Kinematics** - The `forward ± strafe ± rotate` equations behind all 4 wheel powers
✅ **Wheel Power Normalization** - The `Math.max(1.0, ...)` trick that clamps without ever boosting
✅ **JUnit Basics** - `@Test`, `assertEquals` with a `delta`, and why testing without a robot matters
✅ **Field-Oriented Drive** - The robot-relative vs. field-relative mental model
✅ **The Rotation Transform** - Rotating a stick vector by `-heading` into the robot's frame
✅ **Two-Wheel Odometry** - Dead wheel pods, tick-to-distance conversion, and pod-offset correction
✅ **Integration Testing** - Why "drive a square, check you're back at (0,0)" is a powerful sanity check

### Ready for Next Weeks?
Once `MecanumKinematicsTest`, `FieldOrientedTransformTest`, and `TwoWheelOdometryTest` are all green, you're ready for **Week 7-8: GoBilda Pinpoint & Pedro Pathing**, where the heading and odometry values you treated as plain parameters this week get wired up to real sensors and a real path-following library.

---

## Resources

- **This Module's Code**: `training/example_code/robot-math/` — run `./gradlew test` any time as your feedback loop
- **Module README**: `training/example_code/robot-math/README.md` — full recommended learning path and debugging tips
- **Quick Reference Guide**: `training/slides/Quick_Reference_Guide.md` — see the "Gamepad Input" section for the full analog/digital field list used in Slide 4
- **Game Manual 0**: gm0.org — has excellent diagrams of mecanum wheel force vectors and odometry pod placement
- **JUnit 4 User Guide**: junit.org/junit4 — more assertion types and annotations beyond `@Test`/`assertEquals`
- **Coming Next**: Week 7-8 covers the GoBilda Pinpoint odometry computer and Pedro Pathing for autonomous path following

**Remember**: Every formula in this deck has a green test proving it works. When you write your own robot code later, ask yourself the same question this module asks: "how would I test this without a robot?"
