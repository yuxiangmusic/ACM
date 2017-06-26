package notebook.geometry;

import java.util.Arrays;

import notebook.Notebook;

public class CircleUtil {

	public static void main(String[] args) {
		for (double[] arr : tangents(1, 0, 1, 3, 0, 1))
			System.out.println(Arrays.toString(arr));
	}

	/**
	 * https://en.wikibooks.org/wiki/Algorithm_Implementation/Geometry/Tangents_between_two_circles
	 * 
	 * @return 4x4 or 2x4 tangents
	 */
	@Notebook
	public static double[][] tangents(double x1, double y1, double r1, double x2, double y2, double r2) {
		double d_sq = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
		if (d_sq <= (r1 - r2) * (r1 - r2))
			return new double[0][4];

		double d = Math.sqrt(d_sq);
		double vx = (x2 - x1) / d;
		double vy = (y2 - y1) / d;

		double[][] res = new double[4][4];
		int i = 0;

		for (int sign1 = +1; sign1 >= -1; sign1 -= 2) {
			double c = (r1 - sign1 * r2) / d;
			if (c * c > 1.0)
				continue;
			double h = Math.sqrt(Math.max(0.0, 1.0 - c * c));

			for (int sign2 = +1; sign2 >= -1; sign2 -= 2) {
				double nx = vx * c - sign2 * h * vy;
				double ny = vy * c + sign2 * h * vx;

				double[] a = res[i++];
				a[0] = x1 + r1 * nx;
				a[1] = y1 + r1 * ny;
				a[2] = x2 + sign1 * r2 * nx;
				a[3] = y2 + sign1 * r2 * ny;
			}
		}
		return Arrays.copyOf(res, i);
	}

}
