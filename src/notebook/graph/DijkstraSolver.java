package notebook.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import notebook.Notebook;

@Notebook
public class DijkstraSolver<V> {

	private static final Integer INFINITY = Integer.MAX_VALUE;

	private final Map<V, Map<V, Integer>> costMap = new HashMap<>();

	private final Map<V, Integer> distMap = new HashMap<>();

	public void addEdge(V from, V to, int cost) {
		get(from).put(to, cost);
	}

	public int runDijkstra(V s, V t) {
		distMap.clear();
		distMap.put(s, 0);

		Queue<V> pq = new PriorityQueue<>((p, q) -> Integer.compare(dist(p), dist(q)));
		pq.offer(s);

		while (!pq.isEmpty()) {
			V cur = pq.poll();
			int curDist = dist(cur);
			if (cur.equals(t))
				return curDist;

			for (Map.Entry<V, Integer> entry : costMap.get(cur).entrySet()) {
				V neighbor = entry.getKey();
				int neighborCost = entry.getValue();
				int neighborDist = dist(neighbor);
				int altDist = curDist + neighborCost;
				if (altDist < neighborDist) {
					distMap.put(neighbor, altDist);
					pq.offer(neighbor);
				}
			}
		}
		return INFINITY;
	}

	private int dist(V v) {
		return distMap.getOrDefault(v, INFINITY);
	}

	private Map<V, Integer> get(V from) {
		Map<V, Integer> map = costMap.get(from);
		if (map == null)
			costMap.put(from, map = new HashMap<>());
		return map;
	}

}
