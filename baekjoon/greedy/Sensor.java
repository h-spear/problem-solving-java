// https://www.acmicpc.net/problem/2212

package baekjoon.greedy;

import java.io.*;
import java.util.*;

public class Sensor {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		int[] x = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			x[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(x);
		int[] diff = new int[N - 1];
		for (int i = 0; i < N - 1; ++i) {
			diff[i] = x[i + 1] - x[i];
		}

		Arrays.sort(diff);
		System.out.println(getSum(diff, 0, N - K));
	    br.close();
	}

	private static int getSum(int[] array, int fromIndex, int toIndex) {
		int sum = 0;
		for (int i = fromIndex; i < toIndex; ++i) {
			sum += array[i];
		}
		return sum;
	}
}
