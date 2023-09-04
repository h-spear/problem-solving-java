// 3304

package swea.difficulty3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestCommonSubstring {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] dp = new int[1001][1001];
		int n, m;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {

			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			n = a.length();
			m = b.length();
			for (int i = 0; i <= n; ++i) {
				Arrays.fill(dp[i], 0, m + 1, 0);
			}

			for (int i = 1; i <= n; ++i) {
				for (int j = 1; j <= m; ++j) {
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					}
				}
			}
			sb.append("#").append(tc).append(" ")
				.append(dp[n][m]).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
