package day;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/14
 **/
public class AbsoluteSum {
    public static void main(String[] args) {
        AbsoluteSum solution = new AbsoluteSum();
        int[] a = {1, 10, 4, 4, 2, 7};
        int[] b = {9, 3, 5, 1, 7, 4};
        System.out.println(solution.minAbsoluteSumDiff(a, b));

        int[] a1 = {1, 10, 4, 4, 2, 7};
        int[] b1 = {9, 3, 5, 1, 7, 4};
        System.out.println(solution.minAbsoluteSumDiffLess(a1, b1));
        int c = 2147483647;
        int[] d = {2, 0, 0};
        System.out.println("xx:" + superPow(c, d));

        System.out.println(mySqrt(4));
        System.out.println(mySqrt(16));
        System.out.println(mySqrt(8));
    }

    int mod = (int) 1e9 + 7;

    public int minAbsoluteSumDiffLess(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] sorted = nums1.clone();
        Arrays.sort(sorted);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i], b = nums2[i];
            if (a == b) continue;
            int x = Math.abs(a - b);
            sum += x;
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r + 1 >> 1;
                if (sorted[mid] <= b) l = mid;
                else r = mid - 1;
            }
            int nd = Math.abs(sorted[r] - b);
            if (r + 1 < n) nd = Math.min(nd, Math.abs(sorted[r + 1] - b));
            if (nd < x) max = Math.max(max, x - nd);
        }
        return (int) ((sum - max) % mod);
    }

    //你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
    //1818. 绝对差值和nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
    //解题思路:
    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        final int MOD = 1000000007;
        int n = nums1.length;
        //nums1复制给rec
        int[] rec = new int[n];
        System.arraycopy(nums1, 0, rec, 0, n);
        //排序
        Arrays.sort(rec);
        int sum = 0, maxn = 0;
        for (int i = 0; i < n; i++) {
            //观察该式，我们只需要找到和nums2[i]尽可能接近的nums1[j]即可
            int diff = Math.abs(nums1[i] - nums2[i]);
            sum = (sum + diff) % MOD;
            int j = binarySearch(rec, nums2[i]);
            if (j < n) {
                maxn = Math.max(maxn, diff - (rec[j] - nums2[i]));
            }
            if (j > 0) {
                maxn = Math.max(maxn, diff - (nums2[i] - rec[j - 1]));
            }
        }
        return (sum - maxn + MOD) % MOD;
    }

    //
    public int binarySearch(int[] rec, int target) {
        int low = 0, high = rec.length - 1;
        if (rec[high] < target) {
            return high + 1;
        }
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (rec[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //50. Pow(x, n) 快速幂.
    public double myPow(double x, int n) {
        double res = 1;
        //n可能是负数,所以!=0
        for (int i = n; i != 0; i /= 2) {
            //奇数
            if ((i & 1) != 0) {
                res *= x;
            }
            //偶数
            x = x * x;
        }
        return n < 0 ? 1 / res : res;
    }

    static int mo = 1337;

    //这个思路太牛逼了吧,干
    public int superPowRight(int a, int[] b) {
        if (a == 1) return 1;
        if (a > 1337) {
            a = a % 1337;
        }
        int key = 0;
        for (int j : b) {
            key = (key * 10 + j) % 1140;
        }
        int res = a;
        for (int i = 0; i < key - 1; i++) {
            res = res * a % 1337;
        }
        return res;
    }

    //372. 超级次方
    public static int superPow(int a, int[] b) {
        if (a == 1) return 1;
        int len = b.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            int pow = (int) Math.pow(10, (len - i - 1));
            if (b[i] == 0) {
                continue;
            }
            res += quick(a, b[i] * pow);
        }
        return res;
    }

    public static int quick(int x, int n) {
        int res = 1;
        //n可能是负数,所以!=0
        for (int i = n; i != 0; i /= 2) {
            //奇数
            if ((i & 1) != 0) {
                res = res * x % mo;
            }
            //偶数
            x = (x % mo) * (x % mo);
        }
        return res;
    }

    //
    public int mySqrt1(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    //x的平方根
    public static int mySqrt(int x) {
        int i = 0;
        int j = x;
        while (i <= j) {
            int mid = (j - i) / 2 + i;
            if (mid * mid == x) {
                return mid;
            } else if (mid * mid > x) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    private int guess(int n) {
        return 0;
    }

    //
    public int guessNumber(int n) {
        int i = 1;
        int j = n;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (guess(mid) == 0) {
                return mid;
            } else if (guess(mid) > 0) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return -1;
    }
//搜索旋转排序数组
}
