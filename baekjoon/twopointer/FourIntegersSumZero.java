// https://www.acmicpc.net/problem/7453
// hashMap -> 시간초과(크기가 너무 크면 해시맵을 저격한 테스트케이스가 존재할 수 있음)
// Collections.sort -> 시간초과
// Arrays.sort -> 통과(https://www.acmicpc.net/board/view/50851)

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class FourIntegersSumZero {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        long[] B = new long[n];
        long[] C = new long[n];
        long[] D = new long[n];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            A[i] = Long.parseLong(st.nextToken());
            B[i] = Long.parseLong(st.nextToken());
            C[i] = Long.parseLong(st.nextToken());
            D[i] = Long.parseLong(st.nextToken());
        }

        long[] s1 = new long[n * n];
        long[] s2 = new long[n * n];

        int idx = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                s1[idx] = A[i] + B[j];
                s2[idx++] = C[i] + D[j];
            }
        }

        Arrays.sort(s1);
        Arrays.sort(s2);

        long answer = 0;

        int size = s1.length;
        int pointer1 = 0;
        int pointer2 = size - 1;
        while (pointer1 < size && pointer2 >= 0) {
            long sum = s1[pointer1] - s2[pointer2];

            if (sum < 0) {
                ++pointer1;
            } else if (sum > 0) {
                ++pointer2;
            } else {
                // 중복 개수
                long a = 1;
                long b = 1;
                while (pointer1 < size - 1 && s1[pointer1] == s1[pointer1 + 1]) {
                    ++pointer1;
                    ++a;
                }
                while (pointer2 > 0 && s2[pointer2] == s1[pointer2 + 1]) {
                    ++pointer2;
                    ++b;
                }
                answer += (a * b);
                ++pointer1;
                ++pointer2;
            }
        }
        System.out.println(answer);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new FourIntegersSumZero().solution();
    }
}
