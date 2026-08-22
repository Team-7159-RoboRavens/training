package examples;

/** A robot pose on the field: x/y position and heading, in whatever consistent units you choose. */
public class Pose {
    public final double x;
    public final double y;
    public final double headingRadians;

    public Pose(double x, double y, double headingRadians) {
        this.x = x;
        this.y = y;
        this.headingRadians = headingRadians;
    }
}
