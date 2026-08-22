package examples;

/**
 * Pure static mecanum drive math - the answer key. No exercise-package equivalent is
 * consumed by other examples here; PathFollower/AutoRoutine/PointToPointController
 * examples reuse THIS class directly so they don't depend on the student's still-in-
 * progress exercises.MecanumKinematics.
 *
 * Convention (bench-verify on a real robot before trusting it):
 *   forward: +1 = full forward
 *   strafe:  +1 = full right
 *   rotate:  +1 = full clockwise (viewed from above)
 */
public final class MecanumKinematics {
    private MecanumKinematics() {}

    public static MotorPowers robotCentric(double forward, double strafe, double rotate) {
        double lf = forward + strafe + rotate;
        double rf = forward - strafe - rotate;
        double lb = forward - strafe + rotate;
        double rb = forward + strafe - rotate;

        double max = Math.max(1.0, Math.max(Math.max(Math.abs(lf), Math.abs(rf)),
                                             Math.max(Math.abs(lb), Math.abs(rb))));

        return new MotorPowers(lf / max, rf / max, lb / max, rb / max);
    }
}
