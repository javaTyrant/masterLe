package day.day;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author lufengxiang
 * @since 2021/11/19
 **/
public class Day1119 {
    //岛屿数量
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;
        //剪枝,仔细分析一下就出来了,就是几个边界值的判断嘛
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }
        //置零
        grid[r][c] = '0';
        //上下左右,顺序不影响
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        //先判空
        if (grid == null || grid.length == 0) {
            return 0;
        }
        //row
        int nr = grid.length;
        //column
        int nc = grid[0].length;
        //res
        int numIslands = 0;
        //经典的两层遍历
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                //==1再dfs
                if (grid[r][c] == '1') {
                    //++
                    ++numIslands;
                    dfs(grid, r, c);
                }
            }
        }

        return numIslands;
    }

    //397. 整数替换
    public int integerReplacement(int n) {
        return 0;
    }

    int n = 4, k = 13;

    int[] a = {1, 2, 4, 7};

    boolean dfs(int i, int sum) {
        if (i == n) return sum == k;
        if (dfs(i + 1, sum)) return true;
        return dfs(i + 1, sum + a[i]);
    }

    void solve() {
        if (dfs(0, 0)) {
            System.out.println("yes");
        } else {
            System.out.println("No");
        }
    }

    @Test
    public void testSolve() {
        solve();
    }

    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        //
        if (s.equals(goal)) {
            return !once(s);
        }
        if (!ifSame(s, goal)) {
            return false;
        }
        //所有字母出现的次数一样
        return same(s, goal);
    }

    private boolean ifSame(String s, String goal) {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> map1 = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Character c : goal.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        return map.equals(map1);
    }

    //只能有一个位置不一样
    private boolean same(String s, String goal) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != goal.charAt(i)) {
                count++;
            }
        }
        return count <= 2;
    }

    private boolean once(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testBuddyStrings() {
        System.out.println(buddyStrings("ab", "ba"));
        System.out.println(buddyStrings("ab", "ab"));
        System.out.println(buddyStrings("abb", "abb"));
        System.out.println(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        System.out.println(buddyStrings("abcd", "badc"));
        System.out.println(buddyStrings("abcaa", "abcbb"));
    }

    public boolean buddyStringsBest(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                if (count[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < goal.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else
                        return false;
                }
            }

            return (second != -1 && s.charAt(first) == goal.charAt(second) &&
                    s.charAt(second) == goal.charAt(first));
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indgree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] cp : prerequisites) {
            indgree[cp[0]]++;
            adj.get(cp[1]).add(cp[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (indgree[i] == 0) queue.add(i);
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            numCourses--;
            for (int c : adj.get(cur)) {
                if (--indgree[c] == 0) queue.offer(c);
            }
        }
        return numCourses == 0;
    }
}
