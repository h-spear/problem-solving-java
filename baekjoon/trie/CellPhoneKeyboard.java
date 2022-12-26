// https://www.acmicpc.net/problem/5670

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class CellPhoneKeyboard {

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        int count;

        public TrieNode() {
            this.children = new HashMap<>();
            this.isWord = false;
            this.count = 0;
        }
    }

    class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode currNode = this.root;
            ++currNode.count;
            for (int i = 0; i < word.length(); ++i) {
                currNode = currNode.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                ++currNode.count;
            }
            currNode.isWord = true;
        }

        public double search(String word) {
            TrieNode currNode = this.root;
            int prevCount, result = 0;

            if (currNode.count == currNode.children.get(word.charAt(0)).count)
                ++result;

            for (int i = 0; i < word.length(); ++i) {
                prevCount = currNode.count;
                currNode = currNode.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                if (prevCount != currNode.count)
                    ++result;
                if (currNode.count == 1)
                    break;
            }
            return result;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while ((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            String[] words = new String[n];
            Trie trie = new Trie();
            for (int i = 0; i < n; ++i) {
                words[i] = br.readLine();
                trie.insert(words[i]);
            }

            int pushCount = 0;
            for (int i = 0; i < n; ++i) {
                pushCount += trie.search(words[i]);
            }
            System.out.printf("%.2f\n", (double) pushCount / n);
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new CellPhoneKeyboard().solution();
    }
}
