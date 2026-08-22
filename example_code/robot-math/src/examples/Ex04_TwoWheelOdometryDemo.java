package examples;

/**
 * Example 4: Two-Wheel Odometry Math
 *
 * Shows the pod-offset correction in action: with a nonzero pod offset, a PURE ROTATION
 * (no real translation) produces nonzero raw pod ticks, but the corrected delta comes
 * back to (near) zero once we account for the offset.
 */
public class Ex04_TwoWheelOdometryDemo {
    public static void main(String[] args) {
        double mmPerTick = 0.5;
        double forwardOffsetMm = 40;
        double strafeOffsetMm = 60;
        double headingDelta = 0.2; // radians, ~11.5 degrees

        // Ticks that WOULD be produced by pure rotation alone (arc length / mmPerTick)
        double forwardTicksFromRotation = (forwardOffsetMm * headingDelta) / mmPerTick;
        double strafeTicksFromRotation = (strafeOffsetMm * headingDelta) / mmPerTick;

        PoseDelta withOffset = TwoWheelOdometry.computeDelta(
                forwardTicksFromRotation, strafeTicksFromRotation, headingDelta, mmPerTick,
                forwardOffsetMm, strafeOffsetMm);
        System.out.printf("Pure rotation, WITH offset correction: dx=%.4f dy=%.4f (expect ~0,0)%n",
                withOffset.dx, withOffset.dy);

        PoseDelta withoutOffsetCorrection = TwoWheelOdometry.computeDelta(
                forwardTicksFromRotation, strafeTicksFromRotation, headingDelta, mmPerTick,
                0, 0);
        System.out.printf("Same raw ticks, offsets WRONGLY set to 0: dx=%.4f dy=%.4f (spurious drift!)%n",
                withoutOffsetCorrection.dx, withoutOffsetCorrection.dy);
    }
}
