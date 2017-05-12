package notebook.geometry;

import static org.junit.Assert.*;

import org.junit.Test;

public class TriangleUtil {
	static final double DELTA = 1e-15;

	public static double area(double a, double b, double c) {
		double s = semiPerimeter(a, b, c);
		double areaSq = s * (s - a) * (s - b) * (s - c);
		return Math.sqrt(areaSq);
	}

	public static double circumcircleRadius(double a, double b, double c) {
		return a * b * c / Math.sqrt((a + b + c) * (b + c - a) * (a + c - b) * (a + b - c));
	}

	/**
	 * @return radius of excircle touching a
	 */
	public static double excircleRadius(double a, double b, double c) {
		double s = semiPerimeter(a, b, c);
		double radiusSq = s * (s - b) * (s - c) / (s - a);
		return Math.sqrt(radiusSq);
	}

	public static double incircleRadius(double a, double b, double c) {
		return area(a, b, c) / semiPerimeter(a, b, c);
	}

	public static double semiPerimeter(double a, double b, double c) {
		return (a + b + c) / 2;
	}

	@Test
	public void test_area() {
		assertEquals(6, area(3, 4, 5), DELTA);
	}

	@Test
	public void test_circumcircle() {
		assertEquals(2.5, circumcircleRadius(3, 4, 5), DELTA);
	}
}
