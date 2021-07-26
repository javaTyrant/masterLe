package masterKth;

import org.junit.Test;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/23
 **/
public class Day0723 {
    //215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {
        return 0;
    }

    //输入: nums = [1,1,1,2,2,3], k = 2
    //输出: [1,2]
    @Test
    public void testTopKFrequent() {
        int[] arr = {2, 1, 2, 1, 1, 3, 3};
        System.out.println(Arrays.toString(topKFrequent(arr, 2)));
    }

    //前 K 个高频元素
    public static int[] topKFrequent(int[] nums, int k) {
        //
        ArrayList<int[]> rec = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        int count = 1;
        //这个统计牛逼
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                rec.add(new int[]{nums[i - 1], count});
                count = 1;
            } else {
                count++;
            }
        }
        //
        rec.add(new int[]{nums[nums.length - 1], count});
        //排序.
        rec.sort((a, b) -> b[1] - a[1]);
        //结果
        int[] ans = new int[k];
        //
        for (int i = 0; i < k; i++) {
            ans[i] = rec.get(i)[0];
        }
        //
        return ans;
    }

    //小顶堆.
    public int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数.
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(m -> m[1]));
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            //
            if (queue.size() == k) {
                //
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {//
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }

    //第三大的数

    //数据流中的第 K 大元素

    //最接近原点的 K 个点

    //1063. 有效子数组的数目:暴力法.
    public int validSubArrays(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] >= nums[i]) {
                    result++;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    @Test
    public void testCon() {
        System.out.println(confusingNumber(69));
    }

    public static boolean confusingNumber(int n) {
        char[] cs = String.valueOf(n).toCharArray();
        for (char c : cs) {
            if (!is(c - 48)) {
                return false;
            }
        }
        return true;
    }

    private static boolean is(int n) {
        return n == 1 || n == 0 || n == 6 || n == 8 || n == 9;
    }

    @Test
    public void testPurchasePlans() {

    }

    //LCP 28. 采购方案
    public int purchasePlans(int[] nums, int target) {
        int left, right;
        int n = 0;
        Arrays.sort(nums);
        for (left = 0, right = nums.length - 1; left < right; ) {
            if (nums[left] + nums[right] > target) right--;
            else {
                n = n + right - left;
                left++;
            }
        }
        return n % 1000000007;
    }
}
