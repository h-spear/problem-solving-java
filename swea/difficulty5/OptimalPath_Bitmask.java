// 1247

package swea.difficulty5;

import java.io.*;
import java.util.*;

public class OptimalPath_Bitmask {

    private static int N, answer;
    private static int[][] graph;
    private static int fullVisit;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            List<Pair> pairs = new ArrayList<>();
            N = Integer.parseInt(br.readLine());
            graph = new int[N + 2][N + 2];
            fullVisit = (1 << (N + 2)) - 1;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < (N + 2) * 2; i += 2) {
                pairs.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            Pair p1, p2;
            for (int i = 0; i < N + 2; ++i) {
                p1 = pairs.get(i);
                for (int j = i + 1; j < N + 2; ++j) {
                    p2 = pairs.get(j);
                    int w = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
                    graph[i][j] = w;
                    graph[j][i] = w;
                }
            }

            answer = Integer.MAX_VALUE;
            dfs(0, 0, 3);
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int node, int distance, int visited) {
        if (visited == fullVisit) {
            answer = Math.min(answer, distance + graph[node][1]);
        }
        for (int i = 2; i < N + 2; ++i) {
            if ((visited & (1 << i)) == 0) {
                dfs(i, distance + graph[node][i], visited | (1 << i));
            }
        }
    }
}