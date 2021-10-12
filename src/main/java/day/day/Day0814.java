package day.day;

import org.junit.Test;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/8/14
 **/
public class Day0814 {
    @Test
    public void testUnhappy() {
        int[][] preferences = {{1, 2, 3}, {3, 2, 0}, {3, 1, 0}, {1, 2, 0}};
        int[][] pairs = {{0, 1}, {2, 3}};
        System.out.println(unhappyFriends(4, preferences, pairs));
    }

    //1583. 统计不开心的朋友
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        //order[i][j] 表示朋友 j在 i的朋友列表中的亲近程度下标。
        int[][] order = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                //
                order[i][preferences[i][j]] = j;
            }
        }
        for (int[] o : order) {
            System.out.println(Arrays.toString(o));
        }
        //配对数组
        int[] match = new int[n];
        for (int[] pair : pairs) {
            int person0 = pair[0], person1 = pair[1];
            match[person0] = person1;
            match[person1] = person0;
        }
        int unhappyCount = 0;
        //遍历n位朋友
        for (int x = 0; x < n; x++) {
            //x的配对对象
            int y = match[x];
            //
            int index = order[x][y];
            for (int i = 0; i < index; i++) {
                //
                int u = preferences[x][i];
                //
                int v = match[u];
                //
                if (order[u][x] < order[u][v]) {
                    unhappyCount++;
                    break;
                }
            }
        }
        return unhappyCount;
    }

    @Test
    public void testAddBinary() {
        //Assert.assertEquals("10",addBinary("1","1"));
        //Assert.assertEquals("10010",addBinary("1111","11"));
        System.out.println(addBinary("110010", "10111"));
    }

    //二进制加法
    public String addBinary(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        //111 1111
        StringBuilder sb = new StringBuilder();
        int i = aLen - 1, j = bLen - 1;
        int c = 0;
        while (i >= 0 || j >= 0) {
            int f = i >= 0 ? a.charAt(i) - '0' : 0;
            int s = j >= 0 ? b.charAt(j) - '0' : 0;
            sb.append((f + s + c) % 2);
            c = (f + s + c) / 2;
            i--;
            j--;
        }
        if (c > 0) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    //553. 最优除法
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/").append(nums[i]);
        }
        res.append(")");
        return res.toString();
    }

    @Test
    public void testEvalRPN() {
        String[] tokens = {"2", "1", "+", "3", "*"};
        System.out.println(evalRPN(tokens));
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(tokens2));
    }

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String t : tokens) {
            if (isNumber(t)) {
                stack.push(Integer.parseInt(t));
            } else {
                int t1 = stack.pop();
                int t2 = stack.pop();
                if (t.equals("+")) {
                    stack.push(t1 + t2);
                }
                if (t.equals("-")) {
                    stack.push(t1 - t2);
                }
                if (t.equals("*")) {
                    stack.push(t1 * t2);
                }
                if (t.equals("/")) {
                    stack.push(t2 / t1);
                }
            }
        }
        return stack.pop();
    }

    private boolean isNumber(String s) {
        return !(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"));
    }

    @Test
    public void testCalculate() {
        System.out.println(calculate("(1+2)-3"));
    }

    //只有+ -
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        // sign 代表正负
        int sign = 1, res = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            //
            if (Character.isDigit(ch)) {
                int cur = ch - '0';
                while (i + 1 < length && Character.isDigit(s.charAt(i + 1)))
                    cur = cur * 10 + s.charAt(++i) - '0';
                res = res + sign * cur;
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {
                stack.push(res);
                res = 0;
                stack.push(sign);
                sign = 1;
            } else if (ch == ')') {
                res = stack.pop() * res + stack.pop();
            }
        }
        return res;
    }

    @Test
    public void testCheckRecord() {
        System.out.println(checkRecord("PPALLP"));
        System.out.println(checkRecord("PPALLL"));
        System.out.println(checkRecord("PPLLALL"));
    }

    //551. 学生出勤记录 I
    public boolean checkRecord(String s) {
        int a = 0;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                a++;
            }
        }
        int l = 0;
        while (l < s.length()) {
            int lCount = 0;
            while (l < s.length() && s.charAt(l) == 'L') {
                lCount++;
                l++;
                if (lCount >= 3) {
                    return false;
                }
            }
            l++;
        }
        return a < 2;
    }

    public boolean checkRecord2(String s) {
        int absents = 0, lates = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == 'A') {
                absents++;
                if (absents >= 2) {
                    return false;
                }
            }
            if (c == 'L') {
                lates++;
                if (lates >= 3) {
                    return false;
                }
            } else {
                lates = 0;
            }
        }
        return true;
    }

    //13. 罗马数字转整数
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            //获取下一个数字,没有就是0
            int nextVal = i < s.length() - 1 ? map.get(s.charAt(i + 1)) : 0;
            //当前的数字
            int cur = map.get(s.charAt(i));
            //
            res += nextVal > cur ? -cur : cur;
        }
        return res;
    }

    @Test
    public void testMerge() {
        int[] nums1 = {1, 2, 3, 0};
        int[] nums2 = {2};
        merge(nums1, 3, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }

    //88. 合并两个有序数组.输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            nums1[p--] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
        }
        while (p2 >= 0) {
            nums1[p--] = nums2[p2--];
        }
    }
    //552. 学生出勤记录 II
    public int checkRecord(int n) {
        long[][] a = new long[][]{{1}, {1}, {0}, {1}, {0}, {0}};
        long[][] aMatrix = new long[][]{{1, 1, 1, 0, 0, 0}, {1, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0}};
        while (n > 0) {
            int m = n & 1;
            if (m == 1) {
                a = this.multipleMatrix(aMatrix, a);
            }
            aMatrix = this.multipleMatrix(aMatrix, aMatrix);
            n = n >> 1;
        }
        /**
         * 0 A0L0
         * 1 A0L1
         * 2 A0L2
         * 3 A1L0
         * 4 A1L1
         * 5 A1L2
         */
        return (int) a[3][0];

    }

    public long[][] multipleMatrix(long[][] a, long[][] b) {
        long mod = (long) 1e9 + 7;
        long c[][] = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }
}
