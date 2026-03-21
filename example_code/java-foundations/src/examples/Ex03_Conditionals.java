/**
 * Example 3: Conditionals (if/else)
 *
 * Decision-making in code: execute different code based on conditions.
 */
public class Ex03_Conditionals {
    public static void main(String[] args) {
        int motorPower = 75;
        int maxPower = 100;

        // Simple if
        if (motorPower > 50) {
            System.out.println("Motor is running at good speed");
        }

        // if/else
        if (motorPower > maxPower) {
            System.out.println("ERROR: Power exceeds maximum!");
        } else {
            System.out.println("Power is within safe limits");
        }

        // if/else if/else
        if (motorPower == 0) {
            System.out.println("Motor is off");
        } else if (motorPower < 30) {
            System.out.println("Motor is running slowly");
        } else if (motorPower < 70) {
            System.out.println("Motor is running at medium speed");
        } else {
            System.out.println("Motor is running at high speed");
        }

        // Comparison operators
        boolean isReady = motorPower > 0 && motorPower <= maxPower;
        System.out.println("Robot ready: " + isReady);
    }
}
