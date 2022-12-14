// https://school.programmers.co.kr/learn/courses/30/lessons/42579?language=java

package programmers.level3;

import java.util.*;
import java.util.stream.*;

class BestAlbum {
    class Pair implements Comparable<Pair> {
        int idx;
        int play;

        public Pair(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        @Override
        public int compareTo(Pair target) {
            return this.play <= target.play ? 1 : -1;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        Map<String, Integer> playCounter = new HashMap<>();
        Map<String, List<Pair>> genreMap = new HashMap<>();
        List<Integer> result = new LinkedList<>();

        for (int i = 0; i < n; ++i) {
            String genre = genres[i];
            int play = plays[i];

            playCounter.put(genre, playCounter.getOrDefault(genre, 0) + play);
            if (!genreMap.containsKey(genre))
                genreMap.put(genre, new LinkedList<>());
            genreMap.get(genre).add(new Pair(i, play));
        }

        List<Map.Entry<String, Integer>> collect = playCounter.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toList());

        for (Map.Entry<String, Integer> entry : collect) {
            List<Pair> values = genreMap.get(entry.getKey());
            values.sort(Comparator.naturalOrder());

            int i = 0;
            for (Pair pair : values) {
                result.add(pair.idx);
                i++;
                if (i == 2)
                    break;
            }
        }

        int[] resultArray = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            resultArray[i] = result.get(i);
        }
        return resultArray;
    }
}