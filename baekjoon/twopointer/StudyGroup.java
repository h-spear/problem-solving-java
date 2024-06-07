// https://www.acmicpc.net/problem/14572

package baekjoon.twopointer;

import java.io.*;
import java.util.*;

public class StudyGroup {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        Student[] students = new Student[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] algorithm = new int[M];
            for (int j = 0; j < M; ++j) {
                algorithm[j] = Integer.parseInt(st.nextToken());
            }
            students[i] = new Student(d, algorithm);
        }
        Arrays.sort(students, (o1, o2) -> {
            if (o1.skill != o2.skill)
                return Integer.compare(o1.skill, o2.skill);
            return Integer.compare(o1.algorithm.length, o2.algorithm.length);
        });

        int answer = 0;
        int left = 0;
        int[] counter = new int[K + 1];
        for (int right = 0; right < N; ++right) {
            for (int x: students[right].algorithm) {
                ++counter[x];
            }

            while ((students[right].skill - students[left].skill) > D) {
                for (int x: students[left].algorithm) {
                    --counter[x];
                }
                ++left;
            }
            int members = right - left + 1;
            int allAlgorithm = count(counter, 1);
            int allKnows = count(counter, members);
            int efficiency = (allAlgorithm - allKnows) * members;
            answer = Math.max(answer, efficiency);
        }
        System.out.println(answer);
        br.close();
    }

    private static int count(int[] array, int value) {
        int count = 0;
        for (int x: array) {
            if (x >= value)
                ++count;
        }
        return count;
    }

    private static class Student {
        int skill;
        int[] algorithm;

        Student(int skill, int[] algorithm) {
            this.skill = skill;
            this.algorithm = algorithm;
        }
    }
}
