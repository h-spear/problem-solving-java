// 12712

package swea.difficulty2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class FlyExterminate {

    private static int N, M;
    private static int[][] graph;
    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            graph = new int[N][N];

            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    answer = Math.max(answer, simul(i, j));
                }
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int simul(int x, int y) {
        int nx, ny;

        int plus = graph[x][y];
        int X = graph[x][y];
        for (int i = 0; i < 8; ++i) {
            for (int k = 1; k < M; ++k) {
                nx = x + k * dx[i];
                ny = y + k * dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if (i < 4)
                    plus += graph[nx][ny];
                else
                    X += graph[nx][ny];
            }
        }
        return Math.max(plus, X);
    }
}