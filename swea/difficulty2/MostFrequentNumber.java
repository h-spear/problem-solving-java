// 1204

package swea.difficulty2;

import java.io.*;
import java.util.*;

public class MostFrequentNumber {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int[] counter = new int[101];
        int tc, answer, frequency;

        while (T-- > 0) {
            Arrays.fill(counter, 0);
            tc = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 1000; ++i) {
                ++counter[Integer.parseInt(st.nextToken())];
            }

            answer = 0;
            frequency = 0;
            for (int i = 100; i >= 0; --i) {
                if (counter[i] > frequency) {
                    answer = i;
                    frequency = counter[i];
                }
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}