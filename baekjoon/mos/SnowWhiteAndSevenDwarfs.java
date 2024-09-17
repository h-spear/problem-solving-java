// https://www.acmicpc.net/problem/2912

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class SnowWhiteAndSevenDwarfs {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] color = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			color[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		Query[] queries = new Query[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new Query(A, B, i);
		}

		int sqrtN = (int) Math.sqrt(N);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.a / sqrtN;
			int block2 = o2.a / sqrtN;
			if (block1 != block2) {
				return Integer.compare(block1, block2);
			}
			return Integer.compare(o1.b, o2.b);
		});

		int[] counter = new int[C + 1];
		int[] answer = new int[M];

		Query query0 = queries[0];
		int left = query0.a;
		int right = query0.b;
		for (int i = left; i <= right; ++i) {
			++counter[color[i]];
		}
		answer[query0.index] = isPretty(counter, C, right - left + 1);

		for (int i = 1; i < M; ++i) {
			int a = queries[i].a;
			int b = queries[i].b;
			int index = queries[i].index;

			while (left < a) --counter[color[left++]];
			while (left > a) ++counter[color[--left]];
			while (right < b) ++counter[color[++right]];
			while (right > b) --counter[color[right--]];
			answer[index] = isPretty(counter, C, right - left + 1);
		}

		StringBuilder sb = new StringBuilder();
		for (int x: answer) {
			if (x == -1)
				sb.append("no\n");
			else
				sb.append("yes ").append(x).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static int isPretty(int[] counter, int c, int k) {
		int k2 = k >> 1;
		for (int i = 1; i <= c; ++i) {
			if (counter[i] > k2)
				return i;
		}
		return -1;
	}

	private static class Query {
		int a, b, index;

		Query(int a, int b, int index) {
			this.a = a;
			this.b = b;
			this.index = index;
		}
	}
}
