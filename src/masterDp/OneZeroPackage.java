package masterDp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lufengxiang
 * @since 2021/7/12
 **/
public class OneZeroPackage {
    public static void main(String[] args) throws Exception {
        int n = 4, m = 5;
        int[] v = {0, 1, 2, 3, 4};
        int[] w = {0, 2, 4, 4, 5};
        System.out.println(cal(n, m, v, w));
        System.out.println(calOpt(n, m, v, w));
    }

    /**
     * @param n 有几个
     * @param m 最大容量
     * @param v 体积数组
     * @param w 价值数组
     * @return 最大价值
     */
    public static int cal(int n, int m, int[] v, int[] w) {
        //前i个物品，背包容量j下的最优解（最大价值）
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                //  当前背包容量装不进第i个物品，则价值等于前i-1个物品
                if (j < v[i])
                    dp[i][j] = dp[i - 1][j];
                else // 能装，需进行决策是否选择第i个物品
                    //选和不选的最大值.
                    // dp[i-1][j]
                    // dp[i - 1][j - v[i]] + w[i]:是否选w[i]
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
            }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[n][m];
    }

    //优化成一维
    //f[j]定义：NN 件物品，背包容量j下的最优解。
    public static int calOpt(int n, int m, int[] v, int[] w) {
        //前i个物品，背包容量j下的最优解（最大价值）
        int[] f = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            //为什么要逆序?
            for (int j = m; j >= v[i]; j--)
                f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
        }
        return f[m];
    }

    private static void opt() {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];
        //从1开始
        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
        }
        reader.close();
        //读完

        //V:容量:容量为i的最大价值是?
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
    }


}
