// https://www.acmicpc.net/problem/10828

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Stack10828 {

    class Stack {
        int top = -1;
        int[] stack = new int[10001];

        public Stack() {
        }

        void push(int X) {
            stack[++top] = X;
        }

        int pop() {
            if (isEmpty()) {
                return -1;
            }
            return stack[top--];
        }

        int size() {
            return top + 1;
        }

        boolean isEmpty() {
            return top == -1;
        }

        int top() {
            if (isEmpty()) {
                return -1;
            }
            return stack[top];
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Stack stack = new Stack();

        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    stack.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    bw.write("" + stack.pop());
                    bw.newLine();
                    break;
                case "top":
                    bw.write("" + stack.top());
                    bw.newLine();
                    break;
                case "size":
                    bw.write("" + stack.size());
                    bw.newLine();
                    break;
                case "empty":
                    bw.write("" + (stack.isEmpty() ? 1 : 0));
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
        new Stack10828().solution();
    }
}
