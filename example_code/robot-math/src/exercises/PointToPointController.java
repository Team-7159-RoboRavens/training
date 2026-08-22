package exercises;

import examples.FieldOrientedTransform;
import examples.MecanumKinematics;
import examples.MotorPowers;
import examples.Pose;

/**
 * Exercise 6 (stretch): Point-to-Point Controller
 *
 * OBJECTIVE: Build a from-scratch "drive toward a target pose" controller - a tiny
 * alternative to a full path-following library like Pedro Pathing.
 *
 * INSTRUCTIONS:
 *   PART A: Compute the field-relative position error: target.x - current.x,
 *     target.y - current.y.
 *   PART B: Rotate that error into the robot's current frame using
 *     FieldOrientedTransform.toRobotRelative(errorX, errorY, current.headingRadians).
 *   PART C: Scale the rotated error by `gain` to get forward/strafe power, and pass
 *     it (with rotate=0) into MecanumKinematics.robotCentric(...).
 *
 * EXPECTED OUTPUT (verified by PointToPointControllerTest.java, run `./gradlew test`):
 *   Far target, gain=0.01     -> wheel powers clamp to (near) full speed
 *   At the target already     -> wheel powers are ~0
 *   Halving the distance       -> roughly halves the (unclamped) wheel powers
 */
public final class PointToPointController {
    private PointToPointController() {}

    public static MotorPowers computePowers(Pose current, Pose target, double gain) {
        // TODO PART A: double errorX = target.x - current.x; double errorY = target.y - current.y;

        // TODO PART B: FieldOrientedTransform.FieldVector robotError =
        //              FieldOrientedTransform.toRobotRelative(errorX, errorY, current.headingRadians);

        // TODO PART C: double forward = robotError.forward * gain; double strafe = robotError.strafe * gain;
        //              return MecanumKinematics.robotCentric(forward, strafe, 0);

        return new MotorPowers(0, 0, 0, 0); // placeholder - replace this return
    }
}
