// https://www.acmicpc.net/problem/1987

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Alphabet {

    private static int answer;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static Set<Character> set = new HashSet<>();
    private static char[][] graph;
    private static boolean[][] visited;
    private static int r, c;


    private void dfs(int x, int y, int depth) {
        answer = Math.max(answer, depth);

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= r || ny >= c)
                continue;
            if (visited[nx][ny])
                continue;
            if (set.contains(graph[nx][ny]))
                continue;
            set.add(graph[nx][ny]);
            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1);
            visited[nx][ny] = false;
            set.remove(graph[nx][ny]);
        }
    }

    private void solution() throws Exception {
        answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        graph = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; ++i) {
            String line = br.readLine();
            for (int j = 0; j < c; ++j)
                graph[i][j] = line.charAt(j);
        }

        set.add(graph[0][0]);
        dfs(0,0, 1);
        System.out.println(answer);

        br.close();
    }

    public static void main(String[] args) throws Exception      {
        new Alphabet().solution();
    }
}
