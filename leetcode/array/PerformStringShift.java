// https://leetcode.com/problems/perform-string-shifts/
// Beats 100%
// https://leetcode.com/problems/perform-string-shifts/solutions/5721145/java-solution-beats-100-00/

package leetcode.array;

class PerformStringShift {
    public String stringShift(String s, int[][] shift) {
        int n = s.length();
        int cursor = 0;
        for (int[] q: shift) {
            if (q[0] == 0)
                cursor = (cursor + q[1]) % n;
            else
                cursor = (cursor - q[1] + 100 * n) % n;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = cursor; i < n; ++i)
            sb.append(s.charAt(i));
        for (int i = 0; i < cursor; ++i)
            sb.append(s.charAt(i));
        return sb.toString();
    }
}