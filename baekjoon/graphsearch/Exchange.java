// https://www.acmicpc.net/problem/1039

package baekjoon.graphsearch;

import java.io.*;
import java.util.*;

public class Exchange {
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        String N = st.nextToken();
        int M = N.length();
        int K = Integer.parseInt(st.nextToken());

        Queue<String> queue = new LinkedList<>();
        queue.add(N);

        while (!queue.isEmpty() && K > 0) {
            int queueSize = queue.size();
            Set<String> set = new HashSet<>();
            for (int qi = 0; qi < queueSize; ++qi) {
                String curr = queue.poll();

                for (int i = 0; i < M; ++i) {
                    for (int j = i + 1; j < M; ++j) {
                        String next = swap(curr, i, j);
                        if (next.charAt(0) == '0')
                            continue;
                        if (set.contains(next))  // 중복 체크
                            continue;
                        queue.add(next);
                        set.add(next);
                    }
                }
            }
            --K;
        }

        int answer = queue.stream().mapToInt(x -> Integer.parseInt(x)).max().orElse(-1);
        System.out.println(answer);

        br.close();
    }

    private String swap(String numStr, int i, int j) {
        return numStr.substring(0, i) + numStr.charAt(j) +
                numStr.substring(i + 1, j) + numStr.charAt(i) + numStr.substring(j + 1);
    }

    public static void main(String[] args) throws Exception {
        new Exchange().solution();
    }
}
