// https://www.acmicpc.net/problem/2447

package baekjoon.divideandconquer;

import java.io.*;

public class StarPrint10 {

    private static int N;
    private static char[][] map;
    private static char[][] base = { { '*', '*', '*' }, { '*', ' ', '*' }, { '*', '*', '*' } };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                map[i][j] = ' ';
            }
        }
        draw(0, 0, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void draw(int x, int y, int r) {
        if (r == 3) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    map[x + i][y + j] = base[i][j];
                }
            }
            return;
        }

        int r3 = r / 3;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i == 1 && j == 1)
                    continue;
                draw(x + r3 * i, y + r3 * j, r3);
            }
        }
    }
}