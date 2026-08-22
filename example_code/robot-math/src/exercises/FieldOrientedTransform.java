package exercises;

/**
 * Exercise 2: Field-Oriented Transform
 *
 * OBJECTIVE: Rotate a field-relative stick vector into the robot's current frame,
 * so "push forward" always drives away from the driver, no matter which way the
 * robot chassis is currently facing.
 *
 * INSTRUCTIONS:
 *   PART A: Compute cos and sin of headingRadians.
 *   PART B: Apply the rotation-by-negative-heading formula:
 *     robotForward = fieldForward * cos(h) + fieldStrafe * sin(h)
 *     robotStrafe  = -fieldForward * sin(h) + fieldStrafe * cos(h)
 *   PART C: Return a new FieldVector(robotForward, robotStrafe).
 *
 * EXPECTED OUTPUT (verified by FieldOrientedTransformTest.java, run `./gradlew test`):
 *   toRobotRelative(1, 0, 0)                -> forward=1,  strafe=0    (heading 0 = identity)
 *   toRobotRelative(1, 0, Math.PI / 2)      -> forward=0,  strafe=-1   (heading 90 deg)
 *   toRobotRelative(1, 0, Math.PI)          -> forward=-1, strafe=0    (heading 180 deg)
 */
public final class FieldOrientedTransform {
    private FieldOrientedTransform() {}

    public static final class FieldVector {
        public final double forward;
        public final double strafe;

        public FieldVector(double forward, double strafe) {
            this.forward = forward;
            this.strafe = strafe;
        }
    }

    public static FieldVector toRobotRelative(double fieldForward, double fieldStrafe, double headingRadians) {
        // TODO PART A: double cos = Math.cos(headingRadians); double sin = Math.sin(headingRadians);

        // TODO PART B: compute robotForward and robotStrafe using the rotation formula above

        // TODO PART C: return new FieldVector(robotForward, robotStrafe)

        return new FieldVector(0, 0); // placeholder - replace this return
    }
}
