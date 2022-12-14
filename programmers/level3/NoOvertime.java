// https://school.programmers.co.kr/learn/courses/30/lessons/12927?language=java

package programmers.level3;

import java.util.*;

class NoOvertime {
    public long solution(int n, int[] works) {
        long answer = 0;
        int item;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int work: works)
            maxHeap.add(work);

        for (int i = 0; i < n; ++i) {
            if (maxHeap.isEmpty())
                return 0;

            item = maxHeap.remove();
            if (item - 1 > 0)
                maxHeap.add(item - 1);
        }

        while (!maxHeap.isEmpty()) {
            answer += Math.pow(maxHeap.remove(), 2);
        }
        return answer;
    }
}