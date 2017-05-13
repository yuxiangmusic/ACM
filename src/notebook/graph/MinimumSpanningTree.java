package notebook.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.junit.Test;

import notebook.Notebook;

public class MinimumSpanningTree {

	static final int INFINITY = Integer.MAX_VALUE;

	static void connect(int[][] graph, int i, int j, int cost) {
		graph[i][j] = graph[j][i] = cost;
	}

	/**
	 * Example from: <br>
	 * http://www.geeksforgeeks.org/greedy-algorithms-set-2-kruskals-minimum-spanning-tree-mst/
	 */
	@Test
	public void test_geeks() {
		int n = 9;
		int[][] graph = new int[n][n];
		for (int[] row : graph)
			Arrays.fill(row, INFINITY);
		connect(graph, 0, 1, 4);
		connect(graph, 0, 7, 8);
		connect(graph, 1, 2, 8);
		connect(graph, 1, 7, 11);
		connect(graph, 2, 3, 7);
		connect(graph, 2, 5, 4);
		connect(graph, 2, 8, 2);
		connect(graph, 3, 4, 9);
		connect(graph, 3, 5, 14);
		connect(graph, 4, 5, 10);
		connect(graph, 5, 6, 2);
		connect(graph, 6, 7, 1);
		connect(graph, 6, 8, 6);
		connect(graph, 7, 8, 7);
		int[][] edges = runKruskal(graph);
		int sum = 0;
		for (int[] edge : edges) {
			System.out.println(Arrays.toString(edge));
			sum += graph[edge[0]][edge[1]];
		}
		assertEquals(37, sum);
	}

	/**
	 * @param graph
	 *            - undirected graph
	 * @return minimum spanning tree
	 */
	@Notebook
	public static int[][] runKruskal(int[][] graph) {
		int n = graph.length;

		List<int[]> edges = new ArrayList<>();
		// get all edges
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				edges.add(new int[] { i, j });

		// sort edges
		edges.sort((a, b) -> graph[a[0]][a[1]] - graph[b[0]][b[1]]);

		// union find
		int[] parent = new int[n];
		for (int i = 0; i < n; i++)
			parent[i] = i;

		Function<Integer, Integer> find = i -> {
			while (i != parent[i])
				i = parent[i];
			return i;
		};

		int ans[][] = new int[n - 1][], size = 0;
		for (int[] edge : edges) {
			int x = edge[0];
			int y = edge[1];
			int xset = find.apply(x);
			int yset = find.apply(y);
			if (xset != yset) {
				// union (unbalanced)
				parent[xset] = yset;
				ans[size++] = edge;
			}
		}
		return ans;
	}

}
