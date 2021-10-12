package day;

import util.Tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class BuildMaxTree implements Tree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return help(nums, 0, nums.length - 1);
    }

    private TreeNode help(int[] nums, int left, int right) {
        if (right >= left) return null;
        int index = findMax(nums, left, right);
        TreeNode tree = new TreeNode(nums[index]);
        tree.left = help(nums, left, index - 1);
        tree.right = help(nums, index, right);
        return tree;
    }

    private int findMax(int[] arr, int left, int right) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = left; i < right; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }
        return index;
    }

    //918. 环形子数组的最大和
    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int curMax, max, curMin, min, sum;
        curMax = max = curMin = min = sum = A[0];
        for (int i = 1; i < A.length; i++) {
            sum += A[i];
            curMax = curMax > 0 ? curMax + A[i] : A[i];
            max = Math.max(curMax, max);
            curMin = curMin < 0 ? curMin + A[i] : A[i];
            min = Math.min(curMin, min);
        }
        if (max < 0)
            return max;
        return Math.max(sum - min, max);
    }

    Map<Integer, int[]> memo;

    public int[] beautifulArray(int N) {
        memo = new HashMap<>();
        return f(N);
    }

    public int[] f(int N) {
        if (memo.containsKey(N))
            return memo.get(N);
        int[] ans = new int[N];
        if (N == 1) {
            ans[0] = 1;
        } else {
            int t = 0;
            for (int x : f((N + 1) / 2))  // odds
                ans[t++] = 2 * x - 1;
            for (int x : f(N / 2))  // evens
                ans[t++] = 2 * x;
        }
        memo.put(N, ans);
        return ans;
    }
}
