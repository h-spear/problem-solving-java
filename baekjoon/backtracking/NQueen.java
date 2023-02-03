// https://www.acmicpc.net/problem/9663

package baekjoon.backtracking;

import java.util.*;

public class NQueen {

    private int N;
    private int[] queen;
    private int answer = 0;

    private boolean check(int row) {
        for (int i = 0; i < row; ++i) {
            if (queen[row] == queen[i] || Math.abs(queen[i] - queen[row]) == (row - i)) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int row) {
        // 1.

        // 2. 목적지인가?
        if (row == N) {
            ++answer;
        } else {
            // 3. 연결된 곳
            for (int col = 0; col < N; ++col) {
                // 4. 갈 수 있는가
                queen[row] = col;
                if (check(row)) {
                    // 5. 간다
                    dfs(row + 1);
                }
                queen[row] = 0;
            }
        }

        // 6.
    }

    private void solution() throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        queen = new int[N + 1];
        dfs(0);
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        new NQueen().solution();
    }
}
