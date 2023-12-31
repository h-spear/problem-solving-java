// https://www.acmicpc.net/problem/10845

package baekjoon.datastructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Queue10845_CircularQueue {

    private static final int N = 10001;
    private static int[] queue = new int[10001];
    private static int front = 0, rear = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String command;
        int x;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            command = st.nextToken();
            switch (command) {
                case "push":
                    x = Integer.parseInt(st.nextToken());
                    push(x);
                    break;
                case "front":
                    bw.write(front() + "\n");
                    break;
                case "back":
                    bw.write(rear() + "\n");
                    break;
                case "size":
                    bw.write(size() + "\n");
                    break;
                case "empty":
                    bw.write((isEmpty() ? 1 : 0) + "\n");
                    break;
                case "pop":
                    bw.write(pop() + "\n");
                    break;
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void push(int x) {
        queue[rear] = x;
        rear = (rear + 1) % N;
    }

    private static int pop() {
        if (isEmpty())
            return -1;
        int item = queue[front];
        front = (front + 1) % N;
        return item;
    }

    private static int size() {
        return (rear + N - front) % N;
    }

    private static boolean isEmpty() {
        return front == rear;
    }

    private static int front() {
        return isEmpty() ? -1 : queue[front];
    }

    private static int rear() {
        return isEmpty() ? -1 : queue[rear - 1];
    }
}
