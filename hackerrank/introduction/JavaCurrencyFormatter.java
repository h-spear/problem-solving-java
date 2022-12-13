// https://www.hackerrank.com/challenges/java-currency-formatter/

package hackerrank.introduction;

import java.util.*;
import java.text.*;

public class JavaCurrencyFormatter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();

        // Write your code here.
        String us = NumberFormat.getCurrencyInstance(Locale.US).format(payment);

        // 인도의 경우 기본 국가로 설정되어 있지 않기 때문에
        // Locale 클래스를 이용해 임의의 객체를 생성
        // new Locale(String language, String country)
        // en : English, IN : INDIA
        // 참고 : https://heedipro.tistory.com/239
        Locale indiaLocale = new Locale("en", "IN");
        String india = NumberFormat.getCurrencyInstance(indiaLocale).format(payment);

        String china = NumberFormat.getCurrencyInstance(Locale.CHINA).format(payment);
        String france = NumberFormat.getCurrencyInstance(Locale.FRANCE).format(payment);

        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}