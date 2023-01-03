// https://www.acmicpc.net/problem/3190

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class Snake {

    // up, right, down, left
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean isSnakeBody(Deque<Pair> snake, int x, int y) {
        for (Pair pair : snake) {
            if (pair.x == x && pair.y == y)
                return true;
        }
        return false;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        boolean[][] apple = new boolean[n][n];
        Deque<Pair> snake = new ArrayDeque<>();
        snake.add(new Pair(0, 0));

        int dir = 1;

        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            apple[r-1][c-1] = true;
        }

        int l = Integer.parseInt(br.readLine());
        int[] commandTime = new int[l];
        int[] commandDir = new int[l];
        for (int i = 0; i < l; ++i) {
            st = new StringTokenizer(br.readLine());
            commandTime[i] = Integer.parseInt(st.nextToken());
            if (st.nextToken().equals("L"))
                commandDir[i] = 3;
            else
                commandDir[i] = 1;
        }

        int time = 0;
        int x, y, nx, ny;
        int commandCursor = 0;
        while (true) {
            ++time;

            x = snake.getFirst().x;
            y = snake.getFirst().y;

            nx = x + dx[dir];
            ny = y + dy[dir];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n)
                break;
            if (isSnakeBody(snake, nx, ny))
                break;

            snake.addFirst(new Pair(nx, ny));

            // apple
            if (apple[nx][ny]) {
                apple[nx][ny] = false;  // eat
            } else {
                snake.removeLast();
            }

            if (commandCursor < l && time == commandTime[commandCursor]) {
                dir = (dir + commandDir[commandCursor]) % 4;
                ++commandCursor;
            }
        }

        System.out.println(time);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Snake().solution();
    }
}
