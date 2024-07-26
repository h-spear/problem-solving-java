// https://www.acmicpc.net/problem/18437

package baekjoon.segmenttree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CompanyCulture5 {

	private static List<List<Integer>> tree;
	private static int[] in, out;
	private static int count;
	private static int sz;
	private static int[] seg;
	private static int[] lazy;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		in = new int[N + 1];
		out = new int[N + 1];
		tree = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			tree.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			int par = Integer.parseInt(st.nextToken());
			tree.get(par).add(i);
		}

		sz = 1;
		while (sz <= (N + 1))
			sz <<= 1;
		seg = new int[sz << 1];
		lazy = new int[sz << 1];
		Arrays.fill(lazy, -1);

		dfs(0);
		update(1, 0, sz - 1, in[0], out[0], 1);

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			if (q == 1) {
				update(1, 0, sz - 1, in[i] + 1, out[i], 1);
			} else if (q == 2) {
				update(1, 0, sz - 1, in[i] + 1, out[i], 0);
			} else {
				sb.append(query(1, 0, sz - 1, in[i] + 1, out[i]))
					.append("\n");
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int query(int node, int left, int right, int queryLeft, int queryRight) {
		lazyPropagate(node, left, right);
		if (queryRight < left || right < queryLeft)
			return 0;
		if (queryLeft <= left && right <= queryRight)
			return seg[node];
		int mid = (left + right) >> 1;
		return query(node << 1, left, mid, queryLeft, queryRight)
				+ query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
	}

	private static void update(int node, int left, int right, int updateLeft, int updateRight, int value) {
		lazyPropagate(node, left, right);
		if (updateRight < left || right < updateLeft)
			return;
		if (updateLeft <= left && right <= updateRight) {
			lazy[node] = value;
			lazyPropagate(node, left, right);
		} else {
			int mid = (left + right) >> 1;
			update(node << 1, left, mid, updateLeft, updateRight, value);
			update((node << 1) | 1, mid + 1, right, updateLeft, updateRight, value);
			seg[node] = seg[node << 1] + seg[(node << 1) | 1];
		}
	}

	private static void lazyPropagate(int node, int left, int right) {
		if (lazy[node] != -1) {
			seg[node] = lazy[node] * (right - left + 1);
			if (node < sz) {
				lazy[node << 1] = lazy[node];
				lazy[(node << 1) | 1] = lazy[node];
			}
			lazy[node] = -1;
		}
	}

	private static void dfs(int x) {
		in[x] = ++count;
		for (int next: tree.get(x)) {
			dfs(next);
		}
		out[x] = count;
	}
}
