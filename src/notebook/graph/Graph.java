package notebook.graph;

import java.util.Collection;
import java.util.HashMap;

public class Graph {

	private int size = 0;

	public int size() {
		return size;
	}

	public Collection<Node> nodes() {
		return map.values();
	}

	private HashMap<Integer, Node> map = new HashMap<>();

	Node node(int key) {
		if (map.containsKey(key))
			return map.get(key);
		Node node = new Node(key);
		map.put(key, node);
		size++;
		return node;
	}

	public void addEdge(int from, int to) {
		node(from).out.add(node(to));
		node(to).in.add(node(from));
	}

}
