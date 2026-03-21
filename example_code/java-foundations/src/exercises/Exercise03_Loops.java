/**
 * Exercise 3: Loops
 *
 * OBJECTIVE: Print motor power levels using loops.
 *
 * INSTRUCTIONS:
 *   PART A: Print numbers 1 through 5 using a for loop
 *   PART B: Print numbers 5 down to 1 using a for loop
 *   PART C: Loop through the array of motor speeds and print each one
 *
 * EXPECTED OUTPUT:
 *   === Part A ===
 *   1
 *   2
 *   3
 *   4
 *   5
 *   === Part B ===
 *   5
 *   4
 *   3
 *   2
 *   1
 *   === Part C ===
 *   Speed: 20
 *   Speed: 40
 *   Speed: 60
 *   Speed: 80
 *   Speed: 100
 */
public class Exercise03_Loops {
    public static void main(String[] args) {
        // PART A: Print 1 through 5
        System.out.println("=== Part A ===");
        // TODO: Write a for loop that prints 1 through 5

        // PART B: Print 5 down to 1
        System.out.println("=== Part B ===");
        // TODO: Write a for loop that prints 5 down to 1

        // PART C: Loop through motor speeds
        System.out.println("=== Part C ===");
        int[] motorSpeeds = {20, 40, 60, 80, 100};
        // TODO: Write a for-each loop to print each speed with label "Speed: "
    }
}
