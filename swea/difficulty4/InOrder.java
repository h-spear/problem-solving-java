// 1231

package swea.difficulty4;

import java.io.*;
import java.util.*;

public class InOrder {

    private static class TreeNode {
        char c;
        TreeNode left, right;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char c;
        int me;
        int T = 10;

        TreeNode[] tree = new TreeNode[101];
        for (int i = 1; i <= 100; ++i) {
            tree[i] = new TreeNode();
        }

        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; ++tc) {
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                me = Integer.parseInt(st.nextToken());
                c = st.nextToken().charAt(0);
                tree[me].c = c;

                if (st.hasMoreTokens()) {
                    tree[me].left = tree[Integer.parseInt(st.nextToken())];
                } else {
                    tree[me].left = null;
                }

                if (st.hasMoreTokens()) {
                    tree[me].right = tree[Integer.parseInt(st.nextToken())];
                } else {
                    tree[me].right = null;
                }
            }
            sb.append("#").append(tc).append(" ");
            inOrder(tree[1], sb);
            sb.append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    private static void inOrder(TreeNode root, StringBuilder sb) {
        if (root == null)
            return;

        inOrder(root.left, sb);
        sb.append(root.c);
        inOrder(root.right, sb);
    }
}
