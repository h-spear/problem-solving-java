// 1256

package swea.difficulty5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * trie + dfs
 */
public class KthSuffix {

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
		for (int i = 0, length = string.length(); i < length; ++i) {
			insert(string.substring(length - i - 1));
		}
		dfs(root, 0, new StringBuilder());
		return find;
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

class KthSuffix_Sorting {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		String[] strings = new String[401];

		for (int tc = 1; tc <= T; ++tc) {
			int K = Integer.parseInt(br.readLine());
			String string = br.readLine();
			int length = string.length();
			for (int i = 0; i < length; ++i) {
				strings[i] = string.substring(length - i - 1);
			}
			Arrays.sort(strings, 0, length);
			sb.append("#").append(tc).append(" ")
				.append(strings[K - 1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}