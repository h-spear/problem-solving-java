// https://school.programmers.co.kr/learn/courses/30/lessons/12944?language=java

package programmers.level1;

class Average {
    public double solution(int[] arr) {
        double answer = 0;
        for (int num: arr) {
            answer += num;
        }
        return answer / arr.length;
    }
}