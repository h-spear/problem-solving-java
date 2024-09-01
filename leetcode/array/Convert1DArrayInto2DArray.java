// https://leetcode.com/problems/convert-1d-array-into-2d-array/?envType=daily-question&envId=2024-09-01

package leetcode.array;

class Convert1DArrayInto2DArray {

    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n)
            return new int[0][];

        int[][] output = new int[m][n];
        for (int i = 0, idx = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                output[i][j] = original[idx++];
            }
        }
        return output;
    }
}