// https://www.acmicpc.net/problem/13557

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class SequenceAndQuery10 {

	private static class Node {
		long leftSum;
		long rightSum;
		long maxSum;
		long totalSum;

		public Node() {
		}

		public Node(long leftSum, long rightSum, long maxSum, long totalSum) {
			this.leftSum = leftSum;
			this.rightSum = rightSum;
			this.maxSum = maxSum;
			this.totalSum = totalSum;
		}
	}

	private static final long INF = (long) 1e17;
	private static final Node outNode = new Node(-INF, -INF, -INF, 0);
	private static int N, S;
	private static int[] A;
	private static Node[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = 1;
		while (S < N)
			S <<= 1;
		tree = new Node[S << 1];
		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		initialize();

		int M = Integer.parseInt(br.readLine());
		int x1, y1, x2, y2;
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			bw.write(solve(x1, y1, x2, y2) + "\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

	private static long solve(int x1, int y1, int x2, int y2) {
		if (y1 >= x2) {
			Node ovl = query(1, 0, S - 1, x2, y1);
			Node left = query(1, 0, S - 1, x1, x2 - 1);
			Node right = query(1, 0, S - 1, y1 + 1, y2);
			return max(ovl.maxSum,
					left.rightSum + ovl.leftSum,
					ovl.rightSum + right.leftSum,
					left.rightSum + ovl.totalSum + right.leftSum);
		} else {
			Node mid = query(1, 0, S - 1, y1 + 1, x2 - 1);
			Node left = query(1, 0, S - 1, x1, y1);
			Node right = query(1, 0, S - 1, x2, y2);
			return left.rightSum + mid.totalSum + right.leftSum;
		}
	}

	private static Node query(int node, int left, int right, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) {
			return outNode;
		}
		if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		int mid = (left + right) >> 1;
		return merge(query(node << 1, left, mid, queryLeft, queryRight),
				query((node << 1) | 1, mid + 1, right, queryLeft, queryRight));
	}

	private static void initialize() {
		for (int i = 0; i < N; ++i) {
			tree[S + i] = new Node(A[i], A[i], A[i], A[i]);
		}
		for (int i = N; i < S; ++i) {
			tree[S + i] = new Node();
		}
		for (int i = S - 1; i > 0; --i) {
			tree[i] = merge(tree[i << 1], tree[(i << 1) | 1]);
		}
	}

	private static Node merge(Node left, Node right) {
		Node node = new Node();
		node.leftSum = max(left.leftSum, left.totalSum + right.leftSum);
		node.rightSum = max(right.rightSum, left.rightSum + right.totalSum);
		node.maxSum = max(left.maxSum, right.maxSum, left.rightSum + right.leftSum);
		node.totalSum = left.totalSum + right.totalSum;
		return node;
	}

	private static long max(long... v) {
		long res = Long.MIN_VALUE;
		for (long val: v) {
			res = Math.max(res, val);
		}
		return res;
	}
}
