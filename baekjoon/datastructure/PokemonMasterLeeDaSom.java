// https://www.acmicpc.net/problem/1620

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class PokemonMasterLeeDaSom {

    private static String[] pokemon;
    private static Map<String, Integer> map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        pokemon = new String[N + 1];
        map = new HashMap<>();

        for (int i = 1; i <= N; ++i) {
            pokemon[i] = br.readLine();
            map.put(pokemon[i], i);
        }

        int num;
        for (int i = 0; i < M; ++i) {
            String input = br.readLine();
            try {
                num = Integer.parseInt(input);
                bw.write(pokemon[num]);
                bw.newLine();
            } catch (NumberFormatException e) {
                bw.write(String.valueOf(map.get(input)));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
