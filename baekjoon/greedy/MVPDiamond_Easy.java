// https://www.acmicpc.net/problem/20413

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class MVPDiamond_Easy {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] money = new int[26];
		money['B' - 'A'] = Integer.parseInt(st.nextToken()) - 1;
		money['S' - 'A'] = Integer.parseInt(st.nextToken()) - 1;
		money['G' - 'A'] = Integer.parseInt(st.nextToken()) - 1;
		money['D' - 'A'] = Integer.parseInt(st.nextToken());
		money['P' - 'A'] = money['D' - 'A'] - 1;

		char[] level = br.readLine().toCharArray();
		int answer = 0;
		int prev = 0;
		for (int i = 0; i < N; ++i) {
			if (level[i] == 'D') {
				answer += money['D' - 'A'] * (N - i);
			} else {
				int pay = money[level[i] - 'A'] - prev;
				answer += pay;
				prev = pay;
			}
		}
		System.out.println(answer);
	    br.close();
	}
}
