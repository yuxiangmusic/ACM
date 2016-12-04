package notebook.array;

import java.util.Arrays;
import java.util.Random;

public class Shuffle {

	static Random rand = new Random();

	public static void main(String[] args) {
		System.out.println(Arrays.toString(getRandArray(10)));
	}

	public static int[] getRandArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = i;
		shuffle(arr);
		return arr;
	}

	public static void shuffle(int[] arr) {
		for (int i = arr.length; i > 0; i--) {
			swap(arr, i - 1, rand.nextInt(i));
		}
	}

	static void swap(int[] arr, int i, int j) {
		if ((i ^ j) > 0) {
			arr[i] ^= arr[j];
			arr[j] ^= arr[i];
			arr[i] ^= arr[j];
		}
	}
}
