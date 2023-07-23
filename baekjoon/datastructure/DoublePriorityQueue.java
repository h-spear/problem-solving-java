// https://www.acmicpc.net/problem/7662

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class DoublePriorityQueue {

    static class Element {
        int data;
        boolean flag;

        public Element(int data) {
            this.data = data;
        }
    }

    // comparator overflow.
    private static PriorityQueue<Element> minHeap = new PriorityQueue<>((o1, o2) -> o1.data > o2.data ? 1 : -1);
    private static PriorityQueue<Element> maxHeap = new PriorityQueue<>((o1, o2) -> o1.data < o2.data ? 1 : -1);

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int k, n;
        char c;
        while (T-- > 0) {
            initialize();
            k = Integer.parseInt(br.readLine());
            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                c = st.nextToken().charAt(0);
                n = Integer.parseInt(st.nextToken());
                if (c == 'I') {
                    push(n);
                } else {
                    if (n > 0) {
                        popMax();
                    } else {
                        popMin();
                    }
                }
            }

            Element minElement = popMin();
            Element maxElement = popMax();
            maxElement = maxElement == null ? minElement : maxElement;
            if (minElement == null) {
                bw.write("EMPTY");
            } else {
                bw.write(maxElement.data + " " + minElement.data);
            }
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void initialize() {
        minHeap.clear();
        maxHeap.clear();
    }

    private static void push(int x) {
        Element elem = new Element(x);
        minHeap.add(elem);
        maxHeap.add(elem);
    }

    private static Element popMin() {
        while (!minHeap.isEmpty()) {
            Element elem = minHeap.poll();
            if (!elem.flag) {
                elem.flag = true;
                return elem;
            }
        }
        return null;
    }

    private static Element popMax() {
        while (!maxHeap.isEmpty()) {
            Element elem = maxHeap.poll();
            if (!elem.flag) {
                elem.flag = true;
                return elem;
            }
        }
        return null;
    }
}