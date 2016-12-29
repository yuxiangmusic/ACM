package test.string;

import static org.junit.Assert.*;

import org.junit.Test;

import notebook.string.EditDistance;

public class EditDistanceTest {

	@Test
	public void test() {
		assertEquals(1, EditDistance.minDistance("", "a"));
	}

}
