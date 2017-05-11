package notebook.list;

import notebook.Notebook;

public class CycleDection {

	public static void main(String[] args) {
		ListNode head = new ListNode(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		head.tail().next = head.get(5);
		System.out.println(detect(head));
	}

	/**
	 * @return index where cycle starts
	 */
	@Notebook
	public static int detect(ListNode head) {
		ListNode slow = head.next, fast = slow.next;
		while (fast != slow) {
			fast = fast.next.next;
			slow = slow.next;
		}
		int index = 0;
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
			index++;
		}
		return index;
	}

}