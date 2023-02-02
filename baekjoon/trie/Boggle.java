// https://www.acmicpc.net/problem/9202

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class Boggle {

    private static Trie trie;
    private static char[][] graph;
    private static boolean[][] visited;
    private static final int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
    private static final int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
    private static final int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    // answer
    private static int maxScore;
    private static String longestWord;
    private static int findCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int w, b;
        trie = new Trie();

        w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; ++i) {
            trie.insert(br.readLine());
        }

        br.readLine();
        b = Integer.parseInt(br.readLine());

        for(int board = 0; board < b; ++board) {
            // 초기화
            trie.clearHit(trie.root);
            graph = new char[4][4];
            visited = new boolean[4][4];
            maxScore = 0;
            longestWord = "";
            findCount = 0;

            // 맵
            for (int i = 0; i < 4; ++i) {
                String line = br.readLine();
                for (int j = 0; j < 4; ++j) {
                    graph[i][j] = line.charAt(j);
                }
            }

            // dfs
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    dfs(i, j);
                }
            }

            bw.write("" + maxScore + " ");
            bw.write(longestWord + " ");
            bw.write("" + findCount);
            bw.newLine();

            if (board < b - 1)
                br.readLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int x, int y) {
        if (!trie.isExist(graph[x][y])) {
            return;
        }

        // 1.
        trie.advance(graph[x][y]);
        visited[x][y] = true;

        // 2. 목적지
        if (trie.cursor.isWord && !trie.cursor.isHit) {
            addScore(trie.cursor.word);
            trie.hit();
        }

        // 3. 연결된 곳
        for (int i = 0; i < 8; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 4. 갈 수 있는가?
            if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4)
                continue;
            if (visited[nx][ny])
                continue;

            // 5. 간다
            dfs(nx, ny);
        }

        // 6.
        visited[x][y] = false;
        trie.back();
    }

    private static void addScore(String word) {
        int longestLength = longestWord.length();
        int wordLength = word.length();

        // 1. 점수
        maxScore += score[word.length()];

        // 2. 가장 긴 단어
        if (longestLength < wordLength) {
            longestWord = word;
        } else if (longestLength == wordLength) {
            if (word.compareTo(longestWord) < 0) {
                longestWord = word;
            }
        }
        // 3. 찾은 단어 수
        ++findCount;
    }

    static class TrieNode {
        boolean isWord;
        boolean isHit;
        String word;
        HashMap<Character, TrieNode> children = new HashMap<>();
    }

    static class Trie {
        TrieNode root = new TrieNode();
        TrieNode cursor;
        List<TrieNode> path = new LinkedList<>();

        public Trie() {
            cursor = root;
        }

        void insert(String word) {
            TrieNode currentNode = root;
            for (int i = 0; i < word.length(); ++i) {
                currentNode = currentNode.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            currentNode.isWord = true;
            currentNode.word = word;
        }

        void advance(char c) {
            path.add(cursor);
            cursor = cursor.children.get(c);
        }

        void back() {
            cursor = path.remove(path.size() - 1);
        }

        void hit() {
            cursor.isHit = true;
        }

        boolean isExist(char c) {
            return cursor.children.containsKey(c);
        }

        void clearHit(TrieNode node) {
            node.isHit = false;
            for (TrieNode nextNode: node.children.values()) {
                clearHit(nextNode);
            }
        }
    }
}
