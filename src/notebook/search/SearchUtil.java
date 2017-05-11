package notebook.search;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.Notebook;

public class SearchUtil {

	@Test
	public void test() {
		int[] arr = new int[] { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };

		assertEquals(0, binarySearchBiasL(arr, 1));
		assertEquals(0, binarySearchBiasR(arr, 1));

		assertEquals(1, binarySearchBiasL(arr, 2));
		assertEquals(2, binarySearchBiasR(arr, 2));

		assertEquals(3, binarySearchBiasL(arr, 3));
		assertEquals(5, binarySearchBiasR(arr, 3));

		assertEquals(6, binarySearchBiasL(arr, 4));
		assertEquals(9, binarySearchBiasR(arr, 4));

		assertEquals(10, binarySearchBiasL(arr, 5));
		assertEquals(14, binarySearchBiasR(arr, 5));
	}

	@Notebook
	public static int binarySearchBiasL(int[] arr, int key) {
		int l = -1, h = arr.length - 1;
		while (l < h - 1) {
			int m = (l + h) >>> 1;
			if (arr[m] >= key)
				h = m;
			else
				l = m;
		}
		return h;

	}

	@Notebook
	public static int binarySearchBiasR(int[] arr, int key) {
		int l = 0, h = arr.length;
		while (l < h - 1) {
			int m = (l + h) >>> 1;
			if (arr[m] <= key)
				l = m;
			else
				h = m;
		}
		return l;
	}

	/**
	 * public version
	 */
	public static int binarySearch(int[] arr, int key) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int m = (l + h) >>> 1;
			if (key == arr[m])
				return m;
			else if (key < arr[m])
				h = m - 1;
			else
				l = m + 1;
		}
		return ~l;
	}

	public static int binarySearchRec(int[] arr, int key) {
		return binarySearchRec(arr, key, 0, arr.length - 1);
	}

	private static int binarySearchRec(int[] arr, int key, int l, int h) {
		if (l > h)
			return ~l;
		int m = (l + h) >>> 1;
		if (key == arr[m])
			return m;
		else if (key < arr[m])
			return binarySearchRec(arr, key, l, m - 1);
		else
			return binarySearchRec(arr, key, m + 1, h);
	}
}