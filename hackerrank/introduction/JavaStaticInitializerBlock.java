// https://www.hackerrank.com/challenges/java-static-initializer-block/problem/

package hackerrank.introduction;

import java.util.*;

public class JavaStaticInitializerBlock {

    static boolean flag = true;
    static int B, H;

    static {
        Scanner sc = new Scanner(System.in);
        B = sc.nextInt();
        H = sc.nextInt();

        if (B <= 0 || H <= 0) {
            flag = false;
            System.out.println("java.lang.Exception: Breadth and height must be positive");
        }
    }

    public static void main(String[] args){
        if(flag){
            int area=B*H;
            System.out.print(area);
        }
    }//end of main
}//end of class

