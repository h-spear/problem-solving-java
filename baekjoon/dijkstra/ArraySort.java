// https://www.acmicpc.net/problem/28707

package baekjoon.dijkstra;

import java.io.*;
import java.util.*;

public class ArraySort {

	private static final int INF = 123456789;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int A = 0;
		for (int i = 0; i < N; ++i) {
			A = 10 * A + (Integer.parseInt(st.nextToken()) - 1);
		}

		char[] ca = String.valueOf(A).toCharArray();
		Arrays.sort(ca);
		int target = Integer.parseInt(String.valueOf(ca));

		int M = Integer.parseInt(br.readLine());
		Operation[] operations = new Operation[M];
		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			operations[i] = new Operation(l, r, c);
		}

		Map<Integer, Integer> distance = new HashMap<>();
		distance.put(A, 0);
		Queue<Node> heap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		heap.add(new Node(A, 0));
		while (!heap.isEmpty()) {
			Node currNode = heap.poll();

			if (distance.getOrDefault(currNode.x, INF) < currNode.cost)
				continue;

			for (Operation op: operations) {
				int nextValue = digitSwap(currNode.x, N, op.l, op.r);
				int cost = currNode.cost + op.c;
				if (distance.getOrDefault(nextValue, INF) > cost) {
					distance.put(nextValue, cost);
					heap.add(new Node(nextValue, cost));
				}
			}
		}
		System.out.println(distance.getOrDefault(target, -1));
	    br.close();
	}

	private static int digitSwap(int number, int digits, int a, int b) {
		int l = (number / (int) Math.pow(10, digits - a)) % 10;
		int r = (number / (int) Math.pow(10, digits - b)) % 10;
		number -= l * (int) Math.pow(10, digits - a);
		number -= r * (int) Math.pow(10, digits - b);
		number += r * (int) Math.pow(10, digits - a);
		number += l * (int) Math.pow(10, digits - b);
		return number;
	}

	private static class Node {
		int x, cost;

		Node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}

	private static class Operation {
		int l, r, c;

		Operation(int l, int r, int c) {
			this.l = l;
			this.r = r;
			this.c = c;
		}
	}
}
