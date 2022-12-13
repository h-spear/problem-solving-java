// https://www.hackerrank.com/challenges/java-anagrams/

package hackerrank.strings;

import java.util.Scanner;

public class JavaAnagrams {

    static boolean isAnagram(String a, String b) {
        // Complete the function
        int aLen = a.length();
        int bLen = b.length();

        if (aLen != bLen)
            return false;

        a = a.toUpperCase();
        b = b.toUpperCase();

        int[] counter = new int[26];
        for (int i = 0; i < aLen; ++i) {
            int idx = a.charAt(i) - 'A';
            counter[idx]++;
        }

        for (int i = 0; i < bLen; ++i) {
            int idx = b.charAt(i) - 'A';
            counter[idx]--;
        }

        for (int i = 0; i < 26; ++i) {
            if (counter[i] != 0)
                return false;
        }
        return true;

    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String a = scan.next();
        String b = scan.next();
        scan.close();
        boolean ret = isAnagram(a, b);
        System.out.println( (ret) ? "Anagrams" : "Not Anagrams" );
    }
}