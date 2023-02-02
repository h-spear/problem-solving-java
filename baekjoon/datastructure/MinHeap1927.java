package baekjoon.datastructure;

import java.io.*;

public class MinHeap1927 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MinHeap minHeap = new MinHeap();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; ++i) {
            int x = Integer.parseInt(br.readLine());
            if (x > 0) {
                minHeap.add(x);
            } else {
                if (minHeap.isEmpty()) {
                    System.out.println(0);
                    continue;
                }
                int key = minHeap.remove();
                System.out.println(key);
            }
        }

        br.close();
    }

    static class MinHeap {
        int[] heap = new int[100001];
        int heapSize = 0;

        void add(int key) {
            this.heap[++this.heapSize] = key;
            upHeap(this.heapSize);
        }

        int remove() throws Exception {
            if (isEmpty()) {
                throw new Exception("underflow");
            }
            int key = this.heap[1];
            this.heap[1] = this.heap[this.heapSize--];
            downHeap(1);
            return key;
        }

        boolean isEmpty() {
            return this.heapSize == 0;
        }

        private void upHeap(int i) {
            if (i > 1 && heap[parent(i)] > heap[i]) {
                swap(i, parent(i));
                upHeap(parent(i));
            }
        }

        private void downHeap(int i) {
            if (left(i) > this.heapSize) {
                return;
            }

            if (this.heap[i] > this.heap[left(i)] || this.heap[i] > this.heap[right(i)]) {
                if (this.heap[left(i)] < this.heap[right(i)]) {
                    swap(i, left(i));
                    downHeap(left(i));
                } else {
                    swap(i, right(i));
                    downHeap(right(i));
                }
            }
        }

        private void swap(int index1, int index2) {
            int temp = this.heap[index2];
            this.heap[index2] = this.heap[index1];
            this.heap[index1] = temp;
        }

        private int parent(int i) {
            return i / 2;
        }

        private int left(int i) {
            return 2 * i;
        }

        private int right(int i) {
            return 2 * i + 1;
        }
    }
}