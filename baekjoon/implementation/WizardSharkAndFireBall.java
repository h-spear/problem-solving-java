// https://www.acmicpc.net/problem/20056

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class WizardSharkAndFireBall {

    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int n;
    private static int m;
    Queue<FireBall>[][] map;

    class FireBall {
        int m, s, d;

        public FireBall(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    private void command() {
        Queue<FireBall>[][] tempMap = makeMap(n);

        // 1
        int nx, ny;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (FireBall fireBall: map[i][j]) {
                    nx = (i + fireBall.s * dx[fireBall.d] + fireBall.s * n) % n;
                    ny = (j + fireBall.s * dy[fireBall.d] + fireBall.s * n) % n;
                    tempMap[nx][ny].add(fireBall);
                }
            }
        }

        // 2
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tempMap[i][j].size() < 2)
                    continue;

                int mass = 0;
                int speed = 0;
                int count = 0;
                boolean zero = false;
                boolean one = false;

                while (!tempMap[i][j].isEmpty()) {
                    ++count;
                    FireBall fireBall = tempMap[i][j].poll();
                    mass += fireBall.m;
                    speed += fireBall.s;
                    if ((fireBall.d & 1) == 1) {
                        one = true;
                    } else {
                        zero = true;
                    }
                }

                mass /= 5;
                speed /= count;

                if (mass <= 0)
                    continue;

                if (zero != one) {  // 모두 홀수 or 짝수
                    for (int dir = 0; dir < 8; dir += 2) {
                        tempMap[i][j].add(new FireBall(mass, speed, dir));
                    }
                } else {
                    for (int dir = 1; dir <= 8; dir += 2) {
                        tempMap[i][j].add(new FireBall(mass, speed, dir));
                    }
                }
            }
        }
        map = tempMap;
    }

    private Queue<FireBall>[][] makeMap(int n) {
        Queue<FireBall>[][] map = new LinkedList[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                map[i][j] = new LinkedList<>();
            }
        }
        return map;
    }

    private int getResult() {
        int res = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                for (FireBall fireBall: map[i][j]) {
                    res += fireBall.m;
                }
            }
        }
        return res;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = makeMap(n);

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r-1][c-1].add(new FireBall(m, s, d));
        }

        for (int i = 0; i < k; ++i) {
            command();
        }
        System.out.println(getResult());
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new WizardSharkAndFireBall().solution();
    }
}
