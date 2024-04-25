// https://school.programmers.co.kr/learn/courses/30/lessons/250136

package programmers.level2;

import java.util.*;

public class OilDrilling {

	private static final int[] dx = {1, -1, 0, 0};
	private static final int[] dy = {0, 0, 1, -1};

	private int n, m;
	private int[][] group;
	private int[][] land;

	public int solution(int[][] land) {
		this.n = land.length;
		this.m = land[0].length;
		this.land = land;
		this.group = new int[n][m];

		int[] sizes = new int[33333];
		int groupNo = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (land[i][j] == 1 && group[i][j] == 0) {
					++groupNo;
					sizes[groupNo] = grouping(i, j, groupNo);
				}
			}
		}

		int answer = 0;
		for (int j = 0; j < m; ++j) {
			int total = 0;
			boolean[] used = new boolean[groupNo + 1];
			for (int i = 0; i < n; ++i) {
				if (!used[group[i][j]]) {
					used[group[i][j]] = true;
					total += sizes[group[i][j]];
				}
			}
			answer = Math.max(answer, total);
		}
		return answer;
	}

	private int grouping(int x, int y, int groupNo) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.add(new Pair(x, y));
		group[x][y] = groupNo;
		int size = 0;
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			++size;

			for (int i = 0; i < 4; ++i) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m)
					continue;
				if (group[nx][ny] > 0)
					continue;
				if (land[nx][ny] == 0)
					continue;
				group[nx][ny] = groupNo;
				queue.add(new Pair(nx, ny));
			}
		}
		return size;
	}

	private static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
