// 5644

package swea.swtest;

import java.io.*;
import java.util.*;

public class InductiveCharging {

	private static int M, A;
	private static int[][] bs;
	private static int[] dx = {0, -1, 0, 1, 0};
	private static int[] dy = {0, 0, 1, 0, -1};
	private static int[] P;
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[] move1 = new int[1000];
		int[] move2 = new int[1000];

		bs = new int[10][10];
		P = new int[9];

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			initialize();

			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i)
				move1[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; ++i)
				move2[i] = Integer.parseInt(st.nextToken());

			for (int ap = 1; ap <= A; ++ap) {
				st = new StringTokenizer(br.readLine());
				dfs(ap,
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken()));
				P[ap] = Integer.parseInt(st.nextToken());
			}

			int ax = 0, ay = 0;
			int bx = 9, by = 9;

			for (int i = 0; i < M; ++i) {
				// 충전
				charge(ax, ay, bx, by);
				ax += dx[move1[i]];
				ay += dy[move1[i]];
				bx += dx[move2[i]];
				by += dy[move2[i]];
			}
			charge(ax, ay, bx, by);
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void charge(int ax, int ay, int bx, int by) {
		List<Integer> aps1 = getBC(ax, ay);
		List<Integer> aps2 = getBC(bx, by);
		int length1 = aps1.size();
		int length2 = aps2.size();
		int res = 0;
		int ap1, ap2, temp;
		for (int i = 0; i < length1; ++i) {
			for (int j = 0; j < length2; ++j) {
				ap1 = aps1.get(i);
				ap2 = aps2.get(j);
				if (ap1 == ap2) {
					temp = P[ap1];
				} else {
					temp = P[aps1.get(i)] + P[aps2.get(j)];
				}
				res = Math.max(res, temp);
			}
		}
		answer += res;
	}

	private static List<Integer> getBC(int x, int y) {
		List<Integer> bcList = new ArrayList<>();
		bcList.add(0);
		int bm = bs[x][y];
		for (int i = 0; i < 10; ++i) {
			if ((bm & (1 << i)) != 0)
				bcList.add(i);
		}
		return bcList;
	}

	private static void dfs(int ap, int y, int x, int c) {
		if (c < 0)
			return;
		bs[x][y] |= (1 << ap);

		int nx, ny;
		for (int i = 1; i < 5; ++i) {
			nx = x + dx[i];
			ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= 10 || ny >= 10)
				continue;
			dfs(ap, ny, nx, c - 1);
		}
	}

	private static void initialize() {
		answer = 0;
		for (int i = 0; i < 10; ++i) {
			Arrays.fill(bs[i], 0);
		}
	}
}