// https://leetcode.com/problems/queue-reconstruction-by-height/

package leetcode.segmenttree;

import java.util.*;

class QueueReconstructionByHeight {

    private int s = (1 << 11);
    private int[] tree = new int[s << 1];

    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        int[] org = coordinateCompression(people);
        Arrays.sort(people, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        int[][] result = new int[n][];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (people[j] == null)
                    continue;
                int h = people[j][0];
                int k = people[j][1];

                if (query(1, 0, s - 1, h, s) == k) {
                    update(h, +1);
                    result[i] = people[j];
                    people[j] = null;
                    break;
                }
            }
        }

        for (int[] person: result) {
            person[0] = org[person[0]];
        }
        return result;
    }

    private int query(int node, int left, int right, int queryLeft, int queryRight) {
        if (queryRight < left || right < queryLeft)
            return 0;
        if (queryLeft <= left && right <= queryRight)
            return tree[node];
        int mid = (left + right) >> 1;
        return query(node << 1, left, mid, queryLeft, queryRight)
                + query((node << 1) | 1, mid + 1, right, queryLeft, queryRight);
    }

    private void update(int target, int value) {
        int i = s + target;
        while (i > 0) {
            tree[i] += value;
            i >>= 1;
        }
    }

    private int[] coordinateCompression(int[][] people) {
        int n = people.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            set.add(people[i][0]);
        }

        int sz = set.size();
        int[] xs = new int[sz];
        int idx = 0;
        for (int x: set) {
            xs[idx++] = x;
        }

        Arrays.sort(xs);
        for (int i = 0; i < n; ++i) {
            people[i][0] = lowerBound(xs, 0, sz - 1, people[i][0]);
        }
        return xs;
    }

    private int lowerBound(int[] arr, int left, int right, int k) {
        int mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            if (arr[mid] >= k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }
}