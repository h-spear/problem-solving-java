// https://school.programmers.co.kr/learn/courses/30/lessons/12938

package programmers.level3;

class BestSet {
    public int[] solution(int n, int s) {
        int q = s / n;
        int r = s % n;
        int idx = 0;
        if (q <= 0)
            return new int[]{-1};

        int[] answer = new int[n];

        for (int i = 0; i < n - r; ++i) {
            answer[idx] = q;
            idx++;
        }

        for (int i = 0 ; i < r; ++i) {
            answer[idx] = q + 1;
            idx++;
        }
        return answer;
    }
}