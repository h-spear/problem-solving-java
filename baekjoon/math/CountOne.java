// https://www.acmicpc.net/problem/9527

package baekjoon.math;

import java.io.*;
import java.util.*;

public class CountOne {

	private static final long[] d = new long[55];

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		d[0] = 1;
		for (int i = 1; i < 55; ++i) {
			d[i] = 2 * d[i - 1] + (1L << i);
		}

		st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		System.out.println(f(B) - f(A - 1));
	    br.close();
	}

	private static long f(long x) {
		long res = x & 1;
		for (int i = 54; i > 0; --i) {
			long s = 1L << i;
			if ((x & s) > 0) {
				res += d[i - 1] + (x - s + 1);
				x -= s;
			}
		}
		return res;
	}
}
