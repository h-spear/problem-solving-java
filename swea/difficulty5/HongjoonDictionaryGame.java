// 3135

package swea.difficulty5;

import java.util.HashMap;
import java.util.Map;

public class HongjoonDictionaryGame {

    public class UserSolution {

        private TrieNode root;

        public void init() {
            root = new TrieNode();
        }

        public void insert(int buffer_size, String buf) {
            TrieNode p = root;
            for (int i = 0; i < buffer_size; ++i) {
                char c = buf.charAt(i);
                if (!p.children.containsKey(c)) {
                    p.children.put(c, new TrieNode());
                }
                ++p.count;
                p = p.children.get(c);
            }
            ++p.count;
            p.isWord = true;
        }

        public int query(int buffer_size, String buf) {
            TrieNode p = root;
            for (int i = 0; i < buffer_size; ++i) {
                char c = buf.charAt(i);
                if (!p.children.containsKey(c)) {
                    return 0;
                }
                p = p.children.get(c);
            }
            return p.count;
        }

        private class TrieNode {
            Map<Character, TrieNode> children = new HashMap<>();
            boolean isWord = false;
            int count = 0;
        }
    }

}