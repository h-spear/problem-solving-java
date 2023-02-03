// https://www.acmicpc.net/problem/1837

package baekjoon.math;

import java.io.*;
import java.util.*;

public class PasswordCreation {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        String P = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        List<Integer> primes = getPrimes(K - 1);

        boolean good = true;
        int badPrime = 0;
        for (int prime: primes) {
            if (bigMod(P, prime) == 0) {
                good = false;
                badPrime = prime;
                break;
            }
        }

        if (good) {
            System.out.println("GOOD");
        } else {
            System.out.println("BAD " + badPrime);
        }
        br.close();
    }

    private static int bigMod(String p, int k) {
        int n = p.length();
        int m = String.valueOf(k).length();
        int num = Integer.parseInt(p.substring(0, m));
        for (int i = m; i < n; ++i) {
            num = num % k;
            num *= 10;
            num += p.charAt(i) - '0';
        }
        return num % k;
    }

    private static List<Integer> getPrimes(int k) {
        // 에라토스테네스의 체
        boolean[] isPrime = new boolean[k + 5];
        for (int i = 0; i <= k; ++i) {
            isPrime[i] = true;
        }
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i <= (int) Math.sqrt(k) + 1; ++i) {
            if (isPrime[i] == false) {
                continue;
            }
            int j = 2;
            while (i * j <= k) {
                isPrime[i * j] = false;
                j += 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= k; ++i) {
            if (isPrime[i]) {
                result.add(i);
            }
        }
        return result;
    }
}
