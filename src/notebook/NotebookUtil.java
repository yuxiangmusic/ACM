package notebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class NotebookUtil {

	static StringBuilder cacheJavadoc = new StringBuilder();
	static StringBuilder sb = new StringBuilder();

	static int tabs, tabsNotebook;

	static void appendLine(String line) {
		line = removeExtraTabs(line);

		System.out.println(line);
		sb.append(line + "\n");
	}

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("src/notebook");
		Files.walk(path).filter(Files::isRegularFile).forEach(NotebookUtil::visit);

		PrintWriter pw = new PrintWriter("src/notebook/n.txt");
		pw.print(sb);
		pw.close();
	}

	static int numTabs(String line) {
		int n = 0;
		while (n < line.length() && line.charAt(n) == '\t')
			n++;
		return n;
	}

	static String removeExtraTabs(String line) {
		if (line.contains("\t")) {
			line = line.substring(tabsNotebook); // remove extra tabs
			line = line.replaceAll("\t", "    ");
		}
		return line;
	}

	static void visit(Path path) {
		File file = path.toFile();
		try {
			Scanner sc = new Scanner(file);

			boolean notebook = false;
			boolean cJavadoc = false;

			while (sc.hasNextLine()) {
				String line = sc.nextLine(), trim = line.trim();

				if (notebook) {
					appendLine(line);
				}

				int curTabs = numTabs(line);
				if (curTabs != tabs) {
					tabs = numTabs(line);
					cacheJavadoc.setLength(0);
				}

				// doc
				if (trim.startsWith("/**")) {
					cJavadoc = true;
				}
				if (cJavadoc) {
					cacheJavadoc.append(removeExtraTabs(line) + "\n");
					if (trim.endsWith("*/"))
						cJavadoc = false;
				}

				// notebook
				if (trim.startsWith("@Notebook")) {
					notebook = true;
					tabsNotebook = tabs;
					appendLine("// " + file);
					if (cacheJavadoc.length() > 0)
						appendLine(cacheJavadoc.toString().trim());
					cacheJavadoc.setLength(0);
				} else if (notebook && tabs == tabsNotebook && trim.startsWith("}")) {
					notebook = false;
					appendLine("");
				}
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
