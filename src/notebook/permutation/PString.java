package notebook.permutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import notebook.list.ListUtil;

public class PString {

	public static void main(String[] args) {
		List<String> list = p("hello", 2);
		ListUtil.removeDuplicates(list);
		System.out.println(list);
	}

	static List<String> p(String s, int k) {
		List<String> set = new ArrayList<>();
		p(set, s, k, "");
		return set;
	}

	/**
	 * use {@link List} if we want to maintain original order <br>
	 * use {@link HashSet} otherwise
	 */
	static void p(List<String> set, String s, int k, String t) {
		if (t.length() == k)
			set.add(t);
		else {
			for (int i = 0; i < s.length(); i++) {
				String ss = s.substring(0, i) + s.substring(i + 1);
				String tt = t + s.charAt(i);
				p(set, ss, k, tt);
			}
		}
	}

}
