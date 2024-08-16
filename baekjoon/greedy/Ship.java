// https://www.acmicpc.net/problem/1092

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Ship {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<Integer> cranes = new ArrayList<>(N);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			cranes.add(Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());
		List<Integer> boxes = new ArrayList<>(M);
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; ++i) {
			boxes.add(Integer.parseInt(st.nextToken()));
		}

		cranes.sort(((o1, o2) -> Integer.compare(o2, o1)));
		boxes.sort(((o1, o2) -> Integer.compare(o2, o1)));

		if (boxes.get(0) > cranes.get(0)) {
			System.out.println(-1);
		} else {
			int move = 0;
			while (!boxes.isEmpty()) {
				++move;
				for (int crane: cranes) {
					for (int j = 0, sz = boxes.size(); j < sz; ++j) {
						if (crane >= boxes.get(j)) {
							boxes.remove(j);
							break;
						}
					}
				}
			}
			System.out.println(move);
		}
	    br.close();
	}
}
