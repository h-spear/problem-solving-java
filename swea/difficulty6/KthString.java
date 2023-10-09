// 1257

package swea.difficulty6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KthString {

	private static TrieNode root;
	private static int K;
	private static String find;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; ++tc) {
			int K = Integer.parseInt(br.readLine());
			String string = br.readLine();
			sb.append("#").append(tc).append(" ")
				.append(find(string, K)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	private static String find(String string, int k) {
		K = k;
		root = new TrieNode();
		find = null;
		for (int i = 0, length = string.length(); i < length; ++i) {
			for (int j = 0; j < length - i; ++j) {
				insert(string.substring(j, j + i + 1));
			}
		}
		dfs(root, 0, new StringBuilder());
		return find == null ? "none" : find;
	}

	private static void dfs(TrieNode node, int depth, StringBuilder sb) {
		if (K < 0)
			return;
		if (node.isWord) {
			if (--K == 0) {
				find = sb.toString();
				return;
			}
		}

		for (int i = 0; i < 26; ++i) {
			if (node.children[i] == null)
				continue;
			sb.append((char) (i + 'a'));
			dfs(node.children[i], depth + 1, sb);
			sb.setLength(depth);
		}
	}

	private static void insert(String word) {
		int len = word.length();
		int c;
		TrieNode p = root;
		for (int i = 0; i < len; ++i) {
			c = word.charAt(i) - 'a';
			if (p.children[c] == null)
				p.children[c] = new TrieNode();
			p = p.children[c];
		}
		p.isWord = true;
	}

	private static class TrieNode {
		boolean isWord;
		TrieNode[] children = new TrieNode[26];
	}
}

class KthString_Sorting {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int C = 1; C <= T; ++C) {
			int K = Integer.parseInt(br.readLine());
			String str = br.readLine();
			bw.write("#" + C + " " + getKthSubstring(str, K));
			bw.newLine();
		}

		bw.flush();
		bw.close();
		br.close();
	}

	private static String getKthSubstring(String string, int K) {
		Set<String> S = new HashSet<>();
		int length = string.length();
		for (int i = 0; i < length; ++i) {
			for (int j = 0; j < length - i; ++j) {
				S.add(string.substring(j, j + i + 1));
			}
		}
		ArrayList<String> list = new ArrayList<>(S);
		Collections.sort(list);
		return K >= list.size() ? "none" : list.get(K - 1);
	}
}
