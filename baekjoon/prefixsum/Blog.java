// https://www.acmicpc.net/problem/21921

package baekjoon.prefixsum;

import java.io.*;
import java.util.*;

public class Blog {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int[] visitor = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			visitor[i] = Integer.parseInt(st.nextToken());
		}

		int[] result = new int[2];
		int summation = 0;
		for (int i = 0; i < X; ++i) {
			summation += visitor[i];
		}
		result[0] = summation;
		result[1] = 1;

		for (int left = 0, right = X; right < N; ++left, ++right) {
			summation += visitor[right];
			summation -= visitor[left];
			if (summation > result[0]) {
				result[0] = summation;
				result[1] = 1;
			} else if (summation == result[0]) {
				++result[1];
			}
		}

		if (result[0] == 0)
			System.out.println("SAD");
		else
			System.out.println(result[0] + "\n" + result[1]);
	    br.close();
	}
}
