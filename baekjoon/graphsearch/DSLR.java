// https://www.acmicpc.net/problem/9019

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class DSLR {

    static class Pair {
        int num;
        String path;

        public Pair(int num, String path) {
            this.num = num;
            this.path = path;
        }
    }

    private static final char[] command = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10000];
            visited[start] = true;
            Queue<Pair> queue = new LinkedList<>();
            queue.add(new Pair(start, ""));

            while (!queue.isEmpty()) {
                Pair p = queue.poll();

                if (p.num == target) {
                    bw.write(p.path + "\n");
                    break;
                }

                int[] candidate = {getDouble(p.num), getMinus(p.num), getLeftShift(p.num), getRightShift(p.num)};
                for (int i = 0; i < 4; ++i) {
                    int next = candidate[i];
                    if (visited[next])
                        continue;
                    queue.add(new Pair(next, p.path + command[i]));
                    visited[next] = true;
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getDouble(int num) {
        return (num << 1) % 10000;
    }

    private static int getMinus(int num) {
        return (num + 9999) % 10000;
    }

    private static int getLeftShift(int num) {
        return (num % 1000) * 10 + (num / 1000);
    }

    private static int getRightShift(int num) {
        return (num % 10) * 1000 + (num / 10);
    }
}
