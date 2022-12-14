package programmers.level2;

class FibonacciNumber {
    public int solution(int n) {
        int p = 1234567;
        int answer = 0;
        int one = 0;
        int two = 1;

        for (int i = 0; i < n - 1; ++i) {
            answer = (one + two) % p;
            one = two;
            two = answer;
        }

        return answer;
    }
}