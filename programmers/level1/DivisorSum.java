// https://school.programmers.co.kr/learn/courses/30/lessons/12928?language=java

package programmers.level1;

class DivisorSum {

    private int getDivisorSum(int n) {
        int sum = 0;
        for (int i = 1; i < (int)Math.sqrt(n) + 1; ++i) {
            if (n % i == 0) {
                sum += i;
                if (n / i != i)
                    sum += (n / i);
            }
        }
        return sum;
    }

    public int solution(int n) {
        return getDivisorSum(n);
    }
}