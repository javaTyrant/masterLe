package masterBfs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author lufengxiang
 * @since 2021/7/3
 **/
public class PassNote {
    //275. 传纸条
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] data = new int[m][n];
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = scanner.nextInt();
            }
        }

    }
}
