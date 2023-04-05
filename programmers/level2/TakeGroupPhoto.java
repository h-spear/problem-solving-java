// https://school.programmers.co.kr/learn/courses/30/lessons/1835?language=java

package programmers.level2;

import java.util.*;

class TakeGroupPhoto {
    private static final char[] kakao = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static final int fullVisit = (1 << 8) - 1;
    private static Map<Character, Integer> map = new HashMap<>();
    private static Pair[] pairs;
    private static int answer;

    class Pair {
        char c1, c2;
        int distance;
        int compare;

        public Pair(char c1, char c2, int distance, int compare) {
            this.c1 = c1;
            this.c2 = c2;
            this.distance = distance;
            this.compare = compare;
        }
    }

    private boolean isSatisfied() {
        int dist;

        for (Pair p: pairs) {
            dist = Math.abs(map.get(p.c1) - map.get(p.c2)) - 1;
            if (p.compare == 1 && p.distance >= dist) { // '>' 인데 거리가 작거나 같음.
                return false;
            } else if (p.compare == -1 && p.distance <= dist) {
                return false;
            } else if (p.compare == 0 && p.distance != dist) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int visit, int depth) {
        if (visit == fullVisit) {
            if (isSatisfied()) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; ++i) {
            if ((visit & (1 << i)) != 0) {    // 방문한 적 있음
                continue;
            }
            visit |= (1 << i);
            map.put(kakao[i], depth);
            dfs(visit, depth + 1);
            visit &= ~(1 << i);
        }
    }

    public int solution(int n, String[] data) {
        answer = 0;
        pairs = new Pair[n];

        int idx = 0;
        for (String str: data) {
            int compare = str.charAt(3) == '>' ? 1 : (str.charAt(3) == '<' ? -1 : 0);
            pairs[idx++] = new Pair(str.charAt(0), str.charAt(2), str.charAt(4) - '0', compare);
        }

        dfs(0, 0);

        return answer;
    }
}