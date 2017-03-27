import java.util.Scanner;

/*
https://open.kattis.com/problems/hypercube
=====
3 001 111

=====
3 110 100

 */
public class HamiltonianHypercube {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.nextInt(); // useless
		System.out.println(solve(sc.next(), sc.next()));
		sc.close();
	}

	public static long solve(String a, String b) {
		return index(toLong(b)) - index(toLong(a)) - 1;
	}

	static long index(long l) {
		long r = 0;
		while (l > 0) {
			r ^= l;
			l >>= 1;
		}
		return r;
	}

	static long toLong(String bin) {
		long l = 0;
		for (char c : bin.toCharArray()) {
			l = (l << 1) + (c - '0');
		}
		return l;
	}

}
