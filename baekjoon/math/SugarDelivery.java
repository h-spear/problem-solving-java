// https://www.acmicpc.net/problem/2839

package baekjoon.math;

import java.io.*;

public class SugarDelivery {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int answer = -1;

        int n = Integer.parseInt(br.readLine());

        for (int i = (n / 5); i >= 0; --i) {
            int remain = n - i * 5;
            if (remain % 3 == 0) {
                answer = i + remain / 3;
                break;
            }
        }

        bw.write("" + answer);
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new SugarDelivery().solution();
    }
}
