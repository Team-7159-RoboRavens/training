# Week 1-2: Java Foundations for FTC

## What You'll Learn This Week
- Classes and Objects
- Methods and Functions
- Variables and Data Types
- Loops and Conditionals
- Basic Data Structures
- Comments and Code Style

---

## Slide 1: What is Java?

Java is an **object-oriented programming language** used to control FTC robots.

### Key Facts:
- **Compiled language** - Code must be converted to machine readable format
- **Platform independent** - Same code runs on Android phones, Control Hubs, etc.
- **Strongly typed** - Variables have fixed data types
- **Object-oriented** - Everything is organized into classes

### Why Java for FTC?
- Android Studio uses Java for FTC SDK
- Large community and resources
- Scales from simple programs to complex autonomous routines

---

## Slide 2: Classes - The Building Blocks

A **class** is a blueprint for creating objects. Think of it like a template.

```java
public class Robot {
    // Properties (what the robot HAS)
    int wheelSpeed;
    String name;

    // Methods (what the robot DOES)
    void drive() {
        // code here
    }
}
```

### Key Concepts:
- **public** = others can use it
- **class** = defines a new type
- Class name must match filename (e.g., `Robot.java`)
- Use **PascalCase** for class names (Robot, MotorController, etc.)

---

## Slide 3: Objects - Class Instances

An **object** is a specific instance of a class.

```java
// Create a new Robot object
Robot myRobot = new Robot();

// Use it
myRobot.wheelSpeed = 50;
myRobot.drive();
```

### Creating Objects:
```
Type    Name     = new ClassName();
Robot   myBot    = new Robot();
```

Think of it like:
- **Class** = Cookie cutter
- **Object** = Actual cookie made from the cutter

---

## Slide 4: Variables and Data Types

Variables store data. Each variable has a **type** that determines what it can hold.

### Primitive Data Types:

| Type | Purpose | Example | Size |
|------|---------|---------|------|
| `int` | Whole numbers | `42`, `-5`, `1000` | 32-bit |
| `double` | Decimal numbers | `3.14`, `2.5` | 64-bit |
| `boolean` | True/False | `true`, `false` | 1-bit |
| `String` | Text/words | `"Hello"`, `"robot"` | Variable |

### Variable Naming:
```java
int motorSpeed = 50;           // Good
int MOTORSPEED = 50;           // Bad - use camelCase
int motor_speed = 50;          // Bad - don't use underscores

double batteryVoltage = 12.5;
boolean isMoving = true;
String teamName = "FTC Team 0000";
```

---

## Slide 5: Basic Operations

### Arithmetic Operators:
```java
int x = 10;
int y = 3;

x + y   // Addition:      13
x - y   // Subtraction:   7
x * y   // Multiply:      30
x / y   // Division:      3
x % y   // Modulo (remainder): 1
```

### Assignment Operators:
```java
int speed = 50;           // Assign value
speed = speed + 10;       // speed is now 60
speed += 10;              // Shorthand - same as above
speed -= 5;               // Subtract 5
speed *= 2;               // Multiply by 2
```

---

## Slide 6: Making Decisions with If Statements

**If statements** let your robot make decisions based on conditions.

```java
int motorPower = getTelemetry();

if (motorPower > 50) {
    // Motor is high power
    robot.fast();
} else if (motorPower > 20) {
    // Motor is medium power
    robot.medium();
} else {
    // Motor is low power
    robot.slow();
}
```

### Comparison Operators:
```java
x == y    // Equal
x != y    // Not equal
x > y     // Greater than
x < y     // Less than
x >= y    // Greater than or equal
x <= y    // Less than or equal
```

### Logical Operators:
```java
if (x > 0 && y > 0) { }     // AND - both must be true
if (x > 0 || y > 0) { }     // OR - at least one true
if (!(x > 0)) { }           // NOT - reverse the condition
```

---

## Slide 7: Loops - Repeat Actions

**Loops** repeat code multiple times.

### While Loop:
```java
int count = 0;
while (count < 5) {
    robot.spin();
    count++;                // Increment by 1
}
```

### For Loop:
```java
// Cleaner way to repeat a set number of times
for (int i = 0; i < 5; i++) {
    robot.spin();
}

// Same result as the while loop above
```

### Real FTC Example:
```java
// Keep running motor while button is pressed
while (gamepad1.a) {
    motor.setPower(0.5);
}
motor.setPower(0);
```

---

## Slide 8: Methods - Functions

A **method** is a reusable block of code that performs a specific task.

```java
public class Robot {
    // Method with no parameters
    public void stop() {
        motor.setPower(0);
    }

    // Method with parameters
    public void drive(double speed, int distance) {
        motor.setPower(speed);
        sleep(distance);
    }

    // Method that returns a value
    public int getMotorSpeed() {
        return motor.getPower();
    }
}
```

