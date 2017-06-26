package notebook.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class SearchUtilTest {

	private static int length = 20;

	private static Random rand = new Random();

	@Test
	public void test() {
		boolean has[] = new boolean[length];
		for (int i = 0; i < length; i++)
			has[rand.nextInt(length)] = true;

		int arr[] = new int[length], newLength = 0;
		for (int i = 0; i < length; i++)
			if (has[i])
				arr[newLength++] = i;
		arr = Arrays.copyOf(arr, newLength);

		System.out.println(Arrays.toString(arr));

		for (int i = 0, j = 0; i < length; i++) {
			int index = SearchUtil.binarySearch(arr, i);
			if (index >= 0)
				System.out.println("search " + i + " index = " + index);
			else
				System.out.println("search " + i + " not found index = " + (-1 - index));
			if (has[i]) {
				assertEquals(j, index);
				j++;
			} else {
				assertTrue(index < 0);
				assertEquals(j, -1 - index);
			}
		}
	}

}
