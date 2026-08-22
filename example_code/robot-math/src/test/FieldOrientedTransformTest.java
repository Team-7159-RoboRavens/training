import static org.junit.Assert.assertEquals;

import exercises.FieldOrientedTransform;
import org.junit.Test;

/**
 * This suite targets exercises.FieldOrientedTransform - it will fail until you complete
 * the TODOs in src/exercises/FieldOrientedTransform.java.
 */
public class FieldOrientedTransformTest {
    private static final double DELTA = 1e-9;
    private static final double LOOSE_DELTA = 1e-6;

    @Test
    public void headingZeroIsIdentity() {
        FieldOrientedTransform.FieldVector v1 = FieldOrientedTransform.toRobotRelative(1, 0, 0);
        assertEquals(1.0, v1.forward, DELTA);
        assertEquals(0.0, v1.strafe, DELTA);

        FieldOrientedTransform.FieldVector v2 = FieldOrientedTransform.toRobotRelative(0, 1, 0);
        assertEquals(0.0, v2.forward, DELTA);
        assertEquals(1.0, v2.strafe, DELTA);
    }

    @Test
    public void heading90Degrees() {
        FieldOrientedTransform.FieldVector v = FieldOrientedTransform.toRobotRelative(1, 0, Math.PI / 2);
        assertEquals(0.0, v.forward, DELTA);
        assertEquals(-1.0, v.strafe, DELTA);
    }

    @Test
    public void heading180Degrees() {
        FieldOrientedTransform.FieldVector v = FieldOrientedTransform.toRobotRelative(1, 0, Math.PI);
        assertEquals(-1.0, v.forward, DELTA);
        assertEquals(0.0, v.strafe, DELTA);
    }

    @Test
    public void headingNegative90AndPositive270Agree() {
        FieldOrientedTransform.FieldVector vNeg = FieldOrientedTransform.toRobotRelative(1, 0, -Math.PI / 2);
        assertEquals(0.0, vNeg.forward, DELTA);
        assertEquals(1.0, vNeg.strafe, DELTA);

        FieldOrientedTransform.FieldVector vPos = FieldOrientedTransform.toRobotRelative(1, 0, 3 * Math.PI / 2);
        assertEquals(vNeg.forward, vPos.forward, DELTA);
        assertEquals(vNeg.strafe, vPos.strafe, DELTA);
    }

    @Test
    public void arbitraryAngle30Degrees() {
        // cos(30deg) = 0.8660254, sin(30deg) = 0.5
        // robotForward = 1*cos30 + 0.5*sin30 = 1.1160254
        // robotStrafe  = -1*sin30 + 0.5*cos30 = -0.0669873
        FieldOrientedTransform.FieldVector v = FieldOrientedTransform.toRobotRelative(1, 0.5, Math.PI / 6);
        assertEquals(1.1160254, v.forward, LOOSE_DELTA);
        assertEquals(-0.0669873, v.strafe, LOOSE_DELTA);
    }

    @Test
    public void zeroVectorAlwaysReturnsZero() {
        FieldOrientedTransform.FieldVector v = FieldOrientedTransform.toRobotRelative(0, 0, 1.2345);
        assertEquals(0.0, v.forward, DELTA);
        assertEquals(0.0, v.strafe, DELTA);
    }

    @Test
    public void roundTripRecoversOriginalVector() {
        double heading = 0.73;
        FieldOrientedTransform.FieldVector rotated = FieldOrientedTransform.toRobotRelative(1, 0.5, heading);
        FieldOrientedTransform.FieldVector back = FieldOrientedTransform.toRobotRelative(rotated.forward, rotated.strafe, -heading);
        assertEquals(1.0, back.forward, DELTA);
        assertEquals(0.5, back.strafe, DELTA);
    }
}
