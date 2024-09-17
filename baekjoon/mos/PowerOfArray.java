// https://www.acmicpc.net/problem/8462

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class PowerOfArray {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		Query[] queries = new Query[t];
		for (int i = 0; i < t; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new Query(l, r, i);
		}

		final int MAX_NUM = 1_000_100;
		final int sqrtN = (int) Math.sqrt(n);

		int[] counter = new int[MAX_NUM];
		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.l / sqrtN;
			int block2 = o2.l / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.r, o2.r);
		});

		long[] answer = new long[t];
		long power = 0;

		Query query0 = queries[0];
		int left = query0.l;
		int right = query0.r;

		for (int i = left; i <= right; ++i) {
			int c = counter[a[i]]++;
			power -= (long) c * c * a[i];
			power += (long) (c + 1) * (c + 1) * a[i];
		}
		answer[query0.order] = power;

		for (int q = 1; q < t; ++q) {
			int l = queries[q].l;
			int r = queries[q].r;
			int order = queries[q].order;

			while (left < l) {
				power += delete(counter, a[left++]);
			}
			while (left > l) {
				power += add(counter, a[--left]);
			}
			while (right < r) {
				power += add(counter, a[++right]);
			}
			while (right > r) {
				power += delete(counter, a[right--]);
			}
			answer[order] = power;
		}

		StringBuilder sb = new StringBuilder();
		for (long x: answer) {
			sb.append(x).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static long add(int[] counter, int num) {
		int c = counter[num]++;
		return ((long) (c + 1) * (c + 1) * num) - ((long) c * c * num);
	}

	private static long delete(int[] counter, int num) {
		int c = counter[num]--;
		return ((long) (c - 1) * (c - 1) * num) - ((long) c * c * num);
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
