package day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class SlidingWindow {
    //3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int ans = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            //尾指针
            char c = s.charAt(end);
            if (window.containsKey(c)) {
                start = Math.max(start, window.get(c));
            }
            //
            ans = Math.max(ans, end - start + 1);
            //
            window.put(c, end + 1);
        }
        return ans;
    }

    //159. 至多包含两个不同字符的最长子串
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        int l = 0, r = 0;
        int[] f = new int[128];
        int count = 0;
        int res = 0;
        while (r < n) {
            char c = s.charAt(r);
            f[c]++;
            if (f[c] == 1) {
                count++;
            }
            r++;
            //
            while (count == 3) {
                char c1 = s.charAt(l++);
                f[c1]--;
                if (f[c1] == 0) {
                    count--;
                }
            }
            //
            res = Math.max(res, r - l);
        }
        return res;
    }

    //自己写.
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        int l = 0, r = 0;
        int[] f = new int[128];
        int count = 0;
        int res = 0;
        while (r < n) {
            char c = s.charAt(r);
            f[c]++;
            if (f[c] == 1) {
                count++;
            }
            r++;
            //
            while (count == k + 1) {
                char c1 = s.charAt(l++);
                f[c1]--;
                if (f[c1] == 0) {
                    count--;
                }
            }
            //
            res = Math.max(res, r - l);
        }
        return res;
    }

    //992. K 个不同整数的子数组
    public int subarraysWithKDistinct(int[] A, int K) {
        int len = A.length;
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (right < len && map.size() < K) {
            map.put(A[right], map.getOrDefault(A[right], 0) + 1);
            right++;
        }
        if (right == len && map.size() < K) {
            return 0;
        }
        int res = 0;
        while (right < len) {
            int r = right;
            while (r < len && map.containsKey(A[r])) {
                r++;
            }
            int rCount = r - right + 1;
            res += rCount;
            if (map.get(A[left]) == 1) {
                map.remove(A[left]);
            } else {
                map.put(A[left], map.get(A[left]) - 1);
            }
            left++;
            if (map.size() < K) {
                while (right < len && map.size() < K) {
                    map.put(A[right], map.getOrDefault(A[right], 0) + 1);
                    right++;
                }
            }
        }
        while (map.size() == K) {
            if (map.get(A[left]) == 1) {
                map.remove(A[left]);
            } else {
                map.put(A[left], map.get(A[left]) - 1);
            }
            left++;
            res++;
        }
        return res;
    }

    //93. 复原 IP 地址 s = "25525511135"
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, res, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(String s, List<String> res, List<String> cur, int pos) {
        //base case
        if (cur.size() == 4) {
            //ip找完了
            if (pos == s.length()) {
                res.add(String.join(".", cur));
            }
            return;
        }
        //i要从1开始:为什么不从0开始.i表示选一个两个还是三个.
        for (int i = 1; i <= 3; i++) {
            if (pos + i > s.length()) break;
            String seg = s.substring(pos, pos + i);
            //以0开始就不能大于2位 长度位3就不能大于255.
            if (seg.startsWith("0") && seg.length() > 1
                    || (seg.length() == 3 && Integer.parseInt(seg) > 255)) {
                continue;
            }
            //add.
            cur.add(seg);
            backtrack(s, res, cur, pos + i);
            //remove.
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        SlidingWindow solution = new SlidingWindow();
        System.out.println(solution.restoreIpAddresses("25525511135"));
    }
}
