package day;

import org.junit.Test;

/**
 * @author lufengxiang
 * @since 2021/7/23
 **/
public class Day0723 {
    @Test
    public void testIsCovered() {
        int[][] ranges = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(isCovered(ranges, 2, 5));
    }

    //1893. 检查是否区域内所有整数都被覆盖
    public static boolean isCovered(int[][] ranges, int left, int right) {
        int[] covered = new int[52];
        //思想:ranges区间覆盖到的值始终大于0
        for (int[] range : ranges) {
            covered[range[0]]++;
            covered[range[1] + 1]--;
        }
        //更新整个桶
        for (int i = 1; i < covered.length; i++) {
            covered[i] += covered[i - 1];
        }
        for (int i = left; i <= right; i++) {
            if (covered[i] == 0) return false;
        }
        return true;
    }

    @Test
    public void testIsCoveredBruteForce() {
        int[][] ranges = {{1, 2}, {3, 4}, {5, 6}};
        System.out.println(isCoveredBruteForce(ranges, 2, 5));
    }

    public boolean isCoveredBruteForce(int[][] ranges, int left, int right) {
        int cnt = 0;
        for (int i = left; i <= right; ++i) {
            for (int[] range : ranges) {
                if (i >= range[0] && i <= range[1]) {
                    cnt++;
                    break;
                }
            }
        }
        //比较个数.
        return cnt == (right - left + 1);
    }
    //718. 最长重复子数组
}
