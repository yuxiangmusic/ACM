package notebook.geometry;

public class Point {
	public final int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pt = (Point) obj;
			return (x == pt.x) && (y == pt.y);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return x ^ y;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
