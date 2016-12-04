package notebook.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * 
 * For example, Given [1,3],[2,6],[8,10],[15,18], return [1,6],[8,10],[15,18].
 * 
 */
public class MergeIntervals {
	public static void main(String[] args) {
		List<Interval> list = new ArrayList<>();
		list.add(new Interval(1, 3));
		list.add(new Interval(2, 6));
		list.add(new Interval(8, 10));
		list.add(new Interval(15, 18));
		List<Interval> merge = merge(list);
		System.out.println(merge);
	}

	public static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1)
			return intervals;

		Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

		List<Interval> res = new LinkedList<>();

		int start = intervals.get(0).start, end = intervals.get(0).end;
		for (Interval i : intervals) {
			if (i.start <= end)
				end = Math.max(end, i.end); // next interval could be inside previous interval
			else {
				// previous merge complete
				res.add(new Interval(start, end));

				// next merge
				start = i.start;
				end = i.end;
			}
		}
		res.add(new Interval(start, end));
		return res;
	}
}
