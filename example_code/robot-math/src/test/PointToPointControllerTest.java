import static org.junit.Assert.assertEquals;

import examples.MotorPowers;
import examples.Pose;
import exercises.PointToPointController;
import org.junit.Test;

/**
 * This suite targets exercises.PointToPointController - it will fail until you complete
 * the TODOs in src/exercises/PointToPointController.java.
 */
public class PointToPointControllerTest {
    private static final double DELTA = 1e-9;
    private static final double GAIN = 0.01;

    @Test
    public void farTargetSaturatesToFullPower() {
        Pose current = new Pose(0, 0, 0);
        Pose target = new Pose(1000, 0, 0);

        MotorPowers mp = PointToPointController.computePowers(current, target, GAIN);

        assertEquals(1.0, mp.lf, DELTA);
        assertEquals(1.0, mp.rf, DELTA);
        assertEquals(1.0, mp.lb, DELTA);
        assertEquals(1.0, mp.rb, DELTA);
    }

    @Test
    public void atTargetProducesZeroPower() {
        Pose current = new Pose(5, 5, 0);
        Pose target = new Pose(5, 5, 0);

        MotorPowers mp = PointToPointController.computePowers(current, target, GAIN);

        assertEquals(0.0, mp.lf, DELTA);
        assertEquals(0.0, mp.rf, DELTA);
        assertEquals(0.0, mp.lb, DELTA);
        assertEquals(0.0, mp.rb, DELTA);
    }

    @Test
    public void outputScalesProportionallyBeforeClamping() {
        Pose current = new Pose(0, 0, 0);

        MotorPowers full = PointToPointController.computePowers(current, new Pose(10, 0, 0), GAIN);
        MotorPowers half = PointToPointController.computePowers(current, new Pose(5, 0, 0), GAIN);

        assertEquals(0.1, full.lf, DELTA);
        assertEquals(0.05, half.lf, DELTA);
        assertEquals(full.lf / 2.0, half.lf, DELTA);
    }
}
