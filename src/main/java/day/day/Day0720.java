package day.day;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/20
 **/
@SuppressWarnings("unused")
public class Day0720 {
    public static void main(String[] args) {
        int[] arr = {1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage(arr, 4));
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5}, grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        System.out.println(maxSatisfied(customers, grumpy, X));
        String s1 = "abbaccab";
        String s2 = "ab";
        System.out.println(findAnagrams(s1, s2));
    }

    //子数组最大平均数 I [1,12,-5,-6,50,3], k = 4
    public static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        //i到k之和.
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i < n; i++) {
            //减去最左边加上最右边.
            sum = sum - nums[i - k] + nums[i];
            //取最大.
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0 * maxSum / k;
    }


    //爱生气的书店老板:customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int total = 0;
        int n = customers.length;
        //先统计正常情况下不得罪的客户.
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        //
        int increase = 0;
        //把得罪的客人数量统计出来.
        for (int i = 0; i < minutes; i++) {
            //0不生气,所以把生气的统计出来.
            increase += customers[i] * grumpy[i];
        }
        //
        int maxIncrease = increase;
        //
        for (int i = minutes; i < n; i++) {
            //这个公式如何推导出来的.
            increase = increase - customers[i - minutes] * grumpy[i - minutes] + customers[i] * grumpy[i];
            //取较大值.
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }

    //三页的解法.
    public int maxSatisfiedThree(int[] cs, int[] grumpy, int x) {
        int n = cs.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                ans += cs[i];
                cs[i] = 0;
            }
        }
        int max = 0, cur = 0;
        for (int i = 0, j = 0; i < n; i++) {
            cur += cs[i];
            if (i - j + 1 > x) cur -= cs[j++];
            max = Math.max(max, cur);
        }
        return ans + max;
    }

    //最小覆盖子串
    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();

    public String minWindow0(String s, String t) {
        int tLen = t.length();
        //把t放入map里.
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            //映射次数.
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        //需要多少临时变量呢?
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            //右边加加.
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            //
            while (check() && l <= r) {
                //find less
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                //左边加加
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        //cnt全部包含ori里的字符.
        for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    //替换后的最长重复字符
    private final int[] map = new int[26];

    public int characterReplacement(String s, int k) {
        if (s == null) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right;
        int historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            map[index]++;
            historyCharMax = Math.max(historyCharMax, map[index]);
            if (right - left + 1 > historyCharMax + k) {
                map[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }

    //无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int res = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (window.containsKey(c)) {
                start = Math.max(start, window.get(c));
            }
            window.put(c, end + 1);
            if (end - start + 1 > res) {
                res = end - start + 1;
            }
        }
        return res;
    }

    //长度最小的子数组
    public int minSubArrayLen(int target, int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }
        //i j
        int i = 0, j = 0;
        int sum = 0, minLen = Integer.MAX_VALUE;
        while (j < nums.length) {
            sum += nums[j++];
            //接着加
            if (sum < target) {
                continue;
            }
            while (sum >= target) {
                //放弃最左边
                sum -= nums[i];
                i++;
            }
            minLen = Math.min(minLen, j - i + 1);
        }
        return (minLen == Integer.MAX_VALUE) ? 0 : minLen;
    }

    //删除子数组的最大得分:求不含相同元素子数组的和
    //正整数数组 nums
    public int maximumUniqueSubarray(int[] nums) {
        //1.建窗口
        Map<Integer, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int res = 0, cur = 0;
        while (right < nums.length) {
            //2.右侧扩张
            int k = nums[right++];
            window.put(k, window.getOrDefault(k, 0) + 1);
            cur += k;
            //3.判断是否要左侧收缩
            while (window.get(k) > 1) {
                int d = nums[left++];
                window.put(d, window.get(d) - 1);
                cur -= d;
            }
            res = Math.max(res, cur);
        }
        return res;
    }

    public int maximumUniqueSubarrayBest(int[] nums) {
        //滑动窗口，求最大不重复连续子数组的和，思路，固定左指针，右移右指针，移动的过程中，将每个元素的出现次数记录下（同时将sum计算着），当遇到重复元素的时候，更新一次最大值，
        // 同时将左指针右移，右移过程中，将遇到重复元素之前的的元素都清除掉，清除的同时，更新sum，当清除完重复元素，继续往后走，直到右指针到头，结束
        int[] visited = new int[10001];
        int result = 0;
        int tmpSum = 0;
        int left = 0;
        visited[nums[left]] = 1;
        tmpSum += nums[left];
        int right = 1;
        while (right < nums.length) {
            int currNum = nums[right];
            visited[currNum] += 1;
            //遇到重复元素的时候，需要计算一次最大值，同时左指针右移
            if (visited[currNum] > 1) {
                result = Math.max(result, tmpSum);
                while (left <= right) {
                    int leftTmp = nums[left];
                    tmpSum -= leftTmp;
                    visited[leftTmp] -= 1;
                    left++;
                    if (visited[leftTmp] == 1) {
                        break;
                    }
                }
            }

            tmpSum += currNum;
            right++;
        }
        result = Math.max(result, tmpSum);
        return result;
    }

    //找到字符串中所有字母异位词
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s == null || s.length() == 0 || s.length() < p.length()) {
            return ans;
        }
        char[] strP = p.toCharArray();
        char[] strS = s.toCharArray();
        int[] chS = new int[123];
        int[] chP = new int[123];
        //放入hash表
        for (int i = 0; i < strP.length; i++) {
            chP[strP[i]]++;
            chS[strS[i]]++;
        }
        for (int i = 0; i <= strS.length - strP.length; i++) {
            boolean flag = true;
            if (i > 0) {
                chS[strS[i - 1]]--;
                chS[strS[i - 1 + strP.length]]++;
            }
            for (char j = 'a'; j <= 'z'; j++) {
                if (chS[j] != chP[j]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                ans.add(i);
            }
        }
        return ans;
    }

    //字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        int[] count1 = new int[26]; // s1每个字符出现的次数
        int[] count2 = new int[26]; // s2每个字符出现的次数
        for (int i = 0; i < s1.length(); i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        int[] diff = new int[26]; // s1和s2每个字符的数量差距
        for (int i = 0; i < diff.length; i++) {
            diff[i] = count2[i] - count1[i];
        }

        for (int i = s1.length(); i < s2.length(); i++) {
            if (isSame(diff)) {
                return true;
            }
            diff[s2.charAt(i - s1.length()) - 'a']--; // 去掉首个字符
            diff[s2.charAt(i) - 'a']++; // 添加最新的字符
        }
        return isSame(diff);
    }

    public boolean isSame(int[] diff) {
        for (int j : diff) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }

    //最大连续1的个数 II
    public int findMaxConsecutiveOnes(int[] nums) {
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

    //最大连续 1 的个数 III
    public int longestOnes(int[] A, int K) {
        int l = 0, r = 0, res = 0;
        while (r < A.length) {
            if (A[r] == 0) {
                if (K == 0) {
                    while (A[l] == 1) l++;
                    l++;
                } else {
                    K--;
                }
            }
            res = Math.max(res, ++r - l);
        }
        return res;
    }

    //尽可能使字符串相等
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] accDiff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            accDiff[i + 1] = accDiff[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int maxLength = 0;
        for (int i = 1; i <= n; i++) {
            int start = binarySearch(accDiff, i, accDiff[i] - maxCost);
            maxLength = Math.max(maxLength, i - start);
        }
        return maxLength;
    }

    public int binarySearch(int[] accDiff, int endIndex, int target) {
        int low = 0, high = endIndex;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (accDiff[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    //删掉一个元素以后全为 1 的最长子数组
    public int longestSubarray(int[] nums) {
        int res = 0;
        int left = 0, right = 0, count = 0;
        while (right < nums.length) {
            if (nums[right] == 0) ++count;
            if (count <= 1) {
                res = Math.max(res, right - left);
            } else {
                if (nums[left] == 0) count--;
                left++;
            }
            ++right;
        }
        return res;
    }

    //最长湍流子数组
    public int maxTurbulenceSize(int[] arr) {
        int res = 1, up = 1, down = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i]) {
                up = down + 1;
                down = 1;
            } else if (arr[i - 1] > arr[i]) {
                down = up + 1;
                up = 1;
            } else {
                up = 1;
                down = 1;
            }
            res = Math.max(res, Math.max(up, down));
        }
        return res;
    }

    //长度为 K 的无重复字符子串
    public int numKLenSubstrNoRepeats(String s, int k) {
        int[] map = new int[26];
        char[] cs = s.toCharArray();
        int l = 0, r = 0, count = 0, res = 0;
        while (r < cs.length) {
            count += map[cs[r++] - 'a']++ == 0 ? 1 : 0;
            res += count == k ? 1 : 0;
            count -= (r >= k && --map[cs[l++] - 'a'] == 0) ? 1 : 0;
        }
        return res;
    }

    //最少交换次数来组合所有的 1
    public int minSwaps(int[] data) {
        int oneCount = 0;
        for (int datum : data) {
            oneCount += datum;
        }
        int[] prefixSum = new int[data.length + 1];
        for (int i = 1; i < data.length + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + data[i - 1];
        }
        int maxSum = 0;
        for (int i = 0; i < data.length - oneCount + 1; i++) {
            int sum = prefixSum[i + oneCount] - prefixSum[i];
            maxSum = Math.max(maxSum, sum);
        }
        return oneCount - maxSum;
    }

    //健身计划评估
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        if (calories == null || calories.length == 0) {
            return 0;
        }
        int res = 0, sum = 0;
        for (int l = 0, r = 0; r < calories.length; r++) {
            sum += calories[r];
            if (r - l + 1 == k) {
                res += sum < lower ? -1 : 0;
                res += sum > upper ? 1 : 0;
                sum -= calories[l++];
            }
        }
        return res;
    }

    //K 连续位的最小翻转次数
    public int minKBitFlips(int[] nums, int k) {
        return 0;
    }

    //最小窗口子序列
    public String minWindow(String s1, String s2) {
        return null;
    }

    //子数组最大平均数 II:hard.
    public double findMaxAverage2(int[] nums, int k) {
        return 0.0;
    }
}
