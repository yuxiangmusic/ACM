package notebook.regex;

public class Regex {

	public static void main(String[] args) {
		String s = "--- --- Hello --- ---";
		System.out.println(s.replaceAll("[-|\\s]+", "-"));
	}

}
