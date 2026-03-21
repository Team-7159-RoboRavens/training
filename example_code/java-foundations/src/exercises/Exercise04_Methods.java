/**
 * Exercise 4: Methods
 *
 * OBJECTIVE: Write methods to calculate motor power and check if a value is in range.
 *
 * INSTRUCTIONS:
 *   1. Write a method: int calculateMotorPower(int joystickValue)
 *      - Takes joystick value (-100 to 100)
 *      - Returns motor power (0 to 255)
 *      - Formula: (joystickValue + 100) * 255 / 200
 *   2. Write a method: boolean isInRange(int value, int min, int max)
 *      - Returns true if value is between min and max (inclusive)
 *   3. In main(), call both methods and print results
 *
 * EXPECTED OUTPUT:
 *   Joystick 50 → Motor power: 191
 *   Value 191 in range [0, 255]: true
 *   Value 300 in range [0, 255]: false
 */
public class Exercise04_Methods {

    // TODO: Write method calculateMotorPower(int joystickValue)
    // Should return: (joystickValue + 100) * 255 / 200

    // TODO: Write method isInRange(int value, int min, int max)
    // Should return true if min <= value <= max

    public static void main(String[] args) {
        // TODO: Call calculateMotorPower(50) and store result
        // Print "Joystick 50 → Motor power: " + result

        // TODO: Call isInRange(191, 0, 255) and print result

        // TODO: Call isInRange(300, 0, 255) and print result
    }
}
