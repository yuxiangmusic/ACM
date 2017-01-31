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

		int plen = pattern.length(), tlen = text.length();
		int[] kmp = new int[plen];
		for (int i = 1; i < plen; i++) {
			if (pattern.charAt(kmp[i - 1]) == pattern.charAt(i))
				kmp[i] = kmp[i - 1] + 1;
			else
				kmp[i] = 0;
		}

		for (int p = 0, t = 0; t < tlen;) {
			if (p == 0 || t < tlen && pattern.charAt(p) == text.charAt(t)) {
				p++;
				t++;
				if (p == plen) {
					list.add(t - plen);
					p = kmp[p - 1];
				}
			} else {
				p = kmp[p - 1];
			}
		}
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
