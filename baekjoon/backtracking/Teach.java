// https://www.acmicpc.net/problem/1062

package baekjoon.backtracking;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teach {

    private static int N, K;
    private static boolean[] visited;
    private static String[] words;
    private static int answer;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        words = new String[N];

        for (int i = 0; i < N; ++i) {
            words[i] = sc.next();
        }

        visited = new boolean[26];

        for (char c: "antic".toCharArray()) {
            visited[c - 'a'] = true;
        }

        if (K == 5) {
            answer = countValidateWords();
        } else {
            for (int i = 0; i < 26; ++i) {
                if (!visited[i]) {
                    visited[i] = true;
                    dfs(i, 1);
                    visited[i] = false;
                }
            }
        }
        System.out.println(answer);
    }

    private static void dfs(int idx, int selected) {
        // 2. 목적지인가
        if (selected == K - 5) {
            answer = Math.max(answer, countValidateWords());
            return;
        }

        // 3. 연결된 곳을 순회
        for (int i = idx + 1; i < 26; ++i) {
            // 4. 갈 수 있는가?
            if (visited[i]) {
                continue;
            }

            // 1. 체크인
            visited[i] = true;
            // 5. 간다
            dfs(i, selected + 1);
            // 6. 체크아웃
            visited[i] = false;
        }

    }

    private static int countValidateWords() {
        int count = 0;
        for (String word: words) {
            boolean flag = true;

            for (char c: word.toCharArray()) {
                if (!visited[c - 'a']) {
                    flag = false;
                    break;
                }
            }

            if (flag)
                ++count;
        }
        return count;
    }
}
