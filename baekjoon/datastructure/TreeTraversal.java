// https://www.acmicpc.net/problem/1991

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class TreeTraversal {

    class TreeNode {
        int left;
        int right;

        public TreeNode(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    private static final int nullNode = '.' - 'A';
    TreeNode[] tree;


    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new TreeNode[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int p = st.nextToken().charAt(0) - 'A';
            int l = st.nextToken().charAt(0) - 'A';
            int r = st.nextToken().charAt(0) - 'A';
            tree[p] = new TreeNode(l, r);
        }

        preorder(0);
        System.out.println();
        inorder(0);
        System.out.println();
        postorder(0);

        bw.flush();
        bw.close();
        br.close();
    }

    private void preorder(int node) {
        if (node == nullNode) {
            return;
        }
        System.out.printf("%c", node + 'A');
        preorder(tree[node].left);
        preorder(tree[node].right);
    }

    private void inorder(int node) {
        if (node == nullNode) {
            return;
        }
        inorder(tree[node].left);
        System.out.printf("%c", node + 'A');
        inorder(tree[node].right);
    }

    private void postorder(int node) {
        if (node == nullNode) {
            return;
        }
        postorder(tree[node].left);
        postorder(tree[node].right);
        System.out.printf("%c", node + 'A');
    }

    public static void main(String[] args) throws Exception {
        new TreeTraversal().solution();
    }
}
