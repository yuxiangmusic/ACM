package notebook.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import notebook.list.ListUtil;

public class Chars {

	public static void main(String[] args) {
		List<String> list = p("aabbcc", 2);

		System.out.println(list);
		System.out.println("Total with duplicates: " + list.size());

		ListUtil.removeDuplicates(list);

		System.out.println(list);
		System.out.println("Total without duplicates: " + list.size());
	}

	static List<String> p(final String s, final int k) {
		List<String> set = new ArrayList<>();
		p(set, s, "", k);
		return set;
	}

	/**
	 * use {@link List} if we want to maintain original order <br>
	 * use {@link HashSet} otherwise
	 */
	static void p(List<String> set, final String s, final String t, final int k) {
		if (t.length() == k)
			set.add(t);
		else {
			for (int i = 0; i < s.length(); i++) {
				String ss = s.substring(0, i) + s.substring(i + 1);
				String tt = t + s.charAt(i);
				p(set, ss, tt, k);
			}
		}
	}

}
