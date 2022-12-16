// https://www.acmicpc.net/problem/5052

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class PhoneNumberList {

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
        int counter = 0;
    }

    class Trie {
        TrieNode root;

        Trie() {
            this.root = new TrieNode();
        }

        void insert(String word) {
            TrieNode curr = this.root;
            curr.counter++;
            for (int i = 0; i < word.length(); ++i) {
                curr = curr.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
                curr.counter++;
            }
            curr.isWord = true;
        }

        boolean isPrefix(String word) {
            TrieNode curr = this.root;
            for (int i = 0; i < word.length(); ++i) {
                curr = curr.children.get(word.charAt(i));
            }
            return curr.counter > 1;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; ++tc) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];
            Trie trie = new Trie();
            for (int i = 0; i < n; ++i) {
                String number = br.readLine();
                numbers[i] = number;
                trie.insert(number);
            }

            boolean flag = true;
            for (String number: numbers) {
                if (trie.isPrefix(number)) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                bw.write("YES");
            else
                bw.write("NO");
            bw.newLine();
            bw.flush();
        }
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new PhoneNumberList().solution();
    }
}
