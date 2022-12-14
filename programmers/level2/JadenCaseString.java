// https://school.programmers.co.kr/learn/courses/30/lessons/12951?language=java

package programmers.level2;

class JadenCaseString {
    public String solution(String s) {

        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (i != 0 && charArray[i-1] != ' ') {
                charArray[i] = Character.toLowerCase(charArray[i]);
            } else if (charArray[i] != ' ') {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }
        }

        return new String(charArray);
    }
}