// https://www.acmicpc.net/problem/17353

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class StarFallingFromTheSky {

	private static int S;
	private static long[] tree;
	private static long[] lazy;
	private static int[] counter;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		S = 1;
		while (S < N)
			S <<= 1;
		tree = new long[S << 1];
		lazy = new long[S << 1];
		counter = new int[S << 1];

		for (int i = 0; i < N; ++i)
			tree[S + i] = A[i];
		for (int i = S - 1; i > 0; --i)
			tree[i] = tree[i << 1] + tree[(i << 1) | 1];

		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			if (C == 1) {
				int L = Integer.parseInt(st.nextToken()) - 1;
				int R = Integer.parseInt(st.nextToken()) - 1;
				update(1, 0, S - 1, L, R);
			} else {
				int X = Integer.parseInt(st.nextToken()) - 1;
				sb.append(query(1, 0, S - 1, X)).append("\n");
			}

			// for (int i = 0 ;i < N; ++i)
			// 	query(1, 0, S - 1, i);
			// System.out.println(Arrays.toString(tree));
			// System.out.println(Arrays.toString(counter));
			// System.out.println(Arrays.toString(lazy));
			// System.out.println();
		}

		System.out.println(sb.toString());
		br.close();
	}

	private static void update(int node, int left, int right, int updateLeft, int updateRight) {
		lazyPropagate(node, left, right);
		if (updateRight < left || right < updateLeft)
			return;
		if (updateLeft <= left && right <= updateRight) {
			counter[node] = 1;
			lazy[node] = left - updateLeft;
			lazyPropagate(node, left, right);
		} else {
			int mid = (left + right) >> 1;
			update(node << 1, left, mid, updateLeft, updateRight);
			update((node << 1) | 1, mid + 1, right, updateLeft, updateRight);
			tree[node] = tree[node << 1] + tree[(node << 1) | 1];
		}
	}

	private static long query(int node, int left, int right, int target) {
		lazyPropagate(node, left, right);
		if (target < left || right < target)
			return 0;
		if (target <= left && right <= target) {
			return tree[node];
		} else {
			int mid = (left + right) >> 1;
			return query(node << 1, left, mid, target)
				+ query((node << 1) | 1, mid + 1, right, target);
		}
	}

	private static void lazyPropagate(int node, int left, int right) {
		if (counter[node] != 0) {
			tree[node] += lazy[node] * (right - left + 1) + counter[node] * sum(right - left + 1);
			if (node < S) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) | 1] += lazy[node] + (long) counter[node] * (right - left + 1) / 2;
				counter[node << 1] += counter[node];
				counter[(node << 1) | 1] += counter[node];
			}
			lazy[node] = 0;
			counter[node] = 0;
		}
	}

	private static long sum(long num) {
		return (num * (num + 1)) / 2;
	}
}
