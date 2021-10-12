package masterDp;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/12
 **/
public class CoinChange {
    //动态规划:核心问题,我们该这么样利用subproblems
    public int coinChangeDp(int[] coins, int amount) {
        int max = amount + 1;
        //fill最大值
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        //0要处理下
        dp[0] = 0;
        //终于搞懂为啥要双层遍历了,第一层:处理每个金额
        for (int i = 1; i <= amount; i++) {
            //第二层:选择用哪个硬币的最优解
            for (int coin : coins) {
                //如果硬币的值小于当前的索引,才计算,不然值就大了
                if (coin <= i) {
                    //取最小,+1是加的当前金额的数量
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        //大于说明还是amount+1没有处理过
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
