// https://school.programmers.co.kr/learn/courses/30/lessons/12987?language=java

package programmers.level3;

import java.util.*;

class NumberGame {
    public int solution(int[] A, int[] B) {
        int n = A.length;
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        Arrays.sort(A);
        Arrays.sort(B);
        for (int num: B)
            stack.push(num);

        for (int i = n - 1; i >= 0; --i) {
            if (A[i] < stack.peek()) {
                answer++;
                stack.pop();
            }
        }

        return answer;
    }
}