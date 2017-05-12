package notebook.graph;

import notebook.Notebook;

public class FloydWarshall {

	static final int INFINITY = Integer.MAX_VALUE;

	@Notebook
	public static int[][] runFloydWarshall(int[][] graph) {
		int n = graph.length;
		int[][] dist = new int[n][n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				dist[i][j] = graph[i][j];

		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

		return dist;
	}

}
