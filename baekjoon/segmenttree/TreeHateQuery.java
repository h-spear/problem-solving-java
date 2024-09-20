// https://www.acmicpc.net/problem/20212
// Sparse Segment Tree

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class TreeHateQuery {

	private static Node[] tree = new Node[4040404];
	private static int sz = 0;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		nodeAllocate();

		int N = Integer.parseInt(br.readLine());
		List<Query> updateQueries = new ArrayList<>();
		List<Query> sumQueries = new ArrayList<>();
		int sumQueryCnt = 0;
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			Query query = new Query();
			int op = Integer.parseInt(st.nextToken());
			query.i = Integer.parseInt(st.nextToken());
			query.j = Integer.parseInt(st.nextToken());
			query.k = Integer.parseInt(st.nextToken());
			if (op == 1) {
				updateQueries.add(query);
			} else {
				query.order = sumQueryCnt++;
				sumQueries.add(query);
			}
		}
		sumQueries.sort((o1, o2) -> Integer.compare(o1.k, o2.k));

		int uIdx = 0;
		long[] result = new long[sumQueryCnt];
		for (Query sumQuery: sumQueries) {
			while (uIdx < sumQuery.k) {
				Query updateQuery = updateQueries.get(uIdx);
				update(0, 1, (int) 1e9, updateQuery.i, updateQuery.j, updateQuery.k);
				++uIdx;
			}
			result[sumQuery.order] = sum(0, 1, (int) 1e9, sumQuery.i, sumQuery.j);
		}

		StringBuilder sb = new StringBuilder();
		for (long x: result)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static void update(int node, int left, int right, int updateLeft, int updateRight, int diff) {
		lazyPropagate(node, left, right);
		if (node == -1)
			return;
		if (updateRight < left || right < updateLeft)
			return;
		if (updateLeft <= left && right <= updateRight) {
			tree[node].lazy += diff;
			lazyPropagate(node, left, right);
		} else {
			int mid = left + (right - left) / 2;
			if (tree[node].left == -1) tree[node].left = nodeAllocate();
			if (tree[node].right == -1) tree[node].right = nodeAllocate();
			update(tree[node].left, left, mid, updateLeft, updateRight, diff);
			update(tree[node].right, mid + 1, right, updateLeft, updateRight, diff);
			tree[node].value = tree[tree[node].left].value + tree[tree[node].right].value;
		}
	}

	private static long sum(int node, int left, int right, int queryLeft, int queryRight) {
		lazyPropagate(node, left, right);
		if (node == -1)
			return 0;
		if (queryRight < left || right < queryLeft)
			return 0;
		if (queryLeft <= left && right <= queryRight) {
			return tree[node].value;
		}
		int mid = left + (right - left) / 2;
		return sum(tree[node].left, left, mid, queryLeft, queryRight) +
				sum(tree[node].right, mid + 1, right, queryLeft, queryRight);
	}

	private static void lazyPropagate(int node, int left, int right) {
		if (node == -1)
			return;
		if (tree[node].lazy != 0) {
			tree[node].value += tree[node].lazy * (right - left + 1);
			if (left != right) {
				if (tree[node].left == -1) tree[node].left = nodeAllocate();
				if (tree[node].right == -1) tree[node].right = nodeAllocate();
				tree[tree[node].left].lazy += tree[node].lazy;
				tree[tree[node].right].lazy += tree[node].lazy;
			}
			tree[node].lazy = 0;
		}
	}

	private static int nodeAllocate() {
		tree[sz] = new Node();
		return sz++;
	}

	private static class Node {
		int left, right;
		long value, lazy;

		Node() {
			left = right = -1;
			value = lazy = 0;
		}
	}

	private static class Query {
		int i, j;
		int k, order;
	}
}
