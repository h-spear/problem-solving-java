// https://www.acmicpc.net/problem/10993

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class StarPrint18 {

    private static int N, H, W, W2;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        H = 0;
        for (int i = 0; i < N; ++i) {
            H = (H << 1) + 1;
        }
        W = 2 * H - 1;
        W2 = W >> 1;
        map = new char[H][W];

        for (int i = 0; i < H; ++i)
            Arrays.fill(map[i], ' ');

        if ((N & 1) == 0)
            draw(H, H, W, true);
        else
            draw(0, H, W, false);

        print();
        br.close();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        if ((N & 1) == 0) {
            for (int i = 0; i < H; ++i) {
                for (int j = 0; j < W - i; ++j) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        } else {
            for (int i = 0; i < H; ++i) {
                for (int j = 0; j < W2 + i + 1; ++j) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void draw(int x, int h, int w, boolean rev) {
        if (h == 0)
            return;
        int w2 = w / 2;
        if (rev) {
            for (int i = 0; i < h; ++i) {
                map[x - i - 1][W2 - i] = '*';
                map[x - i - 1][W2 + i] = '*';
            }
            for (int j = W2 - w2; j < W2 + w2; ++j) {
                map[x - h][j] = '*';
            }
            draw(x - h + 1, h >> 1, w >> 1, false);
        } else {
            for (int i = 0; i < h; ++i) {
                map[x + i][W2 - i] = '*';
                map[x + i][W2 + i] = '*';
            }
            for (int j = W2 - w2; j < W2 + w2; ++j) {
                map[x + h - 1][j] = '*';
            }
            draw(x + h - 1, h >> 1, w >> 1, true);
        }
    }
}
