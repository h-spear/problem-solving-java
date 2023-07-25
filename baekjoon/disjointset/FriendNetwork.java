// https://www.acmicpc.net/problem/4195

package baekjoon.disjointset;

import java.io.*;
import java.util.*;

public class FriendNetwork {

    private static class HashNode {
        String key;
        int value;
        HashNode next;

        public HashNode() {}

        public HashNode setHashNode(String key, int value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
            return this;
        }
    }

    private static final int MAX_TABLE = 100003;
    private static int[] parent = new int[200020];
    private static int[] size = new int[200020];
    private static HashNode[] hashTable = new HashNode[MAX_TABLE];
    private static HashNode[] pool = new HashNode[200020];
    private static int pIdx;

    private static int hash(String str) {
        int hash = 5381;

        for (int i = 0; i < str.length(); ++i) {
            int c = (int) str.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }
        if (hash < 0) hash *= -1;
        return hash % MAX_TABLE;
    }

    private static void initialize(int N) {
        pIdx = 0;

        for (int i = 0; i < MAX_TABLE; ++i) {
            hashTable[i] = null;
        }

        for (int i = 0; i <= N * 2; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    private static int put(String key, int value) {
        int h = hash(key);
        HashNode hashNode = pool[pIdx++];
        hashTable[h] = hashNode.setHashNode(key, value, hashTable[h]);
        return value;
    }

    private static int get(String key) {
        int h = hash(key);
        for (HashNode p = hashTable[h]; p != null; p = p.next) {
            if (p.key.equals(key)) {
                return p.value;
            }
        }
        return -1;
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[b] = a;
            size[a] += size[b];
        }
        return size[a];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i < 200020; ++i) {
            pool[i] = new HashNode();
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            initialize(F);

            String name1, name2;
            int idx1, idx2, num = 0;
            for (int i = 0; i < F; ++i) {
                st = new StringTokenizer(br.readLine());
                name1 = st.nextToken();
                name2 = st.nextToken();

                idx1 = get(name1);
                idx2 = get(name2);
                if (idx1 == -1)
                    idx1 = put(name1, num++);
                if (idx2 == -1)
                    idx2 = put(name2, num++);

                sb.append(union(idx1, idx2)).append("\n");
            }
        }
        System.out.println(sb.toString());
        br.close();
    }
}
