package notebook.geometry;

public class Line2D {

	final Point2D a, b;

	public Line2D(Point2D a, Point2D b) {
		this.a = a;
		this.b = b;
	}

	public Line2D(int x1, int y1, int x2, int y2) {
		this.a = new Point2D(x1, y1);
		this.b = new Point2D(x2, y2);
	}

}
