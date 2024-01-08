// https://school.programmers.co.kr/learn/courses/30/lessons/258712

package programmers.level1;

import java.util.*;

class MostReceivedGift {
	public int solution(String[] friends, String[] gifts) {
		int answer = 0;
		int n = friends.length;

		Map<String, Integer> map = compression(friends);

		int[] giftScore = new int[n];
		int[][] giveCnt = new int[n][n];
		for (String gift: gifts) {
			String[] splited = gift.split(" ");
			int from = map.get(splited[0]);
			int to = map.get(splited[1]);
			++giftScore[from];
			--giftScore[to];
			++giveCnt[from][to];
		}

		for (int i = 0; i < n; ++i) {
			int cnt = 0;
			for (int j = 0; j < n; ++j) {
				if (i == j)
					continue;
				if (giveCnt[i][j] > giveCnt[j][i]) {
					++cnt;
				} else if (giveCnt[i][j] == giveCnt[j][i]) {
					if (giftScore[i] > giftScore[j]) {
						++cnt;
					}
				}
			}
			answer = Math.max(answer, cnt);
		}
		return answer;
	}

	private Map<String, Integer> compression(String[] arr) {
		int idx = 0;
		Map<String, Integer> map = new HashMap<>();
		for (String x: arr) {
			map.put(x, idx++);
		}
		return map;
	}
}