import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
=====
5
mom: upper upper lower middle class
dad: middle middle lower middle class
queenelizabeth: upper upper class
chair: lower lower class
unclebob: middle lower middle class
10
rich: lower upper class
mona: upper upper class
dave: middle lower class
charles: middle class
tom: middle class
william: lower middle class
carl: lower class
violet: middle class
frank: lower class
mary: upper class

 */

/**
 * <a href="https://icpcarchive.ecs.baylor.edu/external/73/7370.pdf">
 * 
 * Classy</a>
 */
public class Classy {
	static String[] lines;
	static HashMap<String, String> map;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String num = sc.nextLine();
			int n = Integer.parseInt(num);
			lines = new String[n];
			map = new HashMap<>();

			for (int i = 0; i < n; i++) {
				lines[i] = sc.nextLine();
				convert(lines[i]);
			}

			String[] names = map.keySet().toArray(new String[0]);

			Arrays.sort(names, (sname, tname) -> {
				String s = map.get(sname);
				String t = map.get(tname);

				String ss = s, tt = t;

				if (s.length() > t.length()) {
					tt = append2(t, s.length() - t.length());
				} else {
					ss = append2(s, t.length() - s.length());
				}
				if (ss.equals(tt)) {
					return sname.compareTo(tname);
				}
				return tt.compareTo(ss);
			});

			for (String name : names) {
				System.out.println(name.substring(0, name.length() - 1));
			}
		}
		sc.close();
	}

	public static String append2(String s, int n) {
		for (int i = 0; i < n; i++)
			s += 2;
		return s;
	}

	public static String convert(String line) {
		String[] arr = line.trim().split("\\s+");

		String res = "";

		for (int i = arr.length - 2; i >= 1; i--) {
			String s = arr[i];
			switch (s.charAt(0)) {
			case 'u':
				res += 3;
				break;
			case 'm':
				res += 2;
				break;
			case 'l':
				res += 1;
				break;
			}
		}
		map.put(arr[0], res);

		return res;
	}

}
