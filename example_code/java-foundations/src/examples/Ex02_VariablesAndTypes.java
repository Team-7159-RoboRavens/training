/**
 * Example 2: Variables and Data Types
 *
 * Demonstrates declaring and using different data types.
 * Variables are containers for storing data values.
 */
public class Ex02_VariablesAndTypes {
    public static void main(String[] args) {
        // Integer types
        int motorPower = 75;
        long largeNumber = 1000000000L;

        // Floating point
        double servoPosition = 0.5;
        float batteryVoltage = 12.8f;

        // Boolean (true/false)
        boolean isMotorOn = true;

        // String (text)
        String robotName = "Team 9999 Bot";

        // Print all values
        System.out.println("Motor Power: " + motorPower);
        System.out.println("Servo Position: " + servoPosition);
        System.out.println("Battery Voltage: " + batteryVoltage);
        System.out.println("Motor On: " + isMotorOn);
        System.out.println("Robot Name: " + robotName);
    }
}
