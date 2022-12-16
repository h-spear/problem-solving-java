// https://school.programmers.co.kr/learn/courses/30/lessons/67258?language=java

package programmers.level3;

import java.util.*;

class JewelShopping {
    private static Map<String, Integer> counter = new HashMap<>();

    private void increment(String key) {
        counter.put(key, counter.getOrDefault(key, 0) + 1);
    }

    private void decrement(String key) {
        counter.put(key, counter.getOrDefault(key, 1) - 1);
        if (counter.get(key) == 0)
            counter.remove(key);
    }

    public int[] solution(String[] gems) {
        Set<String> set = new HashSet<>();
        for (String gem: gems) {
            set.add(gem);
        }
        int gemsLength = gems.length;
        int targetSize = set.size();
        int i = 0;
        int j = 1;
        int length = 123456;
        int[] answer = {1, gemsLength};

        increment(gems[0]);
        while (j < gemsLength) {
            if (counter.size() < targetSize) {
                increment(gems[j]);
                j++;
            } else {
                if (j - i < length) {
                    length = j - i;
                    answer[0] = i + 1;
                    answer[1] = j;
                }
                decrement(gems[i]);
                i++;
            }
        }

        while (i < j) {
            if (counter.size() < targetSize)
                break;
            if (j - i < length) {
                length = j - i;
                answer[0] = i + 1;
                answer[1] = j;
            }
            decrement(gems[i]);
            i++;
        }

        return answer;
    }
}