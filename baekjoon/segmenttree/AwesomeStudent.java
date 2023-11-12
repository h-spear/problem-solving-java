// // https://www.acmicpc.net/problem/2336

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class AwesomeStudent {

	private static final int INF = 1_000_000;
	private static int N, S;
	private static int[] tree;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		S = 1;
		while (S < N)
			S <<= 1;
		tree = new int[S << 1];

		int[][] students = new int[N][3];
		for (int i = 0; i < 3; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				students[Integer.parseInt(st.nextToken()) - 1][i] = j;
			}
		}

		Arrays.sort(students, (o1, o2) -> Integer.compare(o1[0], o2[0]));
		Arrays.fill(tree, INF);

		int answer = 0;
		for (int[] student: students) {
			int rank2 = student[1];
			int rank3 = student[2];
			int r = query(1, 0, S - 1, 0, rank2);
			if (rank3 < r) {
				++answer;
			}
			update(rank2, rank3);
		}
		System.out.println(answer);
	    br.close();
	}

	private static int query(int node, int left, int right, int queryLeft, int queryRight) {
		if (queryRight < left || right < queryLeft) {
			return INF;
		}
		if (queryLeft <= left && right <= queryRight) {
			return tree[node];
		}
		int mid = (left + right) >> 1;
		return Math.min(query(node << 1, left, mid, queryLeft, queryRight),
			query((node << 1) | 1, mid + 1, right,  queryLeft, queryRight));
	}

	private static void update(int index, int value) {
		int i = S + index;
		if (tree[i] > value) {
			tree[i] = value;
			i >>= 1;
			while (i > 0) {
				tree[i] = Math.min(tree[i << 1], tree[(i << 1) | 1]);
				i >>= 1;
			}
		}
	}
}
