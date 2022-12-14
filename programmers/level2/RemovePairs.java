// https://school.programmers.co.kr/learn/courses/30/lessons/12973?language=java

package programmers.level2;

import java.util.*;

class RemovePairs
{
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        return stack.size() == 0 ? 1 : 0;
    }
}