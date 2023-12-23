// https://www.acmicpc.net/problem/2565

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class ElectricWire {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            pairs[i] = new Pair(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        int[] arr = Arrays.stream(pairs)
                .sorted((o1, o2) -> Integer.compare(o1.first, o2.first))
                .map(pair -> pair.second)
                .mapToInt(Integer::intValue)
                .toArray();

        int[] queue = new int[N];
        int qIdx = 0;
        for (int x: arr) {
            if (qIdx == 0 || queue[qIdx - 1] < x) {
                queue[qIdx++] = x;
            } else {
                int idx = lowerBound(queue, 0, qIdx - 1, x);
                queue[idx] = x;
            }
        }
        System.out.println(N - qIdx);
        br.close();
    }

    private static int lowerBound(int[] arr, int left, int right, int k) {
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (arr[mid] >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }

    private static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
