// 9843

package swea.difficulty5;

import java.io.*;

public class CandleEvent {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; ++tc) {
			long N = Long.parseLong(br.readLine());
			long N2 = N << 1;
			long left = 0;
			long right = (long) Math.sqrt(N2);
			long mid, calc, answer = -1;
			while (left <= right) {
				mid = (left + right) >> 1;
				calc = mid * (mid + 1);
				if (calc == N2) {
					answer = mid;
					break;
				} else if (calc < N2) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.print(sb.toString());
	    br.close();
	}
}
