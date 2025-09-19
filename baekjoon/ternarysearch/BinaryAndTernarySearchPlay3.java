// https://www.acmicpc.net/problem/19601

package baekjoon.ternarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryAndTernarySearchPlay3 {

    private static int getMaxDepthByBinarySearch(long size) {
        if (size == 1) return 0;
        return getMaxDepthByBinarySearch(size / 2) + 1;
    }

    private static int getMaxDepthByTernarySearch(long size) {
        if (size == 1) return 0;
        else if (size == 2) return 1;
        return getMaxDepthByTernarySearch(size / 3) + 2;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            long N = Long.parseLong(br.readLine());
            sb.append(getMaxDepthByBinarySearch(N)).append(" ")
                    .append(getMaxDepthByTernarySearch(N)).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}