package util;

public class StringUtil {

	public static char[][] getCharMatrix(String... arr) {
		char[][] matrix = new char[arr.length][arr[0].length()];
		for (int i = 0; i < matrix.length; i++)
			matrix[i] = arr[i].toCharArray();
		return matrix;
	}

}
