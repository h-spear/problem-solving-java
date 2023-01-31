// https://www.acmicpc.net/problem/1759

package baekjoon.backtracking;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CreatePassword {

    private static int L, C;
    private static char[] input;
    private static char[] vowels = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("src/day01/p1759/input.txt"));

        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        input = new char[C];
        for (int i = 0; i < C; ++i) {
            input[i] = sc.next().charAt(0);
        }

        Arrays.sort(input);
        for (int i = 0; i <= C - L; ++i) {
            dfs(i, "" + input[i]);
        }
    }

    static void dfs(int idx, String word) {
        // 1. 체크인

        // 2. 목적지인가
        if (word.length() == L) {
            if (isPossibleWord(word)) {
                System.out.println(word);
            }
        } else {
            // 3. 연결된 곳 순회
            for (int i = idx + 1; i < C; ++i) {
                // 4. 갈 수 있는가? -> X
                // 5. 간다
                dfs(i, word + input[i]);
            }
        }

        // 6. 체크아웃
    }

    private static boolean isPossibleWord(String word) {
        int length = word.length();
        int vowelCount = 0;
        int consonantCount;
        for (char c: word.toCharArray()) {
            for (char vowel: vowels) {
                if (c == vowel) {
                    ++vowelCount;
                    break;
                }
            }
        }
        consonantCount = length - vowelCount;

        if (vowelCount >= 1 && consonantCount >= 2) {
            return true;
        }
        return false;
    }
}
