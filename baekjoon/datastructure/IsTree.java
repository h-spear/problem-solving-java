// https://www.acmicpc.net/problem/6416

package baekjoon.datastructure;

import java.io.*;
import java.util.*;

public class IsTree {

    private BufferedReader br;
    private BufferedWriter bw;
    private boolean isCycle;
    private boolean isEnd;

    private void solution() throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = 0;
        while (true) {
            ++k;

            boolean isTree = testcase();

            if (isEnd)
                break;

            bw.write("Case " + k + " is ");
            if (isTree) {
                bw.write("a tree.");
            } else {
                bw.write("not a tree.");
            }
            bw.newLine();

        }
        bw.flush();
        bw.close();
        br.close();
    }


    private boolean testcase() throws Exception {
        // 입력
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Map<Integer, List<Integer>> tree = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        isCycle = false;

        treeInput(inDegree, outDegree, tree);

        if (isEnd)
            return false;

        // 판별

        // 0. 비어있으면 트리
        if (tree.isEmpty()) {
            return true;
        }

        int root = 0;
        int rootCount = 0;
        for (int key: inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                root = key;
                rootCount++;
            }
        }

        // 1. 루트노드가 여러 개면 트리가 아님
        if (rootCount != 1) {
            return false;
        }

        // 탐색
        dfs(root, tree, visited);

        // 2. 모든 노드를 방문했는가 체크
        for (int key: tree.keySet()) {
            // 방문한 적 없다면 트리가 아님
            if (visited.contains(key) == false) {
                return false;
            }
        }
        // 사이클이 존재한다면 트리가 아님
        if (isCycle) {
            return false;
        }
        return true;
    }

    private void dfs(int node, Map<Integer, List<Integer>> tree, Set<Integer> visited) {
        visited.add(node);
        List<Integer> children = tree.get(node);
        for (int childNode: children) {
            if (visited.contains(childNode)) {
                isCycle = true;
                break;
            } else {
                dfs(childNode, tree, visited);
            }
        }
    }

    private void treeInput(Map<Integer, Integer> inDegree,
                           Map<Integer, Integer> outDegree,
                           Map<Integer, List<Integer>> tree) throws Exception {
        int parent, child;
        boolean input = true;
        while (input) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                parent = Integer.parseInt(st.nextToken());
                child = Integer.parseInt(st.nextToken());
                if (parent == 0 && child == 0) {
                    input = false;
                    break;
                }
                if (parent == -1 && child == -1) {
                    isEnd = true;
                    return;
                }
                // 해시에 없으면 삽입
                if (!inDegree.containsKey(parent)) {
                    inDegree.put(parent, 0);
                }
                if (!inDegree.containsKey(child)) {
                    inDegree.put(child, 0);
                }
                if (!outDegree.containsKey(parent)) {
                    outDegree.put(parent, 0);
                }
                if (!outDegree.containsKey(child)) {
                    outDegree.put(child, 0);
                }
                if (!tree.containsKey(parent)) {
                    tree.put(parent, new ArrayList<>());
                }
                if (!tree.containsKey(child)) {
                    tree.put(child, new ArrayList<>());
                }
                inDegree.put(child, inDegree.get(child) + 1);
                outDegree.put(parent, outDegree.get(parent) + 1);
                tree.get(parent).add(child);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new IsTree().solution();
    }
}
