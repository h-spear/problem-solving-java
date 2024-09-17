// https://www.acmicpc.net/problem/12999

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class ColorfulTown {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		final int BASE = 100_000;
		final int MAX_NUM = 200_020;
		final int MAX_COUNT = 100_010;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] P = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			P[i] = Integer.parseInt(st.nextToken()) + BASE;
		}

		Query[] queries = new Query[Q];
		for (int i = 0; i < Q; ++i) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()) - 1;
			int Y = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new Query(X, Y, i);
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
		int[] counter = new int[MAX_NUM];
		int[] maxCounter = new int[MAX_COUNT];
		int maxx = 0;

		Query query0 = queries[0];
		int left = query0.x;
		int right = query0.y;
		for (int i = left; i <= right; ++i) {
			int c = ++counter[P[i]];
			++maxCounter[c];
			if (c > 1)
				--maxCounter[c - 1];
			maxx = Math.max(maxx, c);
		}
		answer[query0.order] = maxx;

		for (int i = 1; i < Q; ++i) {
			int x = queries[i].x;
			int y = queries[i].y;
			int order = queries[i].order;

			while (left > x) {
				int c = ++counter[P[--left]];
				--maxCounter[c - 1];
				++maxCounter[c];
				if (maxx < c)
					maxx = c;
			}
			while (right < y) {
				int c = ++counter[P[++right]];
				--maxCounter[c - 1];
				++maxCounter[c];
				if (maxx < c)
					maxx = c;
			}
			while (left < x) {
				int c = --counter[P[left++]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1)
					maxx = c;
			}
			while (right > y) {
				int c = --counter[P[right--]];
				--maxCounter[c + 1];
				++maxCounter[c];
				if (maxCounter[c + 1] == 0 && maxx == c + 1)
					maxx = c;
			}
			answer[order] = maxx;
		}

		StringBuilder sb = new StringBuilder();
		for (int x: answer) {
			sb.append(x).append("\n");
		}
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
