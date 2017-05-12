package notebook.graph;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import notebook.Notebook;

public class MaxFlow {
	/**
	 * Example from: <br>
	 * http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	 */
	@Test
	public void test_geeks() {
		int n = 6;
		int[][] graph = new int[n][n];
		graph[0][1] = 16;
		graph[0][2] = 13;
		graph[1][2] = 10;
		graph[1][3] = 12;
		graph[2][1] = 4;
		graph[2][4] = 14;
		graph[3][2] = 9;
		graph[3][5] = 20;
		graph[4][3] = 7;
		graph[4][5] = 4;
		assertEquals(23, runFordFulkerson(graph, 0, 5));
	}

	@Notebook
	public static long runFordFulkerson(int[][] graph, int s, int t) {
		int maxFlow = 0, parent[] = new int[graph.length];
		while (bfs(graph, s, t, parent)) {
			int p, min = Integer.MAX_VALUE;
			for (int v = t; v != s; v = p) {
				p = parent[v];
				min = Math.min(min, graph[p][v]);
			}
			for (int v = t; v != s; v = p) {
				p = parent[v];
				graph[p][v] -= min;
				graph[v][p] += min;
			}
			maxFlow += min;
		}
		return maxFlow;
	}

	@Notebook
	private static boolean bfs(int[][] graph, int s, int t, int[] parent) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(s);

		boolean[] visited = new boolean[graph.length];

		while (!q.isEmpty()) {
			int v = q.poll();
			visited[v] = true;
			if (v == t)
				break;
			for (int w = 0; w < graph.length; w++) {
				if (graph[v][w] > 0 && !visited[w]) {
					q.offer(w);
					parent[w] = v;
				}
			}
		}
		return visited[t];
	}

}
