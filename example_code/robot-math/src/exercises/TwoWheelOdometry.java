package exercises;

import examples.PoseDelta;

/**
 * Exercise 3: Two-Wheel Odometry Math
 *
 * OBJECTIVE: Convert raw odometry pod encoder ticks into a corrected robot-relative
 * pose delta, compensating for each pod's physical offset from the center of rotation.
 *
 * INSTRUCTIONS:
 *   PART A: Convert each pod's raw tick count to millimeters (ticks * mmPerTick).
 *   PART B: Subtract the rotation-induced arc length from each raw distance:
 *     correctedForward = rawForward - forwardPodOffsetMm * headingDeltaRadians
 *     correctedStrafe  = rawStrafe  - strafePodOffsetMm  * headingDeltaRadians
 *   PART C: Return a new PoseDelta(correctedForward, correctedStrafe).
 *
 * EXPECTED OUTPUT (verified by TwoWheelOdometryTest.java, run `./gradlew test`):
 *   Pure forward, zero offsets:        computeDelta(1000, 0, 0, 0.5, 0, 0)      -> dx=500, dy=0
 *   Pure in-place rotation, offset=0:  computeDelta(0, 0, PI/2, 0.5, 0, 0)       -> dx=0,   dy=0
 *   Pure rotation WITH offset:         (see TwoWheelOdometryTest for exact numbers) -> dx~0, dy~0
 */
public final class TwoWheelOdometry {
    private TwoWheelOdometry() {}

    public static PoseDelta computeDelta(double forwardPodDeltaTicks, double strafePodDeltaTicks,
                                          double headingDeltaRadians, double mmPerTick,
                                          double forwardPodOffsetMm, double strafePodOffsetMm) {
        // TODO PART A: double rawForwardMm = forwardPodDeltaTicks * mmPerTick;
        //              double rawStrafeMm = strafePodDeltaTicks * mmPerTick;

        // TODO PART B: subtract forwardPodOffsetMm * headingDeltaRadians from rawForwardMm,
        //              and strafePodOffsetMm * headingDeltaRadians from rawStrafeMm

        // TODO PART C: return new PoseDelta(correctedForwardMm, correctedStrafeMm)

        return new PoseDelta(0, 0); // placeholder - replace this return
    }
}
