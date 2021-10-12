package day.day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/8/19
 **/
public class Day0819 {
    //中心对称数
    public List<String> findStrobogrammatic(int n) {
        return Collections.emptyList();
    }

    //螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int u = 0;
        int d = matrix.length - 1;
        int l = 0;
        int r = matrix[0].length - 1;
        while (u <= d && l <= r) {
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            u++;
            for (int i = u; i <= d; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            for (int i = r; i >= l && u <= d; i--) {
                res.add(matrix[d][i]);
            }
            d--;
            for (int i = d; i >= u && l <= r; i--) {
                res.add(matrix[i][l]);
            }
            l++;
        }
        return res;
    }
}
