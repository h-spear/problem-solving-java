// https://www.hackerrank.com/challenges/java-stdin-stdout/problem/
/**
 *  scan.nextInt(), scan.nextDouble() 동작 후 scan.nextLine() 진행 시
 *  개행문자 '\n'이 남아있어 다음 본문을 제대로 인식하지 못한다.
 *  
 *  scan.nextInt(), scan.nextDouble()으로 입력받은 후, String을 받기 전에 scan.nextLine();으로 개행문자를 제거
 */

package hackerrank.introduction;
import java.util.Scanner;

public class JavaStdinAndStdout2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        double d = scan.nextDouble();
        scan.nextLine();
        String s = scan.nextLine();

        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
