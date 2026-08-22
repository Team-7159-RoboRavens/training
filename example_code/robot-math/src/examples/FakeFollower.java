package examples;

/**
 * A test double for PathFollower: simulates "busy for N update() ticks, then done" for
 * whichever path was most recently requested. No real Pedro Pathing Follower involved.
 */
public class FakeFollower implements PathFollower {
    private final int busyTicksPerPath;
    private int remainingBusyTicks = 0;
    private String lastPathFollowed = null;
    private int followPathCallCount = 0;

    public FakeFollower(int busyTicksPerPath) {
        this.busyTicksPerPath = busyTicksPerPath;
    }

    @Override
    public void followPath(String pathName) {
        lastPathFollowed = pathName;
        followPathCallCount++;
        remainingBusyTicks = busyTicksPerPath;
    }

    @Override
    public boolean isBusy() {
        return remainingBusyTicks > 0;
    }

    @Override
    public void update() {
        if (remainingBusyTicks > 0) {
            remainingBusyTicks--;
        }
    }

    public String getLastPathFollowed() {
        return lastPathFollowed;
    }

    public int getFollowPathCallCount() {
        return followPathCallCount;
    }
}
