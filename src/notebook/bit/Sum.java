package notebook.bit;

public class Sum {

	public int sum(int a, int b) {
		return (b == 0) ? a : sum(a ^ b, (a & b) << 1);
	}

}
