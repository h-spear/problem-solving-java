// https://www.acmicpc.net/problem/2559

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class Sequence {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		int left = 0;
		int right = 0;

		while (right < K)
			sum += arr[right++];

		int answer = sum;
		while (right < N) {
			sum += arr[right++];
			sum -= arr[left++];
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
		br.close();
	}
}
