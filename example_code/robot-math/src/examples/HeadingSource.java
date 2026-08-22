package examples;

/**
 * A minimal abstraction over "something that reports a heading in degrees" - could be
 * wrapped around a real GoBilda Pinpoint, a REV Hub IMU, or (as here) a fake for tests.
 * This is the key trick for testing code that consumes a sensor without needing the
 * real sensor: depend on the interface, not the concrete hardware class.
 */
public interface HeadingSource {
    double getHeadingDegrees();
}
