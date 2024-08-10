// https://www.acmicpc.net/problem/17269

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class NameCompatibilityTest {

	private static final int[] stroke = {3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();

		int[] strokeAB = new int[N + M];
		int i = 0, index = 0;
		for (int end = Math.min(N, M); i < end; ++i) {
			strokeAB[index++] = stroke[A.charAt(i) - 'A'];
			strokeAB[index++] = stroke[B.charAt(i) - 'A'];
		}
		while (i < N)
			strokeAB[index++] = stroke[A.charAt(i++) - 'A'];
		while (i < M)
			strokeAB[index++] = stroke[B.charAt(i++) - 'A'];

		int length = N + M;
		while (length > 2) {
			for (i = 0; i < length - 1; ++i) {
				strokeAB[i] = (strokeAB[i] + strokeAB[i + 1]) % 10;
			}
			--length;
		}

		System.out.println((strokeAB[0] * 10 + strokeAB[1]) + "%");
	    br.close();
	}
}
