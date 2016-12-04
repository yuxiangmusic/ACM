import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
=====
2
0 0 100
0 300 200
2
0 0 100
0 300 100
16
10 10 5
20 10 5
30 10 5
40 10 5
10 20 5 
20 20 5 
30 20 5 
40 20 5 
10 30 5 
20 30 5 
30 30 5 
40 30 5 
10 40 5 
20 40 5 
30 40 5 
40 40 5 
3
0 0 1
0 3 2
4 0 3

 */

/**
 * <a href="https://icpcarchive.ecs.baylor.edu/external/73/p7379.pdf">
 * 
 * Gears</a>
 */
public class Gears {

	static Gear[] gears;
	static HashSet<Gear> visited = new HashSet<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			int n = sc.nextInt();
			gears = new Gear[n];

			visited.clear();

			for (int i = 0; i < n; i++) {
				gears[i] = new Gear(sc.nextInt(), sc.nextInt(), sc.nextInt());

				for (int j = 0; j < i; j++) {
					if (gears[i].isConnected(gears[j])) {
						gears[i].neighbors.add(gears[j]);
						gears[j].neighbors.add(gears[i]);
					}
				}
			}

			// start rotating
			gears[0].setSpeed(new Frac(1, 1));
			gears[0].dir = 1;
			rotate(gears[0]);

			// check for broken
			boolean broken = false;
			for (Gear g : gears) {
				if (g.broken) {
					broken = true;
					break;
				}
			}

			if (broken) {
				System.out.println("-1");
			} else if (gears[n - 1].speed == null) {
				System.out.println("0");
			} else {
				Frac speed = gears[n - 1].speed;
				if (gears[n - 1].dir < 0)
					speed = mul(speed, -1);
				String ratio = speed.toString().replaceAll("/", " ");
				System.out.println(ratio);
			}
		} // end of case
		sc.close();
	}

	static boolean rotate(Gear g) {
		if (visited.contains(g))
			return true;
		else
			visited.add(g);

		if (g.broken)
			return false;

		for (Gear n : g.neighbors) {
			Frac s = div(g.speed, g.r);
			s = mul(s, n.r);

			int ndir = -g.dir;

			// bad
			if (!n.unset && !s.equals(n.speed)) {
				n.broken = true;
			}
			if (n.dir != 0 && n.dir != ndir) {
				n.broken = true;
			}

			// good
			if (!n.unset) {
			}
			n.setSpeed(s);
			n.dir = ndir;

			rotate(n);
		}
		return true;
	}

	static class Gear {
		final int x, y, r;

		ArrayList<Gear> neighbors = new ArrayList<>();

		int dir = 0;

		boolean broken = false;
		boolean unset = true;
		Frac speed;

		void setSpeed(Frac speed) {
			this.speed = speed;
			unset = false;
		}

		Gear(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}

		boolean isConnected(Gear g) {
			int dx = x - g.x;
			int dy = y - g.y;

			return (dx * dx + dy * dy == (r + g.r) * (r + g.r));
		}

		@Override
		public String toString() {
			return String.format("[%d, %d, %d]", x, y, r);
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Gear) {
				Gear g = (Gear) o;
				return g.x == x && g.y == y && g.r == r;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return x * 31 * 31 + y * 31 + r;
		}
	}

	static long gcd(long a, long b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	static Frac add(Frac x, Frac y) {
		long top = x.top * y.bot + x.bot * y.top;
		long bot = x.bot * y.bot;
		return new Frac(top, bot);
	}

	static Frac mul(Frac x, long mul) {
		return new Frac(x.top * mul, x.bot);
	}

	static Frac div(Frac x, long div) {
		return new Frac(x.top, x.bot * div);
	}

	static class Frac {
		final long top, bot;

		Frac(long top, long bot) {
			long gcd = gcd(top, bot);
			top /= gcd;
			bot /= gcd;

			this.top = top;
			this.bot = bot;
		}

		@Override
		public String toString() {
			if (top < 0)
				return (-top) + "/" + (-bot);
			else
				return top + "/" + bot;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Frac))
				return false;
			Frac f = (Frac) o;
			return top == f.top && bot == f.bot;
		}

		@Override
		public int hashCode() {
			return (int) (top * 31 + bot);
		}
	}
}
