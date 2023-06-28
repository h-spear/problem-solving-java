// 7465

package swea.difficulty4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class TownGroupCount {

    private static int N, M;
    private static List<List<Integer>> graph;
    private static boolean[] visited = new boolean[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int A, B;

        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= N; ++i) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                graph.get(A).add(B);
                graph.get(B).add(A);
            }

            Arrays.fill(visited, false);
            int answer = 0;
            for (int i = 1; i <= N; ++i) {
                if (!visited[i]) {
                    answer += dfs(i);
                }
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int dfs(int x) {
        visited[x] = true;
        for (int y: graph.get(x)) {
            if (!visited[y]) {
                dfs(y);
            }
        }
        return 1;
    }
}