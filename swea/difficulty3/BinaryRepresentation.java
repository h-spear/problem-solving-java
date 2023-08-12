// 10726

package swea.difficulty3;

import java.io.*;
import java.util.*;

public class BinaryRepresentation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int N, M;
        int bm;

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            bm = (1 << N) - 1;
            sb.append("#").append(tc).append(" ");
            if ((M & bm) == bm) {
                sb.append("ON\n");
            } else {
                sb.append("OFF\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
