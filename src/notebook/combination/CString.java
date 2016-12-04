package notebook.combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import notebook.list.ListUtil;

public class CString {

	public static void main(String[] args) {
		List<String> list = c("hello", 2);
		ListUtil.removeDuplicates(list);
		System.out.println(list);
	}

	static List<String> c(String s, int k) {
		List<String> list = new ArrayList<>();
		c(list, s, k, "");
		return list;
	}

	/**
	 * use {@link List} if we want to maintain original order <br>
	 * use {@link HashSet} otherwise
	 */
	static void c(List<String> list, String s, int k, String t) {
		if (t.length() == k)
			list.add(t);
		else {
			for (int i = 0; i < s.length(); i++) {
				String ss = s.substring(i + 1);
				String tt = t + s.charAt(i);
				c(list, ss, k, tt);
			}
		}
	}

}
