package notebook.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import notebook.Notebook;

@Notebook
public class MaxFlowSolver<V> {

	private final Map<V, Map<V, Integer>> capMap = new HashMap<>();

	public void addEdge(V from, V to, int cap) {
		get(from).put(to, cap);
	}

	private boolean bfs(V s, V t, Map<V, V> parent) {
		Queue<V> q = new LinkedList<>();
		q.offer(s);

		Set<V> visited = new HashSet<>();

		while (!q.isEmpty()) {
			V v = q.poll();
			visited.add(v);
			if (v.equals(t))
				break;
			for (Map.Entry<V, Integer> entry : get(v).entrySet()) {
				V w = entry.getKey();
				if (entry.getValue() > 0 && !visited.contains(w)) {
					q.offer(w);
					parent.put(w, v);
				}
			}
		}
		return visited.contains(t);
	}

	private Map<V, Integer> get(V from) {
		Map<V, Integer> map = capMap.get(from);
		if (map == null)
			capMap.put(from, map = new HashMap<>());
		return map;
	}

	private int getCap(V from, V to) {
		return get(from).getOrDefault(to, 0);
	}

	public int runFordFulkerson(V s, V t) {
		int maxFlow = 0;
		Map<V, V> parent = new HashMap<>();
		while (bfs(s, t, parent)) {
			V p;
			int min = Integer.MAX_VALUE;
			for (V v = t; !v.equals(s); v = p) {
				p = parent.get(v);
				min = Math.min(min, getCap(p, v));
			}
			for (V v = t; !v.equals(s); v = p) {
				p = parent.get(v);
				setCap(p, v, getCap(p, v) - min);
				setCap(v, p, getCap(v, p) + min);
			}
			maxFlow += min;
		}
		return maxFlow;
	}

	private void setCap(V from, V to, int cap) {
		get(from).put(to, cap);
	}
}
