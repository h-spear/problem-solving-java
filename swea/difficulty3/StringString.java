// 17319

package swea.difficulty3;

import java.io.*;

public class StringString {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();

            bw.write("#" + tc + " ");
            bw.write(isPossible(N, str) ? "Yes" : "No");
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isPossible(int N, String string) {
        if ((N & 1) == 1) {
            return false;
        }
        int mid = N / 2;
        for (int i = 0; i < mid; ++i) {
            if (string.charAt(i) != string.charAt(mid + i)) {
                return false;
            }
        }
        return true;
    }
}
