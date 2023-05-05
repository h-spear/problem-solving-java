// https://school.programmers.co.kr/learn/courses/30/lessons/150369?language=java#

package programmers.level2;

import java.util.*;

public class DeliveryAndPickup {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Stack<Pair> dStack = new Stack<>();
        Stack<Pair> pStack = new Stack<>();

        for (int i = 0; i < deliveries.length; ++i) {
            if (deliveries[i] > 0) {
                dStack.push(new Pair(i, deliveries[i]));
            }
            if (pickups[i] > 0) {
                pStack.push(new Pair(i, pickups[i]));
            }
        }

        int r1, r2;
        long answer = 0;

        while (true) {
            r1 = getFarthest(dStack, cap);
            r2 = getFarthest(pStack, cap);
            if (r1 <= 0 && r2 <= 0) {
                break;
            }
            answer += Math.max(r1, r2) * 2;
        }
        return answer;
    }

    private int getFarthest(Stack<Pair> stack, int cap) {
        int res = 0;
        while (cap > 0 && stack.size() > 0) {
            Pair p = stack.pop();
            res = Math.max(res, p.index + 1);
            if (cap >= p.count) {
                cap -= p.count;
            } else {
                stack.push(new Pair(p.index, p.count - cap));
                cap = 0;
            }
        }
        return res;
    }

    class Pair {
        int index;
        int count;

        Pair(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }
}
