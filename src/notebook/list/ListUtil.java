package notebook.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListUtil {

	public static <E> void removeDuplicates(List<E> list) {
		List<E> tmp = new ArrayList<>();
		Set<E> set = new HashSet<>();
		for (E e : list) {
			if (!set.contains(e)) {
				tmp.add(e);
				set.add(e);
			}
		}
		list.clear();
		list.addAll(tmp);
	}

}
