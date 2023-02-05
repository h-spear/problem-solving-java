// https://www.acmicpc.net/problem/11003

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class FindMinValue {

    static class Pair {
        int timestamp;
        int value;

        public Pair(int timestamp, int value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        Deque<Pair> deque = new ArrayDeque<>(L + 1);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            Pair A = new Pair(i, Integer.parseInt(st.nextToken()));
            while (!deque.isEmpty() && deque.peekLast().value >= A.value) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && deque.peekFirst().timestamp <= i - L) {
                deque.removeFirst();
            }
            deque.addLast(A);
            bw.write(deque.peekFirst().value + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
