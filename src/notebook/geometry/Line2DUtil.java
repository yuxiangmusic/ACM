package notebook.geometry;

import notebook.Notebook;

/**
 * @see {@link java.awt.geom.Line2D}
 */
@Notebook
public class Line2DUtil {

	public static double ptSegDistSq(double x1, double y1, double x2, double y2, double px, double py) {
		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;
		double dotprod = px * x2 + py * y2;
		double projlenSq;
		if (dotprod <= 0.0) {
			projlenSq = 0.0;
		} else {
			px = x2 - px;
			py = y2 - py;
			dotprod = px * x2 + py * y2;
			if (dotprod <= 0.0) {
				projlenSq = 0.0;
			} else {
				projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
			}
		}
		double lenSq = px * px + py * py - projlenSq;
		if (lenSq < 0) {
			lenSq = 0;
		}
		return lenSq;
	}

	public static double ptSegDist(double x1, double y1, double x2, double y2, double px, double py) {
		return Math.sqrt(ptSegDistSq(x1, y1, x2, y2, px, py));
	}

	public static double ptLineDistSq(double x1, double y1, double x2, double y2, double px, double py) {
		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;
		double dotprod = px * x2 + py * y2;
		double projlenSq = dotprod * dotprod / (x2 * x2 + y2 * y2);
		double lenSq = px * px + py * py - projlenSq;
		if (lenSq < 0) {
			lenSq = 0;
		}
		return lenSq;
	}

	public static double ptLineDist(double x1, double y1, double x2, double y2, double px, double py) {
		return Math.sqrt(ptLineDistSq(x1, y1, x2, y2, px, py));
	}
}
