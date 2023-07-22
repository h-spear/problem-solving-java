// https://www.acmicpc.net/problem/1927

package baekjoon.datastructure;

import java.io.*;

public class MinHeap1927 {

    private static int[] heap = new int[100001];
    private static int heapSize = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int x;
        while (N-- > 0) {
            x = Integer.parseInt(br.readLine());
            if (x == 0)
                bw.write(pop() + "\n");
            else
                push(x);
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void push(int x) {
        heap[++heapSize] = x;
        upHeap(heapSize);
    }

    private static int pop() {
        if (heapSize == 0)
            return 0;

        int item = heap[1];
        heap[1] = heap[heapSize--];
        downHeap(1);
        return item;
    }

    private static void upHeap(int i) {
        while (i > 1 && heap[i >> 1] > heap[i]) {
            swap(i, i >> 1);
            i >>= 1;
        }
    }

    private static void downHeap(int i) {
        if ((i << 1) > heapSize)
            return;
        if (heap[i] > heap[i << 1] ||heap[i] > heap[(i << 1) + 1]) {
            if (heap[i << 1] < heap[(i << 1) + 1]) {
                swap(i, i << 1);
                downHeap(i << 1);
            } else {
                swap(i, (i << 1) + 1);
                downHeap((i << 1) + 1);
            }
        }
    }

    private static void swap(int index1, int index2) {
        int temp = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = temp;
    }
}
