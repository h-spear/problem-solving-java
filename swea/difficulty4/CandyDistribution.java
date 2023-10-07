// 13736

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class CandyDistribution {

	private static long powMod(long a, long b, long c) {
		if (b == 0)
			return 1;

		long temp = powMod(a, b >> 1, c);
		long tSquare = (temp * temp) % c;
		if ((b & 1) == 1) {
			return (tSquare * a) % c;
		} else {
			return tSquare;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			long A = Integer.parseInt(st.nextToken());
			long B = Integer.parseInt(st.nextToken());
			long K = Integer.parseInt(st.nextToken());
			long C = A + B;
			long res = (powMod(2, K, C) * A) % C;
			sb.append("#").append(tc).append(" ")
				.append(Math.min(res, C - res)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}