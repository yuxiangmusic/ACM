import java.util.Arrays;
import java.util.Scanner;

/*
https://open.kattis.com/problems/redistribution
=====
4
2 3 3 1

=====
2
10 20

=====
5
1 3 5 4 2

=====
5
1 2 2 2 8

 */
public class ExamRedistribution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s[] = new int[n];
		for (int i = 0; i < n; i++)
			s[i] = sc.nextInt();
		solve(s);
		sc.close();
	}

	public static void solve(int[] s) {
		int arr[][] = new int[s.length][];
		for (int i = 0; i < s.length; i++)
			arr[i] = new int[] { s[i], i + 1 };
		Arrays.sort(arr, (a, b) -> b[0] - a[0]);

		int rest = 0;
		for (int i = 1; i < s.length; i++)
			rest += arr[i][0];

		if (rest < arr[0][0])
			System.out.println("impossible");
		else {
			StringBuilder sb = new StringBuilder();
			for (int[] e : arr)
				sb.append(e[1]).append(' ');
			System.out.println(sb.toString().trim());
		}
	}

}
