package notebook.tree;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class SegmentTreeTest {

	static final int[] arr = { 1, 3, 5, 7, 9, 11 };
	static final int n = 1000;
	static final Random rand = new Random();

	static long sumBruteForce(int begin, int end) {
		long sum = 0;
		for (int i = begin; i < end; i++)
			sum += arr[i];
		return sum;
	}

	@Test
	public void testSum() {
		SegmentTree tree = new SegmentTree(arr);

		for (int i = 0; i < n; i++) {
			int begin = rand.nextInt(arr.length), end = rand.nextInt(arr.length);
			assertEquals(sumBruteForce(begin, end), tree.sum(begin, end));
		}
	}

	@Test
	public void testUpdate() {
		SegmentTree tree = new SegmentTree(arr);

		for (int i = 0; i < n; i++) {
			int index = rand.nextInt(arr.length), val = rand.nextInt(10);
			tree.update(index, val);
			arr[index] = val;
			int begin = rand.nextInt(arr.length), end = rand.nextInt(arr.length);
			assertEquals("sum [" + begin + ", " + end + ")", sumBruteForce(begin, end), tree.sum(begin, end));
		}
	}

	@Test
	public void test() {
		SegmentTree st = new SegmentTree(1, 3, 5, 7, 9, 11);
		System.out.println(st);
	}

}
