package examples;

/**
 * Pure two-wheel ("dead wheel") odometry math - a simplified model of what a GoBilda
 * Pinpoint automates for you. One pod tracks forward distance, one tracks strafe
 * distance. Neither pod sits exactly on the robot's center of rotation, so when the
 * robot spins in place, each pod also senses a spurious arc-length movement even
 * though the robot didn't actually translate. This class corrects for that using
 * each pod's offset from the center of rotation.
 *
 * Simplified correction model used here: a pod offset by `offsetMm` from the center
 * of rotation sweeps an arc of length `offsetMm * headingDeltaRadians` purely from
 * rotation. Subtracting that arc length from the pod's raw reading leaves only the
 * translation-caused distance.
 */
public final class TwoWheelOdometry {
    private TwoWheelOdometry() {}

    public static PoseDelta computeDelta(double forwardPodDeltaTicks, double strafePodDeltaTicks,
                                          double headingDeltaRadians, double mmPerTick,
                                          double forwardPodOffsetMm, double strafePodOffsetMm) {
        double rawForwardMm = forwardPodDeltaTicks * mmPerTick;
        double rawStrafeMm = strafePodDeltaTicks * mmPerTick;

        double correctedForwardMm = rawForwardMm - forwardPodOffsetMm * headingDeltaRadians;
        double correctedStrafeMm = rawStrafeMm - strafePodOffsetMm * headingDeltaRadians;

        return new PoseDelta(correctedForwardMm, correctedStrafeMm);
    }
}
