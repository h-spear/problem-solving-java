// https://school.programmers.co.kr/learn/courses/30/lessons/42842?language=java

package programmers.level2;

class Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = {0, 0};
        int t = brown / 2 + 2;

        for (int w = t - 1; w >= t - w; --w) {
            if ((w - 2) * (t - w - 2) == yellow) {
                answer[0] = w;
                answer[1] = t - w;
                break;
            }
        }
        return answer;
    }
}