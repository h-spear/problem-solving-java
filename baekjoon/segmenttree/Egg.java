// https://www.acmicpc.net/problem/11012
// Persistent Segment Tree

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Egg {

	private static final int MAX_SIZE = 100_100;
	private static Node[] root = new Node[MAX_SIZE];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			List<Integer>[] xs = new ArrayList[MAX_SIZE];
			for (int i = 0; i < MAX_SIZE; ++i) {
				xs[i] = new ArrayList<>();
			}

			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) + 1;
				int y = Integer.parseInt(st.nextToken()) + 1;
				xs[y].add(x);
			}

			root[0] = new Node();
			root[0].l = root[0].r = root[0];

			for (int y = 1; y < MAX_SIZE; ++y) {
				root[y] = root[y - 1];
				for (int x: xs[y]) {
					root[y] = add(root[y], 0, MAX_SIZE, x, 1);
				}
			}

			long answer = 0;
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken()) + 1;
				int r = Integer.parseInt(st.nextToken()) + 1;
				int b = Integer.parseInt(st.nextToken()) + 1;
				int t = Integer.parseInt(st.nextToken()) + 1;
				answer += query(root[t], 0, MAX_SIZE, l, r);
				answer -= query(root[b - 1], 0, MAX_SIZE, l, r);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static int query(Node node, int left, int right, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) {
			return 0;
		}
		if (queryLeft <= left && right <= queryRight) {
			return node.value;
		}
		int mid = left + (right - left) / 2;
		return query(node.l, left, mid, queryLeft, queryRight) +
				query(node.r, mid + 1, right, queryLeft, queryRight);
	}

	private static Node add(Node node, int left, int right, int index, int value) {
		if (index < left || right < index) {
			return node;
		}

		Node nn = new Node();
		if (left == right) {
			nn.value = node.value + value;
		} else {
			int mid = left + (right - left) / 2;
			nn.l = add(node.l, left, mid, index, value);
			nn.r = add(node.r, mid + 1, right, index, value);
			nn.value = nn.l.value + nn.r.value;
		}
		return nn;
	}

	private static class Node {
		Node l, r;
		int value;
	}
}