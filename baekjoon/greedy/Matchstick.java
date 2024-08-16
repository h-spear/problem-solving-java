// https://www.acmicpc.net/problem/3687

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Matchstick {

	private static final int[] nums = {-1, -1, 1, 7, 4, 2, 0, 8, -1, -1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			sb.append(makeMin(n)).append(" ")
					.append(makeMax(n)).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static String makeMin(int n) {
		long[] cache = new long[n + 10];
		Arrays.fill(cache, -1);
		cache[2] = 1;
		cache[3] = 7;
		cache[4] = 4;
		cache[5] = 2;
		cache[6] = 6;
		cache[7] = 8;
		return String.valueOf(dp(cache, n));
	}

	private static long dp(long[] cache, int n) {
		if (n == 0)
			return 0;
		if (n < 0)
			return Long.MAX_VALUE >> 1;
		if (cache[n] != -1)
			return cache[n];
		long temp = Long.MAX_VALUE >> 1;
		for (int i = 2; i < 8; ++i) {
			temp = Math.min(temp, dp(cache, n - i) * 10 + nums[i]);
		}
		return cache[n] = temp;
	}

	private static String makeMax(int n) {
		StringBuilder sb = new StringBuilder();
		if ((n & 1) == 1) {
			sb.append(7);
			n -= 3;
		}
		for (int i = 0; i < n >> 1; ++i) {
			sb.append(1);
		}
		return sb.toString();
	}
}
