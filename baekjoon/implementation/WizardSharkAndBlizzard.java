package baekjoon.implementation;// https://www.acmicpc.net/problem/2161

import java.io.*;
import java.util.*;

public class WizardSharkAndBlizzard {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int[][] indicesMatrix;

    private int[][] makeIndicesMatrix(int n) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int x = n / 2;
        int y = n / 2;
        int length = 1;
        int[][] res = new int[n][n];

        int idx = 1;
        while (length < n) {
            for (int dir = 0; dir < 4; ++dir) {
                for (int i = 0; i < length; ++i) {
                    x += dx[dir];
                    y += dy[dir];
                    res[x][y] = idx++;
                }
                if ((dir & 1 ) == 1)
                    ++length;
            }
        }
        while (x != 0 || y != 0) {
            y -= 1;
            res[x][y] = idx++;
        }
        return res;
    }

    private int indexer(int x, int y) {
        return indicesMatrix[x][y];
    }

    private int[] twoDimensionToOneDemensionArray(int[][] graph, int n) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int x = n / 2;
        int y = n / 2;
        int length = 1;
        int[] res = new int[n * n];

        int idx = 1;
        while (length < n) {
            for (int dir = 0; dir < 4; ++dir) {
                for (int i = 0; i < length; ++i) {
                    x += dx[dir];
                    y += dy[dir];
                    res[idx++] = graph[x][y];
                }
                if ((dir & 1 ) == 1)
                    ++length;
            }
        }
        while (x != 0 || y != 0) {
            y -= 1;
            res[idx++] = graph[x][y];
        }
        return res;
    }

    private void blizzard(int[] graph1D, int n, int d, int s) {
        int x = n / 2;
        int y = n / 2;

        for (int i = 0; i < s; ++i) {
            x += dx[d];
            y += dy[d];
            graph1D[indexer(x, y)] = 0;
        }
    }

    private void move(int[] graph1D) {
        int idx = 0;
        int graphSize = graph1D.length;
        int[] temp = new int[graphSize];

        for (int i = 0; i < graphSize; ++i) {
            if (graph1D[i] == 0)
                continue;

            temp[++idx] = graph1D[i];
        }

        for (int i = 0; i < graphSize; ++i) {
            graph1D[i] = temp[i];
        }
    }

    private int explosion(int[] graph1D) {
        // 동시에 폭발이 발생해야 함... (스택X)
        int score = 0;
        int graphSize = graph1D.length;

        boolean explosion = true;

        while (explosion) {
            explosion = false;

            int duplicate = 1;
            for (int i = 1; i < graphSize; ++i) {
                if (graph1D[i - 1] == graph1D[i]) {
                    ++duplicate;
                } else {
                    if (duplicate >= 4) {
                        int beadNumber = graph1D[i - 1];
                        for (int j = i - 1; j >= i - duplicate; --j) {
                            graph1D[j] = 0;
                        }
                        score += beadNumber * duplicate;
                        explosion = true;
                    }
                    duplicate = 1;
                }
            }
            // 폭발 후 이동
            move(graph1D);
        }
        return score;
    }

    private void transition(int[] graph1D) {
        Deque<Integer> deque = new ArrayDeque<>();
        int idx = 0;
        int graphSize = graph1D.length;
        int[] temp = new int[graphSize];

        for (int i = 1; i < graphSize && idx < graphSize - 1; ++i) {
            if (!deque.isEmpty() && deque.peekLast() != graph1D[i]) {
                temp[++idx] = deque.size();
                temp[++idx] = deque.peekLast();
                deque.clear();
            }
            deque.add(graph1D[i]);
        }

        for (int i = 0; i < graphSize; ++i) {
            graph1D[i] = temp[i];
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        indicesMatrix = makeIndicesMatrix(n);
        int answer = 0;
        int[] graph1D = twoDimensionToOneDemensionArray(graph, n);

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            //
            blizzard(graph1D, n, d, s);
            move(graph1D);
            answer += explosion(graph1D); // 폭발 후 이동
            transition(graph1D);
        }

        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndBlizzard().solution();
    }
}
