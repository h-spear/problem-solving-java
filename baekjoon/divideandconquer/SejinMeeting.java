// https://www.acmicpc.net/problem/15791

package baekjoon.divideandconquer;

import java.io.*;
import java.util.*;

public class SejinMeeting {

	private static final int P = 1_000_000_007;
	private static final long[] facto = new long[1_000_001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		facto[0] = 1;
		for (int i = 1; i <= N; ++i) {
			facto[i] = (facto[i - 1] * i) % P;
		}

		long a = facto[N];
		long b = (facto[N - M] * facto[M]) % P;
		long bInverse = powMod(b, P - 2);
		System.out.println((a * bInverse) % P);
		br.close();
	}

	private static long powMod(long x, long pow) {
		if (pow == 0)
			return 1;
		long temp = powMod(x, pow >> 1);
		if ((pow & 1) == 1)
			return ((temp * temp) % P * x) % P;
		else
			return (temp * temp) % P;
	}
}
