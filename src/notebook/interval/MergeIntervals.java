package notebook.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import notebook.Notebook;

public class MergeIntervals {
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(1, 3));
		list.add(new Interval(2, 4));
		list.add(new Interval(3, 5));
		list.add(new Interval(4, 6));
		List<Interval> merge = merge(list);
		System.out.println(merge);
	}

	@Notebook
	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;
		Collections.sort(intervals, (a, b) -> a.start - b.start);
		List<Interval> list = new LinkedList<>();
		int start = intervals.get(0).start, end = intervals.get(0).end;
		for (Interval i : intervals) {
			if (i.start <= end)
				end = Math.max(end, i.end); // next interval could be inside previous interval
			else {
				// previous merge complete
				list.add(new Interval(start, end));
				// next merge
				start = i.start;
				end = i.end;
			}
		}
		list.add(new Interval(start, end));
		return list;
	}
}
