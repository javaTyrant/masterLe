package masterDp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lufengxiang
 * @since 2021/7/11
 **/
public class PaintHouse {
    public static void main(String[] args) {
        int[][] arr = {
                {17, 2, 17},
                {16, 16, 5},
                {14, 3, 19}
        };
        System.out.println(minCostRaw(arr));
    }

    //如何推导出动态转移方程
    public static int minCostRaw(int[][] costs) {
        int row = costs.length;
        //dp的含义:
        int[][] dp = new int[row][3];
        if (row == 0) return 0;
        //初始化第一个房子
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < row; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + costs[i][2];
        }
        //最后一行的最小值才是答案
        return Math.min(Math.min(dp[row - 1][0], dp[row - 1][1]), dp[row - 1][2]);
    }

    //256. 粉刷房子
    public int minCost(int[][] costs) {
        int x = 0, y = 0, z = 0;
        for (int[] cost : costs) {
            int a = x, b = y, c = z;
            x = cost[0] + Math.min(b, c);
            y = cost[1] + Math.min(a, c);
            z = cost[2] + Math.min(a, b);
        }
        return Math.min(x, Math.min(y, z));
    }

    public int minCostII(int[][] costs) {
        if (costs.length == 0) return 0;
        int n = costs[0].length;
        int min = 0, secondMin = 0, color = -1;
        for (int[] cost : costs) {
            int preColor = color;
            int preMin = min;
            int preSecondMin = secondMin;
            min = Integer.MAX_VALUE;
            secondMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                int price = i == preColor ? preSecondMin + cost[i] : preMin + cost[i];
                if (price < secondMin) {
                    if (price < min) {
                        secondMin = min;
                        min = price;
                        color = i;
                    } else {
                        secondMin = price;
                    }
                }
            }
        }
        return min;
    }

    //买卖股票的最佳时期
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int p : prices) {
            if (p < min) min = p;
            res = Math.max(res, p - min);
        }
        return res;
    }

    //152. 乘积最大子数组
    public static int maxProduct(int[] nums) {
        //一个保存最大的，一个保存最小的。
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int num : nums) {
            //如果数组的数是负数，那么会导致最大的变最小的，最小的变最大的。因此交换两个的值。
            if (num < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * num, num);
            imin = Math.min(imin * num, num);
            max = Math.max(max, imax);
        }
        return max;
    }

    //309. 最佳买卖股票时机含冷冻期
    public int maxProfitII(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        //由于可以无限次交易，所以只定义两个维度，第一个维度是天数，第二个维度表示是否持有股票，0表示不持有，1表示持有，2表示过渡期
        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            //第i天不持有股票的情况有两种
            //a.第i-1天也不持有股票
            //b.第i-1天是过渡期
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            //第i天持有股票有两种情况
            //a.第i-1天也持有股票，第i天不操作，
            //b.第i-1天不持有股票，在第i天买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            //第i天是冷冻期只有一种情况，第i-1天持有股票且卖出
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        //最后最大利润为最后一天，不持有股票或者进入冷冻期的情况
        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }

    //714. 买卖股票的最佳时机含手续费
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        return cash;
    }

    //合并两个有序数组
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        //
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = (nums2[p2] > nums1[p1]) ? nums2[p2--] : nums1[p1--];
        }
        //[0] 0 [1] 1
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }

    //322. 零钱兑换
    private Integer res = Integer.MAX_VALUE;

    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        findCoins(coins, amount, coins.length - 1, 0);
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    //硬币集合,剩余的钱,索引,硬币的数量
    private void findCoins(int[] coins, int amount, int len, int count) {
        //base case
        if (amount <= 0) {
            res = Math.min(res, count);
            return;
        }
        if (len < 0) return;
        for (int i = amount / coins[len]; i >= 0 && i + count < res; i++) {
            findCoins(coins, amount - i * coins[len], len - 1, count + i);
        }
    }



    static class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if (a >= b) {
                System.out.println(0);
            } else {
                System.out.println(b - a + 1);
            }
        }
    }
}
