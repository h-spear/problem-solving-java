// https://www.acmicpc.net/problem/3109

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Bakery {

    private static int R, C;
    private static boolean[][] graph;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new boolean[R][C];

        for (int i = 0; i < R; ++i) {
            String line = br.readLine();
            for (int j = 0; j < C; ++j) {
                if (line.charAt(j) == 'x')
                    graph[i][j] = true;
            }
        }

        for (int i = 0; i < R; ++i) {
            if (graph[R-1][C-1] == true)
                break;
            dfs(i, 0);
        }
        System.out.println(answer);
        br.close();
    }

    private static boolean dfs(int x, int y) {
        graph[x][y] = true;
        if (y == C - 1) {
            ++answer;
            return true;
        }

        if (x - 1 >= 0 && y + 1 < C)
            if (!graph[x - 1][y + 1] && dfs(x - 1, y + 1))
                return true;

        if (y + 1 < C)
            if (!graph[x][y + 1] && dfs(x, y + 1))
                return true;

        if (x + 1 < R && y + 1 < C)
            if (!graph[x + 1][y + 1] && dfs(x + 1, y + 1))
                return true;

        return false;
    }
}