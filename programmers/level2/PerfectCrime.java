// https://school.programmers.co.kr/learn/courses/30/lessons/389480

package programmers.level2;

public class PerfectCrime {

	private static final int INF = 1234567890;
	private int n, m, countOfItems;
	private int[][] info;
	private int[][][] dp;

	public int solution(int[][] info, int n, int m) {
		this.n = n;
		this.m = m;
		this.countOfItems = info.length;
		this.info = info;
		this.dp = new int[countOfItems][n][m];
		int ret = dfs(0, 0, 0);
		return ret == INF ? -1 : ret;
	}

	private int dfs(int i, int a, int b) {
		if (a >= n || b >= m)
			return INF;
		if (i == countOfItems)
			return a;
		if (dp[i][a][b] != 0)
			return dp[i][a][b];
		return dp[i][a][b] = Math.min(
			dfs(i + 1, a + info[i][0], b),
			dfs(i + 1, a, b + info[i][1])
		);
	}
}
