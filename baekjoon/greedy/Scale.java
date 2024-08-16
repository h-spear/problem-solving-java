// https://www.acmicpc.net/problem/2437

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Scale {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] w = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			w[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(w);
		int target = 1;
		for (int weight: w) {
			if (target < weight)
				break;

			target += weight;
		}
		System.out.println(target);
		br.close();
	}
}
