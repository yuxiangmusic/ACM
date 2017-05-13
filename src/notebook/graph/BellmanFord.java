package notebook.graph;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

import notebook.Notebook;

public class BellmanFord {

	static final int INFINITY = Integer.MAX_VALUE;

	static void connect(int[][] graph, int i, int j, int cost) {
		graph[i][j] = graph[j][i] = cost;
	}

	@Notebook
	public static int[] runBellmanFord(int[][] graph, int s) {
		int[] dist = new int[graph.length];
		Arrays.fill(dist, INFINITY);
		dist[s] = 0;
		int n = graph.length;
		boolean updated = false;
		for (int step = 0; step < n; step++, updated = false) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					// for each edge (more efficient if we have a list of edges)
					if (dist[i] != INFINITY && graph[i][j] != INFINITY) {
						int alt = dist[i] + graph[i][j];
						if (alt < dist[j]) {
							if (step == n - 1)
								throw new RuntimeException("negative cycle");
							dist[j] = alt;
							updated = true;
						}
					}
				}
			}
			if (!updated)
				break;
		}
		return dist;
	}

	/**
	 * Example from: <br>
	 * http://www.geeksforgeeks.org/dynamic-programming-set-23-bellman-ford-algorithm/
	 */
	@Test
	public void test() {
		int n = 5;
		int[][] graph = new int[n][n];
		for (int[] row : graph)
			Arrays.fill(row, INFINITY);
		graph[0][1] = -1;
		graph[0][2] = 4;
		graph[1][2] = 3;
		graph[1][3] = 2;
		graph[1][4] = 2;
		graph[3][1] = 1;
		graph[3][2] = 5;
		graph[4][3] = -3;
		assertArrayEquals(new int[] { 0, -1, 2, -2, 1 }, runBellmanFord(graph, 0));
	}

}
