// https://school.programmers.co.kr/learn/courses/30/lessons/12981?language=java

package programmers.level2;

import java.util.*;

class EnglishFollowUp {
    public int[] solution(int n, String[] words) {
        int length = words.length;
        String prev, curr = words[0];
        Set<String> set = new HashSet<>();
        set.add(curr);
        for (int i = 1; i < length; ++i) {
            prev = curr;
            curr = words[i];
            if (set.contains(curr) || prev.charAt(prev.length() - 1) != curr.charAt(0)) {
                return new int[]{i % n + 1, i / n + 1};
            }
            set.add(curr);
        }

        return new int[]{0, 0};
    }
}