package test.tree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import notebook.tree.SegmentTree;

public class SegmentTreeTest {

	static final long[] arr = { 1, 3, 5, 7, 9, 11 };
	static final int n = 1000;
	static final Random rand = new Random();

	static long sumBruteForce(int from, int to) {
		long sum = 0;
		for (int i = from; i <= to; i++) {
			sum += arr[i];
		}
		return sum;
	}

	@Test
	public void testSum() {
		SegmentTree tree = new SegmentTree(arr);

		for (int i = 0; i < n; i++) {
			int from = rand.nextInt(arr.length), to = rand.nextInt(arr.length);

			assertEquals(sumBruteForce(from, to), tree.sum(from, to));
		}
	}

	@Test
	public void testUpdate() {
		SegmentTree tree = new SegmentTree(arr);

		for (int i = 0; i < n; i++) {
			tree.update(rand.nextInt(arr.length), rand.nextInt(10));

			int from = rand.nextInt(arr.length), to = rand.nextInt(arr.length);

			assertEquals("sum from " + from + " to " + to, sumBruteForce(from, to), tree.sum(from, to));
		}
	}

}
