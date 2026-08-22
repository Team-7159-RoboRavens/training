import static org.junit.Assert.assertEquals;

import examples.MotorPowers;
import exercises.MecanumKinematics;
import org.junit.Test;

/**
 * This suite targets exercises.MecanumKinematics - it will fail until you complete
 * the TODOs in src/exercises/MecanumKinematics.java. Run `./gradlew test` to check
 * your progress as you go; no robot needed.
 */
public class MecanumKinematicsTest {
    private static final double DELTA = 1e-9;

    @Test
    public void allZero() {
        MotorPowers mp = MecanumKinematics.robotCentric(0, 0, 0);
        assertEquals(0, mp.lf, DELTA);
        assertEquals(0, mp.rf, DELTA);
        assertEquals(0, mp.lb, DELTA);
        assertEquals(0, mp.rb, DELTA);
    }

    @Test
    public void pureForward() {
        MotorPowers mp = MecanumKinematics.robotCentric(1, 0, 0);
        assertEquals(1.0, mp.lf, DELTA);
        assertEquals(1.0, mp.rf, DELTA);
        assertEquals(1.0, mp.lb, DELTA);
        assertEquals(1.0, mp.rb, DELTA);
    }

    @Test
    public void pureForwardHalfPower() {
        MotorPowers mp = MecanumKinematics.robotCentric(0.5, 0, 0);
        assertEquals(0.5, mp.lf, DELTA);
        assertEquals(0.5, mp.rf, DELTA);
        assertEquals(0.5, mp.lb, DELTA);
        assertEquals(0.5, mp.rb, DELTA);
    }

    @Test
    public void pureBackward() {
        MotorPowers mp = MecanumKinematics.robotCentric(-1, 0, 0);
        assertEquals(-1.0, mp.lf, DELTA);
        assertEquals(-1.0, mp.rf, DELTA);
        assertEquals(-1.0, mp.lb, DELTA);
        assertEquals(-1.0, mp.rb, DELTA);
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
    public void pureStrafeLeft() {
        MotorPowers mp = MecanumKinematics.robotCentric(0, -1, 0);
        assertEquals(-1.0, mp.lf, DELTA);
        assertEquals(1.0, mp.rf, DELTA);
        assertEquals(1.0, mp.lb, DELTA);
        assertEquals(-1.0, mp.rb, DELTA);
    }

    @Test
    public void pureRotateClockwise() {
        MotorPowers mp = MecanumKinematics.robotCentric(0, 0, 1);
        assertEquals(1.0, mp.lf, DELTA);
        assertEquals(-1.0, mp.rf, DELTA);
        assertEquals(1.0, mp.lb, DELTA);
        assertEquals(-1.0, mp.rb, DELTA);
    }

    @Test
    public void pureRotateCounterClockwise() {
        MotorPowers mp = MecanumKinematics.robotCentric(0, 0, -1);
        assertEquals(-1.0, mp.lf, DELTA);
        assertEquals(1.0, mp.rf, DELTA);
        assertEquals(-1.0, mp.lb, DELTA);
        assertEquals(1.0, mp.rb, DELTA);
    }

    @Test
    public void combinedNoClampNeeded() {
        MotorPowers mp = MecanumKinematics.robotCentric(0.3, 0.3, 0.3);
        assertEquals(0.9, mp.lf, DELTA);
        assertEquals(-0.3, mp.rf, DELTA);
        assertEquals(0.3, mp.lb, DELTA);
        assertEquals(0.3, mp.rb, DELTA);
    }

    @Test
    public void combinedRequiringClamp() {
        MotorPowers mp = MecanumKinematics.robotCentric(1, 1, 0);
        assertEquals(1.0, mp.lf, DELTA);
        assertEquals(0.0, mp.rf, DELTA);
        assertEquals(0.0, mp.lb, DELTA);
        assertEquals(1.0, mp.rb, DELTA);
    }

    @Test
    public void negativeCombinedRequiringClamp() {
        MotorPowers mp = MecanumKinematics.robotCentric(-1, 0, 1);
        assertEquals(0.0, mp.lf, DELTA);
        assertEquals(-1.0, mp.rf, DELTA);
        assertEquals(0.0, mp.lb, DELTA);
        assertEquals(-1.0, mp.rb, DELTA);
    }

    @Test
    public void boundaryExactlyOneIsNotOverClamped() {
        MotorPowers mp = MecanumKinematics.robotCentric(1, 0, 0);
        assertEquals(1.0, mp.lf, DELTA);
    }
}
