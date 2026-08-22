/**
 * Example 15: Pedro Pathing Autonomous, Wired Through a Tested State Machine
 *
 * Demonstrates building a two-segment autonomous path (toPickup, toScore) with the
 * Pedro Pathing library, then driving it through the SAME AutoRoutine state machine
 * class that was already unit-tested in the robot-math module using a FakeFollower.
 * The only new code here is PedroFollowerAdapter - a thin wrapper that makes Pedro
 * Pathing's real Follower satisfy the PathFollower interface AutoRoutine depends on.
 * AutoRoutine itself is copy-pasted unmodified: the exact same class, the exact same
 * logic, now driving a real robot instead of a fake in a test.
 *
 * NOTE: This example assumes PathFollower.java and AutoRoutine.java have been copied
 * from the robot-math module's src/examples/ into this project's teamcode package
 * (e.g. org.firstinspires.ftc.teamcode.examples) before this file will compile.
 *
 * NOTE: Pedro Pathing is a third-party library, not part of the stock FTC SDK. Add it
 * to TeamCode/build.gradle:
 *   repositories {
 *       maven { url = 'https://maven.pedropathing.com' }
 *   }
 *   dependencies {
 *       implementation 'com.pedropathing:ftc:2.0.4'
 *   }
 * Forgetting the maven repo line (not just the dependency line) is a common mistake -
 * Gradle will fail with a dependency-resolution error, not a "class not found" error,
 * which can be confusing to debug.
 *
 * NOTE: `Constants.createFollower(hardwareMap)` below is NOT part of Pedro Pathing
 * itself - it's a helper class you build once per robot, following Pedro Pathing's own
 * setup docs, that constructs a Follower configured for YOUR drivetrain and localizer
 * (in our case, a GoBilda Pinpoint - match the localizer config to Ch14's Pinpoint
 * setup). This example just shows the call site; building Constants.java is a separate
 * one-time setup step.
 *
 * What you'll see: Robot drives from its start pose to a pickup pose, then to a score
 * pose, then stops - with the current AutoRoutine state and Pedro's "isBusy" flag on
 * telemetry the whole time.
 */
package org.firstinspires.ftc.teamcode.examples;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Example 15 - Pedro Pathing Auto", group = "Examples")
public class Ch15_PedroPathingAutoOpMode extends OpMode {

    private Follower follower;
    private AutoRoutine autoRoutine;

    // NOTE: units/heading convention (inches vs mm, radians vs degrees) for
    // com.pedropathing.geometry.Pose must match whatever Constants.createFollower()
    // configured for your localizer - verify against Pedro Pathing's own docs/examples
    // before trusting these numbers on a real field.
    private Pose startPose;
    private Pose pickupPose;
    private Pose scorePose;

    // Adapts Pedro Pathing's real Follower to the PathFollower interface that
    // AutoRoutine (already unit-tested in robot-math with a FakeFollower) depends on.
    // followPath("toPickup") / followPath("toScore") are the two path names AutoRoutine
    // asks for, in that exact order - map each name to the PathChain built for it.
    private class PedroFollowerAdapter implements PathFollower {
        private final PathChain toPickup;
        private final PathChain toScore;

        PedroFollowerAdapter(PathChain toPickup, PathChain toScore) {
            this.toPickup = toPickup;
            this.toScore = toScore;
        }

        @Override
        public void followPath(String pathName) {
            if ("toPickup".equals(pathName)) {
                follower.followPath(toPickup, true);
            } else if ("toScore".equals(pathName)) {
                follower.followPath(toScore, true);
            } else {
                throw new IllegalArgumentException("Unknown path name: " + pathName);
            }
        }

        @Override
        public boolean isBusy() {
            return follower.isBusy();
        }

        @Override
        public void update() {
            follower.update();
        }
    }

    @Override
    public void init() {
        try {
            // NOTE: build this Constants class yourself per Pedro Pathing's setup docs,
            // matching the localizer type/offsets to your real GoBilda Pinpoint config
            // (same offsets you'd pass to pinpoint.setOffsets(...) in Ch14).
            follower = Constants.createFollower(hardwareMap);

            startPose = new Pose(0, 0, 0);
            pickupPose = new Pose(24, 12, Math.toRadians(90));
            scorePose = new Pose(48, 0, 0);

            PathChain toPickup = follower.pathBuilder()
                    .addPath(new BezierLine(startPose, pickupPose))
                    .setLinearHeadingInterpolation(startPose.getHeading(), pickupPose.getHeading())
                    .build();

            PathChain toScore = follower.pathBuilder()
                    .addPath(new BezierLine(pickupPose, scorePose))
                    .setLinearHeadingInterpolation(pickupPose.getHeading(), scorePose.getHeading())
                    .build();

            PedroFollowerAdapter adapter = new PedroFollowerAdapter(toPickup, toScore);
            autoRoutine = new AutoRoutine(adapter);

            telemetry.addData("Status", "Pedro Pathing follower + AutoRoutine ready");
        } catch (Exception e) {
            telemetry.addData("ERROR", "Setup failed");
            telemetry.addData("Error", e.getMessage());
        }
        telemetry.update();
    }

    @Override
    public void loop() {
        // This is the ENTIRE autonomous logic. AutoRoutine.update() internally calls
        // adapter.followPath(...)/adapter.update()/adapter.isBusy() at the right times -
        // no hand-rolled switch statement needed here, because that state machine logic
        // was already written and tested once in robot-math.
        autoRoutine.update();

        telemetry.addData("AutoRoutine State", autoRoutine.getState());
        telemetry.addData("Follower Busy", follower.isBusy());
        telemetry.update();
    }
}
