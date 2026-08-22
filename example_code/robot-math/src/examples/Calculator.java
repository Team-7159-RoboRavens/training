package examples;

/**
 * A tiny, deliberately trivial pure-math class used only to introduce JUnit basics
 * before we apply the same testing technique to real robot math (mecanum kinematics,
 * field-oriented drive, odometry).
 */
public class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }
}
