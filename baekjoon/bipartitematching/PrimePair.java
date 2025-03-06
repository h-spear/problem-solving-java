// https://www.acmicpc.net/problem/18138

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class PrimePair {

	private static final int MAX_NUM = 2025;
	private static final boolean[] isPrime = getPrimes(MAX_NUM);
	private static final List<List<Integer>> graph = new ArrayList<>(MAX_NUM);
	private static final int[] pair = new int[MAX_NUM];
	private static final int[] visited = new int[MAX_NUM];
	private static int callCount = 0;

	static {
		for (int i = 0; i < MAX_NUM; ++i) {
			graph.add(new ArrayList<>());
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int firstNumber = nums[0];
		boolean isFirstNumberEven = isEven(firstNumber);
		for (int i = 0; i < N; ++i) {
			for (int j = i + 1; j < N; ++j) {
				if (!isPrime[nums[i] + nums[j]]) {
					continue;
				}
				int odd = nums[i], even = nums[j];
				if (isEven(odd)) {
					int temp = odd;
					odd = even;
					even = temp;
				}

				if (isFirstNumberEven) {
					graph.get(even).add(odd);
				} else {
					graph.get(odd).add(even);
				}
			}
		}

		List<Integer> answer = new ArrayList<>();
		for (int pairNumber: graph.get(firstNumber)) {
			if (test(firstNumber, pairNumber) == N >> 1) {
				answer.add(pairNumber);
			}
		}
		Collections.sort(answer);
		print(answer);
		br.close();
	}

	private static void print(List<Integer> list) {
		if (list.isEmpty()) {
			System.out.println(-1);
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (int x: list)
			sb.append(x).append(" ");
		System.out.println(sb.toString());
	}

	private static int test(int one, int two) {
		int match = 1;
		Arrays.fill(pair, 0);
		pair[two] = one;

		for (int i = 1; i < MAX_NUM; ++i) {
			if (i == one)
				continue;
			if (!graph.get(i).isEmpty()) {
				++callCount;
				if (dfs(i, one))
					++match;
			}
		}
		return match;
	}

	private static boolean dfs(int x, int ban) {
		if (visited[x] == callCount)
			return false;
		visited[x] = callCount;

		for (int next: graph.get(x)) {
			if (pair[next] == 0 || (pair[next] != ban && dfs(pair[next], ban))) {
				pair[next] = x;
				return true;
			}
		}
		return false;
	}

	private static boolean isEven(int number) {
		return (number & 1) == 0;
	}

	private static boolean[] getPrimes(int bound) {
		boolean[] isPrime = new boolean[bound + 1];
		Arrays.fill(isPrime, 2, bound + 1, true);
		for (int i = 2; i <= bound; ++i) {
			if (isPrime[i]) {
				for (int j = i * 2; j <= bound; j += i) {
					isPrime[j] = false;
				}
			}
		}
		return isPrime;
	}
}
