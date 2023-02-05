// https://www.acmicpc.net/problem/2824
// BigInteger 사용

package baekjoon.math;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class GreatestCommonDivisor_BigInteger {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        BigInteger A = new BigInteger("1");

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; ++i) {
            A = A.multiply(new BigInteger(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        BigInteger B = new BigInteger("1");

        for(int i = 0; i < M; ++i)
            B = B.multiply(new BigInteger(st.nextToken()));

        BigInteger answer = A.gcd(B);
        if (answer.toString().length() > 9) {
            System.out.println(answer.toString().substring(answer.toString().length() - 9));
        } else {
            System.out.println(answer);
        }

        br.close();
    }
}
