package examples;

/**
 * Example 6: Testable Autonomous State Machine
 *
 * Drives AutoRoutine through a FakeFollower and prints every state transition. On the
 * real robot you'd instead pass in an adapter that wraps Pedro Pathing's real Follower
 * (see Ch15_PedroPathingAutoOpMode.java in the ftc-sdk module) - AutoRoutine itself
 * never changes.
 */
public class Ex06_AutoRoutineDemo {
    public static void main(String[] args) {
        FakeFollower fake = new FakeFollower(2);
        AutoRoutine routine = new AutoRoutine(fake);

        for (int tick = 0; tick < 5; tick++) {
            routine.update();
            System.out.println("Tick " + tick + ": state=" + routine.getState()
                    + " lastPathFollowed=" + fake.getLastPathFollowed());
        }
    }
}
