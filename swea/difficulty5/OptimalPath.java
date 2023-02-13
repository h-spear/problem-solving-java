// 1247

package swea.difficulty5;

import java.io.*;
import java.util.*;

public class OptimalPath {

    private static int N, answer;
    private static Pair[] customer;
    private static List<List<Node>> graph;
    private static Pair company, house;
    private static boolean[] visited;

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            customer = new Pair[N];
            graph = new ArrayList<>(11);
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < N + 2; ++i) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());

            company = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            house = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            
            int x, y;
            for (int i = 0; i < N; ++i) {
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                customer[i] = new Pair(x, y);
            }

            // 회사(0) -> 고객의 집(1~N)
            for (int i = 0; i < N; ++i) {
                int cost = Math.abs(customer[i].x - company.x) + Math.abs(customer[i].y - company.y);
                graph.get(0).add(new Node(i + 1, cost));
            }

            // 고객의 집들 끼리 연결
            for (int i = 0; i < N; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    int cost = Math.abs(customer[i].x - customer[j].x) + Math.abs(customer[i].y - customer[j].y);
                    graph.get(i + 1).add(new Node(j + 1, cost));
                    graph.get(j + 1).add(new Node(i + 1, cost));
                }
            }

            visited = new boolean[N + 1];
            dfs(0, 0, 0); // 회사에서 시작
            bw.write("#" + tc + " " + answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int cost, int depth) {
        visited[x] = true;

        if (depth == N) {
            int res = cost + Math.abs(customer[x - 1].x - house.x) + Math.abs(customer[x - 1].y - house.y);
            answer = Math.min(answer, res);
        } else {

            for (Node next: graph.get(x)) {
                if (visited[next.idx] == false) {
                    dfs(next.idx, cost + next.cost, depth + 1);
                }
            }
        }

        visited[x] = false;
    }
}
