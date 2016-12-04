import java.awt.Point;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
=====
2
6 3 3
A 1
B 2
C 3
D 4
F 5
G 6
ABC
FEC
DBG
2 6 3
A 100
B 1000
BBBBBB
AAAAEB
BBBBBB

 */

/**
 * <a href="https://icpcarchive.ecs.baylor.edu/external/65/6516.pdf">
 * 
 * 6516 - Enterprising Escape</a>
 */
public class EnterprisingEscape {

	static long[][] cost, dist;
	static int k, w, h;

	static String[] rows;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			k = sc.nextInt();
			w = sc.nextInt();
			h = sc.nextInt();

			cost = new long[h][w];
			dist = new long[h][w];
			rows = new String[h];

			int[] costArr = new int[1 << 8];
			for (int i = 0; i < k; i++)
				costArr[sc.next().charAt(0)] = sc.nextInt();

			Point E = null;
			for (int y = 0; y < h; y++) {
				String row = sc.next();
				for (int x = 0; x < w; x++) {
					char ch = row.charAt(x);
					if (ch == 'E') {
						cost[y][x] = dist[y][x] = 0;
						E = new Point(x, y);
					} else {
						cost[y][x] = costArr[ch];
						dist[y][x] = Integer.MAX_VALUE;
					}
				}
			}

			PriorityQueue<Point> queue = new PriorityQueue<>((p, q) -> Long.compare(dist[p.y][p.x], dist[q.y][q.x]));
			queue.add(E);
			while (!queue.isEmpty()) {
				Point min = queue.remove();

				if (min.x == 0 || min.x == w - 1 || min.y == 0 || min.y == h - 1) {
					System.out.println(dist[min.y][min.x]);
					break;
				}

				for (Point n : new Point[] { new Point(min.x - 1, min.y), new Point(min.x + 1, min.y),
						new Point(min.x, min.y - 1), new Point(min.x, min.y + 1) }) {
					long alt = dist[min.y][min.x] + cost[n.y][n.x];

					if (alt < dist[n.y][n.x]) {
						dist[n.y][n.x] = alt;
						queue.add(n); // can duplicate but it doesn't matter
					}
				}
			}
		} // end of case
		sc.close();
	}
}
