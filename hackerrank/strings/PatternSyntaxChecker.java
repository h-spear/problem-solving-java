// https://www.hackerrank.com/challenges/pattern-syntax-checker/

package hackerrank.strings;

import java.util.Scanner;
import java.util.regex.*;

public class PatternSyntaxChecker
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());
        while(testCases>0){
            String pattern = in.nextLine();
            //Write your code
            boolean flag = true;
            try {
                Pattern.compile(pattern);
            } catch(PatternSyntaxException e) {
                flag = false;
            }

            if (flag)
                System.out.println("Valid");
            else
                System.out.println("Invalid");

            testCases--;
        }
    }
}



