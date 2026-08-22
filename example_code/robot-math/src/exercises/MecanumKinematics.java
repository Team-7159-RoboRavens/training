package exercises;

import examples.MotorPowers;

/**
 * Exercise 1: Mecanum Kinematics
 *
 * OBJECTIVE: Implement the mecanum drive equations that turn forward/strafe/rotate
 * stick input into 4 wheel powers, with normalization so no wheel ever exceeds 1.0.
 *
 * INSTRUCTIONS:
 *   PART A: Compute the raw (unclamped) power for each wheel:
 *     lf = forward + strafe + rotate
 *     rf = forward - strafe - rotate
 *     lb = forward - strafe + rotate
 *     rb = forward + strafe - rotate
 *   PART B: Find the largest absolute wheel power, but never less than 1.0
 *     (use Math.max(1.0, ...) so small inputs are never artificially boosted).
 *   PART C: Divide all four wheel powers by that value and return them.
 *
 * EXPECTED OUTPUT (verified by MecanumKinematicsTest.java, run `./gradlew test`):
 *   robotCentric(1, 0, 0)   -> lf=1.0,  rf=1.0,  lb=1.0,  rb=1.0
 *   robotCentric(0, 1, 0)   -> lf=1.0,  rf=-1.0, lb=-1.0, rb=1.0
 *   robotCentric(0, 0, 1)   -> lf=1.0,  rf=-1.0, lb=1.0,  rb=-1.0
 *   robotCentric(1, 1, 0)   -> lf=1.0,  rf=0.0,  lb=0.0,  rb=1.0   (clamped)
 */
public final class MecanumKinematics {
    private MecanumKinematics() {}

    public static MotorPowers robotCentric(double forward, double strafe, double rotate) {
        // TODO PART A: compute raw lf, rf, lb, rb from forward/strafe/rotate

        // TODO PART B: compute max = Math.max(1.0, largest absolute raw wheel power)

        // TODO PART C: return new MotorPowers(lf / max, rf / max, lb / max, rb / max)

        return new MotorPowers(0, 0, 0, 0); // placeholder - replace this return
    }
}
