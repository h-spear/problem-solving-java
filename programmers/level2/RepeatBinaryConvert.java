// https://school.programmers.co.kr/learn/courses/30/lessons/70129?language=java

package programmers.level2;

class RepeatBinaryConvert {

    private int getCount(String string, char c) {
        int count = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == c)
                count++;
        }
        return count;
    }

    public int[] solution(String s) {
        int[] answer = {0, 0};
        int n, count;

        while (!s.equals("1")) {
            n = s.length();
            count = getCount(s, '1');
            answer[0]++;
            answer[1] += (n - count);
            s = Integer.toBinaryString(count);
        }
        return answer;
    }
}