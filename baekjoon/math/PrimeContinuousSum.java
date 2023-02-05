// https://www.acmicpc.net/problem/1644

package baekjoon.math;

import java.io.*;
import java.util.*;

public class PrimeContinuousSum {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] checked = new boolean[N + 1];
        List<Integer> primes = new ArrayList<>();

        checked[0] = true;
        checked[1] = true;
        for (int i = 2; i <= N; ++i) {
            if (checked[i]) {
                continue;
            }
            primes.add(i);
            for(int j = i + i; j <= N; j += i) {
                checked[j] = true;
            }
        }

        if (primes.isEmpty()) {
            System.out.println(0);
            return;
        }

        int left = 0;
        int right = 1;
        int curr = primes.get(left);
        int answer = checked[N] ? 0 : 1;
        while (right < primes.size()) {
            if (curr < N) {
                curr += primes.get(right++);
            } else if(curr >= N) {
                if (curr == N) {
                    answer++;
                }
                curr -= primes.get(left++);
            }
        }
        System.out.println(answer);
        br.close();
    }
}
