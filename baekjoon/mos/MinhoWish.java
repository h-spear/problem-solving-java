// https://www.acmicpc.net/problem/13028

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class MinhoWish {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		Query[] queries = new Query[Q];
		for (int i = 0; i < Q; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new Query(x, y, i);
		}

		final int sqrtN = (int) Math.sqrt(N);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.x / sqrtN;
			int block2 = o2.x / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.y, o2.y);
		});

		int[] answer = new int[Q];
		int[] counter = new int[100_100];
		int res = 0;

		Query query0 = queries[0];
		int left = query0.x;
		int right = query0.y;
		for (int i = left; i <= right; ++i) {
			if (++counter[A[i]] == 3)
				++res;
		}
		answer[query0.order] = res;

		for (int i = 1; i < Q; ++i) {
			int x = queries[i].x;
			int y = queries[i].y;
			int order = queries[i].order;

			while (left < x) {
				if (--counter[A[left++]] == 2)
					--res;
			}
			while (left > x) {
				if (++counter[A[--left]] == 3)
					++res;
			}
			while (right < y) {
				if (++counter[A[++right]] == 3)
					++res;
			}
			while (right > y) {
				if (--counter[A[right--]] == 2)
					--res;
			}
			answer[order] = res;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < Q; ++i)
			sb.append(answer[i]).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static class Query {
		int x, y, order;

		Query(int x, int y, int order) {
			this.x = x;
			this.y = y;
			this.order = order;
		}
	}
}
