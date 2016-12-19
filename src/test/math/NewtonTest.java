package test.math;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.math.Newton;

public class NewtonTest {

	static final double DELTA = 1e-15;

	@Test
	public void test() {
		assertEquals(Math.sqrt(5), Newton.sqrt(5), DELTA);
	}

}
