package examples;

/**
 * A minimal abstraction over "something that follows autonomous paths" - could be
 * wrapped around Pedro Pathing's real Follower class, or (as here) a fake for tests.
 * Depending on this interface instead of the real Follower is what makes autonomous
 * STATE MACHINE logic (which path runs next, when to transition) unit-testable.
 */
public interface PathFollower {
    void followPath(String pathName);

    boolean isBusy();

    void update();
}
