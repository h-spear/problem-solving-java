// https://www.acmicpc.net/problem/3020

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Firefly {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, H;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        List<Integer> topObject = new ArrayList<>();
        List<Integer> bottomObject = new ArrayList<>();

        for (int i = 0; i < N / 2; ++i) {
            bottomObject.add(Integer.parseInt(br.readLine()));
            topObject.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(bottomObject, Comparator.reverseOrder());
        Collections.sort(topObject, Comparator.reverseOrder());

        // 누적합
        int[] topCollisions = new int[H + 1];
        int[] bottomCollisions = new int[H + 1];

        int idx = 0;
        for (int height = H; height > 0; --height) {
            while (idx < bottomObject.size() && bottomObject.get(idx) >= height) {
                idx++;
            }
            bottomCollisions[height] = idx;
        }

        idx = 0;
        for (int height = 1; height <= H; ++height) {
            while (idx < topObject.size() && H - topObject.get(idx) < height) {
                idx++;
            }
            topCollisions[height] = idx;
        }

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int height = 1; height <= H; ++height) {
            int collision = topCollisions[height] + bottomCollisions[height];
            if (collision < min) {
                min = collision;
                count = 1;
            } else if (collision == min) {
                count++;
            }
        }
        System.out.println(min + " " + count);
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Firefly().solution();
    }
}
