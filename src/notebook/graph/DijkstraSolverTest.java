package notebook.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DijkstraSolverTest {

	static <V> void connect(DijkstraSolver<V> dijkstra, V a, V b, int cost) {
		dijkstra.addEdge(a, b, cost);
		dijkstra.addEdge(b, a, cost);
	}

	/**
	 * Example from: <br>
	 * https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 */
	@Test
	public void test() {
		DijkstraSolver<Integer> dijkstra = new DijkstraSolver<>();
		connect(dijkstra, 1, 2, 7);
		connect(dijkstra, 1, 3, 9);
		connect(dijkstra, 1, 6, 14);
		connect(dijkstra, 2, 3, 10);
		connect(dijkstra, 2, 4, 15);
		connect(dijkstra, 3, 4, 11);
		connect(dijkstra, 3, 6, 2);
		connect(dijkstra, 4, 5, 6);
		connect(dijkstra, 5, 6, 9);
		long dist = dijkstra.runDijkstra(1, 5);
		assertEquals(20, dist);
	}

}
