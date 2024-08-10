// https://www.acmicpc.net/problem/1016

package baekjoon.math;

import java.io.*;
import java.util.*;

public class SquareFreeNumber {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());

		boolean[] isSquareNumber = new boolean[1000010];

		long sqrt = (long) Math.sqrt(max) + 1;
		for (long i = 2; i < sqrt; ++i) {
			long pow = i * i;
			// long j = (long) (((double) min / pow) + 1) * pow;  // WA
			long j = (long) Math.ceil((double) min / pow) * pow;
			while (j <= max) {
				isSquareNumber[(int) (j - min)] = true;
				j += pow;
			}
		}

		int answer = 0;
		for (int i = 0, diff = (int) (max - min); i <= diff; ++i) {
			if (!isSquareNumber[i])
				++answer;
		}
		System.out.println(answer);
	    br.close();
	}
}
