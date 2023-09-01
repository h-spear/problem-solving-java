// https://www.acmicpc.net/problem/16404
/**
 * 느리게 갱신되는 세그먼트 트리
 * 		+ 오일러 경로 테크닉
 */
package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CorporationSeungBeom {

	private static int N, S;
	private static int[] segTree;
	private static int[] lazy;
	private static List<List<Integer>> tree;
	private static int[] in, out;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		S = 1;
		while (S < N)
			S <<= 1;
		segTree = new int[S << 1];
		lazy = new int[S << 1];
		in = new int[N + 1];
		out = new int[N + 1];

		tree = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			tree.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for (int i = 2, j; i <= N; ++i) {
			j = Integer.parseInt(st.nextToken());
			tree.get(j).add(i);
		}

		dfs(1);

		int c, i, w;
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			if (c == 1) {
				w = Integer.parseInt(st.nextToken());
				update(1, 0, S - 1, in[i], out[i], w);
			} else {
				sb.append(query(1, 0, S - 1, in[i])).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int query(int node, int left, int right, int target) {
		propagate(node, left, right);
		if (target < left || right < target) {
			return 0;
		}
		if (left == right) {
			return segTree[node];
		} else {
			int mid = (left + right) >> 1;
			return query(node << 1, left, mid, target) +
				query((node << 1) + 1, mid + 1, right, target);
		}
	}

	private static void update(int node, int left, int right, int updateLeft, int updateRight, int diff) {
		propagate(node, left, right);
		if (updateRight < left || right < updateLeft) {
			return;
		}

		if (updateLeft <= left && right <= updateRight) {
			lazy[node] += diff;
			return;
		}

		int mid = (left + right) >> 1;
		update(node << 1, left, mid, updateLeft, updateRight, diff);
		update((node << 1) + 1, mid + 1, right, updateLeft, updateRight, diff);
	}

	private static void propagate(int node, int left, int right) {
		if (lazy[node] != 0) {
			segTree[node] += lazy[node] * (right - left + 1);
			if (left != right) {
				lazy[node << 1] += lazy[node];
				lazy[(node << 1) + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	private static void dfs(int curr) {
		in[curr] = cnt;
		for (int next : tree.get(curr)) {
			++cnt;
			dfs(next);
		}
		out[curr] = cnt;
	}
}
