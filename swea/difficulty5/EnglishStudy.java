// 10507

package swea.difficulty5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EnglishStudy {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		boolean[] study = new boolean[1000001];

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			Arrays.fill(study, false);

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; ++i) {
				study[Integer.parseInt(st.nextToken())] = true;
			}

			int left = 0;
			int notStudy = 0;
			int answer = 0;
			for (int right = 0; right <= 1000000; ++right) {
				if (!study[right]) {
					notStudy += study[right] ? 0 : 1;
				}
				while (notStudy > p) {
					notStudy -= study[left++] ? 0 : 1;
				}
				answer = Math.max(answer, right - left + 1);
			}
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
