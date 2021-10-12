package day;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/16
 **/
public class NQueen {
    public static void main(String[] args) {
        NQueen solution = new NQueen();
        //
        int[] q = new int[4];
        System.out.println(solution.generateBoard(q, 2));
        System.out.println("---------------------------------------");
        System.out.println(solution.countDistinct("aabbaba"));
        System.out.println("---------------------------------------");
        List<List<String>> lists = solution.solveNQueens(8);
        for (List<String> li : lists) {
            //System.out.println(li);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> res = new ArrayList<>();
        backtrack(queens, n, 0, 0, 0, 0, res);
        return res;
    }

    public void backtrack(int[] queens, int n, int cols, int dia1, int dia2, int row, List<List<String>> res) {
        //base case: row == n
        if (row == n) {
            res.add(generateBoard(queens, n));
            return;
        }
        int avaPos = ((1 << n) - 1) & (~(cols | dia1 | dia2));
        while (avaPos != 0) {
            int curPos = avaPos & (-avaPos);
            avaPos = avaPos & (avaPos - 1);
            int col = Integer.bitCount(curPos - 1);
            queens[row] = col;
            backtrack(queens, n, (cols | curPos), (dia1 | curPos) << 1, (dia2 | curPos) >> 1, row + 1, res);
            queens[row] = -1;
        }
    }

    //打印棋盘
    public List<String> generateBoard(int[] queens, int n) {
        //
        List<String> list = new ArrayList<>();
        //n控制
        for (int i = 0; i < n; i++) {
            char[] c = new char[n];
            Arrays.fill(c, '.');
            c[queens[i]] = 'Q';
            list.add(new String(c));
        }
        return list;
    }

    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;
        int max = 0;
        for (int n : nums) {
            //如果之前的和小于0,那么一定不能选.
            max = Math.max(0, max) + n;
            //
            res = Math.max(max, res);
        }
        return res;
    }

    //1698. 字符串的不同子字符串个数:暴力打法.
    public int countDistinct(String str) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i <= str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                result.add(str.substring(i, j));
            }
        }
        return result.size();
    }


}
