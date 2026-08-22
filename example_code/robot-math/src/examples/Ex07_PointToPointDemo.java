package examples;

/**
 * Example 7 (stretch): Point-to-Point Controller
 *
 * Shows the P-controller driving toward a fixed target from a few starting poses.
 * Notice how a far-away target saturates (clamps) the wheel powers to full speed,
 * while a nearby target produces proportionally smaller powers.
 */
public class Ex07_PointToPointDemo {
    public static void main(String[] args) {
        double gain = 0.01;
        Pose current = new Pose(0, 0, 0);

        MotorPowers far = PointToPointController.computePowers(current, new Pose(1000, 0, 0), gain);
        System.out.printf("Far target:   lf=%.3f rf=%.3f (expect clamped near 1.0)%n", far.lf, far.rf);

        MotorPowers near = PointToPointController.computePowers(current, new Pose(10, 0, 0), gain);
        System.out.printf("Near target:  lf=%.3f rf=%.3f (expect small, unclamped)%n", near.lf, near.rf);

        MotorPowers atTarget = PointToPointController.computePowers(current, current, gain);
        System.out.printf("At target:    lf=%.3f rf=%.3f (expect ~0)%n", atTarget.lf, atTarget.rf);
    }
}
