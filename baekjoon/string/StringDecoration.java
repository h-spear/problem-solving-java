// https://www.acmicpc.net/problem/1294

package baekjoon.string;

import java.io.*;
import java.util.*;

public class StringDecoration {

    private static final char dummy = 'Z' + 1;

    private static class MyString implements Comparable<MyString> {
        StringBuilder sb;

        public MyString(String string) {
            this.sb = new StringBuilder(string);
            this.sb.append(dummy);
        }

        @Override
        public int compareTo(MyString o) {
            return sb.compareTo(o.sb);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<MyString> pq = new PriorityQueue<>();
        for (int i = 0; i < N; ++i) {
            pq.add(new MyString(br.readLine()));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            MyString myString = pq.poll();
            sb.append(myString.sb.charAt(0));
            if (myString.sb.length() > 1) {
                myString.sb.deleteCharAt(0);
                pq.add(myString);
            }
        }
        sb.setLength(sb.length() - N);
        System.out.println(sb.toString());
        br.close();
    }
}
