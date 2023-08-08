// 1767

package swea.swtest;

import java.io.*;
import java.util.*;

public class Processor {

    static class Pair {
        int x, y;
    }

    // U, D, L, R
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static int N, answer;
    private static int[][] graph;
    private static Pair[] corePos;
    private static int[][] coreDir;
    private static int[] comb;
    private static int maxCoreCnt, totalCoreCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        graph = new int[12][12];
        coreDir = new int[12][5];
        comb = new int[12];
        corePos = new Pair[12];
        for (int i = 0; i < 12; ++i) {
            corePos[i] = new Pair();
        }

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            sb.append("#").append(tc).append(" ");

            N = Integer.parseInt(br.readLine());
            initialize();

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if (graph[i][j] == 1) {
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
                            continue;
                        corePos[totalCoreCnt].x = i;
                        corePos[totalCoreCnt++].y = j;
                    }
                }
            }

            Pair core;
            int nx, ny, idx;
            for (int i = 0; i < totalCoreCnt; ++i) {
                core = corePos[i];
                idx = 0;
                for (int d = 0; d < 4; ++d) {
                    nx = core.x;
                    ny = core.y;
                    while (true) {
                        nx += dx[d];
                        ny += dy[d];
                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                            coreDir[i][idx++] = d;
                            break;
                        }
                        if (graph[nx][ny] == 1)
                            break;
                    }
                }
            }
            dfs(0);
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void initialize() {
        totalCoreCnt = 0;
        maxCoreCnt = 0;
        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; ++i) {
            Arrays.fill(coreDir[i], -1);
        }
    }

    private static void dfs(int depth) {
        int cnt = getUsedCoreCount();
        if (totalCoreCnt - depth + cnt < maxCoreCnt) {
            return;
        }

        if (depth == totalCoreCnt) {
            int res = simul();
            if (res > -1) {
                if (cnt > maxCoreCnt) {
                    maxCoreCnt = cnt;
                    answer = res;
                } else if (cnt == maxCoreCnt) {
                    answer = Math.min(answer, res);
                }
            }
            return;
        }
        for (int dir: coreDir[depth]) {
            comb[depth] = dir;
            dfs(depth + 1);
            if (dir == -1)
                break;
        }
    }

    private static int simul() {
        removeLine();
        int nx, ny, length = 0;
        for (int i = 0; i < totalCoreCnt; ++i) {
            Pair core = corePos[i];
            nx = core.x;
            ny = core.y;
            while (true) {
                if (comb[i] == -1)
                    break;
                nx += dx[comb[i]];
                ny += dy[comb[i]];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    break;
                if (graph[nx][ny] > 0)
                    return -1;
                graph[nx][ny] = 2;
                ++length;
            }
        }
        return length;
    }

    private static int getUsedCoreCount() {
        int cnt = 0;
        for (int i = 0; i < totalCoreCnt; ++i) {
            if (comb[i] > -1)
                ++cnt;
        }
        return cnt;
    }

    private static void removeLine() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (graph[i][j] == 2)
                    graph[i][j] = 0;
            }
        }
    }
}
