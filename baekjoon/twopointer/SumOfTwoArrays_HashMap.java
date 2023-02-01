package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class SumOfTwoArrays_HashMap {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T, N, M;
        int[] arr1, arr2;

        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int[] sub1 = getSubArray(N, arr1);
        int[] sub2 = getSubArray(M, arr2);

        Map<Integer, Long> counter1 = new HashMap<>();

        for (int i = 0; i < sub1.length; ++i) {
            counter1.put(sub1[i], counter1.getOrDefault(sub1[i], 0L) + 1);
        }

        long answer = 0;
        for (int num: sub2) {
            if (counter1.containsKey(T - num)) {
                answer += counter1.get(T - num);
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static int[] getSubArray(int n, int[] arr) {
        int[] subArray = new int[(n * (n + 1)) / 2];
        int idx = 0;
        for (int i = 0; i < n; ++i) {
            int elem = 0;
            for (int j = i; j < n; ++j) {
                elem += arr[j];
                subArray[idx++] = elem;
            }
        }
        return subArray;
    }
}
