package notebook.combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import notebook.list.ListUtil;

public class Chars {

	public static void main(String[] args) {
		List<String> list = c("aabbcc", 2); // must be sorted before removing duplicates

		System.out.println(list);
		System.out.println("Total with duplicates: " + list.size());

		ListUtil.removeDuplicates(list);

		System.out.println(list);
		System.out.println("Total without duplicates: " + list.size());
	}

	static List<String> c(final String s, final int k) {
		List<String> list = new ArrayList<>();
		c(list, s, "", k);
		return list;
	}

	/**
	 * use {@link List} if we want to maintain original order <br>
	 * use {@link HashSet} otherwise
	 */
	static void c(List<String> list, final String s, final String t, final int k) {
		if (t.length() == k)
			list.add(t);
		else {
			for (int i = 0; i < s.length(); i++) {
				String ss = s.substring(i + 1);
				String tt = t + s.charAt(i);
				c(list, ss, tt, k);
			}
		}
	}

}
