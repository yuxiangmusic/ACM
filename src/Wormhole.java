import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
=====
3
4
Earth 0 0 0
Proxima 5 0 0
Barnards 5 5 0
Sirius 0 5 0
2
Earth Barnards
Barnards Sirius
6
Earth Proxima
Earth Barnards
Earth Sirius
Proxima Earth
Barnards Earth
Sirius Earth
3
z1 0 0 0
z2 10 10 10
z3 10 0 0
1
z1 z2
3
z2 z1
z1 z2
z1 z3
2
Mars 12345 98765 87654
Jupiter 45678 65432 11111
0
1
Mars Jupiter

 */

/**
 * <a href="https://icpcarchive.ecs.baylor.edu/external/71/7131.pdf">
 * 
 * Wormhole</a>
 */
// XXX not yet judged
public class Wormhole {

	static HashMap<String, Integer> map = new HashMap<>();
	static long[] x, y, z;
	static double[][] dist;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			map = new HashMap<>();

			int p = sc.nextInt();
			x = new long[p];
			y = new long[p];
			z = new long[p];

			for (int i = 0; i < p; i++) {
				String name = sc.next();
				map.put(name, i);

				x[i] = sc.nextLong();
				y[i] = sc.nextLong();
				z[i] = sc.nextLong();
			}

			dist = new double[p][p];
			for (double[] row : dist)
				Arrays.fill(row, 1 << 30);
			for (int i = 0; i < p; i++) {
				for (int j = i; j < p; j++) {
					dist[i][j] = dist[j][i] = dist(i, j);
				}
			}

			int w = sc.nextInt();
			for (int i = 0; i < w; i++) {
				int a = map.get(sc.next());
				int b = map.get(sc.next());
				dist[a][b] = 0; // one direction
			}

			// Floyd-Warshall
			for (int k = 0; k < p; k++) {
				for (int i = 0; i < p; i++) {
					for (int j = 0; j < p; j++) {
						double alt = dist[i][k] + dist[k][j];
						if (dist[i][j] > alt)
							dist[i][j] = alt;
					}
				}
			}

			System.out.printf("Case %d:\n", t);
			int q = sc.nextInt();
			for (int i = 0; i < q; i++) {
				String aname, bname;
				int a = map.get(aname = sc.next());
				int b = map.get(bname = sc.next());
				System.out.printf("The distance from %s to %s is %d parsecs.\n", aname, bname, Math.round(dist[a][b]));
			}
		}

		sc.close();
	}

	static long sq(long x) {
		return x * x;
	}

	static double dist(int i, int j) {
		return Math.sqrt(sq(x[i] - x[j]) + sq(y[i] - y[j]) + sq(z[i] - z[j]));
	}

}
