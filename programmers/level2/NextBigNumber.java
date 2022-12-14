// https://school.programmers.co.kr/learn/courses/30/lessons/12911?language=java

package programmers.level2;

class NextBigNumber {
    private int getCount(String string, char c) {
        int count = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == c)
                count++;
        }
        return count;
    }

    public int solution(int n) {
        int answer = 0;
        int i = n + 1;
        int count = getCount(Integer.toBinaryString(n), '1');
        while (true) {
            if (getCount(Integer.toBinaryString(i), '1') == count) {
                return i;
            }
            i++;
        }
    }
}