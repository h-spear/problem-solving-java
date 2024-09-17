// https://www.acmicpc.net/problem/13547

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SequenceAndQuery5 {

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
		Query[] queries = new Query[M];
		for (int index = 0; index < M; ++index) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			queries[index] = new Query(i, j, index);
		}

		final int sqrtN = (int) Math.sqrt(N);
		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.i / sqrtN;
			int block2 = o2.i / sqrtN;
			if (block1 != block2) {
				return Integer.compare(block1, block2);
			}
			return Integer.compare(o1.j, o2.j);
		});

		final int MAX_NUM = 1_000_010;

		int[] answer = new int[M];
		int[] counter = new int[MAX_NUM];

		Query query0 = queries[0];
		int diffs = 0;
		int left = query0.i;
		int right = query0.j;
		for (int i = left; i <= right; ++i) {
			if (++counter[A[i]] == 1) {
				++diffs;
			}
		}
		answer[query0.index] = diffs;

		for (int q = 1; q < M; ++q) {
			int i = queries[q].i;
			int j = queries[q].j;
			int index = queries[q].index;

			while (left < i) {
				if (--counter[A[left++]] == 0)
					--diffs;
			}
			while (left > i) {
				if (++counter[A[--left]] == 1)
					++diffs;
			}
			while (right < j) {
				if (++counter[A[++right]] == 1)
					++diffs;
			}
			while (right > j) {
				if (--counter[A[right--]] == 0)
					--diffs;
			}
			answer[index] = diffs;
		}
		printArray(answer, M);
	    br.close();
	}

	private static void printArray(int[] array, int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			sb.append(array[i]).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static class Query {
		int i, j, index;

		Query(int i, int j, int index) {
			this.i = i;
			this.j = j;
			this.index = index;
		}
	}
}
