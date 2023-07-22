// https://www.acmicpc.net/problem/10828

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Stack10828 {

    private static int[] stack = new int[10001];
    private static int top = -1;

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
                case "top":
                    bw.write(top() + "\n");
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
        stack[++top] = x;
    }

    private static int pop() {
        return isEmpty() ? -1 : stack[top--];
    }

    private static int size() {
        return top + 1;
    }

    private static boolean isEmpty() {
        return top == -1;
    }

    private static int top() {
        return isEmpty() ? -1 : stack[top];
    }
}