// 1868

package swea.difficulty4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class PapangPapangMinesweeper {

    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
    private static int N;
    private static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {

            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];

            for (int i = 0; i < N; ++i) {
                String line = br.readLine();
                for (int j = 0; j < N; ++j) {
                    if (line.charAt(j) == '*') {
                        graph[i][j] = 1;
                    }
                }
            }

            int answer = 0;
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (getCountAroundBomb(i, j) == 0) {
                        answer += click(i, j);
                    }
                }
            }

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    answer += click(i, j);
                }
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getCountAroundBomb(int x, int y) {
        int nx, ny;
        int count = 0;
        for (int i = 0; i < 8; ++i) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;
            if (graph[nx][ny] == 1)
                ++count;
        }
        return count;
    }

    private static int click(int x, int y) {
        if (graph[x][y] != 0) {
            return 0;
        }
        graph[x][y] = 2;
        if (getCountAroundBomb(x, y) == 0) {
            int nx, ny;
            for (int i = 0; i < 8; ++i) {
                nx = x + dx[i];
                ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                click(nx, ny);
            }
        }
        return 1;
    }
}