package notebook.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxFlowSolverTest {
	/**
	 * Example from: <br>
	 * https://en.wikipedia.org/wiki/Maximum_flow_problem
	 */
	@Test
	public void test_wiki() {
		MaxFlowSolver<Character> maxFlow = new MaxFlowSolver<>();
		maxFlow.addEdge('s', 'o', 3);
		maxFlow.addEdge('s', 'p', 3);
		maxFlow.addEdge('o', 'p', 2);
		maxFlow.addEdge('o', 'q', 3);
		maxFlow.addEdge('p', 'r', 2);
		maxFlow.addEdge('q', 'r', 4);
		maxFlow.addEdge('q', 't', 2);
		maxFlow.addEdge('r', 't', 3);
		assertEquals(5, maxFlow.runFordFulkerson('s', 't'));
	}

	/**
	 * Example from: <br>
	 * http://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/
	 */
	@Test
	public void test_geeks() {
		MaxFlowSolver<Integer> maxFlow = new MaxFlowSolver<>();
		maxFlow.addEdge(0, 1, 16);
		maxFlow.addEdge(0, 2, 13);
		maxFlow.addEdge(1, 2, 10);
		maxFlow.addEdge(1, 3, 12);
		maxFlow.addEdge(2, 1, 4);
		maxFlow.addEdge(2, 4, 14);
		maxFlow.addEdge(3, 2, 9);
		maxFlow.addEdge(3, 5, 20);
		maxFlow.addEdge(4, 3, 7);
		maxFlow.addEdge(4, 5, 4);
		assertEquals(23, maxFlow.runFordFulkerson(0, 5));
	}

}
