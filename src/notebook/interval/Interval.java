package notebook.interval;

import java.awt.Rectangle;

public class Interval {
	public final int start, end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * @return true if this intersects i, can generalize to n dimensions
	 * @see Rectangle#intersects(Rectangle)
	 */
	public boolean intersects(Interval i) {
		return start <= i.end && end >= i.start;
		// return !(a > i.b || b < i.a);
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
