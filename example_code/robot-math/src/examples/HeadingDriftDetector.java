package examples;

/**
 * Flags large jumps in heading between consecutive reads of any HeadingSource - the
 * answer key. Handles the +/-180 degree wraparound so a true small rotation across
 * the wrap boundary (e.g. 179 -> -179, really just a 2 degree turn) isn't misreported
 * as a huge jump.
 */
public class HeadingDriftDetector {
    private final HeadingSource source;
    private final double thresholdDegrees;
    private Double lastHeadingDegrees = null;

    public HeadingDriftDetector(HeadingSource source, double thresholdDegrees) {
        this.source = source;
        this.thresholdDegrees = thresholdDegrees;
    }

    /** Returns true if the heading changed by more than the threshold since the last call. */
    public boolean checkDrift() {
        double current = source.getHeadingDegrees();
        if (lastHeadingDegrees == null) {
            lastHeadingDegrees = current;
            return false;
        }
        double diff = normalizeDegrees(current - lastHeadingDegrees);
        lastHeadingDegrees = current;
        return Math.abs(diff) > thresholdDegrees;
    }

    private static double normalizeDegrees(double degrees) {
        double d = degrees % 360;
        if (d > 180) d -= 360;
        if (d < -180) d += 360;
        return d;
    }
}
