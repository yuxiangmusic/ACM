package notebook.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class KthLargestElementInArray {

	@Test
	public void test_kth() {
		int[] arr = { 5, 3, 1, 2, 4 };
		assertEquals(5, findKthLargest(arr, 1));
		assertEquals(4, findKthLargest(arr, 2));
		assertEquals(3, findKthLargest(arr, 3));
		assertEquals(2, findKthLargest(arr, 4));
		assertEquals(1, findKthLargest(arr, 5));
	}

	public int findKthLargest(int[] arr, int k) {
		return ArrayUtil.kthLargest(arr, k);
	}

	public int findKthLargest_sort(int[] arr, int k) {
		Arrays.sort(arr);
		return arr[arr.length - k];
	}

}