### Method Structure:
```
public Type MethodName(Parameters) {
    // Code here
    return value;  // Only if type is not void
}
```

### Method Naming:
- Use **camelCase**: `driveForward()`, `calculateDistance()`
- Name should describe what it does
- Starts with a verb: `drive`, `calculate`, `check`

---

## Slide 9: Arrays - Store Multiple Values

An **array** stores multiple values of the same type.

```java
// Create an array of 5 integers
int[] motorSpeeds = new int[5];

// Set values
motorSpeeds[0] = 50;
motorSpeeds[1] = 60;
motorSpeeds[2] = 70;

// Get a value
int firstSpeed = motorSpeeds[0];  // 50

// Array index starts at 0!
// Valid indices: 0, 1, 2, 3, 4
```

### Loop Through Array:
```java
for (int i = 0; i < motorSpeeds.length; i++) {
    motor.setPower(motorSpeeds[i]);
}
```

### ArrayList - Dynamic Size:
```java
ArrayList<Integer> speeds = new ArrayList<>();
speeds.add(50);      // Add value
speeds.add(60);
speeds.remove(0);    // Remove first element
int size = speeds.size();
```

---

## Slide 10: Inheritance - Code Reuse

**Inheritance** lets classes share code.

```java
// Parent class
public class Robot {
    int batteryLevel;

    public void checkBattery() {
        System.out.println(batteryLevel);
    }
}

// Child class inherits from Robot
public class CompetitionRobot extends Robot {
    // Automatically has batteryLevel and checkBattery()

    // Add new functionality specific to competition
    public void driveToGoal() {
        // code here
    }
}
```

### Key Points:
- Child class **extends** parent class
- Child inherits all public methods and properties
- Child can add new methods
- Child can override (replace) parent methods with `@Override`

---

## Slide 11: Strings and Text

**Strings** are sequences of characters (text).

```java
String teamName = "FTC Team 0000";
String message = "Hello, " + "World";

// String concatenation (joining)
String full = "Team: " + teamName;     // "Team: FTC Team 0000"

// String methods
int length = teamName.length();        // 14
String lower = teamName.toLowerCase(); // "ftc team 0000"
boolean contains = teamName.contains("0000");  // true
```

### Printing Output:
```java
System.out.println("Debug message");     // Print and new line
System.out.print("No new line");         // Print only

// In FTC (to driver station)
telemetry.addData("Speed", motorSpeed);
telemetry.update();
```

---

## Slide 12: Comments - Document Your Code

**Comments** are notes for humans. Code ignores them.

### Single-line Comment:
```java
// This is a comment explaining what the code does
int motorSpeed = 50;
```

### Multi-line Comment:
```java
/*
 * This is a longer comment
 * that spans multiple lines
 * Use this for complex explanations
 */
```

### Javadoc Comment:
```java
/**
 * Drives the robot forward at specified speed
 * @param speed - Motor power (0.0 to 1.0)
 * @return true if successful, false if blocked
 */
public boolean driveForward(double speed) {
    // code
}
```

### Comment Guidelines:
- Explain WHY, not WHAT (code shows the WHAT)
- Keep comments up-to-date when code changes
- Don't comment obvious code: `i++; // increment i` is bad

---

## Slide 13: Common Java Gotchas

### Case Sensitivity:
```java
int mySpeed = 50;
int MySpeed = 100;  // Different variable!
myspeed = 50;       // ERROR - wrong case
```

### Semicolons:
```java
int x = 5;          // Correct
int x = 5           // ERROR - forgot semicolon

for (int i = 0; i < 5; i++) { }  // Correct - no semicolon after )
```

### Curly Braces:
```java
if (x > 0) {
    doSomething();
}  // Must close every brace

if (x > 0) {
    doSomething();
// ERROR - missing }
```

### Array Index:
```java
int[] arr = new int[5];  // Valid indices: 0, 1, 2, 3, 4
arr[5];     // ERROR - out of bounds!
arr[-1];    // ERROR - negative index invalid
```

---

## Slide 14: Switch Statement - Clean Decision Making

A **switch statement** makes decisions when you have many possible values. Perfect for state machines!

```java
String robotState = "COLLECTING";

switch (robotState) {
    case "IDLE":
        motor.setPower(0);
        break;
    case "DRIVING":
        motor.setPower(0.8);
        break;
    case "COLLECTING":
        arm.moveDown();
        break;
    case "SCORING":
        arm.moveUp();
        break;
    default:
        System.out.println("Unknown state!");
}
```

