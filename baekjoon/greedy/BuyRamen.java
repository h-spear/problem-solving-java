package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class BuyRamen {

    private static final int[] cost = {3, 5, 7};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int price = 0;
        for (int i = 0; i < N; ++i) {
            if (i < N - 2 && A[i + 1] > A[i + 2]) {
                price += buy(A, i, i + 1, min(A[i], A[i + 1] - A[i + 2]));
                price += buy(A, i, i + 2, min(A[i], A[i + 1], A[i + 2]));
            } else {
                if (i < N - 2)
                    price += buy(A, i, i + 2, min(A[i], A[i + 1], A[i + 2]));
                if (i < N - 1)
                    price += buy(A, i, i + 1, min(A[i], A[i + 1]));
            }
            price += buy(A, i, i, A[i]);
        }
        System.out.println(price);
        br.close();
    }

    private static int buy(int[] A, int fromIndex, int toIndex, int buy) {
        for (int i = fromIndex; i <= toIndex; ++i)
            A[i] -= buy;
        return cost[toIndex - fromIndex] * buy;
    }

    private static int min(int... nums) {
        int res = Integer.MAX_VALUE;
        for (int num: nums)
            res = Math.min(res, num);
        return res;
    }
}
