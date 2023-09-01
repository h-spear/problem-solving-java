// https://www.acmicpc.net/problem/2820

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class CarFactory {

	private static int N, S;
	private static List<List<Integer>> tree;
	private static long[] segTree;
	private static long[] lazy;
	private static int[] in, out;
	private static int[] salary;
	private static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		S = 1;
		while (S < N) {
			S <<= 1;
		}
		segTree = new long[S << 1];
		lazy = new long[S << 1];
		in = new int[N + 1];
		out = new int[N + 1];
		salary = new int[N + 1];

		tree = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; ++i)
			tree.add(new ArrayList<>());

		salary[1] = Integer.parseInt(br.readLine());
		for (int i = 2; i <= N; ++i) {
			st = new StringTokenizer(br.readLine());
			salary[i] = Integer.parseInt(st.nextToken());
			tree.get(Integer.parseInt(st.nextToken())).add(i);
		}

		dfs(1);
		initialize();

		StringBuilder sb = new StringBuilder();
		char c;
		int a, x;
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			c = st.nextToken().charAt(0);
			a = Integer.parseInt(st.nextToken());
			if (c == 'p') {
				x = Integer.parseInt(st.nextToken());
				update(1, 0, S - 1, in[a], out[a], x);
				update(1, 0, S - 1, in[a], in[a], -x);
			} else {
				sb.append(query(1, 0, S - 1, in[a])).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void initialize() {
		for (int i = 1; i <= N; ++i) {
			segTree[S + in[i]] = salary[i];
		}
		for (int i = S - 1; i > 0; --i) {
			segTree[i] = segTree[i << 1] + segTree[(i << 1) + 1];
		}
	}

	private static long query(int node, int left, int right, int target) {
		propagate(node, left, right);
		if (target < left || right < target) {
			return 0;
		}
		if (left == right) {
			return segTree[node];
		}
		int mid = (left + right) >> 1;
		return query(node << 1, left, mid, target)
				+ query((node << 1) + 1, mid + 1, right, target);
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
		for (int next: tree.get(curr)) {
			++cnt;
			dfs(next);
		}
		out[curr] = cnt;
	}
}
