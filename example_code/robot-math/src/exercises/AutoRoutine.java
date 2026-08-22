package exercises;

import examples.PathFollower;

/**
 * Exercise 5: Testable Autonomous State Machine
 *
 * OBJECTIVE: Drive two path segments in sequence through a PathFollower, without
 * depending on Pedro Pathing's real Follower class - so this whole state machine can
 * be unit tested with a fake.
 *
 * INSTRUCTIONS:
 *   PART A (DRIVE_TO_PICKUP): The first time update() is called in this state, call
 *     follower.followPath("toPickup") exactly once. Every call to update() in this
 *     state should also call follower.update(). Once follower.isBusy() becomes false,
 *     move to DRIVE_TO_SCORE.
 *   PART B (DRIVE_TO_SCORE): Same pattern, but call follower.followPath("toScore") and
 *     transition to DONE once follower.isBusy() becomes false.
 *   PART C (DONE): Do nothing.
 *
 * EXPECTED OUTPUT (verified by AutoRoutineTest.java, run `./gradlew test`):
 *   First update() call            -> follower.followPath("toPickup") called exactly once
 *   While isBusy() stays true      -> state remains DRIVE_TO_PICKUP
 *   Once isBusy() flips false      -> state becomes DRIVE_TO_SCORE, "toScore" is followed
 *   After both paths finish        -> state becomes DONE
 */
public class AutoRoutine {
    public enum State { DRIVE_TO_PICKUP, DRIVE_TO_SCORE, DONE }

    private final PathFollower follower;
    private State state = State.DRIVE_TO_PICKUP;
    private boolean pathStarted = false;

    public AutoRoutine(PathFollower follower) {
        this.follower = follower;
    }

    public State getState() {
        return state;
    }

    public void update() {
        switch (state) {
            case DRIVE_TO_PICKUP:
                // TODO PART A: if (!pathStarted) { follower.followPath("toPickup"); pathStarted = true; }
                //              follower.update();
                //              if (!follower.isBusy()) { state = State.DRIVE_TO_SCORE; pathStarted = false; }
                break;
            case DRIVE_TO_SCORE:
                // TODO PART B: same pattern as PART A, but with "toScore" and transitioning to DONE
                break;
            case DONE:
                // TODO PART C: nothing to do
                break;
        }
    }
}
