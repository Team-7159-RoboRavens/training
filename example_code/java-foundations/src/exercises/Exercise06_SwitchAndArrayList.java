import java.util.ArrayList;

/**
 * Exercise 6: Switch Statement and ArrayList
 *
 * OBJECTIVE: Practice using switch statements for state machines and ArrayList for managing collections.
 *
 * INSTRUCTIONS:
 *   PART A: Complete the switch statement that handles robot states (IDLE, MOVING, COLLECTING, SCORING)
 *           For each state, print a different message describing the robot action
 *   PART B: Create an ArrayList to store scored game elements
 *           Add three elements: "Block", "Specimen", "Block"
 *   PART C: Print the initial size of the ArrayList
 *   PART D: Remove the element at index 1
 *   PART E: Print the final ArrayList contents and size
 *
 * EXPECTED OUTPUT:
 *   Testing states:
 *   IDLE: Motor off, waiting for input
 *   MOVING: Driving forward at full power
 *   COLLECTING: Arm down, intake running
 *   SCORING: Arm raised, ready to place
 *
 *   Game elements:
 *   Initial size: 3
 *   [Block, Specimen, Block]
 *   Removed specimen at index 1
 *   Final contents: [Block, Block]
 *   Final size: 2
 */
public class Exercise06_SwitchAndArrayList {
    public static void main(String[] args) {
        System.out.println("Testing states:");

        // PART A: Complete the switch statement
        String[] robotStates = {"IDLE", "MOVING", "COLLECTING", "SCORING"};
        for (String state : robotStates) {
            switch (state) {
                // TODO: case "IDLE": print "Motor off, waiting for input"
                // TODO: case "MOVING": print "Driving forward at full power"
                // TODO: case "COLLECTING": print "Arm down, intake running"
                // TODO: case "SCORING": print "Arm raised, ready to place"
                // Don't forget break; statements!
            }
        }

        System.out.println("\nGame elements:");

        // PART B: Create ArrayList and add elements
        // TODO: Create new ArrayList<String> called gameElements
        // TODO: Add "Block"
        // TODO: Add "Specimen"
        // TODO: Add "Block"

        // PART C: Print initial size
        // TODO: Print "Initial size: " + gameElements.size()

        // PART D: Remove element at index 1
        // TODO: Remove element at index 1 using .remove()
        System.out.println("Removed specimen at index 1");

        // PART E: Print final contents and size
        // TODO: Print gameElements (should print as [Block, Block])
        // TODO: Print "Final size: " + gameElements.size()
    }
}
