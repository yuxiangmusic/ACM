package notebook.array;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.Notebook;

public class LargestRectangleInHistogram {

	@Notebook
	public static int largestRectangleArea(int[] h) {
		int n = h.length, stack[] = new int[n], size = 0, max = 0;
		if (n == 0)
			return 0;
		for (int i = 0; i <= n; i++) {
			while (size > 0 && (i == n || h[stack[size - 1]] >= h[i]))
				max = Math.max(max, h[stack[--size]] * (i - (size == 0 ? 0 : 1 + stack[size - 1])));
			stack[size++] = i;
		}
		return max;
	}

	@Test
	public void test_1() {
		assertEquals(1, largestRectangleArea(new int[] { 1 }));
	}

	@Test
	public void test_empty() {
		assertEquals(0, largestRectangleArea(new int[] {}));
	}

}
