package baekjoon.implementation;// https://www.acmicpc.net/problem/31797

import java.io.*;
import java.util.*;

public class Apartment {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Hand[] hands = new Hand[M];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            hands[i] = new Hand(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }
        compression(hands);

        int[] order = new int[M << 1];
        for (int i = 0; i < M; ++i) {
            order[hands[i].left] = i + 1;
            order[hands[i].right] = i + 1;
        }
        System.out.println(order[(N - 1) % (M << 1)]);
        br.close();
    }

    private static void compression(Hand[] hands) {
        Map<Integer, Integer> cMap = new HashMap<>();
        int[] keys = new int[hands.length << 1];

        int idx = 0;
        for (Hand hand: hands) {
            keys[idx++] = hand.left;
            keys[idx++] = hand.right;
        }

        Arrays.sort(keys);
        idx = 0;
        for (int key: keys) {
            cMap.put(key, idx++);
        }
        for (Hand hand: hands) {
            hand.left = cMap.get(hand.left);
            hand.right = cMap.get(hand.right);
        }
    }

    private static class Hand {
        int left, right;

        Hand(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
