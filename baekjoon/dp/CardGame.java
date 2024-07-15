// https://www.acmicpc.net/problem/10835

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class CardGame {

	private static int[][] cache;
	private static int N;
	private static int[] left, right;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		left = new int[N];
		right = new int[N];
		cache = new int[N][N];
		for (int i = 0; i < N; ++i)
			Arrays.fill(cache[i], -1);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			left[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			right[i] = Integer.parseInt(st.nextToken());
		}
		int x = dfs(0, 0);
		System.out.println(x);
		br.close();
	}

	private static int dfs(int leftIndex, int rightIndex) {
		if (leftIndex >= N || rightIndex >= N)
			return 0;
		if (cache[leftIndex][rightIndex] != -1)
			return cache[leftIndex][rightIndex];

		return cache[leftIndex][rightIndex] = max(
			dfs(leftIndex + 1, rightIndex),
			dfs(leftIndex + 1, rightIndex + 1),
			(left[leftIndex] > right[rightIndex]
				? dfs(leftIndex, rightIndex + 1) + right[rightIndex]
				: Integer.MIN_VALUE)
		);
	}

	private static int max(int... nums) {
		int res = Integer.MIN_VALUE;
		for (int num: nums)
			res = Math.max(res, num);
		return res;
	}
}
