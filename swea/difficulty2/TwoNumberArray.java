// 1959

package swea.difficulty2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TwoNumberArray {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] A = new int[N];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; ++i) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            if (N < M) {
                int temp = N;
                N = M;
                M = temp;

                int[] temp2 = A;
                A = B;
                B = temp2;
            }

            int answer = Integer.MIN_VALUE;
            for (int i = 0; i < N - M + 1; ++i) {
                int temp = 0;
                for (int j = 0; j < M; ++j) {
                    temp += A[i + j] * B[j];
                }
                answer = Math.max(answer, temp);
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}