package notebook.graph;

import java.util.List;

public class TopologicalSorterTest {
	/**
	 * Example from: <br>
	 * http://www.geeksforgeeks.org/topological-sorting/
	 */
	public static void main(String[] args) {
		TopologicalSorter<Integer> ts = new TopologicalSorter<>();
		ts.addEdge(2, 3);
		ts.addEdge(3, 1);
		ts.addEdge(3, 1);
		ts.addEdge(4, 0);
		ts.addEdge(4, 1);
		ts.addEdge(5, 0);
		ts.addEdge(5, 2);
		System.out.println("BFS result: " + ts.sortBFS());
		System.out.println("DFS result: " + ts.sortDFS());
		System.out.println("All:");
		for (List<Integer> sortedList : ts.sortGetAll())
			System.out.println(sortedList);
	}
}
