import static org.junit.Assert.assertEquals;

import examples.FieldOrientedTransform;
import examples.PoseDelta;
import exercises.TwoWheelOdometry;
import org.junit.Test;

/**
 * This suite targets exercises.TwoWheelOdometry - it will fail until you complete
 * the TODOs in src/exercises/TwoWheelOdometry.java.
 */
public class TwoWheelOdometryTest {
    private static final double DELTA = 1e-9;
    private static final double LOOSE_DELTA = 1e-6;
    private static final double MM_PER_TICK = 0.5;

    @Test
    public void pureForwardNoOffsetsNoRotation() {
        PoseDelta d = TwoWheelOdometry.computeDelta(1000, 0, 0, MM_PER_TICK, 0, 0);
        assertEquals(500.0, d.dx, DELTA);
        assertEquals(0.0, d.dy, DELTA);
    }

    @Test
    public void pureStrafeNoOffsetsNoRotation() {
        PoseDelta d = TwoWheelOdometry.computeDelta(0, 1000, 0, MM_PER_TICK, 0, 0);
        assertEquals(0.0, d.dx, DELTA);
        assertEquals(500.0, d.dy, DELTA);
    }

    @Test
    public void pureRotationZeroOffsetProducesNoTranslation() {
        // With offset = 0, a real pod sitting exactly on the rotation axis would see
        // zero ticks during a pure spin - so raw ticks are 0 here too.
        PoseDelta d = TwoWheelOdometry.computeDelta(0, 0, Math.PI / 2, MM_PER_TICK, 0, 0);
        assertEquals(0.0, d.dx, DELTA);
        assertEquals(0.0, d.dy, DELTA);
    }

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

    @Test
    public void combinedTranslationAndRotation() {
        double forwardOffsetMm = 40;
        double strafeOffsetMm = 60;
        double headingDelta = 0.2;

        // 100mm of real forward translation + the same rotation-induced arc as above
        double rawForwardMm = 100 + forwardOffsetMm * headingDelta; // 108mm
        double forwardTicks = rawForwardMm / MM_PER_TICK; // 216 ticks

        // 50mm of real strafe translation + the same rotation-induced arc as above
        double rawStrafeMm = 50 + strafeOffsetMm * headingDelta; // 62mm
        double strafeTicks = rawStrafeMm / MM_PER_TICK; // 124 ticks

        PoseDelta d = TwoWheelOdometry.computeDelta(forwardTicks, strafeTicks, headingDelta,
                MM_PER_TICK, forwardOffsetMm, strafeOffsetMm);
        assertEquals(100.0, d.dx, LOOSE_DELTA);
        assertEquals(50.0, d.dy, LOOSE_DELTA);
    }

    @Test
    public void drivingASquareReturnsToStart() {
        // Offsets = 0 so pure rotation contributes zero robot-relative translation,
        // isolating the field-pose accumulation logic from offset-correction nuances.
        double fieldX = 0;
        double fieldY = 0;
        double heading = 0;
        double sideMm = 100;

        for (int side = 0; side < 4; side++) {
            // Drive forward one side of the square (robot-relative).
            double forwardTicks = sideMm / MM_PER_TICK;
            PoseDelta robotDelta = TwoWheelOdometry.computeDelta(forwardTicks, 0, 0, MM_PER_TICK, 0, 0);

            // Rotate the robot-relative delta into the field frame using the CURRENT heading.
            // (This is the inverse of FieldOrientedTransform.toRobotRelative: rotating by
            // -heading undoes the field->robot rotation, giving robot->field.)
            FieldOrientedTransform.FieldVector fieldDelta =
                    FieldOrientedTransform.toRobotRelative(robotDelta.dx, robotDelta.dy, -heading);
            fieldX += fieldDelta.forward;
            fieldY += fieldDelta.strafe;

            // Quarter turn (90 degrees CCW).
            heading += Math.PI / 2;
        }

        assertEquals(0.0, fieldX, LOOSE_DELTA);
        assertEquals(0.0, fieldY, LOOSE_DELTA);
    }
}
