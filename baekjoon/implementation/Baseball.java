// https://www.acmicpc.net/problem/17281

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class Baseball {

    private static int N, answer = 0;
    private static int[] perm;
    private static int[][] score;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        score = new int[N][9];
        perm = new int[9];
        perm[3] = 0;

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; ++j) {
                score[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 1);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int depth, int visit) {
        if (depth == 9) {
            answer = Math.max(answer, simul());
            return;
        }
        for (int i = 1; i < 9; ++i) {
            if ((visit & (1 << i)) != 0)
                continue;
            if (depth == 3) {
                dfs(depth + 1, visit);
                break;
            }
            perm[depth] = i;
            dfs(depth + 1, visit | (1 << i));
        }
    }

    private static int simul() {
        int res = 0;
        int pIdx = 0;
        int[] counter = new int[3];

        for (int inn = 0; inn < N; ++inn) {
            Arrays.fill(counter, 0);

            for (int out = 0; out < 3; pIdx = (pIdx + 1) % 9) {
                switch (score[inn][perm[pIdx]]) {
                    case 0:
                        ++out;
                        break;
                    case 1:
                        res += counter[2];
                        counter[2] = counter[1];
                        counter[1] = counter[0];
                        counter[0] = 1;
                        break;
                    case 2:
                        res += counter[2];
                        res += counter[1];
                        counter[2] = counter[0];
                        counter[1] = 1;
                        counter[0] = 0;
                        break;
                    case 3:
                        res += counter[2];
                        res += counter[1];
                        res += counter[0];
                        counter[2] = 1;
                        counter[1] = 0;
                        counter[0] = 0;
                        break;
                    case 4:
                        res += counter[2];
                        res += counter[1];
                        res += counter[0];
                        res += 1;
                        counter[0] = 0;
                        counter[1] = 0;
                        counter[2] = 0;
                        break;
                }
            }
        }
        return res;
    }
}