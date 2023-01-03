// https://www.acmicpc.net/problem/1966

package baekjoon.implementation;

import java.io.*;
import java.util.*;

public class PrinterQueue {

    private boolean isLargestPriority(Queue<Integer> queue, int prio) {
        for (int priority: queue) {
            if (priority > prio)
                return false;
        }
        return true;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; ++tc) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Queue<Integer> indicesQueue = new LinkedList<>();
            Queue<Integer> docPrioQueue = new LinkedList<>();

            for (int i = 0; i < n; ++i) {
                indicesQueue.add(i);
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                docPrioQueue.add(Integer.parseInt(st.nextToken()));
            }

            int answer = 0;
            while (!docPrioQueue.isEmpty()) {
                int prio = docPrioQueue.poll();
                int idx = indicesQueue.poll();

                if (isLargestPriority(docPrioQueue, prio)) {
                    ++answer;
                    if (idx == m)
                        break;
                } else {
                    docPrioQueue.add(prio);
                    indicesQueue.add(idx);
                }
            }
            bw.write("" + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new PrinterQueue().solution();
    }
}
