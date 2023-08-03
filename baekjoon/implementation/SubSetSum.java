// https://www.acmicpc.net/problem/1182

package baekjoon.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SubSetSum {

    private static int N, S;
    private static int[] A;

    private static int dfs(int depth, int sum, int count) {
        if (depth == N) {
            if (count > 0 && sum == S)
                return 1;
            return 0;
        }

        return dfs(depth + 1, sum + A[depth], count + 1) +
                dfs(depth + 1, sum, count);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(dfs(0, 0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}
