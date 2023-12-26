// https://www.acmicpc.net/problem/13975

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class MergeFiles3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		Queue<Long> heap = new PriorityQueue<>();
		while (T-- > 0) {
			int k = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; ++i) {
				heap.add(Long.parseLong(st.nextToken()));
			}

			long answer = 0;
			while (heap.size() > 1) {
				long first = heap.remove();
				long second = heap.remove();
				answer += first + second;
				heap.add(first + second);
			}
			sb.append(answer).append("\n");
			heap.clear();
		}
		System.out.println(sb.toString());
		br.close();
	}
}