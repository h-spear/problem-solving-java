// https://www.acmicpc.net/problem/4358

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class Ecology {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
        int count = 0;
    }

    class Trie {
        TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        void insert(String word) {
            TrieNode curr = this.root;
            curr.count++;
            for (int i = 0; i < word.length(); ++i) {
                curr = curr.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
            }
            curr.isWord = true;
            curr.count++;
        }

        double getPercentage(String word) {
            TrieNode curr = this.root;
            for (int i = 0; i < word.length(); ++i) {
                curr = curr.children.get(word.charAt(i));
            }
            return ((double) curr.count / (double) this.root.count) * 100;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Set<String> set = new HashSet<>();
        Trie trie = new Trie();
        while (true) {
            String tree = br.readLine();

            if (tree == null || tree.equals(""))
                break;

            set.add(tree);
            trie.insert(tree);
        }
        String[] trees = set.toArray(new String[set.size()]);
        Arrays.sort(trees);

        for (String tree: trees) {
            bw.write(tree + " " + String.format("%.4f", trie.getPercentage(tree)));
            bw.newLine();
        }
        br.close();
        bw.close();

    }

    public static void main(String[] args) throws Exception {
        new Ecology().solution();
    }
}
