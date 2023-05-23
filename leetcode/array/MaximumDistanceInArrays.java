// https://leetcode.com/problems/maximum-distance-in-arrays/

package leetcode.array;

import java.util.*;

public class MaximumDistanceInArrays {

    private final static int INF = 1000000;

    public int maxDistance(List<List<Integer>> arrays) {
        int M = arrays.size();
        int[] max = new int[M];
        int[] min = new int[M];
        Arrays.fill(max, -INF);
        Arrays.fill(min, INF);

        int idx = 0, arraySize;
        for (List<Integer> array: arrays) {
            arraySize = array.size();
            max[idx] = Math.max(max[idx], array.get(arraySize - 1));
            min[idx] = Math.min(min[idx], array.get(0));
            ++idx;
        }

        int[] minLtoR = new int[M];
        int[] minRtoL = new int[M];
        Arrays.fill(minLtoR, INF);
        Arrays.fill(minRtoL, INF);
        minLtoR[0] = min[0];
        minRtoL[M-1] = min[M-1];

        // fill minLtoR
        for (int i = 1; i < M; ++i) {
            minLtoR[i] = Math.min(minLtoR[i-1], min[i]);
        }

        // fill minRtoL
        for (int i = M - 2; i >= 0; --i) {
            minRtoL[i] = Math.min(minRtoL[i+1], min[i]);
        }

        min[0] = minRtoL[1];
        min[M-1] = minLtoR[M-2];
        for (int i = 1; i < M - 1; ++i) {
            min[i] = Math.min(minLtoR[i-1], minRtoL[i+1]);
        }

        int answer = -INF;
        for (int i = 0; i < M; ++i) {
            answer = Math.max(answer, max[i] - min[i]);
        }
        return answer;
    }

    public int maxDistance_Solution2(List<List<Integer>> arrays) {
        int M = arrays.size();
        List<Integer> first = arrays.get(0);
        int min = first.get(0);
        int max = first.get(first.size() - 1);
        int arraySize, answer = -INF;

        List<Integer> array;
        for (int i = 1; i < M; ++i) {
            array = arrays.get(i);
            arraySize = array.size();
            answer = Math.max(answer, Math.abs(array.get(arraySize - 1) - min));
            answer = Math.max(answer, Math.abs(array.get(0) - max));
            min = Math.min(min, array.get(0));
            max = Math.max(max, array.get(arraySize - 1));
        }
        return answer;
    }
}
