// https://www.acmicpc.net/problem/1351

package baekjoon.dp;

import java.io.*;
import java.util.*;

public class InfiniteSequence {

    private static class HashNode {
        long key, value;
        HashNode next;

        public HashNode(long key, long value, HashNode next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int CAPACITY = 100003;
    private static HashNode[] hashTable = new HashNode[CAPACITY];
    private static long N, P, Q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        P = Long.parseLong(st.nextToken());
        Q = Long.parseLong(st.nextToken());
        bw.write(String.valueOf(dp(N)));
        bw.flush();
        bw.close();
        br.close();
    }

    private static long dp(long i) {
        if (i == 0)
            return 1L;

        if (containsKey(i))
            return get(i);

        put(i, dp(i / P) + dp(i / Q));
        return get(i);
    }

    private static int hash(long h) {
        int hash = 5381;
        String str = String.valueOf(h);

        for (int i = 0; i < str.length(); i++)
        {
            int c = (int)str.charAt(i);
            hash = ((hash << 5) + hash) + c;
        }
        if (hash < 0) hash *= -1;
        return hash % CAPACITY;
    }

    private static void put(long key, long value) {
        int hash = hash(key);
        hashTable[hash] = new HashNode(key, value, hashTable[hash]);
    }

    private static long get(long key) {
        int hash = hash(key);
        for (HashNode p = hashTable[hash]; p != null; p = p.next) {
            if (p.key == key) {
                return p.value;
            }
        }
        return -1L;
    }

    private static boolean containsKey(long key) {
        return get(key) != -1;
    }
}
