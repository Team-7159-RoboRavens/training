import static org.junit.Assert.assertEquals;

import examples.Calculator;
import org.junit.Test;

/**
 * These are the exact same checks as Ex01_JUnitBasics.java's main() method, written as
 * JUnit tests instead of println statements. Run with `./gradlew test` - no need to read
 * console output and compare numbers by eye; JUnit tells you pass/fail directly.
 */
public class JUnitBasicsTest {
    private static final double DELTA = 1e-9;

    @Test
    public void addsTwoPositiveNumbers() {
        Calculator calc = new Calculator();
        assertEquals(5.0, calc.add(2, 3), DELTA);
    }

    @Test
    public void subtractsTwoNumbers() {
        Calculator calc = new Calculator();
        assertEquals(6.0, calc.subtract(10, 4), DELTA);
    }

    @Test
    public void addsTwoNegativeNumbers() {
        Calculator calc = new Calculator();
        assertEquals(-5.0, calc.add(-2, -3), DELTA);
    }
}
