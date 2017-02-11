package notebook.array;

import java.util.Arrays;

public class LIS {

	public static void main(String[] args) {
		int[] arr = ArrayUtil.getRandomArray(1000);
		{
			long begin = System.nanoTime();
			int[] lis = getLIS_log(arr);
			System.out.println(Arrays.toString(lis));
			System.out.println("LIS length = " + lis.length);
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
		{
			long begin = System.nanoTime();
			int[] lis = getLIS_quadratic(arr);
			System.out.println(Arrays.toString(lis));
			System.out.println("LIS length = " + lis.length);
			System.out.println("Runtime: " + (System.nanoTime() - begin));
		}
	}

	// O(n*log(n)) DP solution
	public static int[] getLIS_log(int[] arr) {
		if (arr.length == 0)
			return new int[0];
		int length = 0, dp[] = new int[arr.length];
		int p[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int l = 0, h = length - 1;
			while (l <= h) {
				int mid = (l + h) >>> 1;
				if (arr[dp[mid]] == arr[i]) {
					l = mid;
					break;
				} else if (arr[dp[mid]] < arr[i]) {
					l = mid + 1;
				} else {
					h = mid - 1;
				}
			}
			dp[l] = i;
			if (length == l)
				length++;
			if (l > 0)
				p[i] = dp[l - 1];
		}
		int lis[] = new int[length], arrIndex = dp[length - 1];
		for (int i = length - 1; i >= 0; i--) {
			lis[i] = arr[arrIndex]; // arrIndex; // uncomment if we want indexes of LIS
			arrIndex = p[arrIndex];
		}
		return lis;
	}

	// classic DP solution O(n^2)
	public static int[] getLIS_quadratic(int[] arr) {
		if (arr.length == 0)
			return new int[0];
		int l[] = new int[arr.length]; // l[i] := length of LIS ending at i
		int p[] = new int[arr.length]; // p[i] := predecessor of i
		int maxIndex = 0; // index of max LIS
		for (int i = 0; i < arr.length; i++) {
			l[i] = 1;
			p[i] = -1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && l[i] < l[j] + 1) {
					l[i] = l[j] + 1;
					p[i] = j;
				}
			}
			if (l[i] > l[maxIndex])
				maxIndex = i;
		}
		int lis[] = new int[l[maxIndex]], arrIndex = maxIndex;
		for (int i = lis.length - 1; i >= 0; i--) {
			lis[i] = arr[arrIndex];
			arrIndex = p[arrIndex];
		}
		return lis;
	}

	public static int lenLIS_quadratic(int[] arr) {
		if (arr.length == 0)
			return 0;
		int l[] = new int[arr.length]; // l[i] := length of LIS ending at i
		int max = 0; // index of max LIS
		for (int i = 0; i < arr.length; i++) {
			l[i] = 1;
			for (int j = 0; j < i; j++)
				if (arr[j] < arr[i] && l[i] < l[j] + 1)
					l[i] = l[j] + 1;
			if (l[i] > max)
				max = l[i];
		}
		return max;
	}
}
