// https://school.programmers.co.kr/learn/courses/30/lessons/43163?language=java

package programmers.level3;

import java.util.*;

class WordConverting {
    private boolean isConvertible(String word1, String word2, int n) {
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (word1.charAt(i) != word2.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }

    public int solution(String begin, String target, String[] words) {
        int t = 0;
        int n = begin.length();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(begin);
        queue.add(begin);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int k = 0; k < queueSize; ++k) {
                String now = queue.poll();

                if (now.equals(target))
                    return t;

                for (String next: words) {
                    if (visited.contains(next))
                        continue;
                    if (!isConvertible(now, next, n))
                        continue;
                    queue.add(next);
                    visited.add(next);
                }
            }
            t++;
        }

        return 0;
    }
}