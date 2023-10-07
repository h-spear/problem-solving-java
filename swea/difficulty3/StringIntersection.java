// 2948

package swea.difficulty3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class StringIntersection {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int answer = 0;
			Set<String> set = new HashSet<>();

			st = new StringTokenizer(br.readLine());
			while (N-- > 0) {
				set.add(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			while (M-- > 0) {
				answer += set.contains(st.nextToken()) ? 1 : 0;
			}

			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
