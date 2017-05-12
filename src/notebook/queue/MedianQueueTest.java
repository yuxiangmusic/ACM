package notebook.queue;

import static org.junit.Assert.*;

import org.junit.Test;

public class MedianQueueTest {

	static final double DELTA = 1e-15;

	@Test
	public void test() {
		MedianQueue q = new MedianQueue();
		q.add(1);
		assertEquals(1, q.median(), DELTA);
		q.add(2);
		assertEquals(1.5, q.median(), DELTA);
		q.add(3);
		assertEquals(2, q.median(), DELTA);
		q.remove(1);
		assertEquals(2.5, q.median(), DELTA);
		q.remove(2);
		assertEquals(3, q.median(), DELTA);
	}

}
