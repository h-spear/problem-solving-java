// https://school.programmers.co.kr/learn/courses/30/lessons/12937?language=java

package programmers.level1;

class EvenAndOdd {
    public String solution(int num) {
        if ((num & 1) == 1)
            return "Odd";
        return "Even";
    }
}