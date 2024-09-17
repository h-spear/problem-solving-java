// https://www.acmicpc.net/problem/14897

package baekjoon.mos;

import java.io.*;
import java.util.*;

public class DifferentNumberAndQuery1 {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		coordinateCompression(arr, N);

		int Q = Integer.parseInt(br.readLine());
		Query[] queries = new Query[Q];
		for (int i = 0; i < Q; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()) - 1;
			int r = Integer.parseInt(st.nextToken()) - 1;
			queries[i] = new Query(l, r, i);
		}

		final int MAX_NUM = 1_000_100;
		final int sqrtN = (int) Math.sqrt(N);

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.l / sqrtN;
			int block2 = o2.l / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.r, o2.r);
		});

		int[] counter = new int[MAX_NUM];
		int[] answer = new int[Q];
		int diffs = 0;

		Query query0 = queries[0];
		int left = query0.l;
		int right = query0.r;
		for (int i = left; i <= right; ++i) {
			if (++counter[arr[i]] == 1)
				++diffs;
		}
		answer[query0.order] = diffs;

		for (int i = 1; i < Q; ++i) {
			int l = queries[i].l;
			int r = queries[i].r;
			int order = queries[i].order;

			while (left < l) {
				if (--counter[arr[left++]] == 0)
					--diffs;
			}
			while (left > l) {
				if (++counter[arr[--left]] == 1)
					++diffs;
			}
			while (right < r) {
				if (++counter[arr[++right]] == 1)
					++diffs;
			}
			while (right > r) {
				if (--counter[arr[right--]] == 0)
					--diffs;
			}
			answer[order] = diffs;
		}

		StringBuilder sb = new StringBuilder();
		for (int x: answer)
			sb.append(x).append("\n");
		System.out.println(sb.toString());
	    br.close();
	}

	private static void coordinateCompression(int[] arr, int n) {
		int[] cloned = Arrays.stream(arr).distinct().toArray();
		Arrays.sort(cloned);

		for (int i = 0; i < n; ++i) {
			arr[i] = lowerBound(cloned, arr[i]);
		}
	}

	private static int lowerBound(int[] arr, int k) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (arr[mid] >= k) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return right + 1;
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
