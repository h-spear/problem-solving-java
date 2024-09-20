// https://www.acmicpc.net/problem/13554

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SequenceAndQuery9 {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[100_100];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int[] B = new int[100_100];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			B[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		Query[] queries = new Query[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			Query query = new Query();
			query.i = Integer.parseInt(st.nextToken()) - 1;
			query.j = Integer.parseInt(st.nextToken()) - 1;
			query.k = Integer.parseInt(st.nextToken());
			query.order = i;
			queries[i] = query;
		}

		int sqrtN = (int) Math.sqrt(N);
		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.i / sqrtN;
			int block2 = o2.i / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.j, o2.j);
		});

		long[] answer = new long[M];

		FenwickTree ft1 = new FenwickTree();
		FenwickTree ft2 = new FenwickTree();

		Query query0 = queries[0];
		int left = query0.i;
		int right = query0.j;
		for (int i = left; i <= right; ++i) {
			ft1.update(A[i], +1);
			ft2.update(B[i], +1);
		}
		answer[query0.order] = calc(ft1, ft2, query0.k);

		for (int _i = 1; _i < M; ++_i) {
			int i = queries[_i].i;
			int j = queries[_i].j;
			int k = queries[_i].k;
			int order = queries[_i].order;

			while (left < i) {
				ft1.update(A[left], -1);
				ft2.update(B[left], -1);
				++left;
			}
			while (left > i) {
				--left;
				ft1.update(A[left], 1);
				ft2.update(B[left], 1);
			}
			while (right < j) {
				++right;
				ft1.update(A[right], 1);
				ft2.update(B[right], 1);
			}
			while (right > j) {
				ft1.update(A[right], -1);
				ft2.update(B[right], -1);
				--right;
			}
			answer[order] = calc(ft1, ft2, k);
		}

		StringBuilder sb = new StringBuilder();
		for (long x: answer)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static long calc(FenwickTree ft1, FenwickTree ft2, int k) {
		long ret = 0;
		int sqrtK = (int) Math.sqrt(k);
		for (int i = 1; i <= sqrtK; ++i) {
			ret += ft1.query(i, i) * ft2.query(1, k / i) + ft2.query(i, i) * ft1.query(sqrtK + 1, k / i);
		}
		return ret;
	}

	private static class FenwickTree {
		final int sz = 100_100;
		final int[] tree = new int[sz];

		void update(int i, int x) {
			while (i < 100_100) {
				tree[i] += x;
				i += (i & -i);
			}
		}

		long sum(int i) {
			long ret = 0;
			while (i > 0) {
				ret += tree[i];
				i -= (i & -i);
			}
			return ret;
		}

		long query(int l, int r) {
			return sum(r) - sum(l - 1);
		}
	}

	private static class Query {
		int i, j, k, order;
	}
}
