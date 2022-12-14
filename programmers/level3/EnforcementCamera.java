// https://school.programmers.co.kr/learn/courses/30/lessons/42884?language=java

package programmers.level3;

import java.util.*;

class EnforcementCamera {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));

        int answer = 0;
        int start, end, last = Integer.MIN_VALUE;
        for (int[] route: routes) {
            start = route[0];
            end = route[1];

            if (start <= last) {
                continue;
            }
            last = end;
            answer += 1;
        }
        return answer;
    }
}