package baekjoon.twopointer;

import java.io.*;
import java.util.*;


public class SumOfTwoArrays {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long T;
        int N, M;
        int[] arr1, arr2;

        T = Long.parseLong(br.readLine());

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

        Integer[] subArray1 = getSubArray(N, arr1);
        Integer[] subArray2 = getSubArray(M, arr2);

        Arrays.sort(subArray1);
        Arrays.sort(subArray2, Comparator.reverseOrder());

        int[] sub1 = Arrays.stream(subArray1).mapToInt(Integer::intValue).toArray();
        int[] sub2 = Arrays.stream(subArray2).mapToInt(Integer::intValue).toArray();

        int sub1Size = sub1.length;
        int sub2Size = sub2.length;

        int pointer1 = 0;
        int pointer2 = 0;

        long answer = 0;

        while (pointer1 < sub1Size && pointer2 < sub2Size) {
            long sum = sub1[pointer1] + sub2[pointer2];

            if (sum == T) {
                long a = 1;
                long b = 1;
                while (pointer1 < sub1Size - 1 && sub1[pointer1] == sub1[pointer1 + 1]) {
                    ++pointer1;
                    ++a;
                }
                while (pointer2 < sub2Size - 1 && sub2[pointer2] == sub2[pointer2 + 1]) {
                    ++pointer2;
                    ++b;
                }
                answer += (a * b);
                ++pointer1;
                ++pointer2;
            } else if (sum > T) {
                ++pointer2;
            } else {
                ++pointer1;
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static Integer[] getSubArray(int n, int[] arr) {
        Integer[] subArray = new Integer[(n * (n + 1)) / 2];
        int idx = 0;
        for(int i = 0, j = 0; i < n; i++){
            int sum = 0;
            for(int k = i; k < n; k++, j++){
                sum += arr[k];
                subArray[idx++] = sum;
            }
        }

        return subArray;
    }
}
