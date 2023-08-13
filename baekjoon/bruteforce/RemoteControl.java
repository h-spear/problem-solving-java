// https://www.acmicpc.net/problem/1107

package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

public class RemoteControl {

    private static final int fullVisit = (1 << 10) - 1;
    private static boolean[] button = new boolean[10];
    private static int N, L, answer = Integer.MAX_VALUE;
    private static int[] perm;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        Arrays.fill(button, true);
        int M;

        N = Integer.parseInt(br.readLine());
        L = String.valueOf(N).length();
        perm = new int[L + 1];

        if (N > 100) {
            answer = N - 100;
        } else {
            answer = 100 - N;
        }

        M = Integer.parseInt(br.readLine());
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; ++i) {
                button[Integer.parseInt(st.nextToken())] = false;
            }
        }
        if (button[0])
            answer = Math.min(answer, N + 1);

        if (L > 1 && L - 1 < answer)
            dfs(0, L - 1, 0);
        if (L < answer)
            dfs(0, L, 0);
        if (L < 6 && L < answer)
            dfs(0, L + 1, 0);

        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int maxDepth, int visit) {
        if (depth >= answer)
            return;
        if (depth == maxDepth) {
            int number = permToNumber(perm, maxDepth);
            if (N > number) {
                answer = Math.min(answer, N - number + maxDepth);
            } else {
                answer = Math.min(answer, number - N + maxDepth);
            }
            return;
        }
        for (int i = 0; i < 10; ++i) {
            if ((visit & fullVisit) == 0 && i == 0)
                continue;
            if (!button[i])
                continue;
            perm[depth] = i;
            dfs(depth + 1, maxDepth, visit | (1 << i));
        }
    }

    private static int permToNumber(int[] perm, int length) {
        int res = 0;
        for (int i = 0; i < length; ++i) {
            res += perm[i] * (int) Math.pow(10, length - i - 1);
        }
        return res;
    }
}
