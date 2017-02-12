import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/dogs
=====
2
0 0
10 0
2
30 0
15 0

=====
5
10 0
10 8
2 8
2 0
10 0
9
0 8
4 8
4 12
0 12
0 8
4 8
4 12
0 12
0 8

 */
public class KeepingTheDogsApart {

	public static void assertEquals(double expected, double actual) {
		if (Double.isNaN(actual) || Math.abs(expected - actual) > 1e-4)
			throw new AssertionError(actual);
	}

	public static void test() {
		assertEquals(10, solve(new double[][] { { 0, 0 }, { 10, 0 } }, new double[][] { { 30, 0 }, { 15, 0 } }));
		assertEquals(1.414213562373, solve(new double[][] { { 10, 0 }, { 10, 8 }, { 2, 8 }, { 2, 0 }, { 10, 0 } },
				new double[][] { { 0, 8 }, { 4, 8 }, { 4, 12 }, { 0, 12 }, { 0, 8 }, { 4, 8 }, { 4, 12 }, { 0, 12 }, { 0, 8 } }));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		double walkShallow[][] = new double[sc.nextInt()][];
		for (int i = 0; i < walkShallow.length; i++)
			walkShallow[i] = new double[] { sc.nextInt(), sc.nextInt() };
		double walkLydia[][] = new double[sc.nextInt()][];
		for (int i = 0; i < walkLydia.length; i++)
			walkLydia[i] = new double[] { sc.nextInt(), sc.nextInt() };
		System.out.println(solve(walkShallow, walkLydia));
		sc.close();
	}

	public static double solve(double[][] a, double[][] b) {
		List<double[][]> asplit = new ArrayList<>(), bsplit = new ArrayList<>();
		split(asplit, bsplit, a, b);
		assertEquals(asplit.size(), bsplit.size());
		double minsq = Double.MAX_VALUE;
		for (int i = 0; i < asplit.size(); i++) {
			double[][] sega = asplit.get(i), segb = bsplit.get(i);
			double closestsq = closestsq(sega, segb);
			minsq = Math.min(minsq, closestsq);
		}
		return Math.sqrt(minsq);
	}

	private static double closestsq(double[][] sega, double[][] segb) {
		double x1 = segb[0][0] - sega[0][0], y1 = segb[0][1] - sega[0][1];
		double x2 = segb[1][0] - sega[1][0], y2 = segb[1][1] - sega[1][1];
		double k, y;
		if (x1 == x2 && y1 == y2)
			return sq(x1) + sq(y1);
		else if (x1 == x2)
			return y1 * y2 < 0 ? sq(x1) : Math.min(sq(x1) + sq(y1), sq(x2) + sq(y2));
		else if (y1 == y2)
			return x1 * x2 < 0 ? sq(y1) : Math.min(sq(x1) + sq(y1), sq(x2) + sq(y2));
		else {
			k = (double) (y2 - y1) / (x2 - x1);
			y = (y1 - k * x1) / (1 + k * k);
		}
		if ((y - y1) * (y - y2) < 0)
			return sq(x2 * y1 - y2 * x1) / (sq(y2 - y1) + sq(x2 - x1));
		else
			return Math.min(sq(x1) + sq(y1), sq(x2) + sq(y2));
	}

	private static double length(double[][] line) {
		double dx = line[1][0] - line[0][0], dy = line[1][1] - line[0][1];
		return Math.hypot(dx, dy);
	}

	private static double[][][] split(double[][] line, double length) {
		double dx = line[1][0] - line[0][0], dy = line[1][1] - line[0][1], llength = length(line);
		double[] cut = new double[] { line[0][0] + dx * length / llength, line[0][1] + dy * length / llength };
		return new double[][][] { new double[][] { line[0], cut }, new double[][] { cut, line[1] } };
	}

	private static void split(List<double[][]> asplit, List<double[][]> bsplit, double[][] a, double[][] b) {
		int i = 0, j = 0;
		double acur[][] = { a[0], a[1] }, bcur[][] = { b[0], b[1] };
		while (true) {
			double la = length(acur), lb = length(bcur);
			if (la == lb) {
				asplit.add(acur);
				bsplit.add(bcur);
				if (++i + 1 < a.length && ++j + 1 < b.length) {
					acur = new double[][] { a[i], a[i + 1] };
					bcur = new double[][] { b[j], b[j + 1] };
				} else {
					break;
				}
			} else if (la < lb) {
				asplit.add(acur);
				double[][][] split = split(bcur, la);
				bsplit.add(split[0]);
				bcur = split[1];
				if (++i + 1 < a.length)
					acur = new double[][] { a[i], a[i + 1] };
				else
					break;
			} else {
				bsplit.add(bcur);
				double[][][] split = split(acur, lb);
				asplit.add(split[0]);
				acur = split[1];
				if (++j + 1 < b.length)
					bcur = new double[][] { b[j], b[j + 1] };
				else
					break;
			}
		}
	}

	private static double sq(double x) {
		return x * x;
	}

}
