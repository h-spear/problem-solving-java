// https://school.programmers.co.kr/learn/courses/30/lessons/64064?language=java

package programmers.level3;

import java.util.*;

class BanUser {
    private static String[] userId;
    private static String[] bannedId;
    private static int userCount;
    private static int banCount;
    private static Map<String, Integer> counter = new HashMap<>();
    private static Integer[] path;
    private static Set<String> set = new HashSet<>();

    private static void increment(String key) {
        counter.put(key, counter.getOrDefault(key, 0) + 1);
    }

    private static void decrement(String key) {
        counter.put(key, counter.getOrDefault(key, 1) - 1);
        if (counter.get(key) == 0)
            counter.remove(key);
    }

    private static boolean isEmpty() {
        return counter.size() == 0;
    }

    private boolean match(String id, String ban) {
        if (id.length() != ban.length())
            return false;
        for (int i = 0; i < id.length(); ++i) {
            if (ban.charAt(i) == '*')
                continue;
            if (id.charAt(i) != ban.charAt(i))
                return false;
        }
        return true;
    }

    private void dfs(int idx, int depth) {
        if (depth == banCount) {
            Arrays.sort(path);
            set.add(Arrays.toString(path));
            return;
        }

        for (int i = idx; i < userCount; ++i) {
            String uid = userId[i];
            String[] keys = counter.keySet().toArray(new String[counter.size()]);
            for (String bid: keys) {
                if (!match(uid, bid))
                    continue;
                decrement(bid);
                path[depth] = i;
                dfs(i + 1, depth + 1);
                increment(bid);
            }
        }
    }

    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;
        userCount = user_id.length;
        banCount = banned_id.length;
        path = new Integer[banCount];
        for (String bid: banned_id)
            increment(bid);
        dfs(0, 0);
        return set.size();
    }
}