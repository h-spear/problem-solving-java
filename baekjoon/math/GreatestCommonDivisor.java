// https://www.acmicpc.net/problem/2824
// 에라토스테네스의 체

package baekjoon.math;

import java.io.*;
import java.util.*;

public class GreatestCommonDivisor {

    private static boolean[] checked;
    private static List<Integer> primes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        makePrimeList();

        Map<Integer, Integer> counterA = new HashMap<>();
        Map<Integer, Integer> counterB = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            primeFactorization(counterA, A[i]);
        }

        int M = Integer.parseInt(br.readLine());
        int[] B = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            B[i] = Integer.parseInt(st.nextToken());
            primeFactorization(counterB, B[i]);
        }

        boolean over = false;
        long answer = 1;
        for (int key: counterA.keySet()) {
            int count = Math.min(counterA.get(key), counterB.getOrDefault(key, 0));

            for (int i = 0; i < count; ++i) {
                answer *= key;
                if (answer >= 1000000000) {
                    answer -= (answer / 1000000000) * 1000000000;
                    over = true;
                }
            }
        }

        // 자릿수가 잘리는 경우에는 0을 붙임.
        if (over) {
            System.out.printf("0".repeat(9 - String.valueOf(answer).length()));
        }
        System.out.println(answer);

        br.close();
    }

    private static void makePrimeList() {
        checked = new boolean[40001];
        primes = new ArrayList<>();
        for (int i = 2; i <= 40000; ++i) {
            if (checked[i]) {
                continue;
            }
            primes.add(i);
            for (int j = i + i; j <= 40000; j += i) {
                checked[j] = true;
            }
        }
    }

    private static void primeFactorization(Map<Integer, Integer> counter, int n) {
        int idx = 0;
        while (n != 1) {
            if (idx == primes.size()) {
                counter.put(n, counter.getOrDefault(n, 0) + 1);
                n = 1;
            } else if (n % primes.get(idx) == 0) {
                counter.put(primes.get(idx), counter.getOrDefault(primes.get(idx), 0) + 1);
                n /= primes.get(idx);
            } else {
                idx++;
            }
        }
    }
}
