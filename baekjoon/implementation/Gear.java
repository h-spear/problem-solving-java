// https://www.acmicpc.net/problem/14891

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class Gear {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		MyGear[] gears = new MyGear[4];
		for (int i = 0; i < 4; ++i) {
			gears[i] = new MyGear(br.readLine());
		}

		int K = Integer.parseInt(br.readLine());
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			rotate(gears, num, dir, -1);
		}
		System.out.println(getScore(gears));
		br.close();
	}

	private static int getScore(MyGear[] gears) {
		int score = 0;
		for (int i = 0; i < 4; ++i) {
			score |= (gears[i].get(0) << i);
		}
		return score;
	}

	private static void rotate(MyGear[] gears, int num, int dir, int prev) {
		if (num < 3) {
			if (num + 1 != prev && gears[num + 1].get(6) != gears[num].get(2)) {
				rotate(gears, num + 1, -dir, num);
			}
		}
		if (num > 0) {
			if (num - 1 != prev && gears[num - 1].get(2) != gears[num].get(6)) {
				rotate(gears, num - 1, -dir, num);
			}
		}
		if (dir == 1) {
			gears[num].rotateClockwise();
		} else {
			gears[num].rotateCounterclockwise();
		}
	}

	private static class MyGear {
		private static final int MAX_SIZE = 8;

		int[] gear = new int[MAX_SIZE];
		int front, rear;

		MyGear(String gearString) {
			front = 0;
			rear = MAX_SIZE - 1;
			for (int i = 0; i < MAX_SIZE; ++i) {
				gear[i] = gearString.charAt(i) - '0';
			}
		}

		void rotateClockwise() {
			front = (front - 1 + MAX_SIZE) % MAX_SIZE;
			rear = (rear - 1 + MAX_SIZE) % MAX_SIZE;
		}

		void rotateCounterclockwise() {
			front = (front + 1) % MAX_SIZE;
			rear = (rear + 1) % MAX_SIZE;
		}

		int get(int index) {
			return gear[(front + index) % MAX_SIZE];
		}
	}
}
