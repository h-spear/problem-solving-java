// https://school.programmers.co.kr/learn/courses/30/lessons/389481

package programmers.level3;

import java.util.*;

public class SealedSpell {

	public String solution(long n, String[] bans) {
		int lb = bans.length;
		long[] banIndices = new long[lb];
		for (int i = 0; i < lb; ++i) {
			banIndices[i] = getIndex(bans[i]);
		}
		Arrays.sort(banIndices);

		for (long bIndex: banIndices) {
			if (bIndex <= n) {
				++n;
			}
		}
		return getWord(n);
	}

	private String getWord(long index) {
		StringBuilder sb = new StringBuilder();
		while (index > 0) {
			long q = index / 26;
			long r = index % 26;
			index = q;
			if (r == 0) {
				sb.append('z');
				--index;
			} else {
				sb.append((char) (r + 'a' - 1));
			}
		}
		return sb.reverse().toString();
	}

	private long getIndex(String word) {
		int wLen = word.length();
		long index = 0;
		long x = 1;
		for (int i = wLen - 1; i >= 0; --i, x *= 26) {
			index += (word.charAt(i) - 'a' + 1) * x;
		}
		return index;
	}
}
