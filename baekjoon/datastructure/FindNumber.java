// https://www.acmicpc.net/problem/1920

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class FindNumber {

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        HashSet<Integer> set = new HashSet<>();
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            int num = Integer.parseInt(st.nextToken());
            if (set.contains(num)) {
                bw.write("1");
            } else {
                bw.write("0");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new FindNumber().solution();
    }
}
