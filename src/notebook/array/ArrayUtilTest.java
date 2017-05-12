package notebook.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void test_ith() {
		int n = 10;
		int[] arr = ArrayUtil.getRandomArrayNoDup(n);
		System.out.println(Arrays.toString(arr));

		for (int i = 0; i < n; i++)
			assertEquals(i, ArrayUtil.ith(arr, i));
	}
}
