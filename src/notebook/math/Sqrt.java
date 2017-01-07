package notebook.math;

import java.math.BigInteger;

public class Sqrt {

	public static long sqrt(long n) {
		long l = 0, h = n;
		while (l <= h) {
			long mid = (l + h) >>> 1, sq = mid * mid, sqless = (mid - 1) * (mid - 1);
			if (sq >= n && sqless < n)
				return mid;
			else if (n > sq)
				l = mid + 1;
			else
				h = mid - 1;
		}
		return -1;
	}

	public static BigInteger sqrt(BigInteger n) {
		BigInteger l = BigInteger.ZERO, h = n;
		while (l.compareTo(h) <= 0) {
			BigInteger mid = l.add(h).shiftRight(1);
			BigInteger sqh = mid.pow(2);
			BigInteger sql = mid.subtract(BigInteger.ONE).pow(2);
			if (sqh.compareTo(n) >= 0 && (sql.compareTo(n) < 0 || mid.compareTo(BigInteger.ZERO) <= 0))
				return mid;
			else if (n.compareTo(sqh) > 0)
				l = mid.add(BigInteger.ONE);
			else
				h = mid.subtract(BigInteger.ONE);
		}
		return null;
	}

	public static void main(String[] args) {
		for (int i = 1; i < 100; i++)
			System.out.println(sqrt(new BigInteger(String.valueOf(i * i - 1))));
	}

}
