// https://leetcode.com/problems/confusing-number/
// Beats 100%
// https://leetcode.com/problems/confusing-number/solutions/5721106/java-solution-beats-100-00/

package leetcode.array;

class ConfusingNumber {

    private static final char[] d = {'0', '1', 'x', 'x', 'x', 'x', '9', 'x', '8', '6'};

    public boolean confusingNumber(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        char[] rotated = new char[digits.length];


        for (int i = 0, len = digits.length; i < len; ++i) {
            if (d[digits[i] - '0'] == 'x')
                return false;
            rotated[len - i - 1] = d[digits[i] - '0'];
        }

        int rotatedNumber = Integer.parseInt(String.valueOf(rotated));
        return n != rotatedNumber;
    }
}