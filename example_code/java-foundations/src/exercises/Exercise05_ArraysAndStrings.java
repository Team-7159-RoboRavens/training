/**
 * Exercise 5: Arrays and Strings
 *
 * OBJECTIVE: Work with arrays and perform string operations.
 *
 * INSTRUCTIONS:
 *   PART A: Print the length of the teamMembers array
 *   PART B: Print each team member using a for-each loop
 *   PART C: Find and print the index of "Charlie" in the array
 *   PART D: Check if robotDescription contains "Fast"
 *
 * EXPECTED OUTPUT:
 *   Team size: 3
 *   Team members:
 *   - Alice
 *   - Bob
 *   - Charlie
 *   Index of Charlie: 2
 *   Description contains "Fast": true
 */
public class Exercise05_ArraysAndStrings {
    public static void main(String[] args) {
        String[] teamMembers = {"Alice", "Bob", "Charlie"};
        String robotDescription = "A Fast and Reliable Robot";

        // PART A: Print array length
        // TODO: Print "Team size: " + length of teamMembers

        // PART B: Print each team member
        System.out.println("Team members:");
        // TODO: Write a for-each loop to print each member with " - " prefix

        // PART C: Find index of "Charlie"
        int charlieIndex = -1;
        // TODO: Write a for loop to find index of "Charlie"
        // Hint: compare teamMembers[i].equals("Charlie")
        System.out.println("Index of Charlie: " + charlieIndex);

        // PART D: Check if description contains "Fast"
        // TODO: Use .contains() method to check if robotDescription has "Fast"
        // Print "Description contains \"Fast\": " + result
    }
}
