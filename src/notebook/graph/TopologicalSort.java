package notebook.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

	public static Collection<Node> sortBFS(Graph g) {
		List<Node> sorted = new ArrayList<>(g.size());
		Queue<Node> queue = new LinkedList<>();
		HashMap<Node, Integer> in = new HashMap<>();
		for (Node n : g.nodes()) {
			in.put(n, n.in.size());
			if (n.in.size() == 0)
				queue.offer(n);
		}
		while (!queue.isEmpty()) {
			Node from = queue.poll();
			sorted.add(from);
			for (Node to : from.out) {
				int inCount = in.get(to) - 1;
				if (inCount == 0)
					queue.offer(to);
				else
					in.put(to, inCount);
			}
		}
		return sorted;
	}

	public static Collection<Node> sortDFS(Graph g) {
		Deque<Node> deque = new LinkedList<>();
		HashSet<Node> stacked = new LinkedHashSet<>();
		HashSet<Node> visited = new HashSet<>();
		for (Node n : g.nodes()) {
			if (!visit(deque, stacked, visited, n)) {
				System.err.println("cycle: " + deque);
				return deque;
			}
		}
		return deque;
	}

	/**
	 * @return true if there is no cycle, false otherwise
	 */
	static boolean visit(Deque<Node> deque, HashSet<Node> stacked, HashSet<Node> visited, Node node) {
		if (stacked.contains(node)) {
			deque.clear();
			deque.addAll(stacked);
			while (!deque.peek().equals(node))
				deque.remove();
			return false;
		}
		if (visited.contains(node))
			return true;
		stacked.add(node);
		visited.add(node);
		for (Node out : node.out)
			if (!visit(deque, stacked, visited, out))
				return false;
		deque.addFirst(node);
		stacked.remove(node);
		return true;
	}

	/**
	 * <a href="http://www.geeksforgeeks.org/topological-sorting/">Link to the example</a>
	 */
	public static void main(String[] args) {
		Graph g = new Graph();
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(3, 1);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(5, 0);
		g.addEdge(5, 2);
		System.out.println("BFS result: " + sortBFS(g));
		System.out.println("DFS result: " + sortDFS(g));
	}

}
