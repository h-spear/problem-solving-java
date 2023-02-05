// https://www.acmicpc.net/problem/6588

package baekjoon.math;

import java.io.*;
import java.util.*;

public class GoldbachConjecture {

    private static boolean[] checked = new boolean[1000001];
    private static List<Integer> primes = new ArrayList<>();
    private static BufferedReader br;
    private static BufferedWriter bw;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 에라토스테네스의 체
        checked[0] = true;  // checked -> true : 소수가 아님
        checked[1] = true;
        for (int i = 2; i <= 1000000; ++i) {
            if (checked[i]) {
                continue;
            }
            primes.add(i);
            for (int j = i + i; j <= 1000000; j += i) {
                checked[j] = true;
            }
        }

        int N;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            goldbachConjecture(N);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void goldbachConjecture(int n) throws Exception {
        // 2는 제외
        int a, b;
        for (int i = 1; i < primes.size(); ++i) {
            a = primes.get(i);
            b = n - a;

            if (a > b) {
                bw.write("Goldbach's conjecture is wrong.");
                bw.newLine();
                break;
            } else if (checked[b] == false) {
                bw.write(n + " = " + a + " + " + b);
                bw.newLine();
                break;
            }
        }
    }
}
