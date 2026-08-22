package examples;

/**
 * Example 3: Field-Oriented Transform
 *
 * Shows how the SAME stick push ("forward") produces a DIFFERENT robot-relative
 * vector depending on the robot's current heading. This is the heart of field-
 * oriented drive: the field-relative direction stays fixed; the robot-relative
 * direction rotates with the chassis.
 */
public class Ex03_FieldOrientedTransformDemo {
    public static void main(String[] args) {
        double[] headingsDeg = {0, 90, 180, -90};
        for (double headingDeg : headingsDeg) {
            double headingRad = Math.toRadians(headingDeg);
            FieldOrientedTransform.FieldVector v =
                    FieldOrientedTransform.toRobotRelative(1, 0, headingRad);
            System.out.printf("heading=%6.1f deg  fieldForward=1,fieldStrafe=0  -> robotForward=%.3f robotStrafe=%.3f%n",
                    headingDeg, v.forward, v.strafe);
        }
    }
}
