// https://www.acmicpc.net/problem/7626

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class Rectangle {

    private static final int S = 524288;
    private static long[] tree = new long[S * 2];
    private static int[] counter = new int[S * 2];
    private static Map<Integer, Integer> yMap = new HashMap<>();
    private static int[] yMapReverse;
    private static List<Line> lines = new ArrayList<>(400004);

    class Line implements Comparable<Line> {
        int x, y1, y2, v;


        public Line(int x, int y1, int y2, int v) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.v = v;
        }

        @Override
        public int compareTo(Line o2) {
            return this.x - o2.x;
        }

        @Override
        public String toString() {
            return "Line{" +
                    "x=" + x +
                    ", y1=" + y1 +
                    ", y2=" + y2 +
                    ", v=" + v +
                    '}';
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int x1, x2, y1, y2;

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            lines.add(new Line(x1, y1, y2, 1));
            lines.add(new Line(x2, y1, y2, -1));
            yMap.put(y1, 0);
            yMap.put(y2, 0);
        }

        int idx = 0;
        int[] yKeys = yMap.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        yMapReverse = new int[yKeys.length];

        for (int key: yKeys) {
            yMap.put(key, idx);
            yMapReverse[idx] = key;
            ++idx;
        }

        for (Line line: lines) {
            line.y1 = yMap.get(line.y1);
            line.y2 = yMap.get(line.y2);
        }

        Collections.sort(lines);

        int linesLength = lines.size();
        int prevX = -1;
        long answer = 0;
        for (int i = 0; i < linesLength; ++i) {
            Line p = lines.get(i);

            if (i != 0) {
                long dx = p.x - prevX;
                long yLength = tree[1];
                answer += yLength * dx;
            }
            update(1, 0, S - 1, p.y1, p.y2 - 1, p.v);
            prevX = p.x;
        }
        bw.write(answer + "");

        bw.flush();
        bw.close();
        br.close();
    }

    private void update(int node, int left, int right, int updateLeft, int updateRight, int diff) {
        if (updateRight < left || right < updateLeft) {
            return;
        }
        if (updateLeft <= left && right <= updateRight) {
            counter[node] += diff;
        } else {
            int mid = (left + right) >> 1;
            update(node * 2, left, mid, updateLeft, updateRight, diff);
            update(node * 2 + 1, mid + 1, right, updateLeft, updateRight, diff);
        }

        if (counter[node] > 0) {
            tree[node] = yMapReverse[right + 1] - yMapReverse[left];
        } else {
            if (left == right) {
                tree[node] = 0;
            } else {
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Rectangle().solution();
    }
}
