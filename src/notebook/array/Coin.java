package notebook.array;

public class Coin {

	public static void main(String[] args) {
		System.out.println(coinrep(new int[] { 1, 2, 3, 5 }, 5));
		System.out.println(coinnonrep(new int[] { 1, 2, 3, 5 }, 5));
	}

	/**
	 * @return number of ways to make sum s using repeated coins
	 */
	public static int coinrep(int[] coins, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int coin : coins)
			for (int i = coin; i <= s; i++)
				dp[i] += dp[i - coin];
		return dp[s];
	}

	/**
	 * @return number of ways to make sum s using non-repeated coins
	 */
	public static int coinnonrep(int[] coins, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int coin : coins)
			for (int i = s; i >= coin; i--)
				dp[i] += dp[i - coin];
		return dp[s];
	}
}
