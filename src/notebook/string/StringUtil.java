package notebook.string;

public class StringUtil {

	public static char[][] toCharArray2D(String... arr) {
		char[][] matrix = new char[arr.length][];
		for (int i = 0; i < matrix.length; i++)
			matrix[i] = arr[i].toCharArray();
		return matrix;
	}

}
