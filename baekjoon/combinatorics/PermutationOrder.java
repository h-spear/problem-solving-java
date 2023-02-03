// https://www.acmicpc.net/problem/1722

package baekjoon.combinatorics;

import java.io.*;
import java.util.*;

public class PermutationOrder {

    private static int N;
    private static long K;
    private static long[] factorial;
    private static boolean[] used;
    private static int[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        factorial = new long[N + 1];
        used = new boolean[N + 1];

        factorial[0] = 1;
        factorial[1] = 1;
        for (int i = 2; i <= N; ++i) {
            factorial[i] = factorial[i-1] * i;
        }

        sequence = new int[N];
        st = new StringTokenizer(br.readLine());
        if (Integer.parseInt(st.nextToken()) == 1) {
            K = Long.parseLong(st.nextToken());
            indexToSequence(K, 0);
            for (int i = 0; i < N; ++i) {
                bw.write(sequence[i] + " ");
            }
        } else {
            for (int i = 0; i < N; ++i) {
                sequence[i] = Integer.parseInt(st.nextToken());
            }
            bw.write("" + sequenceToIndex(0));
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void indexToSequence(long k, int i) {
        if (i == N) {
            return;
        }

        long fact = factorial[N - i - 1];
        long temp = 0;
        int count = 0;
        while (temp + fact < k) {
            temp += fact;
            count++;
        }

        for (int number = 1; number <= N; ++number) {
            if (!used[number]) {
                if (count == 0) {
                    used[number] = true;
                    sequence[i] = number;
                    break;
                } else {
                    count--;
                }
            }
        }
        indexToSequence(k - temp, i + 1);
    }

    private static long sequenceToIndex(int i) {
        if (i == N) {
            return 1;
        }

        int number = sequence[i];
        used[number] = true;

        // 한 칸 아래 factorial 수
        long fact = factorial[N - i - 1];
        int count = 0;
        for (int j = 1; j < number; ++j) {
            if (!used[j]) {
                count++;
            }
        }

        return count * fact + sequenceToIndex(i + 1);
    }
}
