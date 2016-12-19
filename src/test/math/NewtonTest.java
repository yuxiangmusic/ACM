package test.math;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.math.Newton;

public class NewtonTest {

	static final int n = 1000;

	@Test
	public void test() {
		for (int i = 0; i < 1000; i++) {
			assertEquals(Math.sqrt(i), Newton.sqrt(i), Newton.DELTA);
		}
	}

}
