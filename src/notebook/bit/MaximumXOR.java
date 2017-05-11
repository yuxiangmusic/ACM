package notebook.bit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import notebook.Notebook;

/**
 * Given a list of numbers, a[0], a[1], a[2], … , a[N-1], <br>
 * where 0 <= a[i] < 2^32. Find the maximum result of a[i] XOR a[j].
 * 
 * Could you do this in O(n) runtime?
 * 
 * Input: [3, 10, 5, 25, 2, 8] Output: 28
 */
public class MaximumXOR {

	@Test
	public void test() {
		assertEquals(28, findMaximumXOR(new int[] { 3, 10, 5, 25, 2, 8 }));
	}

	/**
	 * @return maximum XOR of two numbers
	 */
	@Notebook
	public int findMaximumXOR(int[] nums) {
		int max = 0, mask = 0;
		for (int i = 30; i >= 0; i--) {
			int m = 1 << i;
			mask |= m;
			Set<Integer> set = new HashSet<>();
			for (int num : nums) {
				set.add(num & mask);
			}
			// test if i'th bit of max can be set, given LHS bits
			int tmp = max | m;
			for (int prefix : set) {
				// there exist prefix x and y such that (x ^ y) = (max | m)
				if (set.contains(tmp ^ prefix)) {
					max = tmp;
					break;
				}
			}
		}
		return max;
	}
}
