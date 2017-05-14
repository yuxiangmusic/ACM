package notebook.geometry;

public class Line {

	final Point a, b;

	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
	}

	public Line(int x1, int y1, int x2, int y2) {
		this.a = new Point(x1, y1);
		this.b = new Point(x2, y2);
	}

}
