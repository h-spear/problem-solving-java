// https://www.acmicpc.net/problem/2606

package baekjoon.graphsearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Virus_LinkedListTail {

    static class Node {
        int data;
        Node next;
    }

    private static Node[] head = new Node[101];
    private static Node[] tail = new Node[101];
    private static Node[] pool = new Node[101 * 101];
    private static int pCnt = 0;

    private static void add(int p, int data) {
        Node node = pool[pCnt++];
        node.data = data;

        if (head[p] == null && tail[p] == null) {
            head[p] = node;
            tail[p] = node;
        } else {
            tail[p].next = node;
            tail[p] = node;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 101 * 101; ++i) {
            pool[i] = new Node();
        }

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int A, B;
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            add(A, B);
            add(B, A);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        queue.add(1);
        while (!queue.isEmpty()) {
            int i = queue.poll();

            for (Node p = head[i]; p != null; p = p.next) {
                if (visited[p.data])
                    continue;
                queue.add(p.data);
                visited[p.data] = true;
            }
        }

        int answer = 0;
        for (int i = 2; i <= N; ++i) {
            if (visited[i])
                ++answer;
        }
        bw.write("" + answer);
        bw.flush();
        bw.close();
        br.close();
    }
}