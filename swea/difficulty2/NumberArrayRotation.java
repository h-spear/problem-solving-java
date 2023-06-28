// 1961

package swea.difficulty2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class NumberArrayRotation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            int N = Integer.parseInt(br.readLine());
            int[][] matrix = new int[N][N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; ++j) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            StringBuilder[] answer = new StringBuilder[N];
            for (int i = 0; i < N; ++i) {
                answer[i] = new StringBuilder();
            }
            for (int i = 0; i < 3; ++i) {
                matrix = rotateDegree90(matrix);
                for (int r = 0; r < N; ++r) {
                    for (int c = 0; c < N; ++c) {
                        answer[r].append(matrix[r][c]);
                    }
                    if (i < 2) {
                        answer[r].append(" ");
                    } else {
                        answer[r].append("\n");
                    }
                }
            }

            bw.write("#" + tc + "\n");
            for (int i = 0; i < N; ++i) {
                bw.write(answer[i].toString());
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int[][] rotateDegree90(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] result = new int[M][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                result[j][N - i - 1] = matrix[i][j];
            }
        }
        return result;
    }
}