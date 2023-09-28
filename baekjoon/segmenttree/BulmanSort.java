// https://www.acmicpc.net/problem/5012

package baekjoon.segmenttree;

import java.io.*;
import java.util.*;

public class BulmanSort {

    private static int n, s;
    private static int[] a;
    private static int[] tree;
    private static int[] hi, lo;

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }

    private static void solve() {
        s = 1;
        while (s <= n)
            s <<= 1;

        hi = new int[n];
        lo = new int[n];
        tree = new int[s << 1];

        for (int i = 0; i < n; ++i) {
            hi[i] = query(1, 0, s - 1, a[i] + 1, 100001);
            update(a[i]);
        }

        Arrays.fill(tree, 0);
        for (int i = n - 1; i >= 0; --i) {
            lo[i] = query(1, 0, s - 1, 0, a[i] - 1);
            update(a[i]);
        }

        long answer = 0;
        for (int i = 0; i < n; ++i) {
            answer += (long) hi[i] * (long) lo[i];
        }
        System.out.println(answer);
    }

    private static int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        int mid = (left + right) >> 1;
        return query(node << 1, left, mid, queryLeft, queryRight) +
                query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
    }

    private static void update(int target) {
        int idx = s + target;
        tree[idx] += 1;
        idx >>= 1;
        while (idx > 0) {
            tree[idx] = tree[idx << 1] + tree[(idx << 1) | 1];
            idx >>= 1;
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }
}
