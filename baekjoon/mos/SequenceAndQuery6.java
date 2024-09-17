// https://www.acmicpc.net/problem/13548

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SequenceAndQuery6 {

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

		final int MAX_NUM = 100_100;
		final int MAX_COUNT = 100_100;

		int[] counter = new int[MAX_NUM];
		int[] maxCounter = new int[MAX_COUNT];

		int sqrtN = (int) Math.sqrt(N);
		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.i / sqrtN;
			int block2 = o2.i / sqrtN;
			if (block1 != block2) {
				return Integer.compare(block1, block2);
			}
			return Integer.compare(o1.j, o2.j);
		});

		int[] answer = new int[M];
		Query query0 = queries[0];
		int left = query0.i;
		int right = query0.j;
		int maxx = 0;
		for (int i = left; i <= right; ++i) {
			int c = ++counter[A[i]];
			++maxCounter[c];
			if (c - 1 > 0)
				--maxCounter[c - 1];
			if (maxCounter[c] == 1) {
				maxx = Math.max(maxx, c);
			}
		}
		answer[query0.index] = maxx;

		for (int q = 1; q < M; ++q) {
			int i = queries[q].i;
			int j = queries[q].j;
			int index = queries[q].index;
			while (right < j) {
				int c = ++counter[A[++right]];
				++maxCounter[c];
				--maxCounter[c - 1];
				if (maxCounter[c] == 1 && maxx == c - 1) {
					++maxx;
				}
			}
			while (right > j) {
				int c = --counter[A[right--]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1) {
					--maxx;
				}
			}
			while (left < i) {
				int c = --counter[A[left++]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1) {
					--maxx;
				}
			}
			while (left > i) {
				int c = ++counter[A[--left]];
				++maxCounter[c];
				--maxCounter[c - 1];
				if (maxCounter[c] == 1 && maxx == c - 1) {
					++maxx;
				}
			}
			answer[index] = maxx;
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
