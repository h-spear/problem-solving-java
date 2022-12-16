package programmers.level3;// https://school.programmers.co.kr/learn/courses/30/lessons/12979?language=java

class BaseStationInstallation {
    public int solution(int n, int[] stations, int w) {
        int wide = 2 * w + 1;
        int answer = 0;
        int prev = 1;
        for (int station: stations) {
            int left = station - w;
            int right = station + w;
            answer += (int)Math.ceil((double)(left - prev) / wide);
            prev = right + 1;
        }
        answer += (int)Math.ceil((double)(n - prev + 1) / wide);

        return answer;
    }
}