package examples;

/**
 * Stretch: a from-scratch point-to-point drive controller - a tiny alternative to a
 * full path-following library like Pedro Pathing. Reuses MecanumKinematics (this
 * package's answer key) and FieldOrientedTransform so the "drive toward a target"
 * error vector is expressed in the robot's current frame before mixing into wheel
 * powers. Position-only (no heading control) to keep the math approachable.
 */
public final class PointToPointController {
    private PointToPointController() {}

    public static MotorPowers computePowers(Pose current, Pose target, double gain) {
        double errorX = target.x - current.x;
        double errorY = target.y - current.y;

        FieldOrientedTransform.FieldVector robotError =
                FieldOrientedTransform.toRobotRelative(errorX, errorY, current.headingRadians);

        double forward = robotError.forward * gain;
        double strafe = robotError.strafe * gain;

        return MecanumKinematics.robotCentric(forward, strafe, 0);
    }
}
