// https://www.acmicpc.net/problem/10986

package baekjoon.prefixsum;

import java.io.*;
import java.util.*;

public class RemainderSum {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] counter = new int[M + 1];

		++counter[0];
		long answer = 0;

		st = new StringTokenizer(br.readLine());
		int curr, prev = 0;
		for (int i = 0; i < N; ++i) {
			curr = (prev + Integer.parseInt(st.nextToken())) % M;
			answer += counter[curr];
			++counter[curr];
			prev = curr;
		}
		System.out.println(answer);
	    br.close();
	}
}
