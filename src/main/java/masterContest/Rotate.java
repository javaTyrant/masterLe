package masterContest;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/6/28
 **/
public class Rotate {


    //1914. 循环轮转矩阵
    public static int[][] rotateGrid(int[][] grid, int k) {
        // 矩阵尺寸
        int m = grid.length, n = grid[0].length;
        // 计算一共有多少层
        int layerNumber = Math.min(m, n) / 2;
        // 逐层处理
        for (int i = 0; i < layerNumber; ++i) {
            // 计算出来当前层需要多大的数组来存放, 计算方法是当前层最大尺寸 - 内部下一层尺寸.
            int[] data = new int[(m - 2 * i) * (n - 2 * i) - (m - (i + 1) * 2) * (n - (i + 1) * 2)];
            int idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                data[idx++] = grid[i][offset];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                data[idx++] = grid[offset][n - i - 1];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                data[idx++] = grid[m - i - 1][offset];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                data[idx++] = grid[offset][i];
            // 把旋转完的放回去
            Integer[] ret = rotateVector(data, k);
            idx = 0;
            // 从左往右
            for (int offset = i; offset < n - i - 1; ++offset)
                grid[i][offset] = ret[idx++];
            // 从上往下
            for (int offset = i; offset < m - i - 1; ++offset)
                grid[offset][n - i - 1] = ret[idx++];
            // 从右往左
            for (int offset = n - i - 1; offset > i; --offset)
                grid[m - i - 1][offset] = ret[idx++];
            // 从下往上
            for (int offset = m - i - 1; offset > i; --offset)
                grid[offset][i] = ret[idx++];
        }
        return grid;
    }

    private static Integer[] rotateVector(int[] vector, int offset) {
        // 取余, 否则会有无用功, 超时
        offset = offset % vector.length;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int item : vector) deque.add(item);
        // 旋转操作
        while (offset-- > 0) {
            deque.addLast(deque.pollFirst());
        }
        return deque.toArray(new Integer[0]);
    }

    //54. 螺旋矩阵
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int u = 0;
        int d = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        //u r d l刚好是顺时针
        while (u <= d && l <= r) {
            //从左到右
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            u++;
            //从上到下
            for (int i = u; i <= d; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            //从右到左.u <= d
            for (int i = r; i >= l && u <= d; i--) {
                res.add(matrix[d][i]);
            }
            d--;
            //从下到上 l <= r
            for (int i = d; i >= u && l <= r; i--) {
                res.add(matrix[i][l]);
            }
            l++;
        }
        return res;
    }

    //螺旋矩阵 II
    public static int[][] generateMatrix(int n) {
        //
        int[][] arr = new int[n][n];
        //
        int c = 1, j = 0;
        //
        while (c <= n * n) {
            for (int i = j; i < n - j; i++)
                arr[j][i] = c++;
            for (int i = j + 1; i < n - j; i++)
                arr[i][n - j - 1] = c++;
            for (int i = n - j - 2; i >= j; i--)
                arr[n - j - 1][i] = c++;
            for (int i = n - j - 2; i > j; i--)
                arr[i][j] = c++;
            j++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        System.out.println(Arrays.deepToString(rotateGrid(arr, 3)));
        int[][] arr1 = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}};
        System.out.println(spiralOrder(arr1));
        int[][] ints = generateMatrix(3);
        for (int[] i : ints) {
            System.out.println(Arrays.toString(i));
        }
    }
}
