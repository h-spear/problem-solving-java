// https://www.acmicpc.net/problem/11277

package baekjoon.bruteforce;

import java.io.*;
import java.util.*;

public class TwoSatisfiabilityProblem1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] clause = new int[M][2];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            clause[i][0] = Integer.parseInt(st.nextToken());
            clause[i][1] = Integer.parseInt(st.nextToken());
        }

        int fullVisit = (1 << N) - 1;
        boolean possible = false;
        for (int i = 0; i <= fullVisit && !possible; ++i) {
            possible = validate(i, clause);
        }
        System.out.println(possible ? 1 : 0);
        br.close();
    }

    private static boolean validate(int bit, int[][] clause) {
        for (int[] pair: clause) {
            int x = pair[0];
            int y = pair[1];
            boolean flag1 = x > 0 ? ((bit & (1 << (x - 1))) > 0) : ((bit & (1 << (-x - 1))) == 0);
            boolean flag2 = y > 0 ? ((bit & (1 << (y - 1))) > 0) : ((bit & (1 << (-y - 1))) == 0);
            if (!flag1 && !flag2)
                return false;
        }
        return true;
    }
}
