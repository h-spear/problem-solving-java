// https://www.acmicpc.net/problem/15961

package baekjoon.twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotatingSushi {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] counter = new int[3001];
        int[] arr = new int[N + k];
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < k; ++i) {
            arr[N + i] = arr[i];
        }

        int answer = 0;
        int cnt = 0;
        int i = 0, j;
        for (j = 0; j < k; ++j) {
            if (++counter[arr[j]] == 1)
                ++cnt;
        }
        if (counter[c] == 0)
            answer = cnt + 1;
        else
            answer = cnt;

        while (j < N + k) {
            if (++counter[arr[j++]] == 1)
                ++cnt;
            if (--counter[arr[i++]] == 0)
                --cnt;
            if (counter[c] == 0)
                answer = Math.max(answer, cnt + 1);
            else
                answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
        br.close();
    }

}
