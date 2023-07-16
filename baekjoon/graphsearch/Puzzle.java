// https://www.acmicpc.net/problem/1525

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Puzzle {

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final String completeString = "123456780";
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] graph = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; ++j) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Map<String, Integer> visited = new HashMap<>();
        Queue<String> queue = new LinkedList<>();

        String s = puzzleToString(graph);
        queue.add(s);
        visited.put(s, 0);

        int nx, ny, temp;
        int[][] nPuzzle;
        int answer = -1;
        while (!queue.isEmpty()) {
            s = queue.poll();
            int[][] puzzle = stringToPuzzle(s);
            Pair p = findZero(puzzle);
            if (s.equals(completeString)) {
                answer = visited.get(s);
            }

            for (int i = 0; i < 4; ++i) {
                nPuzzle = copy(puzzle);
                nx = p.x + dx[i];
                ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3)
                    continue;
                temp = nPuzzle[p.x][p.y];
                nPuzzle[p.x][p.y] = nPuzzle[nx][ny];
                nPuzzle[nx][ny] = temp;
                String ns = puzzleToString(nPuzzle);

                if (visited.containsKey(ns))
                    continue;
                queue.add(ns);
                visited.put(ns, visited.get(s) + 1);
            }
        }
        bw.write("" + answer);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[][] copy(int[][] graph) {
        int[][] copied = new int[3][3];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                copied[i][j] = graph[i][j];
            }
        }
        return copied;
    }

    private static Pair findZero(int[][] graph) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (graph[i][j] == 0) {
                    return new Pair(i, j);
                }
            }
        }
        return null;
    }

    private static String puzzleToString(int[][] graph) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(graph[i][j]);
            }
        }
        return sb.toString();
    }

    private static int[][] stringToPuzzle(String string) {
        int[][] graph = new int[3][3];
        for (int i = 0, idx = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                graph[i][j] = string.charAt(idx++) - '0';
            }
        }
        return graph;
    }
}
