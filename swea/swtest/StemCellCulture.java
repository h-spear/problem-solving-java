// 5653

package swea.swtest;

import java.io.*;
import java.util.*;

public class StemCellCulture {

	private static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	private static int N, M, K;
	private static boolean[][] graph;
	private static boolean[][] activate;
	private static Queue<Pair>[] vQueue;
	private static Queue<Pair>[] dQueue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		vQueue = new Queue[11];
		dQueue = new Queue[11];
		graph = new boolean[400][400];
		activate = new boolean[400][400];

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			initialize();

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			for (int i = 0, v; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; ++j) {
					v = Integer.parseInt(st.nextToken());
					if (v > 0) {
						graph[160 + i][160 + j] = true;
						activate[160 + i][160 + j] = true;
						vQueue[v].add(new Pair(160 + i, 160 + j));
					}
				}
			}

			int time = 0;
			int nx, ny;
			int death = 0;
			while (time < K) {
				++time;
				death = (death + 1) % 10;
				while (!dQueue[death].isEmpty()) {
					Pair p = dQueue[death].poll();
					activate[p.x][p.y] = false;
				}

				for (int v = 10; v > 0; --v) {
					if (v == 1) {
						Iterator<Pair> iter = vQueue[v].iterator();
						while (iter.hasNext()) {
							dQueue[(death + v) % 10].add(iter.next());
						}
					}

					if (time % (v + 1) > 0)
						continue;
					if (v > 1) {
						Iterator<Pair> iter = vQueue[v].iterator();
						while (iter.hasNext()) {
							dQueue[(death + v - 1) % 10].add(iter.next());
						}
					}

					int queueSize = vQueue[v].size();
					while (queueSize-- > 0) {
						Pair p = vQueue[v].poll();

						for (int i = 0; i < 4; ++i) {
							nx = p.x + dx[i];
							ny = p.y + dy[i];

							if (graph[nx][ny])
								continue;
							graph[nx][ny] = true;
							activate[nx][ny] = true;
							vQueue[v].add(new Pair(nx, ny));
						}
					}
				}
			}

			int answer = 0;
			for (int i = 0; i < 400; ++i) {
				for (int j = 0; j < 400; ++j) {
					if (activate[i][j])
						++answer;
				}
			}
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static void initialize() {
		for (int i = 0; i <= 10; ++i) {
			vQueue[i] = new ArrayDeque<>();
			dQueue[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < 400; ++i) {
			Arrays.fill(graph[i], false);
			Arrays.fill(activate[i], false);
		}
	}

}