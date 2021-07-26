package day.day;

import org.junit.Test;
import util.Link;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/21
 **/
public class Day0721 implements Link {
    public static void main(String[] args) {
        //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome(",."));
        int[] arr = {-2, 0, 1, 3};
        threeSumSmaller(arr, 2);
        int[] arr1 = {-4, -2, 2, 4};
        System.out.println(Arrays.toString(sortTransformedArray(arr1, 1, 3, 5)));
    }

    //剑指 Offer 52. 两个链表的第一个公共节点
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while (p1 != p2) {
            p1 = (p1 == null) ? headB : p1.next;
            p2 = (p2 == null) ? headA : p2.next;
        }
        return p1;
    }

    //验证回文串:给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !Character.isLetter(s.charAt(i)) && !Character.isDigit(s.charAt(i))) {
                i++;
            }
            while (j > 0 && !Character.isLetter(s.charAt(j)) && !Character.isDigit(s.charAt(j))) {
                j--;
            }
            if (i < j && s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int r = nums[i] + nums[left] + nums[right];
                if (r == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (r > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }

    //盛最多水的容器
    public int maxArea(int[] height) {
        //左右双指针.
        int left = 0, right = height.length - 1;
        //res = 0
        int res = 0;
        while (left < right) {
            //取最小的高度.
            int h = Math.min(height[left], height[right]);
            //取较大的面积.
            res = Math.max(res, h * (right - left));
            //短板原理:
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    //反转字符串
    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp;
            tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
            i++;
            j--;
        }
    }

    //反转字符串中的元音字母
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < arr.length && judge(arr[i])) i++;
            while (j >= 0 && judge(arr[j])) j--;
            if (i >= j) {
                break;
            }
            char tmp = arr[j];
            arr[j--] = arr[i];
            arr[i++] = tmp;
        }
        return new String(arr);
    }

    public boolean judge(char c) {
        return c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u' && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U';
    }

    //接雨水
    public int trap(int[] height) {
        //
        int res = 0, maxLeft = 0, maxRight = 0;
        int left = 0, right = height.length - 1;
        //
        while (left < right) {
            //如果左边的最大值小于右边的:处理短的
            if (maxLeft < height[right]) {
                //更新maxLeft
                if (maxLeft < height[left]) {
                    maxLeft = height[left];
                } else {//计算
                    res += maxLeft - height[left];
                    left++;
                }
            } else {//如果右边的最大值
                if (maxRight < height[right]) {
                    maxRight = height[right];
                } else {
                    res += maxRight - height[right];
                    right--;
                }
            }
        }
        return res;
    }

    //单调栈解法:维护递减的.
    public int trapStack(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                //
                int currWidth = i - left - 1;
                //
                int currHeight = Math.min(height[left], height[i]) - height[top];
                //
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    //最接近的三数之和:给定一个包括n 个整数的数组nums和 一个目标值target。
    //找出nums中的三个整数，使得它们的和与target最接近。返回这三个数的和。假定每组输入只存在唯一答案。
    public int threeSumClosest(int[] nums, int target) {
        int closest = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //双指针
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                int min = sum - target;
                if (sum < target) {
                    j++;
                } else if (sum > target) {
                    k--;
                } else {
                    return sum;
                }
                //更新最小值:要更新两个值.
                if (Math.abs(min) < closest) {
                    closest = Math.abs(min);
                    res = sum;
                }
            }
        }
        return res;
    }


    //四数之和
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        int length = nums.length;
        if (length < 4) return list;
        Arrays.sort(nums);
        for (int i = 0; i < length - 3; i++) {
            // 判重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 最小值都比target大，则四个数相加不可能等于target
            if (nums[i] * 4 > target) {
                return list;
            }

            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) {
                // 判重
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if (nums[i] + nums[j] * 3 > target) {
                    break;
                }

                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int l = j + 1;
                int r = length - 1;
                while (l < r) {
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target) {
                        l++;
                    } else if (sum > target) {
                        r--;
                    } else {
                        list.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;
                        while (l < r && nums[l] == nums[l - 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r + 1]) {
                            r--;
                        }
                    }
                }
            }
        }
        return list;
    }

    //658.找到 K 个最接近的元素:从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int size = arr.length;
        int left = 0;
        int right = size - 1;
        int removeNums = size - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    //较小的三数之和
    public static int threeSumSmaller(int[] nums, int target) {
        //先对数组排序，然后固定一个元素，其余两个元素采用双指针法
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                //如果当前元素和小于target
                if (sum < target) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    //解题思路就是利用 抛物线和直线本身的特性进行求解,所以如果想看懂需要看懂下面的几个概念,百度百科在下面贴的有
    //首先了解,抛物线概念, 极点概念,斜截式
    //抛物线概念
    //极点概念
    //斜截式概念
    //分三种情况进行处理:
    //1, a = 0, bx + c是一条直线 学名:
    //b是斜率, 斜率大于零则x值越大y也越大, 斜率小于0则x越大,y值越小.
    //如果是抛物线则必然存在一个极点(极大值/极小值), 极点就是导数为0的点, f(x)导数: 2ax+b = 0 ==> x = -b/2a
    //2, a > 0, 则抛物线是一条向上的抛物线,存在极小值, x坐标点到极点的绝对值越小则f(x) 越小,反则反之.
    //3, a < 0, 则抛物线是一条向下的抛物线,存在极大值, x坐标点到极点的绝对值越小则f(x) 越大,反则反之.
    //有序转化数组:要注意，返回的这个数组必须按照 升序排列，并且我们所期望的解法时间复杂度为 O(n)
    // ax^2 + bx + c
    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int idx = a > 0 ? n - 1 : 0;
        int L = 0, R = n - 1;
        while (L <= R) {
            int v1 = a * nums[L] * nums[L] + b * nums[L] + c;
            int v2 = a * nums[R] * nums[R] + b * nums[R] + c;
            //a如果是负数
            if (a <= 0) {    // 从两边找小的
                //
                if (v1 < v2) {
                    res[idx] = v1;
                    L++;
                } else {
                    res[idx] = v2;
                    R--;
                }
                idx++;
            } else {        // 找大的
                if (v1 > v2) {
                    res[idx] = v1;
                    L++;
                } else {
                    res[idx] = v2;
                    R--;
                }
                idx--;
            }
        }
        return res;
    }

    //比较含退格的字符串
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    public String build(String str) {
        StringBuffer ret = new StringBuffer();
        int length = str.length();
        for (int i = 0; i < length; ++i) {
            char ch = str.charAt(i);
            if (ch != '#') {
                ret.append(ch);
            } else {
                if (ret.length() > 0) {
                    ret.deleteCharAt(ret.length() - 1);
                }
            }
        }
        return ret.toString();
    }

    //数组中的最长山脉
    public int longestMountain(int[] A) {
        if (A == null || A.length <= 2) {
            return 0;
        }
        int res = 0;
        //从1开始
        for (int i = 1; i < A.length - 1; i++) {
            if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
                //双指针
                int l = i - 1;
                int r = i + 1;
                while (l > 0 && A[l - 1] < A[l]) {
                    l--;
                }
                while (r < A.length - 1 && A[r + 1] < A[r]) {
                    r++;
                }
                res = Math.max(res, (r - l + 1));
            }
        }
        return res;
    }

    //救生艇:第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
    //每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
    //返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        int right = people.length - 1;
        int left = 0;
        Arrays.sort(people);
        while (left <= right) {
            if (left == right) {
                res++;      // 只剩下最后一个,直接一个走,结束
                break;
            }
            if (people[left] + people[right] > limit) {
                res++;
                right--;        // 先载最重的, 而且最小的也无法一起载,那么就最重的单独走
            } else {
                res++;
                right--;        // 最重的与最轻的一起走
                left++;
            }
        }
        return res;
    }

    //长按键入
    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }

    //有序数组的平方:[-4,-1,0,3,10]:优美!!!
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        //返回值
        int[] ans = new int[n];
        //
        for (int i = 0, j = n - 1, pos = n - 1; i <= j; ) {
            //从两端选个最大的
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                //
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }

    //小于 K 的两数之和
    public int twoSumLessThanK(int[] A, int k) {
        if (A == null || A.length < 2) return -1;
        Arrays.sort(A);
        int lo = 0, hi = A.length - 1;
        int curSum = -1;
        while (lo < hi) {
            if (A[lo] + A[hi] >= k) {
                hi--;
            } else {
                curSum = Math.max(A[lo] + A[hi], curSum);
                ++lo;
            }
        }
        return curSum;
    }

    //安排会议日程
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        // 给两个客户按照开始时间排序他们的时间表
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
        int idx1 = 0, idx2 = 0;
        // 遍历两个客户的时间表
        while (idx1 < slots1.length && idx2 < slots2.length) {
            // 当前遍历到的 slots 的末尾值
            int ed1 = slots1[idx1][1];
            int ed2 = slots2[idx2][1];
            // 当前 slots 的最晚开始时间
            int st = Math.max(slots1[idx1][0], slots2[idx2][0]);// 最晚开始时间
            if (ed1 > ed2) {    // 1 的时间比较宽
                if (ed2 - st >= duration) {
                    return Arrays.asList(st, st + duration);
                }
                idx2++;
            } else { // 2 的时间比较宽
                if (ed1 - st >= duration) {
                    return Arrays.asList(st, st + duration);
                }
                idx1++;
            }
        }
        return Collections.emptyList();
    }

    //插入排序:如何证明正确性呢?
    public void insertSort(int[] arr) {
        if (arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] < key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    @Test
    public void testInsertSort() {
        int[] arr = {3, 1, 2, 5, 1,4};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
