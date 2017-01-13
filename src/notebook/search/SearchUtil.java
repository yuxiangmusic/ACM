package notebook.search;

public class SearchUtil {

	public static void main(String[] args) {
		int[] arr = new int[] { 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5 };

		for (int i = 1; i <= 5; i++) {
			System.out.println(i + " index from " + binarySearchBiasL(arr, i) + " to " + binarySearchBiasR(arr, i));
		}
	}

	public static int binarySearchBiasL(int[] arr, int key) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int m = (l + h) >>> 1;
			if (key == arr[m]) {
				if (m == 0 || arr[m - 1] < arr[m])
					return m;
				else
					h = m - 1;
			} else if (key < arr[m]) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}
		return -1 - l;
	}

	public static int binarySearchBiasR(int[] arr, int key) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int m = (l + h) >>> 1;
			if (key == arr[m]) {
				if (m == arr.length - 1 || arr[m + 1] > arr[m])
					return m;
				else
					l = m + 1;
			} else if (key < arr[m]) {
				h = m - 1;
			} else {
				l = m + 1;
			}
		}
		return -1 - l;
	}

	/**
	 * public version
	 */
	public static int binarySearch(int[] arr, int key) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int m = (l + h) >>> 1; // unsigned bit shift to avoid overflow
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
			return -1 - l;
		int m = (l + h) >>> 1;
		if (key == arr[m])
			return m;
		else if (key < arr[m])
			return binarySearchRec(arr, key, l, m - 1);
		else
			return binarySearchRec(arr, key, m + 1, h);
	}
}