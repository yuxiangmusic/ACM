package notebook.tree;

public class SegmentTree {

	final long[] arr;
	final long[] heap;

	public SegmentTree(long[] arr) {
		this.arr = arr;
		int length = (int) Math.pow(2, 1 + Math.ceil(Math.log(arr.length) / Math.log(2))) - 1;
		this.heap = new long[length];

		buildRec(0, 0, arr.length - 1);
	}

	private long buildRec(int hi, int l, int h) {
		if (l >= h) {
			return heap[hi] = arr[l];
		}
		int mid = (l + h) >>> 1;
		long lsum = buildRec(hi * 2 + 1, l, mid);
		long hsum = buildRec(hi * 2 + 2, mid + 1, h);
		return heap[hi] = lsum + hsum;
	}

	private long sumRec(int from, int to, int hi, int l, int h) {
		if (from <= l && h <= to)
			return heap[hi];
		if (from > h || l > to)
			return 0;
		int mid = (l + h) >> 1;
		long lsum = sumRec(from, to, hi * 2 + 1, l, mid);
		long rsum = sumRec(from, to, hi * 2 + 2, mid + 1, h);
		return lsum + rsum;
	}

	public long sum(int from, int to) {
		return sumRec(from, to, 0, 0, arr.length - 1);
	}

	private void updateRec(long diff, int i, int hi, int l, int h) {
		if (i < l || i > h)
			return;
		heap[hi] += diff;
		if (l >= h)
			return;
		int mid = (l + h) >> 1;
		updateRec(diff, i, hi * 2 + 1, l, mid);
		updateRec(diff, i, hi * 2 + 2, mid + 1, h);
	}

	public void update(int i, long value) {
		updateRec(value - arr[i], i, 0, 0, arr.length - 1);
		arr[i] = value;
	}
}
