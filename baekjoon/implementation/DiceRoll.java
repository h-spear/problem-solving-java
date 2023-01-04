// https://www.acmicpc.net/problem/14499

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class DiceRoll {

    private static int top;
    private static int east;
    private static int south;
    private static int west;
    private static int north;
    private static int bottom;
    private static int[] dice = new int[7];
    private static int n, m, x, y;
    private static int[][] map;
    private static final int[] dx = {0, 0, 0, -1, 1};
    private static final int[] dy = {0, 1, -1, 0, 0};

    private boolean diceRoll(int cmd) {
        int temp, nx, ny;

        nx = x + dx[cmd];
        ny = y + dy[cmd];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m)
            return false;

        x = nx;
        y = ny;

        switch (cmd) {
            case 1:
                temp = top;
                top = west;
                west = bottom;
                bottom = east;
                east = temp;
                break;
            case 2:
                temp = top;
                top = east;
                east = bottom;
                bottom = west;
                west = temp;
                break;
            case 3:
                temp = top;
                top = south;
                south = bottom;
                bottom = north;
                north = temp;
                break;
            case 4:
                temp = top;
                top = north;
                north = bottom;
                bottom = south;
                south = temp;
                break;
        }

        // 이동한 칸에 쓰여 있는 수가 0이면
        if (map[x][y] == 0) {
            // 주사위 바닥면에 쓰여 있는 수가 칸에 복사
            map[x][y] = dice[bottom];
        } else {    // 아닌 경우
            // 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사
            // 칸에 쓰여 있는 수는 0으로
            dice[bottom] = map[x][y];
            map[x][y] = 0;
        }
        return true;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] command = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; ++i) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        top = 1;
        north = 2;
        east = 3;
        west = 4;
        south = 5;
        bottom = 6;

        for (int cmd: command) {
            if (!diceRoll(cmd))
                continue;

            bw.write("" + dice[top]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new DiceRoll().solution();
    }
}
