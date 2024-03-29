// https://www.acmicpc.net/problem/1700

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class MultitapScheduling {

	private static int N, K;
	private static int[] items;
	private static List<Integer> tap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		items = new int[K];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; ++i) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		tap = new ArrayList<>(N);
		int answer = 0;
		for (int i = 0; i < K; ++i) {
			if (!tap.contains(items[i])) {
				if (tap.size() < N) {
					tap.add(items[i]);
				} else {
					int idx = findOptimalIndex(i);
					tap.set(idx, items[i]);
					++answer;
				}
			}
		}
		System.out.println(answer);
		br.close();
	}

	private static int findOptimalIndex(int fromIndex) {
		int[] checker = new int[K + 1];
		Arrays.fill(checker, Integer.MAX_VALUE);
		for (int item: tap) {
			for (int i = fromIndex + 1; i < K; ++i) {
				if (items[i] == item) {
					checker[item] = i;
					break;
				}
			}
		}

		int maxIdx = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; ++i) {
			if (checker[tap.get(i)] > max) {
				max = checker[tap.get(i)];
				maxIdx = i;
			}
		}
		return maxIdx;
	}
}