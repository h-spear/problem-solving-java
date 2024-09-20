// https://www.acmicpc.net/problem/14577

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class WeatherForecast {

	private static Node[] tree = new Node[4_000_400];
	private static int sz = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		nodeAllocate();

		long[] S = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			S[i] = Long.parseLong(st.nextToken());
			update(0, 0, (long) 1e18, S[i], +1);
		}

		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			if (op == 1) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int x = Integer.parseInt(st.nextToken());
				update(0, 0, (long) 1e18, S[i], -1);
				S[i] += x;
				update(0, 0, (long) 1e18, S[i], +1);
			} else if (op == 2) {
				int i = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken());
				update(0, 0, (long) 1e18, S[i], -1);
				S[i] = Math.max(S[i] - y, 0);
				update(0, 0, (long) 1e18, S[i], +1);
			} else if (op == 3) {
				long L = Long.parseLong(st.nextToken());
				long R = Long.parseLong(st.nextToken());
				sb.append(query(0, 0, (long) 1e18, L, R))
					.append("\n");
			} else {
				int T = Integer.parseInt(st.nextToken());
				sb.append(rankQuery(0, 0, (long) 1e18, N - T + 1))
					.append("\n");
			}
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static void update(int node, long left, long right, long index, long value) {
		if (left == right) {
			tree[node].value += value;
			return;
		}
		long mid = left + (right - left) / 2;
		if (index <= mid) {
			if (tree[node].left == -1)
				tree[node].left = nodeAllocate();
			update(tree[node].left, left, mid, index, value);
		} else {
			if (tree[node].right == -1)
				tree[node].right = nodeAllocate();
			update(tree[node].right, mid + 1, right, index, value);
		}
		long v1 = tree[node].left != -1 ? tree[tree[node].left].value : 0;
		long v2 = tree[node].right != -1 ? tree[tree[node].right].value : 0;
		tree[node].value = v1 + v2;
	}

	private static long query(int node, long left, long right, long queryLeft, long queryRight) {
		if (node == -1)
			return 0;
		if (queryRight < left || right < queryLeft)
			return 0;
		if (queryLeft <= left && right <= queryRight)
			return tree[node].value;
		long mid = left + (right - left) / 2;
		return query(tree[node].left, left, mid, queryLeft, queryRight) +
				query(tree[node].right, mid + 1, right, queryLeft, queryRight);
	}

	private static long rankQuery(int node, long left, long right, int T) {
		if (left == right)
			return left;
		long mid = left + (right - left) / 2;
		long leftValue = tree[node].left != -1 ? tree[tree[node].left].value : 0;
		if (leftValue >= T)
			return rankQuery(tree[node].left, left, mid, T);
		else
			return rankQuery(tree[node].right, mid + 1, right, (int) (T - leftValue));
	}

	private static int nodeAllocate() {
		tree[sz] = new Node();
		return sz++;
	}

	private static class Node {
		int left, right;
		long value;

		Node() {
			left = right = -1;
			value = 0;
		}
	}
}
