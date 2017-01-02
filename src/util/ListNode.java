package util;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	public ListNode(int... arr) {
		val = arr[0];
		ListNode[] nodes = new ListNode[arr.length];
		nodes[0] = this;
		for (int i = 1; i < arr.length; i++)
			nodes[i - 1].next = nodes[i] = new ListNode(arr[i]);
	}

	@Override
	public String toString() {
		return String.valueOf(val);
	}

	public String toStringList() {
		StringBuilder sb = new StringBuilder();
		ListNode cur = this;
		while (cur != null) {
			if (cur != this)
				sb.append("->").append(cur.val);
			else
				sb.append(cur.val);
			cur = cur.next;
		}
		return sb.toString();
	}
}