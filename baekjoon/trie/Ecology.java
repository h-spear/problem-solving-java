// https://www.acmicpc.net/problem/4358

package baekjoon.trie;

import java.io.*;
import java.util.*;

public class Ecology {

    TrieNode root = new TrieNode();
    Set<String> set = new HashSet<>();

    class TrieNode {
        boolean isEnd = false;
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0;
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int total = 0;

        while (true) {
            String tree = br.readLine();

            if (tree == null || tree.equals(""))
                break;

            insert(tree);
            set.add(tree);
            ++total;
        }
        String[] trees = set.toArray(new String[set.size()]);
        Arrays.sort(trees);

        for (String tree: trees) {
            bw.write(tree + " " + String.format("%.4f", (double) search(tree) * 100 / (double) total));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private void insert(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); ++i) {
            currentNode = currentNode.children.computeIfAbsent(word.charAt(i), c -> new TrieNode());
        }
        currentNode.isEnd = true;
        currentNode.count++;
    }

    private int search(String word) {
        TrieNode currentNode = root;
        for (int i = 0; i < word.length(); ++i) {
            currentNode = currentNode.children.get(word.charAt(i));
        }
        return currentNode.count;
    }

    public static void main(String[] args) throws Exception {
        new Ecology().solution();
    }
}
