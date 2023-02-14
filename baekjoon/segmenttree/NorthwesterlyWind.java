// https://www.acmicpc.net/problem/5419

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class NorthwesterlyWind {

    private static int N, S;
    private static List<Pair> islands = new ArrayList<>(75001);
    private static int[] tree;
    private static Map<Integer, Integer> yMap = new HashMap<>();

    class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {

            islands.clear();
            yMap.clear();

            N = Integer.parseInt(br.readLine());
            S = 1;
            while (S < N) {
                S <<= 1;
            }
            tree = new int[S * 2];

            int x, y;
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                islands.add(new Pair(x, y));
                yMap.put(y, 0);
            }

            // y 좌표 압축
            int[] keys = yMap.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
            int idx = 0;
            for (int key: keys) {
                yMap.put(key, idx++);
            }

            for (Pair island: islands) {
                island.y = yMap.get(island.y);
            }

            // x 좌표 내림차순, y 좌표 오름차순
            Collections.sort(islands, ((o1, o2) -> {
                int comp = o2.x - o1.x;
                if (comp == 0) {
                    return o1.y - o2.y;
                } else {
                    return comp;
                }
            }));

            long answer = 0;
            for (Pair island: islands) {
                answer += query(1, 0, S - 1, 0, island.y);
                update(1, 0, S - 1, island.y, 1);
            }
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int target, int diff) {
        if (target < left || right < target) {
            return;
        }
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(node * 2, left, mid, target, diff);
            update(node * 2 + 1, mid + 1, right, target, diff);
        }
    }

    private long query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) / 2;
        return query(node * 2, left, mid, queryLeft, queryRight) +
                query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
    }

    public static void main(String[] args) throws Exception {
        new NorthwesterlyWind().solution();
    }
}
