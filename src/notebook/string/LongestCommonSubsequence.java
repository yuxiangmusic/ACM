package notebook.string;

import notebook.Notebook;

public class LongestCommonSubsequence {
	@Notebook
	public static int longestCommonSubsequence(String s, String t) {
		// DP[i][j] := LCS(s.substring(i), t.substring(j))
		int dp[][] = new int[s.length() + 1][t.length() + 1];
		for (int i = s.length() - 1; i >= 0; i--)
			for (int j = t.length() - 1; j >= 0; j--)
				dp[i][j] = s.charAt(i) == t.charAt(j) ? dp[i + 1][j + 1] + 1 : Math.max(dp[i + 1][j], dp[i][j + 1]);
		return dp[0][0];
	}
}
