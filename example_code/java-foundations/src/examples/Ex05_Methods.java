/**
 * Example 5: Methods
 *
 * Reusable blocks of code that perform specific tasks.
 */
public class Ex05_Methods {

    // Method that returns nothing
    static void setMotorPower(int power) {
        System.out.println("Setting motor power to: " + power);
    }

    // Method that returns a value
    static int calculateMotorPower(int joystickValue) {
        // joystick range: -100 to 100, scale to motor power 0 to 255
        return (joystickValue + 100) * 255 / 200;
    }

    // Method with multiple parameters
    static boolean isWithinRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    // Method with overloading (same name, different parameters)
    static void printStatus(String robotName) {
        System.out.println("Robot: " + robotName);
    }

    static void printStatus(String robotName, int power) {
        System.out.println("Robot: " + robotName + ", Power: " + power);
    }

    public static void main(String[] args) {
        // Call methods
        setMotorPower(75);

        int calculatedPower = calculateMotorPower(50);
        System.out.println("Calculated motor power: " + calculatedPower);

        boolean safe = isWithinRange(80, 0, 255);
        System.out.println("Power is safe: " + safe);

        // Method overloading
        printStatus("BotX");
        printStatus("BotX", 100);
    }
}
