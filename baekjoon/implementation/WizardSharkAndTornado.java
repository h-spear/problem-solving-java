// https://www.acmicpc.net/problem/20057

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class WizardSharkAndTornado {
    private static int n;
    private static int[][] graph;
    private static int answer = 0;

    // left, down, right, up
    private static final int[] dr = {0, 1, 0, -1};
    private static final int[] dc = {-1, 0, 1, 0};

    private boolean isVaild(int r, int c) {
        if (r < 0 || c < 0 || r >= n || c >= n)
            return false;
        return true;
    }

    private void tornado(int r, int c, int dir) {
        int yr = r + dr[dir];
        int yc = c + dc[dir];
        int sand = graph[yr][yc];
        int alpha = sand;
        graph[yr][yc] = 0;
        int nr, nc, percentage;

        // 1%
        for (int i = -1; i <= 1; i += 2) {
            percentage = (int) (sand * 0.01);
            nr = r + i * ((dir & 1) == 0 ? 1 : 0);
            nc = c + i * ((dir & 1) == 0 ? 0 : 1);
            if (isVaild(nr, nc)) {
                graph[nr][nc] += percentage;
            } else {
                answer += percentage;
            }
            alpha -= percentage;
        }

        // 7%
        for (int i = -1; i <= 1; i += 2) {
            percentage = (int) (sand * 0.07);
            nr = r + i * ((dir & 1) == 0 ? 1 : i * dr[dir]);
            nc = c + i * ((dir & 1) == 0 ? i * dc[dir] : 1);
            if (isVaild(nr, nc)) {
                graph[nr][nc] += percentage;
            } else {
                answer += percentage;
            }
            alpha -= percentage;
        }

        // 10%
        for (int i = -1; i <= 1; i += 2) {
            percentage = (int) (sand * 0.10);
            nr = r + i * ((dir & 1) == 0 ? 1 : 2 * i * dr[dir]);
            nc = c + i * ((dir & 1) == 0 ? 2 * i * dc[dir] : 1);
            if (isVaild(nr, nc)) {
                graph[nr][nc] += percentage;
            } else {
                answer += percentage;
            }
            alpha -= percentage;
        }

        // 2%
        for (int i = -1; i <= 1; i += 2) {
            percentage = (int) (sand * 0.02);
            nr = r + i * ((dir & 1) == 0 ? 2 : i * dr[dir]);
            nc = c + i * ((dir & 1) == 0 ? i * dc[dir] : 2);
            if (isVaild(nr, nc)) {
                graph[nr][nc] += percentage;
            } else {
                answer += percentage;
            }
            alpha -= percentage;
        }

        // 5%
        percentage = (int) (sand * 0.05);
        nr = r + 3 * dr[dir];
        nc = c + 3 * dc[dir];
        if (isVaild(nr, nc)) {
            graph[nr][nc] += percentage;
        } else {
            answer += percentage;
        }
        alpha -= percentage;

        // alpha
        nr = r + 2 * dr[dir];
        nc = c + 2 * dc[dir];
        if (isVaild(nr, nc)) {
            graph[nr][nc] += alpha;
        } else {
            answer += alpha;
        }

    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int length = 1;
        int r = n / 2;
        int c = n / 2;
        while (isVaild(r, c) && length < n) {
            for (int dir = 0; dir < 4; ++dir) {
                for (int i = 0; i < length; ++i) {
                    tornado(r, c, dir);
                    r += dr[dir];
                    c += dc[dir];
                }
                if ((dir & 1) == 1)     // 2번 방향 전환할 때마다 이동 길이 증가
                    ++length;
            }
        }

        // left
        // 토네이도가 0, 0으로 이동할 때까지 left
        while (r != 0 || c != 0) {
            tornado(r, c, 0);
            r += dr[0];
            c += dc[0];
        }

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndTornado().solution();
    }
}
