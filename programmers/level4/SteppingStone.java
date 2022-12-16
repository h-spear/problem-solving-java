// https://school.programmers.co.kr/learn/courses/30/lessons/43236

package programmers.level4;

import java.util.*;

class SteppingStone {

    private int func(int[] rocks, int t) {
        int removed = 0;
        int prev = 0;
        for (int rock: rocks) {
            if (rock - prev < t) {
                removed++;
            } else {
                prev = rock;
            }
        }
        return removed;
    }

    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        int left = 0;
        int right = distance;
        int mid, answer = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (func(rocks, mid) <= n) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }
}