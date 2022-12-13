// https://www.hackerrank.com/challenges/java-end-of-file/problem/
package hackerrank.introduction;

import java.util.*;

public class JavaEndOfFile {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int i = 0;
        while(sc.hasNext()) {
            System.out.println(++i + " " + sc.nextLine());
        }

    }
}