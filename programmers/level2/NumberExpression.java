// https://school.programmers.co.kr/learn/courses/30/lessons/12924?language=java

package programmers.level2;

class NumberExpression {
    // Solution 1
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; ++i) {
            int temp = i;
            int j = i + 1;
            while (temp < n) {
                temp += j;
                j++;
            }
            if (temp == n)
                answer += 1;
        }
        return answer;
    }

    // Solution 2
    // 주어진 자연수를 연속된 자연수의 합으로 표현하는 방법의 수는 주어진 수의 홀수 약수의 개수와 같다.
    private int getOddDivisor(int n) {
        int count = 0;
        for (int i = 1; i < (int)Math.sqrt(n) + 1; ++i) {
            if (n % i != 0)
                continue;
            if ((i & 1) == 1)
                count++;
            if (i != (n / i) && (n / i) % 2 == 1)
                count++;
        }
        return count;
    }

    public int solution2(int n) {
        return getOddDivisor(n);
    }
}