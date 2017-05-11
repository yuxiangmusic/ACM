package notebook.bit;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import notebook.Notebook;

public class GrayCode {

	public static List<Integer> grayCode(int n) {
		return IntStream.range(0, 1 << n).map(i -> toGrayCode(i)).boxed().collect(Collectors.toList());
	}

	@Notebook
	public static int toBinary(int n) {
		for (int m = n >> 1; m != 0; m >>= 1)
			n = n ^ m;
		return n;
	}

	@Notebook
	public static int toGrayCode(int n) {
		return n ^ (n >> 1);
	}

	@Test
	public void test() {
		int n = 5;
		System.out.println(grayCode(n));
		for (int i = 0; i < 1 << n; i++)
			assertEquals(i, toBinary(toGrayCode(i)));
	}

}
