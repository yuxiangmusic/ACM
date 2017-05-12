package notebook.graph;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

import notebook.Notebook;

public class Dijkstra {

	static final int INFINITY = Integer.MAX_VALUE;

	static void connect(int[][] graph, int i, int j, int cost) {
		graph[i][j] = graph[j][i] = cost;
	}

	@Notebook
	public static int runDijkstra(int[][] graph, int s, int t) {
		int[] dist = new int[graph.length];
		Arrays.fill(dist, INFINITY);
		dist[s] = 0;

		Queue<Integer> pq = new PriorityQueue<>((p, q) -> dist[p] - dist[q]);
		pq.offer(s);
		while (!pq.isEmpty()) {
			int p = pq.poll();
			if (p == t)
				return dist[p];
			// for each neighbor of p
			for (int q = 0; q < graph.length; q++) {
				if (graph[p][q] != INFINITY) {
					int alt = dist[p] + graph[p][q];
					if (alt < dist[q]) {
						dist[q] = alt;
						pq.offer(q);
					}
				}
			}
		}
		return INFINITY;
	}

	/**
	 * Example from: <br>
	 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 */
	@Test
	public void test() {
		int n = 7;
		int[][] graph = new int[n][n];
		for (int[] row : graph)
			Arrays.fill(row, INFINITY);
		connect(graph, 1, 2, 7);
		connect(graph, 1, 3, 9);
		connect(graph, 1, 6, 14);
		connect(graph, 2, 3, 10);
		connect(graph, 2, 4, 15);
		connect(graph, 3, 4, 11);
		connect(graph, 3, 6, 2);
		connect(graph, 4, 5, 6);
		connect(graph, 5, 6, 9);
		assertEquals(20, runDijkstra(graph, 1, 5));
	}

}
