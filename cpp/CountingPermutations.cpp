#include <iostream>
#include <string.h>

#define MIN(x, y) ((x) < (y) ? (x) : (y))

using namespace std;

int mod = 10007;

int fact(int n) {
	int f = 1;
	while (n > 0) {
		f *= n--;
		f %= mod;
	}
	return f;
}

int solve(int n, int k) {
	int dp[n+1][k+1];
	memset(dp, 0, sizeof(int) * (n+1) * (k+1));
	for (int i = 1; i <= n; i++) dp[i][0] = 1;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= MIN(k, i * (i-1) / 2); j++) {
			dp[i][j] += mod + dp[i][j-1] 
				+ dp[i-1][j] 
				- (j<i?0:dp[i-1][j-i]);
			dp[i][j] %= mod;
		}
	}
	int total = 0;
	for (int j = 0; j < k; j++) {
		total += dp[n][j];
		total %= mod;
	}
	return (fact(n) - total + mod) % mod;
}

int main(int argc, char *argv[]) {
	int T, n, k;
	cin >> T;
	for (int t = 1; t <= T; t++) {
		cin >> n >> k;
		cout << "Case " << t << ": " << solve(n, k) << endl;
	}
}
