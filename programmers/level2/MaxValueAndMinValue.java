// https://school.programmers.co.kr/learn/courses/30/lessons/12939?language=java

package programmers.level2;

import java.util.*;

class MaxValueAndMinValue {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int maximum = -Integer.MAX_VALUE;
        int minimum = Integer.MAX_VALUE;
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            maximum = maximum > num ? maximum : num;
            minimum = minimum < num ? minimum : num;
        }

        return Integer.toString(minimum) + " " + Integer.toString(maximum);
    }
}