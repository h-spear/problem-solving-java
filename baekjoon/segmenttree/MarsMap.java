// https://www.acmicpc.net/problem/3392
// sweeping
// tree에는 0보다 큰 값들의 개수

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class MarsMap {

    private static final int Y = 30002;
    private static final int S = 65536;
    private static int[] tree = new int[S * 2];
    private static int[] counter = new int[S * 2];

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
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<Line> lines = new ArrayList<>();
        int x1, y1, x2, y2;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            lines.add(new Line(x1, y1, y2 - 1, 1));
            lines.add(new Line(x2, y1, y2 - 1, -1));
        }

        Collections.sort(lines);

        Line firstLine = lines.get(0);

        int yLength, prevX = firstLine.x;
        update(1, 0, S - 1, firstLine.y1, firstLine.y2, 1);

        Line line;
        long dx, answer = 0;
        for (int i = 1; i < lines.size(); ++i) {
            line = lines.get(i);
            dx = line.x - prevX;
            yLength = tree[1];  // query(1, 0, S - 1, 0, 30002);

            answer += yLength * dx;

            update(1, 0, S - 1, line.y1, line.y2, line.v);
            prevX = line.x;
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
            tree[node] = right - left + 1;
        } else {
            if (left == right) {
                tree[node] = 0;
            } else {
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new MarsMap().solution();
    }
}
