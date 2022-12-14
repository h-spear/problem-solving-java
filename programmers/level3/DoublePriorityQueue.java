// https://school.programmers.co.kr/learn/courses/30/lessons/42628?language=java

package programmers.level3;

import java.util.*;

class DoublePriorityQueue {
    public int[] solution(String[] operations) {
        char cmd;
        int num, count, item = 0;
        int[] answer = {Integer.MIN_VALUE, Integer.MAX_VALUE};
        Map<Integer, Integer> counter = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (String operation: operations) {
            cmd = operation.charAt(0);
            num = Integer.parseInt(operation.substring(2));

            if (cmd == 'I') {
                counter.put(num, counter.getOrDefault(num, 0) + 1);
                minHeap.add(num);
                maxHeap.add(num);
            } else {
                if (num == 1) {
                    while (!counter.isEmpty() && !counter.containsKey(maxHeap.peek())) {
                        maxHeap.remove();
                    }
                    if (counter.isEmpty())
                        continue;
                    item = maxHeap.remove();
                    count = counter.get(item);
                    if (count == 1)
                        counter.remove(item);
                    else
                        counter.put(item, count - 1);
                } else if (num == -1) {
                    while (!counter.isEmpty() && !counter.containsKey(minHeap.peek())) {
                        minHeap.remove();
                    }
                    if (counter.isEmpty())
                        continue;
                    item = minHeap.remove();
                    count = counter.get(item);
                    if (count == 1)
                        counter.remove(item);
                }
            }
        }

        if (counter.isEmpty())
            return new int[]{0, 0};

        counter.keySet().iterator().forEachRemaining(v -> {
            answer[0] = answer[0] > v ? answer[0] : v;
            answer[1] = answer[1] < v ? answer[1] : v;
        });
        return answer;
    }
}