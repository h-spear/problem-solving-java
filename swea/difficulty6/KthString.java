// 1257

package swea.difficulty6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KthString {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for (int C = 1; C <= T; ++C) {
            int K = Integer.parseInt(br.readLine());
            String str = br.readLine();
            bw.write("#" + C + " " + getKthSubstring(str, K));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static String getKthSubstring(String string, int K) {
        Set<String> S = new HashSet<>();
        int length = string.length();
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length - i; ++j) {
                S.add(string.substring(j, j + i + 1));
            }
        }
        ArrayList<String> list = new ArrayList<>(S);
        Collections.sort(list);
        return K >= list.size() ? "none" : list.get(K - 1);
    }
}
