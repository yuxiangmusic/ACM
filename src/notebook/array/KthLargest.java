package notebook.array;

import util.ArrayUtil;

public class KthLargest {

	public static int kthLargest(int[] arr, int k) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int p = ArrayUtil.partition(arr, l, h);
			if (arr.length - p == k)
				return arr[p];
			else if (arr.length - p > k)
				l = p + 1;
			else
				h = p - 1;
		}
		return ~l;
	}

}
