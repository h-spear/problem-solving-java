// 11446

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class CandyBag {

	private static final long R = (long) Math.pow(10, 18);

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		long[] A = new long[111];
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long M = Long.parseLong(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; ++i) {
				A[i] = Long.parseLong(st.nextToken());
			}

			long left = 1;
			long right = R;
			long mid;
			while (left <= right) {
				mid = (left + right) >> 1;
				if (test(A, N, mid) >= M) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			sb.append("#").append(tc).append(" ")
				.append(left - 1).append("\n");
		}
		System.out.println(sb.toString());
	    br.close();
	}

	private static long test(long[] arr, int n, long make) {
		long res = 0;
		for (int i = 0; i < n; ++i) {
			res += arr[i] / make;
		}
		return res;
	}
}
