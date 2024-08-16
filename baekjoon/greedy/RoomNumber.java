// https://www.acmicpc.net/problem/1082

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class RoomNumber {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] P = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());

		Queue<Integer> queue = new ArrayDeque<>();

		if (N == 1)			// 경계값 주의
			System.out.println(0);
		else {
			int a = minIdx(P, 1, N);
			queue.add(a);
			M -= P[a];

			int b = minIdx(P, 0, N);
			while (M >= P[b]) {
				queue.add(b);
				M -= P[b];
			}

			List<Integer> answer = new ArrayList<>();
			while (!queue.isEmpty()) {
				int now = queue.poll();
				for (int i = N - 1; i >= 0; --i) {
					if (P[i] - P[now] <= M) {
						M -= (P[i] - P[now]);
						now = i;
						break;
					}
				}
				answer.add(now);
			}

			StringBuilder sb = new StringBuilder();
			for (int x: answer)
				sb.append(x);
			System.out.println(sb.toString());
		}
		br.close();
	}

	private static int minIdx(int[] nums, int a, int b) {
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		for (int i = a; i < b; ++i) {
			if (min > nums[i]) {
				min = nums[i];
				minIdx = i;
			}
		}
		return minIdx;
	}
}
