// https://school.programmers.co.kr/learn/courses/30/lessons/388351

package programmers.level1;

class FlexibleWorkingHours {

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n = schedules.length;
        int answer = n;
        for (int i = 0; i < n; ++i) {
            int st = convertTimeToMinutes(schedules[i]) + 10;

            for (int j = 0, k = startday; j < 7; ++j) {
                if (k < 6) {
                    int tt = convertTimeToMinutes(timelogs[i][j]);
                    if (tt > st) {
                        --answer;
                        break;
                    }
                }
                if (++k == 8)
                    k = 1;
            }
        }
        return answer;
    }

    private int convertTimeToMinutes(int time) {
        int h = time / 100;
        int m = time % 100;
        return h * 60 + m;
    }
}