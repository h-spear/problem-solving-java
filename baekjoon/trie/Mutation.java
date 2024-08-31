// https://www.acmicpc.net/problem/10256

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class Mutation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            String dna = br.readLine();
            String marker = br.readLine();

            AhoCorasick ac = new AhoCorasick();

            ac.insert(marker);
            for (int i = 0; i < m; ++i) {
                for (int j = i + 1; j < m; ++j) {
                    String mutation =
                            marker.substring(0, i) +
                                    new StringBuilder(marker.substring(i, j + 1)).reverse() +
                                    marker.substring(j + 1);
                    ac.insert(mutation);
                }
            }
            ac.computeFailFunction();
            sb.append(ac.solve(dna))
                    .append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static class AhoCorasick {

        private final TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode p = this.root;
            for (char c: word.toCharArray()) {
                int index = toIndex(c);
                if (p.children[index] == null)
                    p.children[index] = new TrieNode();
                p = p.children[index];
            }
            p.isWord = true;
        }

        void computeFailFunction() {
            TrieNode p = this.root;
            Queue<TrieNode> queue = new ArrayDeque<>();
            queue.add(p);

            while (!queue.isEmpty()) {
                TrieNode curr = queue.poll();
                for (int i = 0; i < 4; ++i) {
                    TrieNode next = curr.children[i];
                    if (next == null)
                        continue;

                    if (curr == this.root) {
                        next.fail = this.root;
                    } else {
                        TrieNode failLinkNode = curr.fail;

                        while (failLinkNode != this.root && failLinkNode.children[i] == null)
                            failLinkNode = failLinkNode.fail;

                        if (failLinkNode.children[i] != null)
                            failLinkNode = failLinkNode.children[i];

                        next.fail = failLinkNode;
                    }

                    if (next.fail.isWord) {
                        next.isWord = true;
                    }
                    queue.add(next);
                }
            }
        }

        int solve(String word) {
            TrieNode p = this.root;

            int count = 0;
            for (char c: word.toCharArray()) {
                while (p != this.root && p.children[toIndex(c)] == null)
                    p = p.fail;

                if (p.children[toIndex(c)] != null)
                    p = p.children[toIndex(c)];

                if (p.isWord)
                    ++count;
            }
            return count;
        }

        private static int toIndex(char c) {
            if (c == 'A') return 0;
            else if (c == 'C') return 1;
            else if (c == 'G') return 2;
            else if (c == 'T') return 3;
            else return -1;
        }

        private static class TrieNode {
            boolean isWord;
            TrieNode[] children = new TrieNode[4];
            TrieNode fail;
        }
    }
}