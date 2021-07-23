package day;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/20
 **/
public class StrStr {
    public static void main(String[] args) {
        System.out.println(divide(40, 2));
    }

    //实现strStr:大海捞针.
    public int strStr(String haystack, String needle) {
        //
        int m = haystack.length();
        int n = needle.length();
        if (n == 0) return 0;
        int pn = 0;
        while (pn < m - n + 1) {
            //找到第一个等于needle的字符串.
            while (pn < m - n + 1 && haystack.charAt(pn) != needle.charAt(0)) pn++;
            //保存最大长度.
            int maxLen = 0;
            //needle指针
            int pl = 0;
            //相等:
            while (pl < n && pn < m && haystack.charAt(pn) == needle.charAt(pl)) {
                maxLen++;
                pl++;
                pn++;
            }
            //找到了.
            if (maxLen == n) return pn - n;
            //回溯:pn
            pn = pn - maxLen + 1;
        }
        return -1;
    }

    //1887:
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j)
            ans = Math.max(ans, nums[i] + nums[j]);
        return ans;
    }

    //两数相除:

    /**
     * 解题思路：这题是除法，所以先普及下除法术语
     * 商，公式是：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，是一种数学术语。
     * 在一个除法算式里，被除数、余数、除数和商的关系为：(被除数-余数)÷除数=商，记作：被除数÷除数=商...余数，
     * 进而推导得出：商×除数+余数=被除数。
     * <p>
     * 要求商，我们首先想到的是减法，能被减多少次，那么商就为多少，但是明显减法的效率太低
     * <p>
     * 那么我们可以用位移法，因为计算机在做位移时效率特别高，向左移1相当于乘以2，向右位移1相当于除以2
     * <p>
     * 我们可以把一个dividend（被除数）先除以2^n，n最初为31，不断减小n去试探,当某个n满足dividend/2^n>=divisor时，
     * <p>
     * 表示我们找到了一个足够大的数，这个数*divisor是不大于dividend的，所以我们就可以减去2^n个divisor，以此类推
     * <p>
     * 我们可以以100/3为例
     * <p>
     * 2^n是1，2，4，8...2^31这种数，当n为31时，这个数特别大，100/2^n是一个很小的数，肯定是小于3的，所以循环下来，
     * <p>
     * 当n=5时，100/32=3, 刚好是大于等于3的，这时我们将100-32*3=4，也就是减去了32个3，接下来我们再处理4，同样手法可以再减去一个3
     * <p>
     * 所以一共是减去了33个3，所以商就是33
     * <p>
     * 这其中得处理一些特殊的数，比如divisor是不能为0的，Integer.MIN_VALUE和Integer.MAX_VALUE
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative;
        negative = (dividend ^ divisor) < 0;//用异或来计算是否符号相异
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int result = 0;
        for (int i = 31; i >= 0; i--) {
            if ((t >> i) >= d) {//找出足够大的数2^n*divisor
                result += (1 << i);//将结果加上2^n
                t -= (d << i);//将被除数减去2^n*divisor
            }
        }
        return negative ? -result : result;//符号相异取反
    }

    //整数反转
    public int reverse(int x) {
        long n = 0;
        while (x != 0) {
            n = n * 10 + x % 10;
            x = x / 10;
        }
        return (int) n == n ? (int) n : 0;
    }
}
