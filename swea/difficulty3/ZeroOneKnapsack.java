// 3282

package swea.difficulty3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ZeroOneKnapsack {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] dp = new int[101][1001];
		int[] V = new int[101];
		int[] C = new int[101];
		int N, K;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= N; ++i) {
				Arrays.fill(dp[i], 0, K + 1, 0);
			}
			for (int i = 1; i <= N; ++i) {
				st = new StringTokenizer(br.readLine());
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= K; ++j) {
					if (j >= V[i])
						dp[i][j] = Math.max(dp[i - 1][j], C[i] + dp[i - 1][j - V[i]]);
					else
						dp[i][j] = dp[i - 1][j];
				}
			}

			sb.append("#").append(tc).append(" ")
				.append(dp[N][K]).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
