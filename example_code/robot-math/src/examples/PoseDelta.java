package examples;

/** Robot-relative pose change over one odometry update tick, in millimeters. */
public class PoseDelta {
    public final double dx;
    public final double dy;

    public PoseDelta(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
