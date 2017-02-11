import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
https://ncpc16.kattis.com/problems/artwork
=====
4 6 5
2 2 2 6
1 3 4 3
2 5 3 5
4 6 4 6
1 6 4 6

 */
public class Artwork {

	public static void assertArrayEquals(int[] expecteds, int[] actuals) {
		if (expecteds.length != actuals.length)
			throw new AssertionError(actuals);
		for (int i = 0; i < expecteds.length; i++)
			if (expecteds[i] != actuals[i])
				throw new AssertionError(Arrays.toString(actuals));
	}

	public static void test() {
		int n = 4, m = 6,
				input[][] = { //
						{ 2, 2, 2, 6 }, //
						{ 1, 3, 4, 3 }, //
						{ 2, 5, 3, 5 }, //
						{ 4, 6, 4, 6 }, //
						{ 1, 6, 4, 6 }, //
				};
		assertArrayEquals(new int[] { 1, 3, 3, 4, 3 }, solve(n, m, input));
	}

	public static void main(String[] args) {
		test();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt(), q = sc.nextInt();
		int[][] input = new int[q][];
		for (int i = 0; i < q; i++)
			input[i] = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
		for (int i : solve(n, m, input))
			System.out.println(i);
		sc.close();
	}

	static int count;

	static int grid[][], height[][], parent[][][];

	public static int[] solve(int n, int m, int[][] input) {
		grid = new int[n][m];
		height = new int[n][m];
		parent = new int[n][m][];

		// initialize strokeList
		List<List<int[]>> strokeList = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			List<int[]> stroke = new ArrayList<>();
			int x1 = input[i][0], y1 = input[i][1], x2 = input[i][2], y2 = input[i][3];
			if (x1 == x2) {
				int stepy = y2 == y1 ? 1 : (y2 - y1) / Math.abs(y2 - y1);
				for (int y = y1; y <= y2; y += stepy) {
					int r = x1 - 1, c = y - 1;
					if (grid[r][c] == 0) {
						grid[r][c] = 1;
						stroke.add(new int[] { r, c });
					}
				}
			} else {
				int stepx = x2 == x1 ? 1 : (x2 - x1) / Math.abs(x2 - x1);
				for (int x = x1; x <= x2; x += stepx) {
					int r = x - 1, c = y1 - 1;
					if (grid[r][c] == 0) {
						grid[r][c] = 1;
						stroke.add(new int[] { r, c });
					}
				}
			}
			strokeList.add(stroke);
		} // end of strokeList initialization

		count = 0;
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (grid[r][c] == 0) {
					count++;
					unionNeighbors(new int[] { r, c });
				}
			}
		}
		int sol[] = new int[input.length];
		sol[sol.length - 1] = count;
		for (int i = strokeList.size() - 1; i > 0; i--) {
			List<int[]> stroke = strokeList.get(i);
			for (int[] pair : stroke) {
				count++;
				int r = pair[0], c = pair[1];
				grid[r][c] = 0;
				unionNeighbors(pair);
			}
			sol[i - 1] = count;
		}
		return sol;
	}

	static int[] find(int[] pair) {
		int r = pair[0], c = pair[1];
		return parent[r][c] == null ? new int[] { r, c } : find(parent[r][c]);
	}

	static void union(int[] a, int[] b) {
		if (height[a[0]][a[1]] < height[b[0]][b[1]]) {
			height[b[0]][b[1]]++;
			parent[a[0]][a[1]] = b;
		} else {
			height[a[0]][a[1]]++;
			parent[b[0]][b[1]] = a;
		}
	}

	static void unionNeighbors(int[] pair) {
		int r = pair[0], c = pair[1];
		int[][] neighbors = { { r + 1, c }, { r - 1, c }, { r, c + 1 }, { r, c - 1 } };
		for (int[] n : neighbors) {
			if (n[0] >= 0 && n[0] < grid.length && n[1] >= 0 && n[1] < grid[0].length && grid[n[0]][n[1]] == 0) {
				int[] a = find(pair), b = find(n);
				if (!Arrays.equals(a, b)) {
					union(a, b);
					count--;
				}
			}
		}
	}

}