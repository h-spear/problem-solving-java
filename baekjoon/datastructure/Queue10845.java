// https://www.acmicpc.net/problem/10845

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Queue10845 {

    class Queue {
        static final int MAX_SIZE = 10001;

        int[] queue = new int[MAX_SIZE];
        int front = 0;
        int rear = 0;

        public Queue() {
        }

        void push(int X) {
            queue[rear] = X;
            rear = (rear + 1) % MAX_SIZE;
        }

        int pop() {
            if (isEmpty()) {
                return -1;
            }
            int item = queue[front];
            front = (front + 1) % MAX_SIZE;
            return item;
        }

        int size() {
            return rear - front;
        }

        boolean isEmpty() {
            return front == rear;
        }

        int front() {
            if (isEmpty()) {
                return -1;
            }
            return queue[front];
        }

        int back() {
            if (isEmpty()) {
                return -1;
            }
            return queue[(rear - 1 + MAX_SIZE) % MAX_SIZE];
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Queue queue = new Queue();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    queue.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    bw.write("" + queue.pop());
                    bw.newLine();
                    break;
                case "front":
                    bw.write("" + queue.front());
                    bw.newLine();
                    break;
                case "back":
                    bw.write("" + queue.back());
                    bw.newLine();
                    break;
                case "size":
                    bw.write("" + queue.size());
                    bw.newLine();
                    break;
                case "empty":
                    bw.write("" + (queue.isEmpty() ? 1 : 0));
                    bw.newLine();
                    break;
                default:
                    throw new Exception("error");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Queue10845().solution();
    }
}
