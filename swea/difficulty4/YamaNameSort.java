// 7701

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class YamaNameSort {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			int N = Integer.parseInt(br.readLine());
			Set<String> set = new HashSet<>();
			for (int i = 0; i < N; ++i) {
				set.add(br.readLine());
			}
			String[] names = set.toArray(new String[0]);
			Arrays.sort(names, (o1, o2) -> {
				int l1 = o1.length();
				int l2 = o2.length();
				if (l1 > l2) {
					return 1;
				} else if (l1 < l2) {
					return -1;
				} else {
					return o1.compareTo(o2);
				}
			});
			sb.append("#").append(tc).append("\n");
			for (String name: names)
				sb.append(name).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}