/**
 * Example 6: Arrays and Strings
 *
 * Collections of data and text manipulation.
 */
public class Ex06_ArraysAndStrings {
    public static void main(String[] args) {
        // Array of integers
        int[] motorPowers = {30, 50, 75, 100, 85};
        System.out.println("Motor powers: " + motorPowers.length + " values");
        System.out.println("First power: " + motorPowers[0]);
        System.out.println("Last power: " + motorPowers[motorPowers.length - 1]);

        // Modify array values
        motorPowers[1] = 60;
        System.out.println("Updated second power: " + motorPowers[1]);

        // Loop through array
        System.out.println("\nAll powers:");
        for (int power : motorPowers) {
            System.out.println("  " + power);
        }

        // String operations
        System.out.println("\n=== Strings ===");
        String robotName = "Team 9999 Championship Bot";
        System.out.println("Name length: " + robotName.length());
        System.out.println("Uppercase: " + robotName.toUpperCase());
        System.out.println("Contains 'Championship': " + robotName.contains("Championship"));

        // String concatenation
        String status = "Motor power: " + 75 + " / " + 100;
        System.out.println(status);

        // String array
        String[] teamMembers = {"Alice", "Bob", "Charlie"};
        System.out.println("\nTeam members:");
        for (String member : teamMembers) {
            System.out.println("  - " + member);
        }
    }
}
