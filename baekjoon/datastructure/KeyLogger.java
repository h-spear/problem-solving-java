// https://www.acmicpc.net/problem/5397

package baekjoon.datastructure;

import java.io.*;

public class KeyLogger {

    private static class ListNode {
        char c;
        ListNode prev;
        ListNode next;

        public ListNode() {
        }

        public ListNode(char c) {
            this.c = c;
        }
    }

    private static ListNode head;
    private static ListNode tail;
    private static ListNode cursor;

    private static void initialize() {
        cursor = head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;
    }

    private static void cursorLeft() {
        if (cursor != head) {
            cursor = cursor.prev;
        }
    }

    private static void cursorRight() {
        if (cursor != tail.prev) {
            cursor = cursor.next;
        }
    }

    private static ListNode delete(ListNode removeNode) {
        if (removeNode == head || removeNode == tail)
            return removeNode;
        removeNode.prev.next = removeNode.next;
        removeNode.next.prev = removeNode.prev;
        return removeNode.prev;
    }

    private static ListNode insert(ListNode node, char c) {
        ListNode newNode = new ListNode(c);
        newNode.prev = node;
        newNode.next = node.next;
        node.next.prev = newNode;
        node.next = newNode;
        return newNode;
    }

    private static String getString() {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head.next; p != tail; p = p.next) {
            sb.append(p.c);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            initialize();
            String s = br.readLine();
            for (char c: s.toCharArray()) {
                switch (c) {
                    case '<':
                        cursorLeft(); break;
                    case '>':
                        cursorRight(); break;
                    case '-':
                        cursor = delete(cursor); break;
                    default:
                        cursor = insert(cursor, c); break;
                }
            }
            bw.write(getString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
