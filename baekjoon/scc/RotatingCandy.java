// https://www.acmicpc.net/problem/1471

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class RotatingCandy {

	private static final Deque<Integer> stack = new ArrayDeque<>();
	private static final int[] visited = new int[200020];
	private static final boolean[] finished = new boolean[200020];
	private static final int[] cache = new int[200020];
	private static final List<List<Integer>> scc = new ArrayList<>();
	private static int id;
	private static int N;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		Arrays.fill(cache, -1);

		for (int i = 0; i < N; ++i) {
			if (!finished[i])
				dfs(i);
		}

		for (List<Integer> component: scc) {
			if (component.size() > 1) {
				for (int node: component)
					cache[node] = component.size();
			}
		}

		for (int i = 0; i < N; ++i) {
			if (cache[i] == -1)
				dp(i);
		}
		System.out.println(max(cache, N));
	    br.close();
	}

	private static int max(int[] nums, int n) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; ++i) {
			max = Math.max(max, nums[i]);
		}
		return max;
	}

	private static int dp(int x) {
		int next = next(x);
		if (x == next)
			return cache[x] = 1;
		if (cache[x] != -1)
			return cache[x];
		return cache[x] = dp(next) + 1;
	}

	private static int dfs(int x) {
		int parent = visited[x] = ++id;
		stack.push(x);

		int next = next(x);
		if (visited[next] == 0)
			parent = Math.min(parent, dfs(next));
		else if (!finished[next])
			parent = Math.min(parent, visited[next]);

		if (parent == visited[x]) {
			List<Integer> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				int node = stack.pop();
				component.add(node);
				finished[node] = true;
				if (node == x)
					break;
			}
			scc.add(component);
		}
		return parent;
	}

	private static int next(int x) {
		return (x + getSumOfDigits(x + 1)) % N;
	}

	private static int getSumOfDigits(int number) {
		int sum = 0;
		while (number > 0) {
			sum += number % 10;
			number /= 10;
		}
		return sum;
	}
}
