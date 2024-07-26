// https://www.acmicpc.net/problem/14288

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CompanyCulture4 {

	private static List<List<Integer>> tree;
	private static int[] in, out;
	private static int count;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		in = new int[n + 1];
		out = new int[n + 1];
		tree = new ArrayList<>(n + 1);
		for (int i = 0; i <= n; ++i)
			tree.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i <= n; ++i) {
			int par = Integer.parseInt(st.nextToken());
			tree.get(par).add(i);
		}

		dfs(1);

		int sIdx = 1;
		SegmentTree seg0 = new SegmentTree(n);
		SegmentTreeWithLazyPropagation seg1 = new SegmentTreeWithLazyPropagation(n);

		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = q != 3 ? Integer.parseInt(st.nextToken()) : -1;
			int w = q == 1 ? Integer.parseInt(st.nextToken()) : -1;

			if (q == 1) {
				if (sIdx == 0)
					seg0.update(in[i], w);
				else seg1.update(in[i], out[i], w);
			} else if (q == 2) {
				sb.append(seg0.query(in[i], out[i]) + seg1.query(in[i], in[i]))
					.append("\n");
			} else {
				sIdx = 1 - sIdx;
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static void dfs(int x) {
		in[x] = ++count;
		for (int next: tree.get(x))
			dfs(next);
		out[x] = count;
	}

	private static class SegmentTree {
		int[] tree;
		int sz;

		SegmentTree(int n) {
			sz = 1;
			while (sz <= n)
				sz <<= 1;
			tree = new int[sz << 1];
		}

		void update(int target, int value) {
			int i = sz + target;
			while (i > 0) {
				tree[i] += value;
				i >>= 1;
			}
		}

		int query(int queryLeft, int queryRight) {
			return query(1, 0, sz - 1, queryLeft, queryRight);
		}

		private int query(int node, int left, int right, int queryLeft, int queryRight) {
			if (queryRight < left || right < queryLeft)
				return 0;
			if (queryLeft <= left && right <= queryRight)
				return tree[node];
			int mid = (left + right) >> 1;
			return query(node << 1, left, mid, queryLeft, queryRight)
					+ query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
		}
	}

	private static class SegmentTreeWithLazyPropagation extends SegmentTree {
		int[] lazy;

		SegmentTreeWithLazyPropagation(int n) {
			super(n);
			lazy = new int[sz << 1];
		}

		@Override
		void update(int target, int value) {
			update(target, target, value);
		}

		void update(int updateLeft, int updateRight, int value) {
			update(1, 0, sz - 1, updateLeft, updateRight, value);
		}

		@Override
		int query(int queryLeft, int queryRight) {
			return query(1, 0, sz - 1, queryLeft, queryRight);
		}

		private int query(int node, int left, int right, int queryLeft, int queryRight) {
			lazyPropagate(node, left, right);
			if (queryRight < left || right < queryLeft)
				return 0;
			if (queryLeft <= left && right <= queryRight)
				return tree[node];
			int mid = (left + right) >> 1;
			return query(node << 1, left, mid, queryLeft, queryRight)
				+ query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
		}

		private void update(int node, int left, int right, int updateLeft, int updateRight, int value) {
			lazyPropagate(node, left, right);
			if (updateRight < left || right < updateLeft)
				return;
			if (updateLeft <= left && right <= updateRight) {
				lazy[node] += value;
				lazyPropagate(node, left, right);
			} else {
				int mid = (left + right) >> 1;
				update(node << 1, left, mid, updateLeft, updateRight, value);
				update((node << 1) | 1, mid + 1, right, updateLeft, updateRight, value);
				tree[node] = tree[node << 1] + tree[(node << 1) | 1];
			}
		}

		private void lazyPropagate(int node, int left, int right) {
			if (lazy[node] != 0) {
				tree[node] += (right - left + 1) * lazy[node];
				if (node < sz) {
					lazy[node << 1] += lazy[node];
					lazy[(node << 1) | 1] += lazy[node];
				}
				lazy[node] = 0;
			}
		}
	}
}
