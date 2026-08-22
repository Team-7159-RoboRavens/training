import static org.junit.Assert.assertEquals;

import examples.FakeFollower;
import exercises.AutoRoutine;
import org.junit.Test;

/**
 * This suite targets exercises.AutoRoutine - it will fail until you complete the TODOs
 * in src/exercises/AutoRoutine.java. FakeFollower (from the examples package) simulates
 * "busy for N ticks, then done" - no real Pedro Pathing Follower or robot needed.
 */
public class AutoRoutineTest {

    @Test
    public void firstUpdateCallsFollowPathOnceForFirstSegment() {
        FakeFollower fake = new FakeFollower(2);
        AutoRoutine routine = new AutoRoutine(fake);

        routine.update();

        assertEquals(1, fake.getFollowPathCallCount());
        assertEquals("toPickup", fake.getLastPathFollowed());
        assertEquals(AutoRoutine.State.DRIVE_TO_PICKUP, routine.getState());
    }

    @Test
    public void staysInSameStateWhileFollowerIsBusy() {
        FakeFollower fake = new FakeFollower(3);
        AutoRoutine routine = new AutoRoutine(fake);

        routine.update();
        routine.update();

        assertEquals(AutoRoutine.State.DRIVE_TO_PICKUP, routine.getState());
    }

    @Test
    public void transitionsToNextSegmentOnceFollowerFinishes() {
        FakeFollower fake = new FakeFollower(2);
        AutoRoutine routine = new AutoRoutine(fake);

        routine.update(); // tick 1: follows "toPickup", busyTicks 2 -> 1, still busy
        routine.update(); // tick 2: busyTicks 1 -> 0, not busy -> transitions to DRIVE_TO_SCORE
        routine.update(); // tick 3: state is now DRIVE_TO_SCORE -> follows "toScore"

        assertEquals(AutoRoutine.State.DRIVE_TO_SCORE, routine.getState());
        assertEquals(2, fake.getFollowPathCallCount());
        assertEquals("toScore", fake.getLastPathFollowed());
    }

    @Test
    public void reachesDoneAfterBothSegmentsFinish() {
        FakeFollower fake = new FakeFollower(2);
        AutoRoutine routine = new AutoRoutine(fake);

        for (int i = 0; i < 4; i++) {
            routine.update();
        }

        assertEquals(AutoRoutine.State.DONE, routine.getState());
    }
}
