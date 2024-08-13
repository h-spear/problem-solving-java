// https://www.acmicpc.net/problem/12850

package baekjoon.divideandconquer;

import java.io.*;

public class UniversityWalking2 {

	private static final int MOD = 1_000_000_007;
	private static final long[][] graph = {
		{0, 1, 1, 0, 0, 0, 0, 0},
		{1, 0, 1, 1, 0, 0, 0, 0},
		{1, 1, 0, 1, 1, 0, 0, 0},
		{0, 1, 1, 0, 1, 1, 0, 0},
		{0, 0, 1, 1, 0, 1, 0, 1},
		{0, 0, 0, 1, 1, 0, 1, 0},
		{0, 0, 0, 0, 0, 1, 0, 1},
		{0, 0, 0, 0, 1, 0, 1, 0}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long D = Long.parseLong(br.readLine());
		System.out.println(matrixPower(graph, D)[0][0]);
		br.close();
	}

	private static long[][] matrixPower(long[][] a, long b) {
		if (b == 1)
			return a;

		long[][] temp = matrixPower(a, b >> 1);
		if ((b & 1) == 0) {
			return matrixMultiplication(temp, temp);
		} else {
			return matrixMultiplication(matrixMultiplication(temp, temp), a);
		}
	}

	private static long[][] matrixMultiplication(long[][] a, long[][] b) {
		long[][] multiplicated = new long[8][8];
		for (int i = 0; i < 8; ++i) {
			for (int j = 0; j < 8; ++j) {
				for (int k = 0; k < 8; ++k) {
					multiplicated[i][j] += (a[i][k] * b[k][j]) % MOD;
					multiplicated[i][j] %= MOD;
				}
			}
		}
		return multiplicated;
	}
}