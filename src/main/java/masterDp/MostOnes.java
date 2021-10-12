package masterDp;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author lufengxiang
 * @since 2021/7/13
 **/
public class MostOnes {


    public static void main(String[] args) {
        int[] arr = {1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1};
        System.out.println(findMaxConsecutiveOnes(arr));
        System.out.println(findMaxConsecutiveOnesDp(arr));
        String a = "aaccaaaaaaccccc";
        System.out.println(lengthOfLongestSubstringTwoDistinctBest(a));
    }

    //487. 最大连续1的个数 II
    //其实这个题目等价于：给定一个区间，该区间中最多只能包含1个0，求出该区间的最大长度。
    //如果题目是这样给的，相信对滑动窗口比较熟悉的xd们就能一眼看出这是可以用滑动窗口解决的问题。
    //我的思路：只用维护一个区间，这个区间中最多只包含一个0。
    //当区间中包含两个0的时候，直接移动左边界l直到区间只包含一个0即可。这个过程中去更新最大区间长度，最后就能得到答案。
    public int findMaxConsecutiveOnesEasy(int[] nums) {
        int res = 0, count = 0;
        for (int l = 0, r = 0; r < nums.length; r++) {
            if (nums[r] == 0) {
                count++;
                while (count > 1) {
                    count -= nums[l++] == 0 ? 1 : 0;
                }
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int l = 0, r = 0, zero = -1, n = nums.length, ans = 1;
        while (r < n) {
            if (nums[r] == 0) {
                if (zero != -1) {
                    ans = Math.max(ans, r - l);
                    l = zero + 1;
                }
                zero = r;
            }
            r++;
        }
        ans = Math.max(ans, r - l);
        return ans;
    }

    //1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1
    static int findMaxConsecutiveOnesDp(int[] nums) {
        int ans = 0, dp0 = 0, dp1 = 0;
        for (int num : nums) {
            if (num == 1) {
                dp1++;
                dp0++;
            } else {
                dp1 = dp0 + 1;
                dp0 = 0;
            }
            ans = Math.max(ans, Math.max(dp0, dp1));
        }
        return ans;
    }

    //159. 至多包含两个不同字符的最长子串
    //给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;
        int left = 0;
        int right = 0;
        HashMap<Character, Integer> hashmap = new HashMap<>();
        int maxLen = 2;
        while (right < n) {
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);
            if (hashmap.size() == 3) {
                int delIdx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(delIdx));
                left = delIdx + 1;
            }
            maxLen = Math.max(maxLen, right - left);
        }
        return maxLen;
    }

    //aaccaaaaaaccccc
    public static int lengthOfLongestSubstringTwoDistinctBest(String s) {
        int left = 0;
        int right = 0;
        int valid = 0;
        int len = 0;
        int[] window = new int[128];
        while (right < s.length()) {
            char c = s.charAt(right);
            if (window[c]++ == 0) {
                valid++;
            }
            len = Math.max(len, right - left);
            right++;
            while (valid > 2) {
                char d = s.charAt(left++);
                if (--window[d] == 0) {
                    valid--;
                }
            }
        }
        return Math.max(len, right - left);
    }
}
