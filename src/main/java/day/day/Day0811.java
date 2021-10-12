package day.day;

import org.junit.Test;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/8/11
 **/
public class Day0811 {
    @Test
    public void testNumberOfArithmeticSlices() {
        int[] arr = {1, 4, 3, 8, 5, 7, 12};
        System.out.println(numberOfArithmeticSlices(arr));
    }

    //446. 等差数列划分 II - 子序列
    public int numberOfArithmeticSlices(int[] nums) {
        int ans = 0;
        int n = nums.length;
        //
        Map<Long, Integer>[] f = new Map[n];
        for (int i = 0; i < n; ++i) {
            f[i] = new HashMap<>();
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                long d = (long) nums[i] - nums[j];
                //get
                int cnt = f[j].getOrDefault(d, 0);
                //累加答案
                ans += cnt;
                //放入hash表
                f[i].put(d, f[i].getOrDefault(d, 0) + cnt + 1);
            }
        }
        return ans;
    }

    @Test
    public void testLongestPalindromeSubseq() {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }

    //516. 最长回文子序列:对于一个子序列而言，如果它是回文子序列，并且长度大于 2，那么将它首尾的两个字符去除之后，它仍然是个回文子序列。
    //用dp[i][j] 表示字符串s的下标范围 [i, j]内的最长回文子序列的长度。
    public int longestPalindromeSubseq(String s) {
        //dp[i,j] i到 j 表示 i j 范围内的最长子序列的长度
        //最短为1
        char[] p = s.toCharArray();
        //为什么要加1,不要处理边界值了
        int[][] dp = new int[p.length + 1][p.length + 1];
        int n = p.length;
        //
        for (int i = 1; i <= n; ++i) dp[i][i] = 1;
        //如何处理边界.
        for (int i = n - 1; i > 0; --i) {
            for (int j = i + 1; j <= n; ++j) {
                //如果相等:都减1了.
                if (p[i - 1] == p[j - 1]) {
                    //长度加2了
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {//如果不等,取最大的.
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int[] d : dp) {
            System.out.println(Arrays.toString(d));
        }
        return dp[1][n];
    }

    //最长回文子串
    public String longestPalindrome(String s) {
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            int max = Math.max(expand(s, i, i), expand(s, i, i + 1));
            if (right - left < max) {
                left = i - (max - 1) / 2;
                right = i + max / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    private int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return right - left - 1;
    }

    public String longestPalindromeDp(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        int maxLen = 0;
        int start = 0;
        int end = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    end = j + 1;
                    start = i;
                }
            }
        }
        if (start == 0 && end == 0) {
            return s.substring(0, 1);
        }
        return s.substring(start, end);
    }

    @Test
    public void testCountStrings() {
        System.out.println(countSubstrings("aaa"));
    }

    //回文子串
    int num = 0;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i);//回文串长度为奇数
            count(s, i, i + 1);//回文串长度为偶数
        }
        return num;
    }

    public void count(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            num++;
            start--;
            end++;
        }
    }
    //统计不同回文子序列

    //875. 爱吃香蕉的珂珂
    //输入: piles = [3,6,7,11], H = 8
    //输出: 4
    public int minEatingSpeed(int[] piles, int H) {
        int lo = 1;
        int hi = 1_000_000_000;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (!possible(piles, H, mi))
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }

    // Can Koko eat all bananas in H hours with eating speed K?
    //以k的速度,H小时内能把香蕉吃完吗?
    public boolean possible(int[] piles, int H, int K) {
        int time = 0;
        //吃掉香蕉的时间小于警卫回来的时间
        for (int p : piles)
            time += (p - 1) / K + 1;
        return time <= H;
    }

    public int longestPalindromeSubseqOwn(String s) {
        int len = s.length();
        int[][] dp = new int[len + 1][len + 1];
        for (int i = 0; i <= len; i++) {
            dp[i][i] = 1;
        }
        return 0;
    }

    //774. 最小化去加油站的最大距离
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0, hi = 1e8;
        while (hi - lo > 1e-6) {
            double mi = (lo + hi) / 2.0;
            if (possible(mi, stations, K))
                hi = mi;
            else
                lo = mi;
        }
        return lo;
    }

    public boolean possible(double distance, int[] stations, int K) {
        int used = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            used += (int) ((stations[i + 1] - stations[i]) / distance);
        return used <= K;
    }

    @Test
    public void testMinStoneSum() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        priorityQueue.add(2);
        priorityQueue.add(3);
        priorityQueue.add(100);
        System.out.println(priorityQueue.poll());
    }
    //1962. 移除石子使总数最小:堆的使用.不是从两端.
    public int minStoneSum(int[] piles, int k) {
        // 大根堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for (int n : piles) {
            priorityQueue.add(n);
        }
        while (k > 0 && !priorityQueue.isEmpty()) {
            // 当前数组最大值
            int pop = priorityQueue.poll();
            // 最大值减去自身的一半放入大根堆
            int temp = pop - (pop >> 1);
            priorityQueue.add(temp);
            k--;
        }
        return priorityQueue.stream().reduce(Integer::sum).get();
    }

    @Test
    public void testMinSwap() {
        System.out.println(minSwaps("]]][[["));
    }
    //1963. 使字符串平衡的最小交换次数
    public int minSwaps(String s) {
        //cnt的意义是?
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == ']') {
                if (cnt > 0) {
                    cnt--;
                }
            } else {
                cnt++;
            }
        }
        return cnt % 2 + cnt / 2;
    }

    //
    @Test
    public void testPartition() {
        System.out.println(Arrays.deepToString(partition("google")));
    }
    private List<String[]> ans = new ArrayList<>();

    public String[][] partition(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                dp[i][j] = true;
            }
        }
        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i+1][j-1];
            }
        }
        dfs(s, 0, dp, new ArrayList<>());
        return ans.toArray(new String[ans.size()][]);
    }
    void dfs(String s, int step, boolean[][] dp, ArrayList<String> path) {
        if(step == s.length()) {
            ans.add(path.toArray(new String[0]));
            return;
        }
        for(int i = step; i < s.length(); i++) {
            if(dp[step][i]) {
                path.add(s.substring(step, i+1));
                dfs(s, i+1, dp, path);
                path.remove(path.size()-1);
            }
        }
    }
    //233. 数字 1 的个数
    public int countDigitOne(int n) {
        if (n < 1)
            return 0;
        int len = getLenOfNum(n);
        if (len == 1)
            return 1;
        int tmp = (int) Math.pow(10, len - 1);
        int first = n / tmp; // 获取n的最高位数字
        int firstOneNum = first == 1 ? n % tmp + 1 : tmp; // 获取n的最高位为1时有多少个数字
        int otherOneNUm = first * (len - 1) * (tmp / 10); // 在介于n % tmp到n之间的数字中，除了最高位为1，其余各个数字分别为1 的总数和
        return firstOneNum + otherOneNUm + countDigitOne(n % tmp);
    }
    private int getLenOfNum(int n) {
        int len = 0;
        while (n != 0) {
            len++;
            n /= 10;
        }
        return len;
    }

    @Test
    public void testCountDigitOne() {
        //10 * 1 + 1
        System.out.println(countDigitOne1(20));
    }
    public int countDigitOne1(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}
