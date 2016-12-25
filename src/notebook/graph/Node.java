package notebook.graph;

import java.util.ArrayList;

public final class Node {
	int data;

	Node(int data) {
		this.data = data;
	}

	ArrayList<Node> out = new ArrayList<>();
	ArrayList<Node> in = new ArrayList<>();

	@Override
	public String toString() {
		return String.valueOf(data);
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Node) {
			Node n = (Node) o;
			return n.data == data;
		}
		return false;
	}
}