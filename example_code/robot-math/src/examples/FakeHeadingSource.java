package examples;

/**
 * A test double for HeadingSource: returns a pre-scripted sequence of headings, one per
 * call, holding on the last value once the script runs out. No real sensor involved.
 */
public class FakeHeadingSource implements HeadingSource {
    private final double[] scriptedHeadingsDegrees;
    private int index = 0;

    public FakeHeadingSource(double... scriptedHeadingsDegrees) {
        this.scriptedHeadingsDegrees = scriptedHeadingsDegrees;
    }

    @Override
    public double getHeadingDegrees() {
        double value = scriptedHeadingsDegrees[index];
        if (index < scriptedHeadingsDegrees.length - 1) {
            index++;
        }
        return value;
    }
}
