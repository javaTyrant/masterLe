package day;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/7/22
 **/
@SuppressWarnings("unused")
public class Day0722 {
    //长度为 K 的无重复字符子串
    public static int numKLenSubstrNoRepeats(String s, int k) {
        //hash表
        int[] map = new int[26];
        char[] cs = s.toCharArray();
        int l = 0, r = 0, count = 0, res = 0;
        //r
        while (r < cs.length) {
            //太简洁了.
            count += (map[cs[r++] - 'a']++ == 0) ? 1 : 0;
            res += (count == k) ? 1 : 0;
            //
            count -= (r >= k && --map[cs[l++] - 'a'] == 0) ? 1 : 0;
        }
        return res;
    }

    //138. 复制带随机指针的链表
    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        while (p != null) {
            Node clone = new Node(p.val);
            map.put(p, clone);
            p = p.next;
        }
        p = head;
        while (p != null) {
            map.get(p).next = map.get(p.next);
            map.get(p).random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    //可获得的最大点数:剩余卡牌点数之和的最小值.
    //记数组 cardPoints 的长度为 n，由于只能从开头和末尾拿 k 张卡牌，所以最后剩下的必然是连续的 n−k 张卡牌。
    //我们可以通过求出剩余卡牌点数之和的最小值，来求出拿走卡牌点数之和的最大值。
    public int maxScore0(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }

    public int maxScore(int[] cardPoints, int k) {
        int curCount = 0;
        int maxCount;
        int i = 0;
        for (; i < k; i++) {
            curCount += cardPoints[i];
        }
        maxCount = curCount;
        if (k == cardPoints.length) {
            return maxCount;
        }
        int j = cardPoints.length;
        while (--i >= 0) {
            curCount -= cardPoints[i];
            curCount += cardPoints[--j];
            if (curCount > maxCount) {
                maxCount = curCount;
            }
        }
        return maxCount;
    }

    //最小窗口子序列
    public String minWindow(String s1, String s2) {
        return "";
    }

    @Test
    public void testLen() {
        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5));
    }

    @Test
    public void testMinSwaps() {
        int[] arr = {1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1};
        System.out.println(minSwaps(arr));
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

    //将 x 减到 0 的最小操作数
    public int minOperations(int[] nums, int x) {
        int maxPart = -1;
        int sum = Arrays.stream(nums).sum();
        int currentSum = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            currentSum += nums[right++];
            while (currentSum > sum - x && left < nums.length) {
                currentSum -= nums[left++];
            }
            if (currentSum == sum - x) {
                maxPart = Math.max(maxPart, right - left);
            }
        }
        return maxPart == -1 ? -1 : nums.length - maxPart;
    }

    //定长子串中元音的最大数目
    public int maxVowels(String s, int k) {
        int n = s.length();
        int vowel_count = 0;
        for (int i = 0; i < k; ++i) {
            vowel_count += isVowel(s.charAt(i));
        }
        int ans = vowel_count;
        for (int i = k; i < n; ++i) {
            vowel_count += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            ans = Math.max(ans, vowel_count);
        }
        return ans;
    }

    public int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }

    @Test
    public void testOwn() {
        String s = "havefunonleetcode";
        String s1 = "home";
        //System.out.println(numKLenSubstrNoRepeatsOwn(s, 5));
        System.out.println(numKLenSubstrNoRepeatsRight(s, 5));
    }

    public int numKLenSubstrNoRepeatsRight(String S, int K) {
        if (S.length() < K) {
            return 0;
        }
        int left = 0;
        int right = 0;
        char[] chs = S.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;
        while (right < S.length()) {
            map.put(chs[right], map.getOrDefault(chs[right], 0) + 1);
            //如果right - left + 1 == K
            if ((right - left + 1) == K) {
                //这个满足了才能++
                if (map.size() == K) {
                    count++;
                }
                //
                int val = map.get(chs[left]);
                //
                if (val == 1) {
                    map.remove(chs[left]);
                } else {
                    map.put(chs[left], val - 1);
                }
                left++;
            }
            //
            right++;
        }
        return count;
    }

    //S = "home", K = 5
    public int numKLenSubstrNoRepeatsOwn(String s, int k) {
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        int res = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            if (window.containsKey(c)) {
                Integer i = window.get(c);
                window.remove(c);
                l = i;
            } else {
                window.put(c, r + 1);
                if (r - l + 1 == k) {
                    System.out.println(s.substring(l, r + 1));
                    res++;
                    l++;
                }
            }
            r++;
        }
        return res;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