### Switch vs If-Else:
```java
// If-Else approach (verbose)
if (robotState.equals("IDLE")) { }
else if (robotState.equals("DRIVING")) { }
else if (robotState.equals("COLLECTING")) { }
else { }

// Switch approach (cleaner)
switch (robotState) {
    case "IDLE": ...
    case "DRIVING": ...
    case "COLLECTING": ...
    default: ...
}
```

### Key Points:
- Use switch when comparing one variable to many values
- **break;** exits the switch — forget it and code will "fall through"!
- **default** case catches anything not matched (like `else`)
- Works with enums, strings, integers, chars
- Common in FTC for robot state machines

---

## Slide 15: ArrayList - Flexible Container

An **ArrayList** is like an array, but it grows and shrinks dynamically. Use when you don't know the final size.

```java
// Import required
import java.util.ArrayList;

// Create ArrayList (stores Strings)
ArrayList<String> gameElements = new ArrayList<>();

// Add items
gameElements.add("Block");
gameElements.add("Specimen");
gameElements.add("Block");

// Get size and access items
int count = gameElements.size();  // 3
String first = gameElements.get(0);  // "Block"

// Remove an item
gameElements.remove(1);  // Removes "Specimen"

// Loop through ArrayList
for (String element : gameElements) {
    System.out.println(element);
}
```

### ArrayList vs Array:

| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed (set at creation) | Dynamic (grows as needed) |
| Syntax | `int[] arr` | `ArrayList<Integer> list` |
| Add | Cannot (must replace) | `.add()` method |
| Remove | Cannot (must shift manually) | `.remove()` method |
| Performance | Faster | Slightly slower (flexible) |
| When to use | Known size | Unknown size, frequent adds/removes |

### Real FTC Examples:
```java
// Track scored game elements
ArrayList<String> scoredItems = new ArrayList<>();
scoredItems.add("Block");      // Score a block
scoredItems.add("Specimen");   // Score a specimen
int totalScored = scoredItems.size();

// Store waypoints for autonomous
ArrayList<Integer> waypoints = new ArrayList<>();
waypoints.add(0);
waypoints.add(500);
waypoints.add(1000);

for (int wp : waypoints) {
    robot.moveToPosition(wp);
}
```

### Common Methods:
```java
list.add(item);          // Add to end
list.add(index, item);   // Add at position
list.remove(index);      // Remove by index
list.get(index);         // Get item at index
list.size();             // Get number of items
list.contains(item);     // Check if item exists
list.clear();            // Remove all items
```

---

## Slide 16: Practice Exercises

### Exercise 1: Variables and Types
Create variables to store:
- Robot name (String)
- Battery voltage (double)
- Motor count (int)
- Is moving (boolean)

### Exercise 2: If Statements
Write code that:
- Checks if battery > 11V, print "Good"
- Else if battery > 9V, print "Low"
- Else print "Critical"

### Exercise 3: Loops
Write a loop that:
- Prints numbers 1 to 10
- Then prints numbers 10 to 1 (backwards)

### Exercise 4: Methods
Write a method that:
- Takes two integers as input
- Returns their average
- Handle the case where inputs are negative

### Exercise 5: Arrays
Create an array of 4 motor speeds
- Set each value to a different number
- Print each value using a loop
- Calculate the average

### Exercise 6: Switch Statement
Write a switch statement that:
- Accepts a robot state (IDLE, DRIVING, COLLECTING, SCORING)
- Prints different messages for each state
- Has a default case for unknown states

### Exercise 7: ArrayList
Write code that:
- Creates an ArrayList to store game element names
- Adds 3 elements ("Block", "Specimen", "Ring")
- Removes one element
- Prints remaining items
- Prints total count

---

## Week 1-2 Summary

You've learned the **fundamentals** of Java programming:

✅ **Classes & Objects** - How to structure code
✅ **Variables & Types** - How to store data
✅ **Conditionals** - How to make decisions
✅ **Loops** - How to repeat actions
✅ **Methods** - How to organize reusable code
✅ **Arrays** - How to manage multiple values
✅ **Strings** - How to work with text
✅ **Switch Statements** - Clean state machine decisions
✅ **ArrayList** - Dynamic, flexible containers

### Ready for Next Week?
Once you're comfortable with these concepts, you'll move to **FTC SDK Basics** where you'll apply this knowledge to actually control a robot!

---

## Resources

- **Java Documentation**: docs.oracle.com/javase/tutorial/
- **Learn Java for FTC**: GitHub alan412/LearnJavaForFTC
- **Android Studio Guide**: FTC official documentation
- **Practice Online**: codingame.com, hackerrank.com (Java track)

**Remember**: Programming is a skill learned by DOING, not watching. Type the code yourself!
