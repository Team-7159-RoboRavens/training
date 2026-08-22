package examples;

/**
 * Pure field-to-robot-relative vector rotation - the answer key.
 * Assumes heading increases counter-clockwise (matches Pinpoint's AngleUnit.RADIANS
 * convention) - bench-verify on a real robot before trusting it.
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

    /** Rotates a field-frame stick vector by -headingRadians into the robot's current frame. */
    public static FieldVector toRobotRelative(double fieldForward, double fieldStrafe, double headingRadians) {
        double cos = Math.cos(headingRadians);
        double sin = Math.sin(headingRadians);

        double robotForward = fieldForward * cos + fieldStrafe * sin;
        double robotStrafe = -fieldForward * sin + fieldStrafe * cos;

        return new FieldVector(robotForward, robotStrafe);
    }
}
