// https://www.acmicpc.net/problem/20004

package baekjoon.game;

import java.io.*;

public class BaskinRobbins31 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());

		// n = 4 - r;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= A; ++i) {
			if (30 % (i + 1) == 0) {
				sb.append(i).append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}
