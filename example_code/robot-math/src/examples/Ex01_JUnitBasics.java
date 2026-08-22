package examples;

/**
 * Example 1: JUnit Basics
 *
 * Before this module, every exercise in this curriculum was checked by eye against an
 * "EXPECTED OUTPUT" comment. That works, but it's slow and easy to fool yourself with.
 * This example shows the manual, eyeball-it way of checking code - then JUnitBasicsTest.java
 * (in src/test/) shows the same checks written as automated JUnit assertions instead.
 *
 * Run this file's main() and compare the printed values to the comments by eye.
 * Then open JUnitBasicsTest.java and run `./gradlew test` - same checks, but the
 * computer does the comparing for you, and tells you exactly which one failed.
 */
public class Ex01_JUnitBasics {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        double sum = calc.add(2, 3);
        System.out.println("2 + 3 = " + sum + " (expected 5.0)");

        double difference = calc.subtract(10, 4);
        System.out.println("10 - 4 = " + difference + " (expected 6.0)");

        double negative = calc.add(-2, -3);
        System.out.println("-2 + -3 = " + negative + " (expected -5.0)");
    }
}
