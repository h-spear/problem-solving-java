// https://www.acmicpc.net/problem/3077

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class ImjinWar {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		Map<String, Set<String>> answer = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			String name = st.nextToken();
			Set<String> set = new HashSet<>(answer.keySet());
			answer.put(name, set);
		}

		String[] hyeonwoo = new String[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			hyeonwoo[i] = st.nextToken();
		}

		int a = 0;
		int b = (N * (N - 1)) >> 1;
		for (int i = 0; i < N; ++i) {
			String one = hyeonwoo[i];
			for (int j = i + 1; j < N; ++j) {
				String two = hyeonwoo[j];
				if (answer.get(two).contains(one))
					++a;
			}
		}
		System.out.println(a + "/" + b);
	    br.close();
	}
}
