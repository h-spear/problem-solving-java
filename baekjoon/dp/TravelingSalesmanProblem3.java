// https://www.acmicpc.net/problem/16991

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class TravelingSalesmanProblem3 {

	private static final double INF = Integer.MAX_VALUE >> 2;
	private static int N;
	private static int fullVisit;
	private static double[][] W;
	private static double[][] dp;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		fullVisit = (1 << N) - 1;
		int[][] cities = new int[N][2];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			cities[i][0] = Integer.parseInt(st.nextToken());
			cities[i][1] = Integer.parseInt(st.nextToken());
		}
		W = preprocessingCoordinate(cities, N);
		dp = new double[N][1 << N];
		System.out.println(dfs(0, 1));
	    br.close();
	}

	private static double[][] preprocessingCoordinate(int[][] cities, int n) {
		double[][] distance = new double[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				double dist = getDistance(cities[i][0], cities[i][1], cities[j][0], cities[j][1]);
				distance[i][j] = dist;
				distance[j][i] = dist;
			}
		}
		return distance;
	}

	private static double getDistance(int xa, int ya, int xb, int yb) {
		return Math.sqrt(Math.pow(xb - xa, 2) + Math.pow(yb - ya, 2));
	}

	private static double dfs(int x, int visit) {
		if (dp[x][visit] > 0)
			return dp[x][visit];
		if (visit == fullVisit) {
			return W[x][0] > 0 ? W[x][0] : INF;
		}
		double temp = INF;
		for (int i = 1; i < N; ++i) {
			if ((visit & (1 << i)) > 0)
				continue;
			if (W[x][i] == 0)
				continue;
			temp = Math.min(temp, dfs(i, visit | (1 << i)) + W[x][i]);
		}
		return dp[x][visit] = temp;
	}
}
