// 2930

package swea.difficulty3;

import java.io.*;
import java.util.*;

public class Heap {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		MaxHeap heap = new MaxHeap(100001);
		int N;

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			sb.append("#").append(tc).append(" ");

			N = Integer.parseInt(br.readLine());
			int c, x;
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				c = Integer.parseInt(st.nextToken());
				if (c == 1) {
					x = Integer.parseInt(st.nextToken());
					heap.heapPush(x);
				} else {
					sb.append(heap.heapPop()).append(" ");
				}
			}
			sb.append("\n");
			heap.clear();
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static class MaxHeap {
		int[] heap;
		int heapSize;

		MaxHeap(int capacity) {
			this.heap = new int[capacity];
			this.heapSize = 0;
		}

		void heapPush(int x) {
			heap[++heapSize] = x;
			int i = heapSize;
			while (i > 1 && heap[i >> 1] < heap[i]) {
				swap(i >> 1, i);
				i >>= 1;
			}
		}

		int heapPop() {
			if (heapSize == 0) {
				return -1;
			}
			int item = heap[1];
			heap[1] = heap[heapSize--];
			downHeap(1);
			return item;
		}

		void clear() {
			Arrays.fill(heap, 0);
			heapSize = 0;
		}

		void downHeap(int i) {
			if ((i << 1) > heapSize)
				return;
			if (heap[i] < heap[i << 1] || heap[i] < heap[(i << 1) + 1]) {
				if (heap[i << 1] > heap[(i << 1) + 1]) {
					swap(i, i << 1);
					downHeap(i << 1);
				} else {
					swap(i, (i << 1) + 1);
					downHeap((i << 1) + 1);
				}
			}
		}

		void swap(int i, int j) {
			int temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
		}
	}
}
