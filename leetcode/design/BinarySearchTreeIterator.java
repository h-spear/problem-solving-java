// https://leetcode.com/problems/binary-search-tree-iterator/

package leetcode.design;

import java.util.*;

public class BinarySearchTreeIterator {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class BSTIterator {

        private Stack<TreeNode> stack = new Stack<>();

        public BSTIterator(TreeNode root) {
            TreeNode p = root;
            while (p != null) {
                stack.add(p);
                p = p.left;
            }
        }

        public int next() {
            TreeNode node = stack.pop();
            int val = node.val;
            TreeNode p = node.right;
            while (p != null) {
                stack.add(p);
                p = p.left;
            }
            return val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }
    
/**
Your BSTIterator object will be instantiated and called as such:
BSTIterator obj = new BSTIterator(root);
int param_1 = obj.next();
boolean param_2 = obj.hasNext();
 */
}
