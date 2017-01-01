package notebook.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Finds all possible solutions of topological sort
 */
public class AllTopological {

	public static List<List<Node>> sortBFS(Graph g) {
		List<List<Node>> all = new ArrayList<>();
		List<Node> fromList = new LinkedList<>();
		List<Node> sortList = new LinkedList<>();
		HashMap<Node, Integer> in = new HashMap<>();
		for (Node n : g.nodes()) {
			in.put(n, n.in.size());
			if (n.in.size() == 0)
				fromList.add(n);
		}
		rec(all, fromList, sortList, in);
		return all;
	}

	static void rec(List<List<Node>> all, List<Node> fromList, List<Node> sortList, HashMap<Node, Integer> in) {
		if (fromList.isEmpty())
			all.add(sortList);
		else {
			for (int i = 0; i < fromList.size(); i++) {
				List<Node> copySortList = new LinkedList<>(sortList);
				List<Node> copyFromList = new LinkedList<>(fromList);
				HashMap<Node, Integer> copyInMap = new HashMap<>(in);
				Node from = copyFromList.remove(i);
				copySortList.add(from);
				for (Node to : from.out) {
					int inCount = copyInMap.get(to) - 1;
					if (inCount == 0)
						copyFromList.add(to);
					else
						copyInMap.put(to, inCount);
				}
				rec(all, copyFromList, copySortList, copyInMap);
			}
		}
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
		for (List<Node> sort : sortBFS(g))
			System.out.println(sort);
	}

}
