// https://www.acmicpc.net/problem/2110

package baekjoon.binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WifiRouterInstallation {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] x = new int[N];
		for (int i = 0; i < N; ++i) {
			x[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(x);
		int left = 0;
		int right = 1_000_000_000;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) >> 1;
			if (simul(x, mid) >= C) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(left - 1);
		br.close();
	}

	private static int simul(int[] x, int k) {
		int res = 1;
		int prev = x[0];
		for (int curr: x) {
			if (curr < prev + k)
				continue;
			++res;
			prev = curr;
		}
		return res;
	}
}
