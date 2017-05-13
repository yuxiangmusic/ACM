package notebook.string;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

// XXX screwed up
public class KMP {
	/**
	 * @return list of starting indexes of text where pattern matches
	 */
	public static List<Integer> kmp(String text, String pattern) {
		List<Integer> list = new ArrayList<>();
		// TODO
		return list;
	}

	@Test
	public void test_ABC_ABABCABABC() {
		List<Integer> actual = kmp("ABC", "ABABCABABC");
		List<Integer> expected = Arrays.asList(2, 7);
		assertEquals(expected, actual);
	}

	@Test
	public void test_AA_AAAAA() {
		List<Integer> actual = kmp("AA", "AAAAA");
		List<Integer> expected = Arrays.asList(0, 1, 2, 3);
		assertEquals(expected, actual);
	}

	@Test
	public void test_ABA_ABABA() {
		List<Integer> actual = kmp("ABA", "ABABA");
		List<Integer> expected = Arrays.asList(0, 2);
		assertEquals(expected, actual);
	}

}
