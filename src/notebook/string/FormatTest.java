package notebook.string;

import static org.junit.Assert.assertEquals;

import java.text.DecimalFormat;

import org.junit.Test;

public class FormatTest {

	@Test
	public void test() {
		// rounded to 4th
		assertEquals("0.1235", String.format("%.4f", 0.12345));

		// add 0's to end
		assertEquals("0.1000", String.format("%.4f", 0.1));

		// add 0's to begin
		assertEquals("0012", String.format("%04d", 12));

		// rounded
		assertEquals("00.14", new DecimalFormat("00.00").format(0.135));
	}

}
