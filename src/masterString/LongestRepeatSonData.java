package masterString;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lufengxiang
 * @since 2021/7/5
 **/
//Maximum Length of Repeated Subarray
public class LongestRepeatSonData {
    long P = 131L;
    long[] h1;
    long[] h2;
    long[] p;
    int n;
    int m;

    public int findLength(int[] nums1, int[] nums2) {
        //假设 最长重复子数组 的长度为mid ,则枚举nums1中的所有mid长度的子数组hash ,枚举nums2中的所有mid长度的子数组hash.如果存在数组的hash值相同，则说明mid即为解，否则二分求mid
        n = nums1.length;
        m = nums2.length;
        h1 = new long[n + 1];
        h2 = new long[m + 1];
        for (int i = 1; i <= n; i++) h1[i] = h1[i - 1] * P + nums1[i - 1];
        for (int i = 1; i <= m; i++) h2[i] = h2[i - 1] * P + nums2[i - 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) p[i] = p[i - 1] * P;
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            //System.out.println(" ord mid: " + mid);
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    boolean check(int mid) {
        Set<Long> set = new HashSet<>();
        for (int i = mid; i <= n; i++) {
            set.add(get(h1, i, i - mid + 1));
        }
        for (int i = mid; i <= m; i++) {
            if (set.contains(get(h2, i, i - mid + 1))) return true;
        }
        return false;
    }

    long get(long[] h, int r, int l) {
        return h[r] - p[r - l + 1] * h[l - 1];
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 3, 3};
        int[] b = {0, 1, 2, 3, 4};
        LongestRepeatSonData solution = new LongestRepeatSonData();
        System.out.println(solution.findLength(a, b));
    }
}
