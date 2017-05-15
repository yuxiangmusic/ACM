package notebook.graph;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import notebook.Notebook;

public class BipartiteMatching {

	/**
	 * Example from: <br>
	 * https://www.hackerrank.com/topics/bipartite-matching
	 */
	@Test
	public void test() {
		int m = 5, n = 4;
		boolean[][] graph = new boolean[m][n];
		graph[0][0] = true;
		graph[1][0] = true;
		graph[2][1] = true;
		graph[3][2] = true;
		graph[4][2] = true;
		graph[4][3] = true;
		assertEquals(4, matchDFS(graph));
	}

	/**
	 * @param graph
	 *            - graph[i][j] := source i to sink j
	 * @return max number of matchings
	 */
	@Notebook
	public static int matchDFS(boolean[][] graph) {
		int m = graph.length; // source
		int n = graph[0].length; // sink
		int[] back = new int[n]; // back edges
		Arrays.fill(back, -1);

		int match = 0;
		for (int s = 0; s < m; s++) {
			if (matchDFS(graph, new boolean[n], back, s)) {
				match++;
			}
		}
		return match;
	}

	@Notebook
	private static boolean matchDFS(boolean[][] graph, boolean[] visited, int[] back, int s) {
		int n = graph[0].length; // sink
		for (int t = 0; t < n; t++) {
			if (graph[s][t] && !visited[t]) {
				visited[t] = true;
				// s can match t, or back[t] can match something else
				if (back[t] < 0 || matchDFS(graph, visited, back, back[t])) {
					back[t] = s;
					return true;
				}
			}
		}
		return false;
	}

}
