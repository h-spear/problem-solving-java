// https://leetcode.com/problems/wiggle-sort/

package leetcode.array;

public class WiggleSort {
    // Solution1
    private int N;
    private int[] counter;
    private int high, low;

    public void wiggleSort(int[] nums) {
        N = nums.length;
        counter = new int[10001];
        for (int num: nums) {
            ++counter[num];
        }

        high = 10000;
        low = 0;
        for (int i = 0; i < N; ++i) {
            nums[i] = (i & 1) == 0 ? getLow() : getHigh();
        }
    }

    private int getHigh() {
        while (counter[high] == 0) {
            --high;
        }
        --counter[high];
        return high;
    }

    private int getLow() {
        while (counter[low] == 0) {
            ++low;
        }
        --counter[low];
        return low;
    }

    // Solution2
    public void wiggleSort_Solution2(int[] nums) {
        int N = nums.length;
        int temp;
        for (int i = 0; i < N - 1; ++i) {
            if (((i & 1) == 0 && nums[i] > nums[i+1]) ||
                    ((i & 1) == 1 && nums[i] < nums[i+1])) {
                temp = nums[i];
                nums[i] = nums[i+1];
                nums[i+1] = temp;
            }
        }
    }
}
