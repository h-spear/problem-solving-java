// https://www.acmicpc.net/problem/12869

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class Mutalisk {

	private static final int[][] attack = {
		{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 3, 9}, {1, 9, 3}
	};
	private static final int[][][] cache = new int[61][61][61];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] SCV = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			SCV[i] = Integer.parseInt(st.nextToken());
		}
		int dfs = dfs(SCV[0], N > 1 ? SCV[1] : 0, N > 2 ? SCV[2] : 0);
		System.out.println(dfs);
		br.close();
	}

	private static int dfs(int one, int two, int three) {
		if (one <= 0 && two <= 0 && three <= 0)
			return 0;
		if (cache[one][two][three] != 0)
			return cache[one][two][three];

		int temp = Integer.MAX_VALUE;
		for (int[] atk: attack) {
			temp = Math.min(temp,
				dfs(Math.max(0, one - atk[0]), Math.max(0, two - atk[1]), Math.max(0, three - atk[2])) + 1);
		}
		return cache[one][two][three] = temp;
	}
}
