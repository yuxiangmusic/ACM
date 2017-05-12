package notebook.array;

import java.util.Random;

import notebook.Notebook;

public class ArrayUtil {

	static final Random rand = new Random();

	public static int[] getRandomArray(int size) {
		return getRandomArray(0, size, size);
	}

	public static int[] getRandomArray(int begin, int end, int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = begin + rand.nextInt(end - begin);
		return arr;
	}

	public static int[] getRandomArrayNoDup(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i;
		shuffle(arr);
		return arr;
	}

	public static int[] getSortedArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = i;
		return arr;
	}

	/**
	 * The i'th smallest, where i is 0 based
	 */
	@Notebook
	public static int ith(int[] arr, int i) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int p = partition(arr, l, h);
			if (p == i)
				return arr[p];
			else if (p < i)
				l = p + 1;
			else
				h = p - 1;
		}
		return -1;
	}

	@Notebook
	public static int kthLargest(int[] arr, int k) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int p = partition(arr, l, h);
			int rank = arr.length - p;
			if (rank == k)
				return arr[p];
			else if (rank > k)
				l = p + 1;
			else
				h = p - 1;
		}
		return -1;
	}

	@Notebook
	public static int partition(int[] arr, int l, int h) {
		for (int i = l; i < h; i++)
			if (arr[i] < arr[h])
				swap(arr, i, l++);
		swap(arr, l, h);
		return l;
	}

	public static void reverse(int[] arr) {
		reverse(arr, 0, arr.length - 1);
	}

	public static void reverse(int[] arr, int l, int h) {
		while (l < h) {
			arr[l] ^= arr[h];
			arr[h] ^= arr[l];
			arr[l] ^= arr[h];
			l++;
			h--;
		}
	}

	public static void shuffle(int[] arr) {
		for (int i = arr.length; i > 0; i--)
			swap(arr, i - 1, rand.nextInt(i));
	}

	public static void swap(int[] arr, int i, int j) {
		if ((i ^ j) > 0) {
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}
	}

}
