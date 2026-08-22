package examples;

/**
 * A minimal autonomous state machine - the answer key. Drives two path segments in
 * sequence through any PathFollower (real or fake). None of this depends on Pedro
 * Pathing directly, which is exactly what makes it unit-testable without a robot.
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
                if (!pathStarted) {
                    follower.followPath("toPickup");
                    pathStarted = true;
                }
                follower.update();
                if (!follower.isBusy()) {
                    state = State.DRIVE_TO_SCORE;
                    pathStarted = false;
                }
                break;
            case DRIVE_TO_SCORE:
                if (!pathStarted) {
                    follower.followPath("toScore");
                    pathStarted = true;
                }
                follower.update();
                if (!follower.isBusy()) {
                    state = State.DONE;
                    pathStarted = false;
                }
                break;
            case DONE:
                break;
        }
    }
}
