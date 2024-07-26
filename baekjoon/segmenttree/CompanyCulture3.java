// https://www.acmicpc.net/problem/14287

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CompanyCulture3 {

	private static List<List<Integer>> tree;
	private static int[] in, out;
	private static int count;
	private static int sz;
	private static int[] seg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		in = new int[n + 1];
		out = new int[n + 1];
		tree = new ArrayList<>();
		for (int i = 0; i <= n; ++i)
			tree.add(new ArrayList<>());

		sz  = 1;
		while (sz < n)
			sz <<= 1;
		seg = new int[sz << 1];

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2; i <= n; ++i) {
			int par = Integer.parseInt(st.nextToken());
			tree.get(par).add(i);
		}

		dfs(1);
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			int w = q == 1 ? Integer.parseInt(st.nextToken()) : -1;
			if (q == 1) {
				update(in[i], w);
			} else {
				sb.append(query(1, 0, sz - 1, in[i], out[i]))
					.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int query(int node, int left, int right, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft)
			return 0;
		if (queryLeft <= left && right <= queryRight)
			return seg[node];
		int mid = (left + right) >> 1;
		return query(node << 1, left, mid, queryLeft, queryRight)
				+ query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
	}

	private static void update(int target, int value) {
		int i = sz + target;
		while (i > 0) {
			seg[i] += value;
			i >>= 1;
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
