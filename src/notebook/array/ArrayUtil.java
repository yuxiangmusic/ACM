package notebook.array;

import java.util.Comparator;
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
	 * @return i'th smallest
	 */
	@Notebook
	public static <E> E ith(E[] arr, int i, Comparator<E> comp) {
		int l = 0, h = arr.length - 1;
		while (l <= h) {
			int p = partition(arr, l, h, comp);
			if (p == i)
				return arr[p];
			else if (p < i)
				l = p + 1;
			else
				h = p - 1;
		}
		return null;
	}

	/**
	 * @return (i - l)'th smallest in [l, h]
	 */
	@Notebook
	public static <E> E ith(E[] arr, int l, int h, int i, Comparator<E> comp) {
		if (i < l || i > h)
			throw new IllegalArgumentException();
		while (l <= h) {
			int p = partition(arr, l, h, comp);
			if (p == i)
				return arr[p];
			else if (p < i)
				l = p + 1;
			else
				h = p - 1;
		}
		return null;
	}

	/**
	 * The i'th smallest
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
		return 0;
	}

	/**
	 * @return (i - l)'th smallest in [l, h]
	 */
	@Notebook
	public static int ith(int[] arr, int l, int h, int i) {
		if (i < l || i > h)
			throw new IllegalArgumentException();
		while (l <= h) {
			int p = partition(arr, l, h);
			if (p == i)
				return arr[p];
			else if (p < i)
				l = p + 1;
			else
				h = p - 1;
		}
		return 0;
	}

	/**
	 * @return k'th largest = (length - k)'th smallest
	 */
	@Notebook
	public static int kthLargest(int[] arr, int k) {
		if (k > arr.length)
			throw new IllegalArgumentException();
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
		return 0;
	}

	public static <E> int partition(E[] arr, int l, int h, Comparator<E> comp) {
		for (int i = l; i < h; i++)
			if (comp.compare(arr[i], arr[h]) < 0)
				swap(arr, i, l++);
		swap(arr, l, h);
		return l;
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
		for (; l < h; l++, h--) {
			arr[l] ^= arr[h];
			arr[h] ^= arr[l];
			arr[l] ^= arr[h];
		}
	}

	public static void shuffle(int[] arr) {
		for (int i = arr.length; i > 0; i--)
			swap(arr, i - 1, rand.nextInt(i));
	}

	public static void shuffle(int[] arr, int l, int h) {
		for (int i = h; i > l; i--)
			swap(arr, i, l + rand.nextInt(h - l));
	}

	public static <E> void swap(E[] arr, int i, int j) {
		if (i != j) {
			E swap = arr[i];
			arr[i] = arr[j];
			arr[j] = swap;
		}
	}

	public static void swap(int[] arr, int i, int j) {
		if (i != j) {
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}
	}

}
