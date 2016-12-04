package notebook.math;

public class Newton {

	public static void main(String[] args) {
		int c = 5;
		System.out.println(sqrt(c));
		System.out.println(Math.sqrt(c));
	}

	// f(x) = x^2 - n = 0
	// x = x - f/f' = x - (x^2 - n) / 2x
	public static double sqrt(double c) {
		double x = c, next;
		while (true) {
			next = x - (x - c / x) / 2;
			if (next == x)
				break;
			x = next;
		}
		return x;
	}

}
