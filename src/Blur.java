import java.util.HashSet;
import java.util.Scanner;

/*
=====
5 4 1
0 0 1 1 0
0 0 1 1 0
0 0 1 1 0
0 0 1 1 0
3 3 2
1 0 0
0 1 0
0 1 0

 */

/**
 * <a href="http://www.acmicpc-pacnw.org/ProblemSet/2015/Statements/div2.pdf">
 * 
 * Problem U - Blur</a>
 */
// XXX not yet judged
public class Blur {
	static int w, h, b;
	static Frac grid[][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			w = sc.nextInt();
			h = sc.nextInt();
			b = sc.nextInt();
			grid = new Frac[h][w];
			for (int r = 0; r < h; r++)
				for (int c = 0; c < w; c++)
					grid[r][c] = new Frac(sc.nextLong(), 1);
			for (int i = 0; i < b; i++)
				grid = blur(grid);
			HashSet<Frac> set = new HashSet<>();
			for (Frac[] row : grid)
				for (Frac f : row)
					set.add(f);
			System.out.println(set.size());
		} // end of case
		sc.close();
	}

	static Frac[][] blur(Frac[][] grid) {
		Frac[][] blur = new Frac[h][w];
		for (int r = 0; r < h; r++)
			for (int c = 0; c < w; c++) {
				// blur (r, c)
				Frac total = new Frac(0, 1);
				for (int i = r - 1; i <= r + 1; i++) {
					for (int j = c - 1; j <= c + 1; j++) {
						int ii = i, jj = j;
						if (i == -1)
							ii = h - 1;
						else if (i == h)
							ii = 0;
						if (j == -1)
							jj = w - 1;
						else if (j == w)
							jj = 0;
						total = add(total, grid[ii][jj]);
					}
				}
				Frac avg = div(total, 9);
				blur[r][c] = avg;
			}
		return blur;
	}

	static long gcd(long a, long b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	static Frac add(Frac x, Frac y) {
		long top = x.top * y.bot + x.bot * y.top;
		long bot = x.bot * y.bot;
		return new Frac(top, bot);
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
