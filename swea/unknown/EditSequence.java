package swea.unknown;

import java.io.*;
import java.util.*;

public class EditSequence {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        List<Integer> list = new ArrayList<>(2023);
        int T = Integer.parseInt(br.readLine());

        int N, M, L;
        int idx, num;
        char c;

        for (int tc = 1; tc <= T; ++tc) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            list.clear();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            for (int i = 0; i < M; ++i) {
                st = new StringTokenizer(br.readLine());
                c = st.nextToken().charAt(0);
                idx = Integer.parseInt(st.nextToken());
                switch (c) {
                    case 'I':
                        num = Integer.parseInt(st.nextToken());
                        list.add(idx, num);
                        break;
                    case 'D':
                        list.remove(idx);
                        break;
                    case 'C':
                        num = Integer.parseInt(st.nextToken());
                        list.set(idx, num);
                        break;
                }
            }
            sb.append("#").append(tc).append(" ")
                    .append(list.size() >= L ? list.get(L) : -1).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
}
