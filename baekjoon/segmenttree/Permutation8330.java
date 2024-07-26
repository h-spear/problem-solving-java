// https://www.acmicpc.net/problem/8330

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Permutation8330 {

	private static int sz;
	private static int[] seg;
	private static int[] lazy;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		sz = 1;
		while (sz <= n)
			sz <<= 1;
		seg = new int[sz << 1];
		lazy = new int[sz << 1];

		initialize(a, n);

		StringBuilder sb = new StringBuilder();
		sb.append(query(1, n) <= 0 ? "TAK\n" : "NIE\n");

		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int j = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			update(a[j], n, -1);
			update(w, n, +1);
			a[j] = w;
			sb.append(query(1, n) <= 0 ? "TAK\n" : "NIE\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int query(int queryLeft, int queryRight) {
		return query(1, 0, sz - 1, queryLeft, queryRight);
	}

	private static int query(int node, int left, int right, int queryLeft, int queryRight) {
		lazyPropagate(node, left, right);
		if (queryRight < left || right < queryLeft)
			return Integer.MIN_VALUE;
		if (queryLeft <= left && right <= queryRight)
			return seg[node];
		int mid = (left + right) >> 1;
		return Math.max(query(node << 1, left, mid, queryLeft, queryRight),
					query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
	}

	private static void update(int updateLeft, int updateRight, int value) {
		update(1, 0, sz - 1, updateLeft, updateRight, value);
	}

	private static void update(int node, int left, int right, int updateLeft, int updateRight, int value) {
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
			seg[node] = Math.max(seg[node << 1], seg[(node << 1) | 1]);
		}
	}

	private static void lazyPropagate(int node, int left, int right) {
		if (lazy[node] != 0) {
			seg[node] += lazy[node];
			if (node < sz) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) | 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	private static void initialize(int[] arr, int n) {
		for (int x: arr) {
			++seg[sz + x];
		}

		for (int i = 1; i <= n; ++i)
			seg[sz + i] += seg[sz + i - 1];

		for (int i = 0; i <= n; ++i)
			seg[sz + i] -= i;

		for (int i = sz - 1; i > 0; --i) {
			seg[i] = Math.max(seg[i << 1], seg[(i << 1) | 1]);
		}
	}
}
