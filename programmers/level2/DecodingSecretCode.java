// https://school.programmers.co.kr/learn/courses/30/lessons/388352

package programmers.level2;

class DecodingSecretCode {

    private final int[] comb = new int[5];
    private int n, m, answer;
    private int[][] q;
    private int[] ans;

    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.m = q.length;
        this.q = q;
        this.ans = ans;
        dfs(1, 0);
        return answer;
    }

    private void dfs(int index, int depth) {
        if (depth == 5) {
            if (test(comb))
                ++answer;
            return;
        }
        for (int i = index; i <= n; ++i) {
            comb[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    private boolean test(int[] pwd) {
        for (int i = 0; i < m; ++i) {
            int count = 0;
            for (int j = 0; j < 5; ++j) {
                if (contains(pwd, q[i][j]))
                    ++count;
            }

            if (count != ans[i])
                return false;
        }
        return true;
    }

    private boolean contains(int[] arr, int num) {
        for (int x: arr) {
            if (x == num)
                return true;
        }
        return false;
    }
}