
/**
 * Keeps track of the number of processes executed and the sum of turn around
 * time for this simulation. It provides a method 'average' to return the
 * average turn around time after the simulation.
 * 
 * @author cs321-instructors
 * 
 */
public class Averager {
	private int count;
	private double sum;

	public Averager() {
		count = 0;
		sum = 0;
	}

	public void addNumber(double value) {
		if (count == Integer.MAX_VALUE)
			throw new IllegalStateException("Too many Processes.");
		count++;
		sum += value;
	}

	public double getAverage() {
		if (count == 0)
			return Double.NaN;
		else
			return sum / count;
	}

	public int getCount() {
		return count;
	}
}
