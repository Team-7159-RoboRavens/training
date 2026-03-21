/**
 * Example 4: Loops
 *
 * Repeating code: for loops, while loops, and iteration.
 */
public class Ex04_Loops {
    public static void main(String[] args) {
        // for loop: repeat a fixed number of times
        System.out.println("=== For Loop ===");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Motor power level: " + i);
        }

        // while loop: repeat while a condition is true
        System.out.println("\n=== While Loop ===");
        int motorPower = 20;
        while (motorPower <= 100) {
            System.out.println("Current power: " + motorPower);
            motorPower += 20;  // increase by 20 each time
        }

        // for-each loop: iterate through arrays
        System.out.println("\n=== For-Each Loop ===");
        int[] motorSpeeds = {10, 20, 30, 40, 50};
        for (int speed : motorSpeeds) {
            System.out.println("Testing speed: " + speed);
        }

        // Loop control
        System.out.println("\n=== Break and Continue ===");
        for (int i = 1; i <= 10; i++) {
            if (i == 3) continue;  // skip this iteration
            if (i == 7) break;      // exit the loop
            System.out.println("Iteration: " + i);
        }
    }
}
