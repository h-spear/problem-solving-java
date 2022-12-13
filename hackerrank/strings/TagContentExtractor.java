// https://www.hackerrank.com/challenges/tag-content-extractor/

package hackerrank.strings;

import java.util.*;
import java.util.regex.*;

public class TagContentExtractor {
    public static void main(String[] args){

        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        String regex = "<(.+)>([^<]+)</\\1>";
        Pattern r = Pattern.compile(regex);
        Matcher m;
        while(testCases-- > 0){
            String line = in.nextLine();

            //Write your code here
            boolean flag = false;
            m = r.matcher(line);

            while (m.find()) {
                System.out.println(m.group(2));
                flag = true;
            }
            if (!flag)
                System.out.println("None");
        }
    }
}

/*
- Matcher 클래스 메서드
    find() : 패턴이 일치하는 경우 true를 반환하고, 그 위치로 이동(여러개가 매칭되는 경우 반복 실행가능)
    find(int start) : start위치 이후부터 매칭검색을 수행
    start() : 매칭되는 문자열 시작위치 반환
    start(int group) : 지정된 그룹이 매칭되는 시작위치 반환
    end() : 매칭되는  문자열 끝 다음 문자위치 반환
    end(int group) : 지정되 그룹이 매칭되는 끝 다음 문자위치 반환
    group() : 매칭된 부분을 반환
    group(int group) : 매칭된 부분중 group번 그룹핑 매칭부분 반환
    groupCount() : 패턴내 그룹핑한(괄호지정) 전체 갯수 반환
    matches() : 패턴이 전체 문자열과 일치할 경우 true 반환

    출처 : https://devfalledinmac.tistory.com/15
*/
