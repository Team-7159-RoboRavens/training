import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import examples.FakeHeadingSource;
import examples.HeadingSource;
import exercises.HeadingDriftDetector;
import org.junit.Test;

/**
 * This suite targets exercises.HeadingDriftDetector - it will fail until you complete
 * the TODOs in src/exercises/HeadingDriftDetector.java. No real sensor needed - a
 * FakeHeadingSource (from the examples package) scripts the heading readings.
 */
public class HeadingDriftDetectorTest {

    @Test
    public void smallChangeUnderThresholdIsNotFlagged() {
        HeadingSource fake = new FakeHeadingSource(0, 5);
        HeadingDriftDetector detector = new HeadingDriftDetector(fake, 10);

        detector.checkDrift(); // baseline read
        assertFalse(detector.checkDrift());
    }

    @Test
    public void largeChangeOverThresholdIsFlagged() {
        HeadingSource fake = new FakeHeadingSource(0, 20);
        HeadingDriftDetector detector = new HeadingDriftDetector(fake, 10);

        detector.checkDrift(); // baseline read
        assertTrue(detector.checkDrift());
    }

    @Test
    public void wraparoundNearPlusMinus180DoesNotFalsePositive() {
        // 179 -> -179 is really just a 2 degree turn across the wrap boundary.
        HeadingSource fake = new FakeHeadingSource(179, -179);
        HeadingDriftDetector detector = new HeadingDriftDetector(fake, 10);

        detector.checkDrift(); // baseline read
        assertFalse(detector.checkDrift());
    }
}
