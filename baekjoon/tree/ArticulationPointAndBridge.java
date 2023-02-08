// https://www.acmicpc.net/problem/14675

package baekjoon.tree;

import java.io.*;
import java.util.*;

public class ArticulationPointAndBridge {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] counter = new int[N + 1];
        // 트리는 모든 간선이 단절선
        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            counter[a]++;
            counter[b]++;
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if (t == 1) {
                if (counter[k] > 1)
                    bw.write("yes\n");
                else
                    bw.write("no\n");
            } else {
                bw.write("yes\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
