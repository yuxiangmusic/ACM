package notebook.string;

import java.text.DecimalFormat;

public class FormatTest {

	public static void main(String[] args) {
		// rounded to 4th
		System.out.println(String.format("%.4f", 0.12345));

		// add 0's to end
		System.out.println(String.format("%.4f", 0.1));

		// add 0's to begin
		System.out.println(String.format("%04d", 12));

		// rounded
		DecimalFormat df = new DecimalFormat("00.00");
		System.out.println(df.format(0.135));
	}

}
