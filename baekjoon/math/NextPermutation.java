// https://www.acmicpc.net/problem/10972

package baekjoon.math;

import java.io.*;
import java.util.*;

public class NextPermutation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] perm = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i)
            perm[i] = Integer.parseInt(st.nextToken());

        boolean np = nextPermutation(perm, N);
        if (np) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; ++i)
                sb.append(perm[i]).append(" ");
            System.out.println(sb.toString());
        } else {
            System.out.println(-1);
        }
        br.close();
    }

    private static boolean nextPermutation(int[] perm, int n) {
        int i, j, k;

        i = n - 1;
        while (i > 0 && perm[i - 1] > perm[i])
            --i;

        if (i == 0)
            return false;

        j = n - 1;
        while (perm[i - 1] > perm[j])
            --j;

        swap(perm, i - 1, j);

        k = n - 1;
        while (i < k)
            swap(perm, i++, k--);

        return true;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}