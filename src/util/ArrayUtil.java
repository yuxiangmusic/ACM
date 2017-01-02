package util;

import java.util.Random;

public class ArrayUtil {

	static Random rand = new Random();

	public static int[] getRandomArrayNoDup(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i;
		shuffle(arr);
		return arr;
	}

	public static int[] getRandomArray(int size) {
		return getRandomArray(0, size, size);
	}

	public static int[] getRandomArray(int begin, int end, int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = begin + rand.nextInt(end - begin);
		return arr;
	}

	public static int[] getSortedArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++)
			arr[i] = i;
		return arr;
	}

	public static int partition(int[] arr, int l, int h) {
		for (int i = l; i < h; i++)
			if (arr[i] < arr[h])
				swap(arr, i, l++);
		swap(arr, l, h);
		return l;
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
