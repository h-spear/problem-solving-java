// https://www.acmicpc.net/problem/1620

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class PokemonMasterLeeDaSom_HashTable {

    static class HashTable {

        class Node {
            String key;
            int value;
            Node next;

            public Node(String key, int value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        private final int capacity;
        private final Node[] table;

        public HashTable(int capacity) {
            this.capacity = capacity;
            this.table = new Node[capacity];
        }

        private int hash(String str) {
            int c, hash = 5381;
            int length = str.length();
            for (int i = 0; i < length; ++i) {
                c = str.charAt(i);
                hash = ((hash << 5) + hash) + c;
            }
            if (hash < 0)
                hash *= -1;
            return hash % capacity;
        }

        public void put(String key, int value) {
            int h = hash(key);
            table[h] = new Node(key, value, table[h]);
        }

        public int get(String key) {
            int h = hash(key);
            for (Node p = table[h]; p != null; p = p.next) {
                if (p.key.equals(key)) {
                    return p.value;
                }
            }
            return -1;
        }
    }

    private static String[] pokemon;
    private static HashTable hashTable = new HashTable(100003);

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        pokemon = new String[N + 1];

        for (int i = 1; i <= N; ++i) {
            pokemon[i] = br.readLine();
            hashTable.put(pokemon[i], i);
        }

        int num;
        for (int i = 0; i < M; ++i) {
            String input = br.readLine();
            try {
                num = Integer.parseInt(input);
                bw.write(pokemon[num]);
                bw.newLine();
            } catch (NumberFormatException e) {
                bw.write(String.valueOf(hashTable.get(input)));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
