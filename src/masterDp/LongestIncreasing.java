package masterDp;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/17
 **/
public class LongestIncreasing {
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(Arrays.binarySearch(arr, 0, 7, 10));
        System.out.println(lengthOfLIS(arr));
        System.out.println(lengthOfLISDp(arr));
    }

    public static int length(int[] arr) {
        return 0;
    }

    //普通的dp:定义dp[i] 为考虑前i个元素，以第i个数字结尾的最长上升子序列的长度，注意nums[i]必须被选取。
    public static int lengthOfLISDp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //
        int[] dp = new int[nums.length];
        dp[0] = 1;
        //
        int maxAns = 1;
        //注意下路径
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    //
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            //
            maxAns = Math.max(maxAns, dp[i]);
        }
        return maxAns;
    }

    //方法二：贪心 + 二分查找:O(nlogn)
    public static int lengthOfLIS(int[] nums) {
        //表示长度为 ii 的最长上升子序列的末尾元素的最小值
        int[] dp = new int[nums.length];
        int right = 0;
        for (int num : nums) {
            int index = Arrays.binarySearch(dp, 0, right, num);
            //如果index小于0
            if (index < 0) {
                //why?
                index = -index - 1;
            }
            //
            dp[index] = num;
            if (index == right) {
                right++;
            }
        }
        System.out.println("dp:" + Arrays.toString(dp));
        return right;
    }

    public int lengthOfLISBinary(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
