// https://school.programmers.co.kr/learn/courses/30/lessons/12978?language=java

package programmers.level2;

import java.util.*;

public class Delivery {
    private static final int INF = 1234567890;

    public int solution(int N, int[][] roads, int K) {
        List<List<Pair>> graph = new ArrayList<>();

        for (int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        int a, b, c, cost;
        for (int[] road: roads) {
            a = road[0];
            b = road[1];
            c = road[2];
            graph.get(a).add(new Pair(b, c));
            graph.get(b).add(new Pair(a, c));
        }

        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;
        PriorityQueue<Pair> heap = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        heap.add(new Pair(1, 0));

        while (heap.size() > 0) {
            Pair currNode = heap.poll();

            if (distance[currNode.idx] < currNode.distance) {
                continue;
            }
            for (Pair nextNode: graph.get(currNode.idx)) {
                cost = distance[currNode.idx] + nextNode.distance;
                if (distance[nextNode.idx] > cost) {
                    distance[nextNode.idx] = cost;
                    heap.add(new Pair(nextNode.idx, cost));
                }
            }
        }

        int answer = 0;
        for (int dist: distance) {
            if (dist <= K) {
                ++answer;
            }
        }
        return answer;
    }

    class Pair {
        int idx, distance;

        Pair(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }
    }
}
