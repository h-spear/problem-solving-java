// https://www.acmicpc.net/problem/14003
// dp + binary search

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class LongestIncreasingSubsequence5 {

    static class Pair {
        int value, length;

        public Pair(int value, int length) {
            this.value = value;
            this.length = length;
        }

        @Override
        public String toString() {
            return "{" +
                    "" + value +
                    ", " + length +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        int[] length = new int[N];
        int[] queue = new int[N];
        int qIdx = 0;
        List<Pair> temp = new ArrayList<>(N);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // lis
        int maxLength = 0;
        for (int i = 0; i < N; ++i) {
            if (qIdx == 0 || queue[qIdx - 1] < arr[i]) {
                queue[qIdx++] = arr[i];
                temp.add(new Pair(arr[i], qIdx));
                length[i] = qIdx;
            } else {
                int idx = lowerBound(queue, 0, qIdx, arr[i]);
                queue[idx] = arr[i];
                temp.add(new Pair(arr[i], idx + 1));
                length[i] = idx + 1;
            }
            maxLength = Math.max(maxLength, length[i]);
        }

        // tracing
        int[] lis = new int[maxLength];

        int cur = maxLength;
        for (int i = temp.size() - 1; i >= 0; --i) {
            if (cur == temp.get(i).length) {
                lis[--cur] = temp.get(i).value;
            }
        }

        bw.write(maxLength + "\n");
        for (int i = 0; i < maxLength; ++i) {
            bw.write(lis[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int lowerBound(int[] queue, int left, int right, int x) {
        int res = right + 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (queue[mid] >= x) {
                right = mid - 1;
                res = mid;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
