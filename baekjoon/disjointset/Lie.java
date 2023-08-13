package baekjoon.disjointset;

import java.io.*;
import java.util.*;

public class Lie {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; ++i)
            parent[i] = i;

        st = new StringTokenizer(br.readLine());
        int numsOfKnows = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numsOfKnows; ++i) {
            union(parent, 0, Integer.parseInt(st.nextToken()));
        }

        int[][] parties = new int[M][];
        int people;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            people = Integer.parseInt(st.nextToken());
            parties[i] = new int[people];
            parties[i][0] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < people; ++j) {
                parties[i][j] = Integer.parseInt(st.nextToken());
                union(parent, parties[i][0], parties[i][j]);
            }
        }

        int answer = 0;
        boolean lie;
        for (int[] party: parties) {
            lie = true;
            for (int num: party) {
                if (find(parent, num) == 0) {
                    lie = false;
                    break;
                }
            }
            if (lie)
                ++answer;
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    private static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b)
            parent[b] = a;
        else
            parent[a] = b;
    }
}
