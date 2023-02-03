// https://www.acmicpc.net/problem/5639

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class BinarySearchTree {

    private static List<Integer> preorder;
    private int cursor;

    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

//        @Override
//        public String toString() {
//            return "{" +
//                    "" + value +
//                    ", l=" + (left == null ? "null" : left.value) +
//                    ", r=" + (right == null ? "null" : right.value) +
//                    '}';
//        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        preorder = new ArrayList<>(10001);
        String line;
        while ((line = br.readLine()) != null) {
            preorder.add(Integer.parseInt(line));
        }

        cursor = 0;
        TreeNode root = new TreeNode(preorder.get(cursor++));
        makeTreeFromPreorder(root, 0, (int) 1e6);
        postorder(root);

        bw.flush();
        bw.close();
        br.close();
    }

    private void makeTreeFromPreorder(TreeNode node, int lowerBound, int upperBound) {
        // left
        if (cursor < preorder.size()) {
            int current = preorder.get(cursor);
            if (lowerBound < current && current < node.value) {
                cursor++;
                node.left = new TreeNode(current);
                makeTreeFromPreorder(node.left, lowerBound, node.value);
            }
        }

        // right
        if (cursor < preorder.size()) {
            int current = preorder.get(cursor);
            if (node.value < current && current < upperBound) {
                cursor++;
                node.right = new TreeNode(current);
                makeTreeFromPreorder(node.right, node.value, upperBound);
            }
        }
    }

//    private void preorder(TreeNode node) {
//        System.out.println(node.value + " " + node);
//        if (node.left != null) {
//            preorder(node.left);
//        }
//        if (node.right != null) {
//            preorder(node.right);
//        }
//    }

    private void postorder(TreeNode node) {
        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        System.out.println(node.value);
    }

    public static void main(String[] args) throws Exception {
        new BinarySearchTree().solution();
    }
}
