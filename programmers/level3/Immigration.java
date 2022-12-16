// https://school.programmers.co.kr/learn/courses/30/lessons/43238?language=java#
// 오버플로우 조심
// right 값을 잘 설정

package programmers.level3;

import java.util.*;

class Immigration {
    private long func(long t, int[] times) {
        long capacity = 0;
        for (int time: times) {
            capacity += t / time;
        }
        return capacity;
    }

    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long left = 0;
        long right = (long)times[times.length-1] * n;
        long mid, capacity, answer = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            capacity = func(mid, times);
            if (capacity >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}