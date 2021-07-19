package masterDp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lufengxiang
 * @since 2021/7/17
 **/
public class FlipCoins {
    //请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。
    public static void main(String[] args) {
        double[] arr = {0.5, 0.5, 0.5, 0.5, 0.5};
        System.out.println(probabilityOfHeads(arr, 3));
    }

    //1230. 抛掷硬币
    public static double probabilityOfHeads(double[] prob, int target) {
        //dp[i][j] 表示到前i个对象选择了j个的概率
        double[][] dp = new double[prob.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= prob.length; i++) {
            for (int j = 0; j <= Math.min(i, target); j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] * (1 - prob[i - 1]);
                } else {
                    dp[i][j] = dp[i - 1][j - 1] * prob[i - 1] + dp[i - 1][j] * (1 - prob[i - 1]);
                }
            }
        }
        for (double[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[prob.length][target];
    }

    //1746. 经过一次操作后的最大子数组和
    public int maxSumAfterOperation(int[] nums) {
        int n = nums.length, res = Integer.MIN_VALUE;
        int[][] f = new int[n][2];
        f[0][0] = nums[0];
        f[0][1] = nums[0] * nums[0];
        res = Math.max(res, Math.max(f[0][0], f[0][1]));
        for (int i = 1; i < n; i++) {
            f[i][1] = Math.max(f[i - 1][1] + nums[i], Math.max(0, f[i - 1][0]) + nums[i] * nums[i]);
            f[i][0] = Math.max(0, f[i - 1][0]) + nums[i];
            res = Math.max(res, Math.max(f[i][0], f[i][1]));
        }
        return res;
    }

    //两个字符串的最小ASCII删除和
    public int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i + 1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j + 1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j] + s1.codePointAt(i),
                            dp[i][j + 1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    //1048. 最长字符串链
    public int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        int n = words.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isPredecessor(words[i], words[j])) {
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                }
            }
        }
        int res = 0;
        for (int i : dp) res = Math.max(res, i);
        return res + 1;
    }

    public boolean isPredecessor(String a, String b) {
        if (b.length() - a.length() != 1) return false;
        int flag = 0;
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else {
                flag++;
                j++;
                if (flag > 1) return false;
            }
        }
        return true;
    }

    //673. 最长递增子序列的个数
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int[] count = new int[nums.length];
        int max = 0, result = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        count[i] = count[j];
                        dp[i] = dp[j] + 1;
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                }
            }
            count[i] = Math.max(count[i], 1);
            if (dp[i] > max) {
                result = count[i];
                max = dp[i];
            } else if (dp[i] == max) {
                result += count[i];
            }
        }
        return result;
    }
}
