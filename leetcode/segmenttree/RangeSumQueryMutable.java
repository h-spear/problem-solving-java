// https://leetcode.com/problems/range-sum-query-mutable/

package leetcode.segmenttree;

public class RangeSumQueryMutable {

    class NumArray {

        private int N, S;
        private int[] tree;

        public NumArray(int[] nums) {
            N = nums.length;
            S = 1;
            while (S < N) {
                S <<= 1;
            }
            tree = new int[S * 2];

            for (int i = 0; i < N; ++i) {
                tree[S + i] = nums[i];
            }
            for (int i = S - 1; i > 0; --i) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
        }

        public void update(int index, int val) {
            update(1, 0, S - 1, index, val);
        }

        private void update(int node, int left, int right, int index, int val) {
            if (index < left || right < index) {
                return;
            }
            if (left == right) {
                tree[node] = val;
            } else {
                int mid = (left + right) >> 1;
                update(node * 2, left, mid, index, val);
                update(node * 2 + 1, mid + 1, right, index, val);
                tree[node] = tree[node * 2] + tree[node * 2 + 1];
            }
        }

        public int sumRange(int queryLeft, int queryRight) {
            return sumRange(1, 0, S - 1, queryLeft, queryRight);
        }

        private int sumRange(int node, int left, int right, int queryLeft, int queryRight) {
            if (queryRight < left || right < queryLeft) {
                return 0;
            }
            if (queryLeft <= left && right <= queryRight) {
                return tree[node];
            }
            int mid = (left + right) >> 1;
            return sumRange(node * 2, left, mid, queryLeft, queryRight) +
                    sumRange(node * 2 + 1, mid + 1, right, queryLeft, queryRight);
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
}