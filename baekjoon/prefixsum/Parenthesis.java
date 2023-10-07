// https://www.acmicpc.net/problem/9012

package baekjoon.prefixsum;

import java.io.*;

public class Parenthesis {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


		int[] sum = new int[55];
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int i = 0;
			char[] p = br.readLine().toCharArray();
			int length = p.length;
			boolean vps = true;
			for (; i < length; ++i) {
				if (p[i] == '(') {
					sum[i + 1] = sum[i] + 1;
					continue;
				}

				sum[i + 1] = sum[i] - 1;
				if (sum[i + 1] == -1) {
					vps = false;
					break;
				}
			}

			if (i == length && sum[i] != 0) {
				vps = false;
			}
			bw.write(vps ? "YES\n" : "NO\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
