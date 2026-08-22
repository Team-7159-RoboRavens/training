package examples;

/**
 * Example 2: Mecanum Kinematics
 *
 * Demonstrates the pure MecanumKinematics.robotCentric(...) math with a few concrete
 * stick inputs. Run this to SEE the numbers; MecanumKinematicsTest.java (in src/test/)
 * checks the same math automatically.
 */
public class Ex02_MecanumKinematicsDemo {
    public static void main(String[] args) {
        print("Pure forward", MecanumKinematics.robotCentric(1, 0, 0));
        print("Pure strafe right", MecanumKinematics.robotCentric(0, 1, 0));
        print("Pure rotate clockwise", MecanumKinematics.robotCentric(0, 0, 1));
        print("Forward + strafe (needs clamping)", MecanumKinematics.robotCentric(1, 1, 0));
    }

    private static void print(String label, MotorPowers mp) {
        System.out.printf("%-32s lf=%.2f rf=%.2f lb=%.2f rb=%.2f%n", label, mp.lf, mp.rf, mp.lb, mp.rb);
    }
}
