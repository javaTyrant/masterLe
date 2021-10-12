package masterContest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 最长公共上升子序列
 *
 * @author lufengxiang
 * @since 2021/6/28
 **/
public class Main {
    static int maxN = 3002;
    static int[] A = new int[maxN];
    static int[] B = new int[maxN];
    //dp意味着.
    static int[][] dp = new int[maxN][maxN];

    //4
    //2 2 1 3
    //2 1 2 3
    public static void main(String[] args) throws Exception {
        Scanner sys = new Scanner(System.in);
        //获取长度.
        int n = sys.nextInt();
        //只有两行数据.
        for (int i = 1; i <= n; i++) A[i] = sys.nextInt();
        for (int i = 1; i <= n; i++) B[i] = sys.nextInt();
        //开始处理.
        for (int j = 1; j <= n; j++) {
            //
            int maxV = 1;
            for (int i = 1; i <= n; i++) {  // A的循环在内层是方便更新maxV
                dp[i][j] = dp[i][j - 1];                                // 不包含b[j]
                //如果相等
                if (A[i] == B[j]) dp[i][j] = Math.max(dp[i][j], maxV); // 包含b[j], 为什么条件是相等呢？因为公共啊。。。
                //如果小于
                if (A[i] < B[j]) maxV = Math.max(dp[i][j - 1] + 1, maxV); // i 是每次遍历的值，j是不变的，即B[j]=A[last]，满足上升要保证<
            }
        }
        int res = 0;
        //不断的更新res
        for (int i = 1; i <= n; i++) res = Math.max(res, dp[i][n]);
        System.out.println(res);
    }


}
