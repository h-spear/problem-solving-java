// https://www.acmicpc.net/problem/13553

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SequenceAndQuery8 {

	private static int N;
	private static int[] A;
	private static int[] tree = new int[100_100];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		Query[] queries = new Query[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			queries[i] = new Query(l, r, i);
		}

		final int sqrtN = (int) Math.sqrt(N);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.l / sqrtN;
			int block2 = o2.l / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.r, o2.r);
		});

		long[] answer = new long[M];
		long res = 0;

		Query query0 = queries[0];
		int left = query0.l;
		int right = query0.r;
		for (int i = left; i <= right; ++i) {
			res += sum(A[i] + K) - sum(A[i] - K - 1);
			update(A[i], +1);
		}
		answer[query0.order] = res;

		for (int i = 1; i < M; ++i) {
			int l = queries[i].l;
			int r = queries[i].r;
			int order = queries[i].order;

			while (left < l) {
				update(A[left], -1);
				res -= sum(A[left] + K) - sum(A[left] - K - 1);
				++left;
			}
			while (left > l) {
				--left;
				res += sum(A[left] + K) - sum(A[left] - K - 1);
				update(A[left], +1);
			}
			while (right < r) {
				++right;
				res += sum(A[right] + K) - sum(A[right] - K - 1);
				update(A[right], +1);
			}
			while (right > r) {
				update(A[right], -1);
				res -= sum(A[right] + K) - sum(A[right] - K - 1);
				--right;
			}
			answer[order] = res;
		}

		StringBuilder sb = new StringBuilder();
		for (long x: answer)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static void update(int i, int diff) {
		while (i < 100_100) {
			tree[i] += diff;
			i += (i & -i);
		}
	}

	private static int sum(int i) {
		if (i < 0)
			return 0;
		if (i > 100_000)
			i = 100_000;
		int ret = 0;
		while (i > 0) {
			ret += tree[i];
			i -= (i & -i);
		}
		return ret;
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
