package notebook.queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import notebook.Notebook;

/**
 * Data structure that keeps track of median
 */
@Notebook
public class MedianQueue {

	Queue<Long> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	Queue<Long> minHeap = new PriorityQueue<>();

	public void add(long n) {
		maxHeap.add(n);
		minHeap.add(maxHeap.poll());
	}

	public double median() {
		while (maxHeap.size() - minHeap.size() >= 2)
			minHeap.offer(maxHeap.poll());
		while (minHeap.size() - maxHeap.size() >= 1)
			maxHeap.offer(minHeap.poll());
		return maxHeap.size() == minHeap.size() ? (maxHeap.peek() + minHeap.peek()) / 2.0 : maxHeap.peek();
	}

	public boolean remove(long n) {
		return maxHeap.remove(n) || minHeap.remove(n);
	}

}
