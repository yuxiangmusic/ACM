package notebook.tree;

import static notebook.geometry.PointUtil.distsq;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.function.Function;

import org.junit.Test;

import notebook.geometry.Point;

public class TwoDTreeTest {

	static final Random rand = new Random();

	@Test
	public void test() {
		Point points[] = { //
				new Point(0, 0), //
				new Point(1, 1), //
				new Point(2, 2), //
				new Point(3, 3), //
				new Point(4, 4), //
				new Point(1, 0), //
				new Point(2, 0), //
				new Point(3, 0), //
				new Point(4, 0), //
				new Point(0, 1), //
				new Point(0, 2), //
				new Point(0, 3), //
				new Point(0, 4), //
		};
		TwoDTree tree = new TwoDTree(points);

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				Point p = new Point(x, y);
				int expected = distsq(p, nearestBruteForce(points, p));
				int actual = distsq(p, tree.nearest(p));
				assertEquals(expected, actual);
			}
		}
	}

	@Test
	public void test_rand() {
		int n = 1000;
		Point points[] = new Point[n];
		for (int i = 0; i < n; i++)
			points[i] = new Point(rand.nextInt(n), rand.nextInt(n));

		TwoDTree tree = new TwoDTree(points);

		int[][] expected = new int[n][n];
		int[][] actual = new int[n][n];

		testFunction(expected, n, tree::nearest, "2D Tree");
		testFunction(actual, n, p -> nearestBruteForce(points, p), "Brute Force");

		assertArrayEquals(expected, actual);
	}

	static void testFunction(int[][] result, int n, Function<Point, Point> nearest, String name) {
		long start = System.nanoTime(), pc = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				Point p = new Point(x, y);
				result[x][y] = distsq(p, nearest.apply(p));
				int npc = (int) (Math.round(100 * (double) (x * n + y) / (n * n)));
				if (npc != pc) {
					System.out.println("Running " + name + " " + (pc = npc) + "% completed (time = "
							+ (System.nanoTime() - start) + ")");
					start = System.nanoTime();
				}
			}
		}
	}

	static Point nearestBruteForce(Point[] points, Point q) {
		Point nearest = points[0];
		int minSq = distsq(q, nearest);
		for (Point p : points) {
			int distSq = distsq(p, q);
			if (distSq < minSq) {
				minSq = distSq;
				nearest = p;
			}
		}
		return nearest;
	}

}
