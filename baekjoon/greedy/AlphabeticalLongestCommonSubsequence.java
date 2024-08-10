// https://www.acmicpc.net/problem/30805

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class AlphabeticalLongestCommonSubsequence {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		int[] B = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> result = new ArrayList<>();
		while (true) {
			int[] indices = getCommonNumber(A, B);
			if (indices == null)
				break;

			result.add(A[indices[0]]);
			A = Arrays.copyOfRange(A, indices[0] + 1, A.length);
			B = Arrays.copyOfRange(B, indices[1] + 1, B.length);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append("\n");
		for (int x: result)
			sb.append(x).append(" ");
		System.out.println(sb.toString());
	    br.close();
	}

	private static int[] getCommonNumber(int[] one, int[] two) {
		int n = one.length;
		int m = two.length;
		Pair[] first = new Pair[n];
		Pair[] second = new Pair[m];
		for (int i = 0; i < n; ++i) {
			first[i] = new Pair(i, one[i]);
		}
		for (int i = 0; i < m; ++i) {
			second[i] = new Pair(i, two[i]);
		}
		Arrays.sort(first, (o1, o2) -> Integer.compare(o2.number, o1.number));
		Arrays.sort(second, (o1, o2) -> Integer.compare(o2.number, o1.number));

		int i = 0;
		int j = 0;
		while (i < n && j < m && first[i].number != second[j].number) {
			if (first[i].number > second[j].number)
				++i;
			else if (first[i].number < second[j].number)
				++j;
		}
		if (i >= n || j >= m)
			return null;
		return new int[]{first[i].index, second[j].index};
	}

	private static class Pair {
		int index, number;

		Pair(int index, int number) {
			this.index = index;
			this.number = number;
		}
	}
}
