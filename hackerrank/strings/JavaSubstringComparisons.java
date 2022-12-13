// https://www.hackerrank.com/challenges/java-string-compare/

package hackerrank.strings;

import java.util.Scanner;

public class JavaSubstringComparisons {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";

        // Complete the function
        // 'smallest' must be the lexicographically smallest substring of length 'k'
        // 'largest' must be the lexicographically largest substring of length 'k'
        int count = s.length() - k + 1;

        for (int i = 0; i < count; i++) {
            String sub = s.substring(i, i + k);

            if (i == 0) {
                smallest = sub;
                largest = sub;
                continue;
            }

            if (sub.compareTo(smallest) < 0)
                smallest = sub;
            if (sub.compareTo(largest) > 0)
                largest = sub;
        }

        return smallest + "\n" + largest;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int k = scan.nextInt();
        scan.close();

        System.out.println(getSmallestAndLargest(s, k));
    }
}