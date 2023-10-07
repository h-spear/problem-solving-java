// 1256

package swea.difficulty5;

import java.io.*;
import java.util.*;

public class KthSuffix {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] strings = new String[401];

		for (int tc = 1; tc <= T; ++tc) {
			int K = Integer.parseInt(br.readLine());
			String string = br.readLine();
			int length = string.length();
			for (int i = 0; i < length; ++i) {
				strings[i] = string.substring(length - i - 1);
			}
			Arrays.sort(strings, 0, length);
			sb.append("#").append(tc).append(" ")
				.append(strings[K - 1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}