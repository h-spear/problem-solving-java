// https://www.acmicpc.net/problem/4358

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Ecology {

    Map<String, Integer> map = new HashMap<>();

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int total = 0;
        while (true) {
            String tree = br.readLine();

            if (tree == null || tree.equals(""))
                break;

            map.put(tree, map.getOrDefault(tree, 0) + 1);
            ++total;
        }

        Set<String> set = map.keySet();
        String[] trees = set.toArray(new String[set.size()]);
        Arrays.sort(trees);

        for (String tree: trees) {
            bw.write(tree + " " + String.format("%.4f", (double) map.get(tree) * 100 / (double) total));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new Ecology().solution();
    }
}
