// https://school.programmers.co.kr/learn/courses/30/lessons/389478

package programmers.level1;

public class TakeOffDeliveryBox {

	public int solution(int n, int w, int num) {
		int h = (n / w) + 1;
		int[][] map = new int[h][w];

		for (int i = h - 1, left = 0, nn = 0; i >= 0; --i, left = 1 - left) {
			if (left == 0) {
				for (int j = 0; j < w; ++j) {
					map[i][j] = ++nn;
				}
			} else {
				for (int j = w - 1; j >= 0; --j) {
					map[i][j] = ++nn;
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				if (map[i][j] != num) {
					continue;
				}
				for (int ii = i; ii >= 0; --ii) {
					if (0 <= map[ii][j] && map[ii][j] <= n) {
						++answer;
					}
				}
				i = h;
				break;
			}
		}
		return answer;
	}
}
