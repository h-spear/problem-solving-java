// https://www.acmicpc.net/problem/14226

package baekjoon.graphsearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Emoticon {
    static StringTokenizer st;
    static int S;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        S = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[2000][2000];

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 1, 1));
        visited[1][0] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.view == S) {
                answer = now.t;
                break;
            }

            if (!visited[now.view][now.view]) {
                queue.add(new Node(now.t + 1, now.view, now.view));
                visited[now.view][now.view] = true;
            }

            if (now.view + now.clipboard < 2000
                    && !visited[now.view + now.clipboard][now.clipboard]) {
                queue.add(new Node(now.t + 1, now.view + now.clipboard, now.clipboard));
                visited[now.view + now.clipboard][now.view] = true;
            }

            if (now.view > 0
                    && !visited[now.view - 1][now.clipboard]) {
                queue.add(new Node(now.t + 1, now.view - 1, now.clipboard));
                visited[now.view - 1][now.view] = true;
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static class Node {
        int t;
        int view;
        int clipboard;

        public Node(int t, int view, int clipboard) {
            this.t = t;
            this.view = view;
            this.clipboard = clipboard;
        }
    }
}