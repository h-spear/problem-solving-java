// https://www.acmicpc.net/problem/7042

package baekjoon.scc;

import java.io.*;
import java.util.*;

public class CowSkiArea {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};
	private static final Deque<Pair> stack = new ArrayDeque<>();
	private static int W, L;
	private static int[][] graph;
	private static int[][] sccId;
	private static int[][] visited;
	private static boolean[][] finished;
	private static int id;
	private static List<List<Pair>> scc;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		graph = new int[L][W];
		sccId = new int[L][W];
		visited = new int[L][W];
		finished = new boolean[L][W];
		scc = new ArrayList<>();

		for (int i = 0; i < L; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; ++j) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < L; ++i) {
			for (int j = 0; j < W; ++j) {
				if (!finished[i][j])
					dfs(i, j);
			}
		}

		int sz = scc.size();
		if (sz == 1) {
			// component가 1개인 경우 리프트를 설치할 필요가 없음!
			System.out.println(0);
		} else {
			int[] sccInDegree = new int[sz];
			int[] sccOutDegree = new int[sz];

			for (int x = 0; x < L; ++x) {
				for (int y = 0; y < W; ++y) {
					for (int i = 0; i < 4; ++i) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if (!isValid(x, y, nx, ny))
							continue;
						if (sccId[x][y] != sccId[nx][ny]) {
							++sccOutDegree[sccId[x][y]];
							++sccInDegree[sccId[nx][ny]];
						}
					}
				}
			}

			int[] counter = new int[2];
			for (int i = 0; i < sz; ++i) {
				if (sccInDegree[i] == 0)
					++counter[0];
				if (sccOutDegree[i] == 0)
					++counter[1];
			}
			System.out.println(Math.max(counter[0], counter[1]));
		}
	    br.close();
	}

	private static int dfs(int x, int y) {
		int parent = visited[x][y] = ++id;
		stack.push(new Pair(x, y));

		for (int i = 0; i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!isValid(x, y, nx, ny))
				continue;
			if (visited[nx][ny] == 0)
				parent = Math.min(parent, dfs(nx, ny));
			else if (!finished[nx][ny])
				parent = Math.min(parent, visited[nx][ny]);
		}

		if (parent == visited[x][y]) {
			List<Pair> component = new ArrayList<>();
			while (!stack.isEmpty()) {
				Pair p = stack.pop();
				finished[p.x][p.y] = true;
				sccId[p.x][p.y] = scc.size();
				component.add(p);
				if (p.x == x && p.y == y)
					break;
			}
			scc.add(component);
		}
		return parent;
	}

	private static boolean isValid(int x, int y, int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= L || ny >= W)
			return false;
		if (graph[nx][ny] > graph[x][y])
			return false;
		return true;
	}

	private static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
