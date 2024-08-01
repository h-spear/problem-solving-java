// https://www.acmicpc.net/problem/11278

package baekjoon.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TwoSatisfiabilityProblem2 {

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
        int bit = -1;
        for (int i = 0; i <= fullVisit && !possible; ++i) {
            bit = i;
            possible = validate(i, clause);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(possible ? 1 : 0).append("\n");
        if (possible) {
            for (int i = 0; i < N; ++i) {
                if ((bit & (1 << i)) > 0)
                    sb.append(1);
                else sb.append(0);
                sb.append(" ");
            }
        }
        System.out.println(sb.toString());
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
