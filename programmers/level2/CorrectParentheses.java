// https://school.programmers.co.kr/learn/courses/30/lessons/12909?language=java

package programmers.level2;

class CorrectParentheses {
    boolean solution(String s) {
        int n = s.length();
        int counter = 0;

        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                counter++;
            } else {
                counter--;
                if (counter < 0)
                    return false;
            }
        }
        if (counter != 0)
            return false;
        return true;
    }
}