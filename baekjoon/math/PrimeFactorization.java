// https://www.acmicpc.net/problem/11653

package baekjoon.math;

import java.io.*;

public class PrimeFactorization {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int divisor = 2;
        while (N != 1) {
            if (N % divisor == 0) {
                bw.write("" + divisor);
                bw.newLine();
                N /= divisor;
            } else {
                divisor++;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
