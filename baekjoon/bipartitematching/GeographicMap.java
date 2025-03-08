// https://www.acmicpc.net/problem/3145
// 마을 이름이 중복으로 들어오는 경우가 있음 ***

package baekjoon.bipartitematching;

import java.io.*;
import java.util.*;

public class GeographicMap {

	private static final int[] dr = {1, -1, 0, 0, 1, 1, -1, -1};
	private static final int[] dc = {0, 0, 1, -1, 1, -1, 1, -1};
	private static List<Pair> xs;
	private static List<String> townMapper;
	private static int N, countOfTowns;
	private static List<Set<Integer>> graph;
	private static int[] visited;
	private static int[] match;
	private static int call = 0;

	public static void main(String[] args) throws Exception {
		inputAndPreprocessing();
		solveAndPrint();
	}

	private static void solveAndPrint() {
		for (int i = 0; i < N; ++i) {
			++call; dfs(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < countOfTowns; ++i) {
			int idx = match[i];
			assert idx != -1;
			Pair x = xs.get(idx);
			sb.append(x.r + 1).append(" ")
				.append(x.c + 1).append(" ")
				.append(townMapper.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static boolean dfs(int x) {
		if (visited[x] == call) {
			return false;
		}
		visited[x] = call;

		for (int y: graph.get(x)) {
			if (match[y] == -1 || dfs(match[y])) {
				match[y] = x;
				return true;
			}
		}
		return false;
	}

	private static void inputAndPreprocessing() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = countOfTowns = 0;
		xs = new ArrayList<>();
		townMapper = new ArrayList<>();

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[][] indexer = new int[R][C]; // ***
		fill2D(indexer, -1);

		for (int i = 0; i < R; ++i) {
			String line = br.readLine();
			for (int j = 0; j < C; ++j) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'x') {
					xs.add(new Pair(i, j));
					++N;
				}
			}
		}

		graph = new ArrayList<>(N);
		for (int i = 0; i < N; ++i) {
			graph.add(new HashSet<>());
		}

		String[][] nameMap = new String[R][C];
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] == '.' || map[i][j] == 'x')
					continue;

				int _j = j;
				for (; _j < C && isUpper(map[i][_j]); ++_j) {}
				String name = new String(map[i], j, _j - j);
				for (int k = j; k < _j; ++k) {
					map[i][k] = '.';
					nameMap[i][k] = name;
					indexer[i][k] = countOfTowns;
				}
				j = --_j;
				townMapper.add(name);
				++countOfTowns;
			}
		}

		for (int i = 0; i < N; ++i) {
			Pair x = xs.get(i);

			for (int d = 0; d < 8; ++d) {
				int nx = x.r + dr[d];
				int ny = x.c + dc[d];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if (nameMap[nx][ny] == null)
					continue;
				graph.get(i).add(indexer[nx][ny]);
			}
		}

		visited = new int[N];
		match = new int[countOfTowns];
		Arrays.fill(match, -1);
		br.close();
	}

	private static boolean isUpper(char alphabet) {
		return 'A' <= alphabet && alphabet <= 'Z';
	}

	private static void fill2D(int[][] array, int value) {
		for (int[] row: array) {
			Arrays.fill(row, value);
		}
	}

	private static class Pair {
		int r, c;

		Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
