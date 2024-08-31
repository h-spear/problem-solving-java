// https://www.acmicpc.net/problem/9250

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class DeterminingSetOfStrings {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        AhoCorasick ac = new AhoCorasick();
        for (int i = 0; i < N; ++i) {
            ac.insert(br.readLine());
        }

        ac.computeFailureFunction();

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        while (Q-- > 0) {
            if (ac.search(br.readLine())) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static class AhoCorasick {

        TrieNode root = new TrieNode();

        void insert(String word) {
            TrieNode p = this.root;
            for (char c: word.toCharArray()) {
                if (p.children[c - 'a'] == null)
                    p.children[c - 'a'] = new TrieNode();
                p = p.children[c - 'a'];
            }
            p.isWord = true;
        }

        void computeFailureFunction() {
            Queue<TrieNode> queue = new ArrayDeque<>();
            TrieNode p = this.root;
            queue.add(p);

            while (!queue.isEmpty()) {
                TrieNode curr = queue.poll();
                for (int i = 0; i < 26; ++i) {
                    char c = (char) (i + 97);

                    TrieNode next = curr.children[c - 'a'];
                    if (next == null)
                        continue;

                    if (curr == this.root)
                        next.fail = this.root;
                    else {
                        TrieNode failLinkNode = curr.fail;

                        while (failLinkNode != this.root && failLinkNode.children[c - 'a'] == null)
                            failLinkNode = failLinkNode.fail;

                        if (failLinkNode.children[c - 'a'] != null)
                            failLinkNode = failLinkNode.children[c - 'a'];

                        next.fail = failLinkNode;
                    }

                    if (next.fail.isWord) {
                        next.isWord = true;
                    }
                    queue.add(next);
                }
            }
        }

        boolean search(String word) {
            TrieNode p = this.root;

            for (char c: word.toCharArray()) {
                while (p != this.root && p.children[c - 'a'] == null) {
                    p = p.fail;
                }
                if (p.children[c - 'a'] != null)
                    p = p.children[c - 'a'];

                if (p.isWord)
                    return true;
            }
            return false;
        }
    }

    private static class TrieNode {
        boolean isWord;
        TrieNode[] children = new TrieNode[26];
        TrieNode fail;
    }
}
