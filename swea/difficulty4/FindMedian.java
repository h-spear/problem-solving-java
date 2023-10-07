// 3000

package swea.difficulty4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class FindMedian {

	private static final int MOD = 20171109;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Queue<Integer> minHeap = new PriorityQueue<>();
		Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			minHeap.clear();
			maxHeap.clear();

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int X, Y;
			int answer = 0;
			while (N-- > 0) {
				st = new StringTokenizer(br.readLine());
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				if (X > Y) {
					int temp = X;
					X = Y;
					Y = temp;
				}

				if (A < X) {
					maxHeap.add(A);
					maxHeap.add(X);
					maxHeap.add(Y);
					minHeap.add(maxHeap.poll());
					minHeap.add(maxHeap.poll());
					A = minHeap.poll();
				} else if (A > Y) {
					minHeap.add(A);
					minHeap.add(X);
					minHeap.add(Y);
					maxHeap.add(minHeap.poll());
					maxHeap.add(minHeap.poll());
					A = maxHeap.poll();
				} else {
					maxHeap.add(X);
					minHeap.add(Y);
				}
				answer = (answer + A) % MOD;
			}
			sb.append("#").append(tc).append(" ")
				.append(answer).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
