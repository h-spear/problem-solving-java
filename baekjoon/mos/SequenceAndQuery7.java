package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SequenceAndQuery7 {

	private static int N, K;
	private static int[] A = new int[100_100];
	private static int[] counter = new int[100_100];
	private static Deque<Integer>[] dq = new Deque[1_000_100];
	private static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; ++i) {
			A[i + 1] = (A[i + 1] + A[i]) % K;
		}

		for (int i = 0; i <= K; ++i) {
			dq[i] = new ArrayDeque<>();
		}

		int M = Integer.parseInt(br.readLine());
		Query[] queries = new Query[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken());
			queries[i] = new Query(l, r, i);
		}

		int sqrtN = (int) Math.sqrt(N);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.l / sqrtN;
			int block2 = o2.l / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.r, o2.r);
		});

		int[] answer = new int[M];

		Query query0 = queries[0];
		int left = query0.l;
		int right = query0.r;
		for (int i = left; i <= right; ++i) {
			insertRight(i);
		}
		answer[query0.order] = res;


		for (int i = 1; i < M; ++i) {
			int l = queries[i].l;
			int r = queries[i].r;
			int order = queries[i].order;
			while (left > l) insertLeft(--left);
			while (right < r) insertRight(++right);
			while (left < l) eraseLeft(left++);
			while (right > r) eraseRight(right--);
			while (res > 0 && counter[res] == 0) --res;
			answer[order] = res;
		}

		StringBuilder sb = new StringBuilder();
		for (int x: answer)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
		br.close();
	}

	private static void eraseLeft(int i) {
		int v = A[i];
		if (dq[v].size() > 1) {
			int length = getLength(v);
			--counter[length];
		}
		dq[v].removeFirst();
		if (dq[v].size() > 1) {
			int length = getLength(v);
			++counter[length];
			res = Math.max(res, length);
		}
	}

	private static void eraseRight(int i) {
		int v = A[i];
		if (dq[v].size() > 1) {
			int length = getLength(v);
			--counter[length];
		}
		dq[v].removeLast();
		if (dq[v].size() > 1) {
			int length = getLength(v);
			++counter[length];
			res = Math.max(res, length);
		}
	}

	private static void insertLeft(int i) {
		int v = A[i];
		if (dq[v].size() > 1) {
			int length = getLength(v);
			--counter[length];
		}
		dq[v].addFirst(i);
		if (dq[v].size() > 1) {
			int length = getLength(v);
			++counter[length];
			res = Math.max(res, length);
		}
	}

	private static void insertRight(int i) {
		int v = A[i];
		if (dq[v].size() > 1) {
			int length = getLength(v);
			--counter[length];
		}
		dq[v].addLast(i);
		if (dq[v].size() > 1) {
			int length = getLength(v);
			++counter[length];
			res = Math.max(res, length);
		}
	}

	private static int getLength(int v) {
		if (dq[v].isEmpty())
			return 0;
		return Math.abs(dq[v].peekLast() - dq[v].peekFirst());
	}

	private static class Query {
		int l, r, order;

		Query(int l, int r, int order) {
			this.l = l;
			this.r = r;
			this.order = order;
		}
	}
}
