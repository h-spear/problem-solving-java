// https://www.acmicpc.net/problem/2295

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class SumOfThreeNumbers {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        Set<Integer> sumOfTwo = new HashSet<>();
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(br.readLine());
        }

        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {
                sumOfTwo.add(A[x] + A[y]);
            }
        }

        int answer = 0;
        for (int z = 0; z < N; ++z) {
            for (int k = 0; k < N; ++k) {
                if (sumOfTwo.contains(A[k] - A[z])) {
                    answer = Math.max(answer, A[k]);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
