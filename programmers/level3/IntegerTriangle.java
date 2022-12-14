// https://school.programmers.co.kr/learn/courses/30/lessons/43105

package programmers.level3;

import java.util.Arrays;

class IntegerTriangle {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;

        for (int i = 1; i < n; ++i) {
            triangle[i][0] += triangle[i-1][0];
            triangle[i][i] += triangle[i-1][i-1];
            for (int j = 1; j < i; ++j) {
                triangle[i][j] += Math.max(triangle[i-1][j], triangle[i-1][j-1]);
            }
        }

        for (int j = 0; j < n; ++j) {
            answer = Math.max(answer, triangle[n-1][j]);
        }
        return answer;
        // return Arrays.stream(triangle[n - 1]).max().getAsInt();
    }
}