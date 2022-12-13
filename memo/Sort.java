package memo;

import java.util.*;

public class Sort {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("C");
        list.add("B");
        list.add("Z");
        list.add("a");
        list.add("A");

        System.out.println("original = " + list);

        list.sort(Comparator.naturalOrder());   // 오름차순 정렬
        System.out.println("naturalOrder = " + list);

        list.sort(Comparator.reverseOrder());   // 내림차순 정렬
        System.out.println("reverseOrder = " + list);

        list.sort(String.CASE_INSENSITIVE_ORDER);   // 대소문자 구분없이 오름차순 정렬
        System.out.println("caseInsensitiveOrder = " + list);

        list.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER)); // 대소문자 구분없이 내림차순 정렬
        System.out.println("caseInsensitiveReverseOrder = " + list);
    }
}
