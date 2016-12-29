package notebook.array;

public class LargestRectangleInHistogram {

	public static int max(int[] h) {
		int stack[] = new int[h.length], size = 0, max = 0;
		for (int i = 0; i < h.length; i++) {
			if (size == 0 || h[stack[size - 1]] < h[i])
				stack[size++] = i;
			while (size > 0 && h[stack[size - 1]] >= h[i])
				max = Math.max(max, h[stack[--size]] * (i - (size == 0 ? 0 : stack[size - 1] + 1)));
			stack[size++] = i;
		}
		while (size > 0)
			max = Math.max(max, h[stack[--size]] * (h.length - (size == 0 ? 0 : stack[size - 1] + 1)));
		return max;
	}

}
