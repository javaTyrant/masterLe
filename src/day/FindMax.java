package day;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/15
 **/
public class FindMax {
    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 2, 1};
        System.out.println(maximumElementAfterDecrementingAndRearranging(arr));
    }

    public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] > 1) {
                arr[i] = arr[i - 1] + 1;
            }
        }
        return arr[n - 1];
    }


}
