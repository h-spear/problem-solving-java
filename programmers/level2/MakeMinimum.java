// https://school.programmers.co.kr/learn/courses/30/lessons/12941?language=java

package programmers.level2;

import java.util.*;

class MakeMinimum
{
    public int solution(int []A, int []B)
    {
        int n = A.length;
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < n; ++i) {
            answer += A[i] * B[n - i - 1];
        }

        return answer;
    }
}