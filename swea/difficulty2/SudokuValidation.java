// 1974

package swea.difficulty2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SudokuValidation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            int[][] sudoku = new int[9][9];
            for (int i = 0; i < 9; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; ++j) {
                    sudoku[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bw.write("#" + tc + " ");
            bw.write(validate(sudoku) ? "1" : "0");
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean validate(int[][] sudoku) {
        boolean[] checker = new boolean[10];

        for (int r = 0; r < 9; ++r) {
            Arrays.fill(checker, false);
            for (int c = 0; c < 9; ++c) {
                if (checker[sudoku[r][c]]) {
                    return false;
                }
                checker[sudoku[r][c]] = true;
            }
        }

        for (int c = 0; c < 9; ++c) {
            Arrays.fill(checker, false);
            for (int r = 0; r < 9; ++r) {
                if (checker[sudoku[r][c]]) {
                    return false;
                }
                checker[sudoku[r][c]] = true;
            }
        }

        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                Arrays.fill(checker, false);
                for (int i = 0; i < 9; ++i) {
                    if (checker[sudoku[r + (i / 3)][c + (i % 3)]]) {
                        return false;
                    }
                    checker[sudoku[r][c]] = true;
                }
            }
        }
        return true;
    }
}