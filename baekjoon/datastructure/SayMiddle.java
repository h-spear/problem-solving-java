// https://www.acmicpc.net/problem/1655

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class SayMiddle {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N, num;
        PriorityQueue<Integer> leftHeap = new PriorityQueue<>(5010, (o1, o2) -> o2 - o1);
        PriorityQueue<Integer> rightHeap = new PriorityQueue<>(5010);

        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; ++i) {
            num = Integer.parseInt(br.readLine());
            while (leftHeap.size() - rightHeap.size() > 1) {
                // 사이즈를 같게 만들어준다.
            }

            if (leftHeap.size() == rightHeap.size()) {
                if (leftHeap.isEmpty()) {
                    leftHeap.add(num);
                } else if (rightHeap.peek() > num) {
                    leftHeap.add(num);
                } else {
                    leftHeap.add(rightHeap.poll());
                    rightHeap.add(num);
                }
            } else {
                if (leftHeap.peek() < num) {
                    rightHeap.add(num);
                } else {
                    rightHeap.add(leftHeap.poll());
                    leftHeap.add(num);
                }
            }

            bw.write("" + leftHeap.peek());
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new SayMiddle().solution();
    }
}
