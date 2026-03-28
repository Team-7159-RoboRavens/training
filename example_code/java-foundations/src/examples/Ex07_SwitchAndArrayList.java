import java.util.ArrayList;

/**
 * Example 7: Switch Statement and ArrayList
 *
 * Switch statements for clean decision-making and ArrayList for flexible collections.
 * Common pattern in FTC: switch for state machines, ArrayList for tracking game elements.
 */
public class Ex07_SwitchAndArrayList {
    public static void main(String[] args) {
        System.out.println("=== Part 1: Switch Statement (State Machine) ===\n");

        // Switch statement - cleaner than if/else for many cases
        String robotState = "DRIVING";
        double motorPower = 0;

        switch (robotState) {
            case "IDLE":
                motorPower = 0;
                System.out.println("Robot stopped - idle");
                break;
            case "DRIVING":
                motorPower = 0.8;
                System.out.println("Robot driving at 80% power");
                break;
            case "COLLECTING":
                motorPower = 0.5;
                System.out.println("Robot collecting game element");
                break;
            case "SCORING":
                motorPower = 0.6;
                System.out.println("Robot positioning to score");
                break;
            default:
                motorPower = 0;
                System.out.println("Unknown state - stopping!");
        }
        System.out.println("Motor power set to: " + motorPower);

        // Demonstrate all states
        System.out.println("\nTrying different states:");
        String[] states = {"IDLE", "COLLECTING", "SCORING", "INVALID"};
        for (String state : states) {
            processRobotState(state);
        }

        System.out.println("\n=== Part 2: ArrayList (Dynamic Collections) ===\n");

        // Create ArrayList to store scored game elements
        ArrayList<String> scoredElements = new ArrayList<>();

        System.out.println("Starting a match - ArrayList is empty");
        System.out.println("Size: " + scoredElements.size());

        // Add elements as we score them
        scoredElements.add("Block");
        scoredElements.add("Specimen");
        scoredElements.add("Block");
        scoredElements.add("Ring");

        System.out.println("\nAfter scoring 4 elements:");
        System.out.println("Size: " + scoredElements.size());
        System.out.println("Scored: " + scoredElements);

        // Access specific elements
        System.out.println("\nFirst element: " + scoredElements.get(0));
        System.out.println("Last element: " + scoredElements.get(scoredElements.size() - 1));

        // Check if something was scored
        if (scoredElements.contains("Specimen")) {
            System.out.println("We scored a Specimen!");
        }

        // Count occurrences
        int blockCount = 0;
        for (String element : scoredElements) {
            if (element.equals("Block")) {
                blockCount++;
            }
        }
        System.out.println("Blocks scored: " + blockCount);

        // Remove an element (e.g., penalty for dropping)
        System.out.println("\nPenalty! Removing first element...");
        scoredElements.remove(0);
        System.out.println("Remaining scored: " + scoredElements);
        System.out.println("Size now: " + scoredElements.size());

        // Print all remaining elements
        System.out.println("\nDetailed breakdown:");
        for (int i = 0; i < scoredElements.size(); i++) {
            System.out.println("  Position " + i + ": " + scoredElements.get(i));
        }

        // ArrayList with integers (motor speeds)
        System.out.println("\n=== ArrayList Example: Motor Speeds ===");
        ArrayList<Integer> motorSpeeds = new ArrayList<>();
        motorSpeeds.add(30);
        motorSpeeds.add(50);
        motorSpeeds.add(75);
        motorSpeeds.add(100);

        System.out.println("Motor speed sequence:");
        for (Integer speed : motorSpeeds) {
            System.out.println("  Setting power to: " + (speed / 100.0));
        }

        // Calculate average speed
        int totalSpeed = 0;
        for (Integer speed : motorSpeeds) {
            totalSpeed += speed;
        }
        double averageSpeed = (double) totalSpeed / motorSpeeds.size();
        System.out.println("Average speed: " + averageSpeed);
    }

    // Helper method to demonstrate switch statement
    static void processRobotState(String state) {
        switch (state) {
            case "IDLE":
                System.out.println("  [" + state + "] Motor off");
                break;
            case "COLLECTING":
                System.out.println("  [" + state + "] Arm down, intake on");
                break;
            case "SCORING":
                System.out.println("  [" + state + "] Arm up, ready to place");
                break;
            default:
                System.out.println("  [" + state + "] UNKNOWN STATE!");
        }
    }
}
