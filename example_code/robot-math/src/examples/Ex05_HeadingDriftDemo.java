package examples;

/**
 * Example 5: Testable Hardware Adapter Pattern
 *
 * HeadingDriftDetector never touches a real sensor - it only depends on the
 * HeadingSource interface. Here we drive it with a FakeHeadingSource to see it work;
 * on the real robot you'd instead pass in an adapter that wraps a GoBildaPinpointDriver
 * or IMU (see Ch14_GoBildaPinpointOpMode.java in the ftc-sdk module).
 */
public class Ex05_HeadingDriftDemo {
    public static void main(String[] args) {
        HeadingSource fake = new FakeHeadingSource(0, 5, 25, 24);
        HeadingDriftDetector detector = new HeadingDriftDetector(fake, 10);

        for (int i = 0; i < 4; i++) {
            boolean flagged = detector.checkDrift();
            System.out.println("Read #" + i + ": drift flagged = " + flagged);
        }
    }
}
