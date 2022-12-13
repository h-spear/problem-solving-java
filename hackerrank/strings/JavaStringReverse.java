// https://www.hackerrank.com/challenges/java-string-reverse/

package hackerrank.strings;

import java.util.*;

public class JavaStringReverse {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */

        int aLen = A.length();
        boolean flag = true;

        for (int i = 0; i < aLen / 2; ++i) {
            if (A.charAt(i) != A.charAt(aLen - i - 1)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}



