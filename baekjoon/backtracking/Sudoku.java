// https://www.acmicpc.net/problem/2580

package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class Sudoku {

    private static int[][] graph = new int[9][9];
    private static boolean find = false;

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        br.close();
    }
    
    private void dfs(int x, int y) {
        if (find)
            return;

        int next = x * 9 + y + 1;
        int nx = next / 9;
        int ny = next % 9;

        // 1.

        // 2. 목적지인가
        if (next > 81) {
            // 출력
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    System.out.printf("%d ", graph[i][j]);
                }
                System.out.println();
            }
            // 정답 찾음.
            find = true;
        } else if (graph[x][y] != 0) {
            dfs(nx, ny);
        } else {
            // 3.
            for (int num = 1; num <= 9; ++num) {
                // 4.
                if (check(x, y, num)) {
                    // 5.
                    graph[x][y] = num;  // 체크인
                    dfs(nx, ny);
                    graph[x][y] = 0;    // 체크아웃
                }
            }
        }
        
        // 6.
    }

    private boolean check(int x, int y, int num) {
        for (int i = 0; i < 9; ++i) {
            if (graph[i][y] == num) {
                return false;
            }
        }
        for (int j = 0; j < 9; ++j) {
            if (graph[x][j] == num) {
                return false;
            }
        }
        for (int i = x - x % 3; i < x - (x % 3) + 3; ++i) {
            for (int j = y - y % 3; j < y - (y % 3) + 3; ++j) {
                if (graph[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        new Sudoku().solution();
    }
}
