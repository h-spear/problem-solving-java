// https://www.acmicpc.net/problem/25462

package baekjoon.mos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Inverzije {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] P = new int[N + 1];
		int[] tree = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; ++i) {
			P[i] = Integer.parseInt(st.nextToken());
		}

		coordinateCompression(P, N + 1);

		Query[] queries = new Query[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			queries[i] = new Query(a, b, i);
		}

		int sqrtN = (int) Math.sqrt(N);
		long[] answer = new long[M];

		Arrays.sort(queries, (o1, o2) -> {
			int block1 = o1.a / sqrtN;
			int block2 = o2.a / sqrtN;
			if (block1 != block2)
				return Integer.compare(block1, block2);
			return Integer.compare(o1.b, o2.b);
		});

		Query query0 = queries[0];
		int left = query0.a;
		int right = query0.b;
		long res = 0;
		for (int i = left; i <= right; ++i) {
			res += sum(tree, N) - sum(tree, P[i]);
			update(tree, P[i], +1);
		}
		answer[query0.order] = res;

		for (int i = 1; i < M; ++i) {
			int s = queries[i].a;
			int e = queries[i].b;
			int order = queries[i].order;

			while (left < s) {
				res -= sum(tree, P[left] - 1);
				update(tree, P[left], -1);
				++left;
			}
			while (left > s) {
				--left;
				res += sum(tree, P[left] - 1);
				update(tree, P[left], +1);
			}
			while (right < e) {
				++right;
				res += sum(tree, N) - sum(tree, P[right]);
				update(tree, P[right], +1);
			}
			while (right > e) {
				res -= sum(tree, N) - sum(tree, P[right]);
				update(tree, P[right], -1);
				--right;
			}
			answer[order] = res;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; ++i) {
			sb.append(answer[i]).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static void update(int[] tree, int i, int diff) {
		int sz = tree.length;
		while (i < sz) {
			tree[i] += diff;
			i += (i & -i);
		}
	}

	private static int sum(int[] tree, int i) {
		int ret = 0;
		while (i > 0) {
			ret += tree[i];
			i -= (i & -i);
		}
		return ret;
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
		int a, b, order;

		Query(int a, int b, int order) {
			this.a = a;
			this.b = b;
			this.order = order;
		}
	}
}
