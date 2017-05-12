package notebook.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import notebook.Notebook;

public class TopologicalSort {
	/**
	 * Topologically sorts n nodes labeled from 0 to (n - 1)
	 */
	@Notebook
	public static int[] sortBFS(int n, int[][] edges) {
		// adjacent list
		List<List<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		// in-degrees
		int[] in = new int[n];
		for (int[] edge : edges) {
			in[edge[1]]++;
			adj.get(edge[0]).add(edge[1]);
		}

		int ans[] = new int[adj.size()], size = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < adj.size(); i++)
			if (in[i] == 0)
				q.offer(i);
		while (!q.isEmpty()) {
			int from = q.poll();
			ans[size++] = from;
			for (int to : adj.get(from))
				if (--in[to] == 0)
					q.offer(to);
		}
		return size == adj.size() ? ans : new int[0];
	}

	/**
	 * Topologically sorts n nodes labeled from 0 to (n - 1)
	 */
	@Notebook
	public static int[] sortDFS(int n, int[][] edges) {
		// adjacent list
		List<List<Integer>> adj = new ArrayList<>(n);
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<>());
		for (int[] edge : edges)
			adj.get(edge[0]).add(edge[1]);

		Stack<Integer> stack = new Stack<>();
		boolean[] stacked = new boolean[adj.size()];
		boolean[] visited = new boolean[adj.size()];
		for (int i = 0; i < adj.size(); i++)
			if (!sortDFS(stack, stacked, visited, i, adj))
				return new int[0];

		int ans[] = new int[n];
		for (int i = 0; i < ans.length; i++)
			ans[i] = stack.pop();
		return ans;
	}

	@Notebook
	private static boolean sortDFS(Stack<Integer> stack, boolean[] stacked, boolean[] visited, int v,
			List<List<Integer>> adj) {
		if (stacked[v]) // check stacked 1st
			return false;
		if (visited[v]) // check visited 2nd
			return true;
		// start visiting
		stacked[v] = true;
		visited[v] = true;
		for (int to : adj.get(v))
			if (!sortDFS(stack, stacked, visited, to, adj))
				return false;
		stack.push(v);
		stacked[v] = false;
		return true;
	}
}
