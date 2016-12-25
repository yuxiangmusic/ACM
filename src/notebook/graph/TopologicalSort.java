package notebook.graph;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class TopologicalSort {

	public static Deque<Node> sort(Graph g) {
		Deque<Node> deque = new LinkedList<>();
		HashSet<Node> stacked = new LinkedHashSet<>();
		HashSet<Node> visited = new HashSet<>();
		for (Node n : g.nodes())
			if (n.in.size() == 0)
				visit(deque, stacked, visited, n);
		return deque;
	}

	static void visit(Deque<Node> deque, HashSet<Node> stacked, HashSet<Node> visited, Node node) {
		if (stacked.contains(node)) {
			deque.clear();
			deque.addAll(stacked);
			while (!deque.peek().equals(node))
				deque.remove();
			throw new RuntimeException("cycle: " + deque);
		}
		if (visited.contains(node))
			return;
		stacked.add(node);
		visited.add(node);
		for (Node out : node.out)
			visit(deque, stacked, visited, out);
		deque.addFirst(node);
		stacked.remove(node);
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addEdge(2, 3);
		g.addEdge(3, 1);
		g.addEdge(3, 1);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(5, 0);
		g.addEdge(5, 2);
		System.out.println(sort(g));
	}

}
