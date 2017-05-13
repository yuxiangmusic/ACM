package notebook.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import notebook.Notebook;

@Notebook
public class TopologicalSorter<V> {
	/**
	 * Adjacent list
	 */
	private final Map<V, List<V>> adj = new HashMap<>();
	/**
	 * In-degrees, needed only by BFS
	 */
	private final Map<V, Integer> in = new HashMap<>();

	public void addEdge(V from, V to) {
		// make sure in-degree map has all vertexes stored
		if (!in.containsKey(from))
			in.put(from, 0);
		if (!in.containsKey(to))
			in.put(to, 0);

		adj(from).add(to);
		in.put(to, 1 + in.getOrDefault(to, 0));
	}

	private List<V> adj(V v) {
		List<V> list = adj.get(v);
		if (list == null)
			adj.put(v, list = new ArrayList<>());
		return list;
	}

	public List<V> sortBFS() {
		List<V> sorted = new ArrayList<>();
		Queue<V> q = new LinkedList<>();

		// create a copy to avoid modifying the original in-degree map
		Map<V, Integer> in = new HashMap<>(this.in);

		for (Map.Entry<V, Integer> entry : in.entrySet()) {
			V v = entry.getKey();
			int degree = entry.getValue();
			if (degree == 0)
				q.offer(v);
		}
		while (!q.isEmpty()) {
			V from = q.poll();
			sorted.add(from);
			for (V to : adj(from)) {
				int indegree = in.get(to) - 1;
				if (indegree == 0)
					q.offer(to);
				in.put(to, indegree);
			}
		}
		return sorted;
	}

	public List<V> sortDFS() {
		Stack<V> stack = new Stack<>();
		Set<V> stacked = new LinkedHashSet<>();
		Set<V> visited = new HashSet<>();
		for (V n : adj.keySet()) {
			if (!sortDFS(stack, stacked, visited, n)) {
				System.err.println("cycle: " + stack);
				return new ArrayList<>(stack);
			}
		}

		// get sorted list
		List<V> sorted = new ArrayList<>();
		while (!stack.isEmpty())
			sorted.add(stack.pop());
		return sorted;
	}

	/**
	 * @return true if there is no cycle, false otherwise
	 */
	private boolean sortDFS(Stack<V> stack, Set<V> stacked, Set<V> visited, V v) {
		// cycle found
		if (stacked.contains(v)) {
			stack.clear();
			stack.addAll(stacked);
			while (!stack.peek().equals(v))
				stack.pop();
			return false;
		}
		if (visited.contains(v))
			return true;

		// start visiting
		stacked.add(v);
		visited.add(v);
		for (V out : adj(v))
			if (!sortDFS(stack, stacked, visited, out))
				return false;
		stack.push(v);
		stacked.remove(v);
		return true;
	}

	/**
	 * Gets all topologically sorted lists
	 */
	public List<List<V>> sortGetAll() {
		List<List<V>> all = new ArrayList<>();
		List<V> sortedList = new LinkedList<>();
		List<V> zeroinList = new LinkedList<>();

		for (Map.Entry<V, Integer> entry : in.entrySet()) {
			V v = entry.getKey();
			int degree = entry.getValue();
			if (degree == 0)
				zeroinList.add(v);
		}
		sortGetAll(all, sortedList, zeroinList, in);
		return all;
	}

	private void sortGetAll(List<List<V>> all, List<V> sortedList, List<V> zeroinList, Map<V, Integer> in) {
		if (zeroinList.isEmpty()) {
			all.add(sortedList);
			return;
		}
		for (int i = 0; i < zeroinList.size(); i++) {
			List<V> sortedListCopy = new LinkedList<>(sortedList);
			List<V> zeroinListCopy = new LinkedList<>(zeroinList);
			Map<V, Integer> inCopy = new HashMap<>(in);

			V from = zeroinListCopy.remove(i);
			sortedListCopy.add(from);

			for (V to : adj.get(from)) {
				int indegree = inCopy.get(to) - 1;
				if (indegree == 0)
					zeroinListCopy.add(to);
				inCopy.put(to, indegree);
			}
			sortGetAll(all, sortedListCopy, zeroinListCopy, inCopy);
		}
	}

}
