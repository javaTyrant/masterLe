package masterDp;

/**
 * @author lufengxiang
 * @since 2021/7/9
 **/
public class ClimbStairs {
    public static void main(String[] args) {
        System.out.println(climbStairs(4));
        System.out.println(fib(4));
        int[] arr = {2, 2, -3, 4, 1};
        System.out.println(maxSubArray(arr));
    }

    //每次爬1个或者2个.有多少种方式能排到楼梯上
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    public static int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;
        int a = 1, b = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = a + b;
            a = b;
            b = res;
        }
        return res;
    }

    //使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        //0和1都要赋值。从哪开始选太重要了。
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            //转移方程
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }

    //53. 最大子序和
    public static int maxSubArray(int[] nums) {
        //
        int res = Integer.MIN_VALUE;
        //维护
        int max = 0;
        for (int num : nums) {
            //
            max = Math.max(max, 0) + num;
            //
            res = Math.max(res, max);
        }
        return res;
    }

    //213. 打家劫舍 II
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp1 = new int[nums.length];
        int[] dp2 = new int[nums.length];
        dp1[1] = nums[0]; //从第1个房屋开始偷
        dp2[1] = nums[1]; //从第2个房屋开始偷
        for (int i = 2; i < nums.length; i++) {
            dp1[i] = Math.max(dp1[i - 2] + nums[i - 1], dp1[i - 1]);
            dp2[i] = Math.max(dp2[i - 2] + nums[i], dp2[i - 1]);
        }
        return Math.max(dp1[nums.length - 1], dp2[nums.length - 1]);
    }

    //198. 打家劫舍
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length <= 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        //从2开始.
        for (int i = 2; i < nums.length; i++) {
            //如何选择.
            dp[i] = Math.max((nums[i] + dp[i - 2]), dp[i - 1]);
        }
        return dp[nums.length - 1];
    }
}
