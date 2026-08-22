package exercises;

import examples.HeadingSource;

/**
 * Exercise 4: Testable Hardware Adapter Pattern
 *
 * OBJECTIVE: Detect large jumps in heading between reads of a HeadingSource, without
 * ever depending on a real sensor - depend on the interface, and it becomes trivial to
 * unit test with a fake.
 *
 * INSTRUCTIONS:
 *   PART A: On the first call, there's nothing to compare to - just remember the
 *     current heading and return false.
 *   PART B: On later calls, compute the difference between the current heading and
 *     the last one, and normalize it into the range (-180, 180] so a wraparound
 *     (e.g. 179 -> -179) isn't reported as a huge jump.
 *   PART C: Remember the current heading for next time, and return true if the
 *     absolute normalized difference is greater than the threshold.
 *
 * EXPECTED OUTPUT (verified by HeadingDriftDetectorTest.java, run `./gradlew test`):
 *   Headings [0, 5]       with threshold 10 -> 2nd read NOT flagged (5 deg change)
 *   Headings [0, 20]      with threshold 10 -> 2nd read IS flagged (20 deg change)
 *   Headings [179, -179]  with threshold 10 -> 2nd read NOT flagged (really only 2 deg)
 */
public class HeadingDriftDetector {
    private final HeadingSource source;
    private final double thresholdDegrees;
    private Double lastHeadingDegrees = null;

    public HeadingDriftDetector(HeadingSource source, double thresholdDegrees) {
        this.source = source;
        this.thresholdDegrees = thresholdDegrees;
    }

    public boolean checkDrift() {
        double current = source.getHeadingDegrees();

        // TODO PART A: if lastHeadingDegrees is null, store `current` in it and return false

        // TODO PART B: compute diff = current - lastHeadingDegrees, then normalize it into
        //              (-180, 180] - hint: diff % 360, then +/- 360 if outside that range

        // TODO PART C: store `current` in lastHeadingDegrees, then return whether
        //              Math.abs(diff) > thresholdDegrees

        return false; // placeholder - replace this return
    }
}
