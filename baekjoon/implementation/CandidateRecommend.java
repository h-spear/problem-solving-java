// https://www.acmicpc.net/problem/1713

package baekjoon.implementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CandidateRecommend {

    static class Student {
        int count;
        int timestamp;
        boolean isIn;

        public Student(int count, int timestamp, boolean isIn) {
            this.count = count;
            this.timestamp = timestamp;
            this.isIn = isIn;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        if (K == 0)
            return;

        int[] recommended = new int[K];
        Student[] students = new Student[101];



        for (int i = 0; i < K; ++i) {
            recommended[i] = sc.nextInt();
        }

        List<Integer> frames = new ArrayList<>();

        for (int i = 0; i < K; ++i) {
            int num = recommended[i];
            if (students[num] == null) {
                students[num] = new Student(0, 0, false);
            }

            // 이미 액자가 존재
            if (students[num].isIn) {
                students[num].count++;
            } else {
                if (frames.size() >= N) {
                    Collections.sort(frames, (o1, o2) -> {
                        Student s1 = students[o1];
                        Student s2 = students[o2];
                        int comp1 = s2.count - s1.count;
                        if (comp1 == 0) {
                            return s2.timestamp - s1.timestamp;
                        } else {
                            return comp1;
                        }
                    });

                    // 기존 후보 제거
                    Integer removed = frames.remove(N - 1);
                    students[removed].count = 0;
                    students[removed].timestamp = 0;
                    students[removed].isIn = false;
                }
                // 새로운 후보
                frames.add(num);
                students[num].count = 1;
                students[num].timestamp = i;
                students[num].isIn = true;
            }
        }

        Collections.sort(frames);
        for (int i = 0; i < frames.size(); ++i) {
            System.out.printf("%d ", frames.get(i));
        }
    }
}
