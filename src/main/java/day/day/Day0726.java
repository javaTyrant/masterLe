package day.day;

import org.junit.Test;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/26
 **/
public class Day0726 {
    @Test
    public void testMinOperations() {
        int[] target = {5, 1, 3};
        int[] arr = {1, 9, 4, 2, 3, 4};
        System.out.println(minOperations(target, arr));
    }

    @Test
    public void testSet() {
        int[] target = {6, 4, 8, 1, 3, 2};
        int[] arr = {4, 7, 6, 2, 3, 8, 6, 1};
        System.out.println(minOperations(target, arr));
    }

    //1713. 得到子序列的最少操作次数:使得 target成为arr的一个子序列
    //假设最少操作数是x，那么有target.length-x长度的子序列属于arr的子序列
    //联想最长上升子序列，我也可以维护一个d[i]数组，表示长度为i的子序列的末尾元素
    //且这个末尾元素在target中的位置越靠前越好
    //如果新的元素的位置大于所有的d[i]，那么子序列长度+1;
    //从而找到了最长的target.length-x长度的子序列
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        //target转map
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            pos.put(target[i], i);
        }
        //d的含义是:
        List<Integer> d = new ArrayList<>();
        for (int val : arr) {
            //过滤掉target里不存在的arr里的元素
            if (pos.containsKey(val)) {
                //
                int idx = pos.get(val);
                //二分的意义是?
                int it = binarySearch(d, idx);
                //什么时候不等呢?
                if (it != d.size()) {
                    //什么时候更新呢
                    d.set(it, idx);
                } else {
                    //添加
                    d.add(idx);
                }
            }
        }
        //
        return n - d.size();
    }

    @Test
    public void testBinarySearch() {
        List<Integer> d = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(binarySearch(d, 1));
        System.out.println(binarySearch(d, 2));
        System.out.println(binarySearch(d, 3));
        System.out.println(binarySearch(d, 9));
    }

    public int binarySearch(List<Integer> d, int target) {
        int size = d.size();
        if (size == 0 || d.get(size - 1) < target) {
            return size;
        }
        int low = 0, high = size - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (d.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //300. 最长递增子序列 n^2
    public int lengthOfLISDp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }

    @Test
    public void testLengthOfLIS() {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(arr));
    }

    //300.贪心 + 二分查找O(nLogN)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int right = 0;
        for (int num : nums) {
            //
            int index = Arrays.binarySearch(dp, 0, right, num);
            if (index < 0) {
                index = -index - 1;
            }
            dp[index] = num;
            if (index == right) {
                right++;
            }
        }
        return right;
    }

    @Test
    public void testLengthOfLISLeet() {
        int[] arr = {0, 8, 4, 12, 2};
        System.out.println(lengthOfLISLeet(arr));
    }

    //[0,0,0,0,0,0]
    //[0,0,0,0,
    //
    public int lengthOfLISLeet(int[] nums) {
        //双指针
        int index = 1, n = nums.length;
        //n == 0
        if (n == 0) return 0;
        //dp:
        int[] dp = new int[n + 1];
        dp[index] = nums[0];
        for (int i = 1; i < n; ++i) {
            //更新右边
            if (nums[i] > dp[index]) {
                dp[++index] = nums[i];
                continue;
            }
            //更新左边
            binarySearch(dp, index, nums[i]);
        }
        return index;
    }


    private void binarySearch(int[] d, int right, int num) {
        //如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
        int l = 1, r = right, pos = 0;
        while (l <= r) {
            int mid = (l + r) >> 1;
            if (d[mid] < num) {
                pos = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        d[pos + 1] = num;
    }

    //递增的三元子序列

    //俄罗斯套娃信封问题

    //最长数对链

    //最长递增子序列的个数

    //两个字符串的最小ASCII删除和

    //368. 最大整除子集

}
