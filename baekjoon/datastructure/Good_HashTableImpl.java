// https://www.acmicpc.net/problem/1253

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class Good_HashTableImpl {

    static class HashTable {
        class HashNode {
            int key, value;
            HashNode next;

            public HashNode(int key, int value, HashNode next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }
        }

        final int capacity;
        HashNode[] hashTable;

        HashTable(int capacity) {
            this.capacity = capacity;
            this.hashTable = new HashNode[capacity];
        }

        int hash(int h) {
            String str = String.valueOf(h);
            int hash = 5381;

            for (int i = 0; i < str.length(); i++)
            {
                int c = (int)str.charAt(i);
                hash = ((hash << 5) + hash) + c;
            }
            if (hash < 0) hash *= -1;
            return hash % capacity;
        }

        void put(int key) {
            if (get(key) == -1) {
                int hash = hash(key);
                hashTable[hash] = new HashNode(key, 1, hashTable[hash]);
            } else {
                int hash = hash(key);
                for (HashNode p = hashTable[hash]; p != null; p = p.next) {
                    if (p.key == key) {
                        ++p.value;
                        return;
                    }
                }
            }
        }

        int get(int key) {
            int hash = hash(key);
            for (HashNode p = hashTable[hash]; p != null; p = p.next) {
                if (p.key == key) {
                    return p.value;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        HashTable hashTable = new HashTable(2003);
        HashTable counter = new HashTable(2003);

        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
            hashTable.put(A[i]);
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == j)
                    continue;
                if (A[i] == 0 && A[j] == 0) {
                    if (hashTable.get(A[i] + A[j]) > 2) {
                        counter.put(A[i] + A[j]);
                    }
                } else if (A[i] == 0 || A[j] == 0) {
                    if (hashTable.get(A[i] + A[j]) > 1) {
                        counter.put(A[i] + A[j]);
                    }
                } else if (hashTable.get(A[i] + A[j]) > 0) {
                    counter.put(A[i] + A[j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; ++i) {
            if (counter.get(A[i]) > 0)
                ++answer;
        }
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}
