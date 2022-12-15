// https://www.acmicpc.net/problem/7575
// List의 원소를 비교할 때는 equals!!!!

package baekjoon.kmp;

import java.io.*;
import java.util.*;

public class Virus {

    private int[] failure(List<Integer> pattern) {
        int n = pattern.size();
        int[] table = new int[n];

        int j = 0;
        for (int i = 1; i < n; ++i) {
            while (j > 0 && !pattern.get(i).equals(pattern.get(j)))
                j = table[j - 1];

            if (pattern.get(i).equals(pattern.get(j))) {
                j++;
                table[i] = j;
            }
        }
        return table;
    }

    private boolean kmp(List<Integer> program, List<Integer> pattern) {
        int n = program.size();
        int m = pattern.size();
        int[] table = failure(pattern);

        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && !program.get(i).equals(pattern.get(j)))
                j = table[j - 1];

            if (program.get(i).equals(pattern.get(j))) {
                j++;
                if (j == m)
                    return true;
            }
        }
        return false;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Integer>> programs = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<Integer> program = new ArrayList<>();
            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; ++j) {
                program.add(Integer.parseInt(st.nextToken()));
            }
            programs.add(program);
        }
        br.close();

        boolean flag;
        List<Integer> firstProgram = programs.get(0);
        int firstProgramSize = firstProgram.size();
        for (int i = 0; i < firstProgramSize - k + 1; ++i) {
            List<Integer> pattern = new ArrayList<>(firstProgram.subList(i, i + k));
            List<Integer> reversePattern = new ArrayList<>();
            for (int j = 0; j < k; ++j)
                reversePattern.add(pattern.get(k - j - 1));

            flag = true;
            for (List<Integer> program: programs) {
                flag = kmp(program, pattern) || kmp(program, reversePattern);
                if (!flag)
                    break;
            }
            if (flag) {
                System.out.println("YES");
                return;
            }
        }
        System.out.println("NO");
    }

    public static void main(String[] args) throws Exception {
        new Virus().solution();
    }
}
