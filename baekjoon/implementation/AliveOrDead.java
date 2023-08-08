// https://www.acmicpc.net/problem/25598

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class AliveOrDead {

    static class Zombie {
        int y, x, k, d, l;

        public Zombie(int y, int x, int k, int d, int l) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.d = d;
            this.l = l;
        }
    }

    static class Pair implements Comparable<Pair> {
        int dir, count;

        @Override
        public int compareTo(Pair o2) {
            if (count > o2.count) {
                return -1;
            } else if (count < o2.count) {
                return 1;
            } else {
                return Integer.compare(dir, o2.dir);
            }
        }
    }

    // 상 우 하 좌
    private static final int[] dy = {-1, 0, 1, 0, 0};
    private static final int[] dx = {0, 1, 0, -1, 0};
    private static final int[] back = {2, 3, 0, 1};

    private static int N;
    private static boolean[][] wall;
    private static Zombie[] zombies;
    private static int py, px;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Map<Character, Integer> dirMapper = new HashMap<>();
        dirMapper.put('U', 0);
        dirMapper.put('R', 1);
        dirMapper.put('D', 2);
        dirMapper.put('L', 3);
        dirMapper.put('S', 4);

        N = Integer.parseInt(br.readLine());
        wall = new boolean[N][N];

        int Z, O, W, D;

        String cmd = br.readLine();
        O = cmd.length();
        int[] command = new int[O];
        for (int i = 0; i < O; ++i) {
            command[i] = dirMapper.get(cmd.charAt(i));
        }

        st = new StringTokenizer(br.readLine());
        py = Integer.parseInt(st.nextToken()) - 1;
        px = Integer.parseInt(st.nextToken()) - 1;

        W = Integer.parseInt(br.readLine());
        int y, x, k, l;
        char d;
        for (int i = 0; i < W; ++i) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            wall[y][x] = true;
        }

        Z = Integer.parseInt(br.readLine());
        zombies = new Zombie[Z];
        for (int i = 0; i < Z; ++i) {
            st = new StringTokenizer(br.readLine());
            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            k = Integer.parseInt(st.nextToken());
            d = st.nextToken().charAt(0);
            l = Integer.parseInt(st.nextToken());
            zombies[i] = new Zombie(y, x, k, dirMapper.get(d), l);
        }

        D = Integer.parseInt(br.readLine());
        int day = 1;
        boolean collide = false;
        for (; day <= D; ++day) {
            movePlayer(command[day - 1]);   // 3
            moveZombie();   // 5
            if (isCollide()) {
                collide = true;
                break;
            }
        }
        if (collide) {
            bw.write(String.valueOf(day));
            bw.newLine();
            bw.write("DEAD...");
        } else {
            bw.write("ALIVE!");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isCollide() {
        for (Zombie zombie: zombies) {
            if (zombie.y == py && zombie.x == px)
                return true;
        }
        return false;
    }

    private static void moveZombie() {
        for (Zombie zombie: zombies) {
            if (zombie.k == 0) {
                moveJuniorZombie(zombie);
            } else {
                moveSeniorZombie(zombie);
            }
        }
    }

    private static void moveSeniorZombie(Zombie zombie) {
        int nx, ny;
        for (int i = 0; i < zombie.l; ++i) {
            ny = zombie.y + dy[zombie.d];
            nx = zombie.x + dx[zombie.d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                break;
            if (wall[ny][nx]) {
                wall[ny][nx] = false;
                break;
            }
            zombie.y = ny;
            zombie.x = nx;
        }
        zombie.d = getNextDirection(zombie.y, zombie.x);
    }

    private static void moveJuniorZombie(Zombie zombie) {
        int nx, ny;
        for (int i = 0; i < zombie.l; ++i) {
            ny = zombie.y + dy[zombie.d];
            nx = zombie.x + dx[zombie.d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N || wall[ny][nx]) {
                zombie.d = back[zombie.d];
                break;
            }
            zombie.y = ny;
            zombie.x = nx;
        }
    }

    private static int getNextDirection(int y, int x) {
        Pair[] res = new Pair[4];
        int ny, nx;
        for (int i = 0; i < 4; ++i) {
            res[i] = new Pair();
            res[i].dir = i;
            ny = y + dy[i];
            nx = x + dx[i];
            while (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (wall[ny][nx]) {
                    ++res[i].count;
                }
                ny += dy[i];
                nx += dx[i];
            }
        }
        Arrays.sort(res);
        return res[0].dir;
    }

    private static void movePlayer(int dir) {
        int ny = py + dy[dir];
        int nx = px + dx[dir];
        if (nx < 0 || ny < 0 || nx >= N || ny >= N)
            return;
        if (wall[ny][nx])
            return;
        py = ny;
        px = nx;
    }
}

