// https://www.acmicpc.net/problem/1253

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Good {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        }

        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (i == j)
                    continue;
                int sum = A[i] + A[j];
                if (A[i] == 0 && A[j] == 0) {
                    if (map.containsKey(0) && map.get(0) > 2) {
                        set.add(sum);
                    }
                } else if (A[i] == 0 || A[j] == 0) {
                    if (map.containsKey(sum) && map.get(sum) > 1) {
                        set.add(sum);
                    }
                } else if (map.containsKey(sum) && map.get(sum) > 0) {
                    set.add(sum);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            if (set.contains(A[i])) {
                ++answer;
            }
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
