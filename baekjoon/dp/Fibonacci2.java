// https://www.acmicpc.net/problem/2748

package baekjoon.dp;

import java.io.*;

public class Fibonacci2 {

    private long fibonacci(int n) {
        if (n < 0)
            return 0;
        if (n <= 2)
            return 1;

        long prevPrev = 0L;
        long prev = 1L;
        long res = prevPrev + prev;
        for (int i = 0; i < n - 2; ++i) {
            prevPrev = prev;
            prev = res;
            res = prevPrev + prev;
        }
        return res;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(fibonacci(n));

        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Fibonacci2().solution();
    }
}
