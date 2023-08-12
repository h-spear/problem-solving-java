// 1230

package swea.difficulty3;

import java.io.*;
import java.util.*;

public class Cryptogram {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = 10;
        int N, M, x, y;
        char c;
        Stack<Integer> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; ++tc) {
            N = Integer.parseInt(br.readLine());
            List<Integer> list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; ++i)
                list.add(Integer.parseInt(st.nextToken()));

            M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; ++i) {
                c = st.nextToken().charAt(0);
                switch (c) {
                    case 'I':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        while (y-- > 0)
                            stack.push(Integer.parseInt(st.nextToken()));
                        while (!stack.isEmpty())
                            list.add(x, stack.pop());
                        break;
                    case 'D':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        while (y-- > 0)
                            list.remove(x);
                        break;
                    case 'A':
                        y = Integer.parseInt(st.nextToken());
                        while (y-- > 0)
                            list.add(Integer.parseInt(st.nextToken()));
                        break;
                }
            }
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < 10; ++i)
                sb.append(list.get(i)).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
