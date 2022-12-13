// https://www.hackerrank.com/challenges/java-loops/problem/

package hackerrank.introduction;

import java.util.*;
import java.io.*;

public class JavaLoops2 {
    public static int pow(int a, int x) {
        if (x == 0) {
            return 1;
        } else if (x == 1) {
            return a;
        }

        int temp = pow(a, x / 2);
        if (x % 2 == 1) {
            return temp * temp * a;
        } else {
            return temp * temp;
        }
    }

    public static void main(String []argh){
        Scanner in = new Scanner(System.in);
        int t=in.nextInt();

        for(int i=0;i<t;i++){
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();

            int s = a;
            for (int j=0;j<n;j++) {
                s += pow(2, j) * b;
                System.out.printf("%d ", s);
            }
            System.out.println();
        }
        in.close();
    }
}