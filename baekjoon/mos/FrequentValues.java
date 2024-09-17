// https://www.acmicpc.net/problem/6515

package baekjoon.mos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FrequentValues {

	private static final int NUM_BASE = 100_000;
	private static final int MAX_NUM = 200_020;
	private static final int MAX_COUNT = 100_010;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringBuilder sb = new StringBuilder();
		while (solve(br, sb)) {}
		System.out.println(sb.toString());
	    br.close();
	}

	private static boolean solve(BufferedReader br, StringBuilder sb) throws IOException {
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		if (n == 0)
			return false;
		int q = Integer.parseInt(st.nextToken());

		int[] a = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; ++i) {
			a[i] = Integer.parseInt(st.nextToken()) + NUM_BASE;
		}

		Query[] queries = new Query[q];
		for (int index = 0; index < q; ++index) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			queries[index] = new Query(i, j, index);
		}

		final int sqrtN = (int) Math.sqrt(n);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.i / sqrtN;
			int block2 = o2.i / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.j, o2.j);
		});

		int[] answer = new int[q];
		int[] counter = new int[MAX_NUM];
		int[] maxCounter = new int[MAX_COUNT];
		int maxx = 0;

		Query query0 = queries[0];
		int left = query0.i;
		int right = query0.j;
		for (int i = left; i <= right; ++i) {
			int c = ++counter[a[i]];
			++maxCounter[c];
			if (c > 1)
				--maxCounter[c - 1];
			maxx = Math.max(maxx, c);
		}
		answer[query0.order] = maxx;

		for (int o = 1; o < q; ++o) {
			int i = queries[o].i;
			int j = queries[o].j;
			int order = queries[o].order;

			while (left > i) {
				int c = ++counter[a[--left]];
				--maxCounter[c - 1];
				++maxCounter[c];
				if (maxx < c)
					maxx = c;
			}
			while (right < j) {
				int c = ++counter[a[++right]];
				--maxCounter[c - 1];
				++maxCounter[c];
				if (maxx < c)
					maxx = c;
			}
			while (left < i) {
				int c = --counter[a[left++]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1)
					maxx = c;
			}
			while (right > j) {
				int c = --counter[a[right--]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1)
					maxx = c;
			}
			answer[order] = maxx;
		}

		for (int x: answer)
			sb.append(x).append("\n");
		return true;
	}

	private static class Query {
		int i, j, order;

		Query(int i, int j, int order) {
			this.i = i;
			this.j = j;
			this.order = order;
		}
	}
}
