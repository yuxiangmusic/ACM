package notebook.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void test_ith() {
		int n = 10;
		int[] arr = ArrayUtil.getRandomArrayNoDup(n);
		for (int i = 0; i < n; i++)
			assertEquals(i, ArrayUtil.ith(arr, i));
	}

	@Test
	public void test_ith_subarray() {
		int n = 5;
		int[] arr = new int[n * 3];
		for (int i = 0; i < n; i++)
			arr[i] = arr[n + i] = arr[n + n + i] = i;

		ArrayUtil.shuffle(arr, n * 0, n * 1 - 1);
		ArrayUtil.shuffle(arr, n * 1, n * 2 - 1);
		ArrayUtil.shuffle(arr, n * 2, n * 3 - 1);

		System.out.println(Arrays.toString(arr));

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(i, ArrayUtil.ith(arr, n * j, n * j + n - 1, n * j + i));
			}
		}
	}
}
