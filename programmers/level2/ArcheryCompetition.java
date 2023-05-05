// https://school.programmers.co.kr/learn/courses/30/lessons/92342

package programmers.level2;

public class ArcheryCompetition {

    private boolean findAnswer = false;
    private int diff = 0;
    private int[] ryan = new int[11];
    private int[] apeach;
    private int[] answer;

    private int compareRyanAndApeach() {
        int scoreApeach = 0;
        int scoreRyan = 0;
        for (int i = 0; i <= 10; ++i) {
            if (apeach[10 - i] == 0 && ryan[10 - i] == 0) {
                continue;
            }
            if (apeach[10 - i] >= ryan[10 - i]) {
                scoreApeach += i;
            } else {
                scoreRyan += i;
            }
        }
        return scoreRyan - scoreApeach;
    }

    private void compareAnswerAndUpdate(int[] array) {
        if (answer == null) {
            answer = array.clone();
            return;
        }
        for (int i = 10; i >= 0; --i) {
            if (answer[i] < array[i]) {
                answer = array.clone();
                return;
            } else if (answer[i] > array[i]) {
                return;
            }
        }
    }

    private void dfs(int n, int cursor) {
        if (cursor == 11) {
            int compare = compareRyanAndApeach();
            if (compare > diff) {
                findAnswer = true;
                diff = compare;
                answer = ryan.clone();
            } else if (compare == diff) {
                compareAnswerAndUpdate(ryan);
            }
        } else {
            for (int i = 0; i <= n; ++i) {
                ryan[cursor] = i;
                dfs(n - i, cursor + 1);
                ryan[cursor] = 0;
            }
        }
    }

    public int[] solution(int n, int[] info) {
        apeach = info;
        dfs(n, 0);
        return findAnswer ? answer : new int[]{-1};
    }
}
