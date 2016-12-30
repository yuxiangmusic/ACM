package util;

import java.util.Random;

public class ArrayUtil {

	static Random rand = new Random();

	public static int partition(int[] arr, int l, int h) {
		for (int i = l; i < h; i++)
			if (arr[i] < arr[h])
				swap(arr, i, l++);
		swap(arr, l, h);
		return l;
	}

	public static void swap(int[] arr, int i, int j) {
		if ((i ^ j) > 0) {
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}
	}

	public static int[] getRandArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i;
		shuffle(arr);
		return arr;
	}

	public static void shuffle(int[] arr) {
		for (int i = arr.length; i > 0; i--)
			swap(arr, i - 1, rand.nextInt(i));
	}

}
