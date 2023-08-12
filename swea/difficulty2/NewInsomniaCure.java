// 1288

package swea.difficulty2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NewInsomniaCure {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int N, visit;
        int fullVisit = (1 << 10) - 1;
        long curr;

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            curr = 0;
            visit = 0;

            while (visit != fullVisit) {
                curr += N;
                for (char c: String.valueOf(curr).toCharArray()) {
                    visit |= (1 << (c - '0'));
                }
            }
            sb.append("#").append(tc).append(" ")
                            .append(curr).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
