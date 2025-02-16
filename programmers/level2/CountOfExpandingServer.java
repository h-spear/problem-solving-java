// https://school.programmers.co.kr/learn/courses/30/lessons/389479

package programmers.level2;

public class CountOfExpandingServer {

	public int solution(int[] players, int m, int k) {
		int answer = 0;
		int[] servers = new int[24];
		for (int addedCount, i = 0; i < 24; ++i) {
			if ((servers[i] + 1) * m > players[i]) {
				continue;
			}
			answer += addedCount = (players[i] - servers[i] * m) / m;
			for (int j = 0; j < Math.min(24, i + k); ++j) {
				servers[j] += addedCount;
			}
		}
		return answer;
	}
}
