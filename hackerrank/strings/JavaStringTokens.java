// https://www.hackerrank.com/challenges/java-string-tokens/

package hackerrank.strings;

import java.util.*;

public class JavaStringTokens {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        scan.close();
        // Write your code here.

        String[] tokens = s.split("[ !,?._'@]+");

        int count = 0;
        for (String token: tokens) {
            if (token.length() >= 1)
                count++;
        }

        System.out.println(count);
        for (String token: tokens) {
            if (token.length() >= 1)
                System.out.println(token);
        }
    }
}

