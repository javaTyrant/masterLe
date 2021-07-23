package masterSort;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/7/20
 **/
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 1, -8, 4, 7, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        qSort(arr, 0, arr.length - 1);
    }

    static void qSort(int[] arr, int low, int high) {
        if (low >= high) return;
        int p = partition(arr, low, high);
        qSort(arr, low, p - 1);
        qSort(arr, p + 1, high);
    }

    //分区
    private static int partition(int[] arr, int l, int r) {
        //先找一个pivot
        int temp = arr[l];
        while (l < r) {
            //找到arr[r]小于temp的
            while (l < r && arr[r] >= temp) r--;
            //交换
            if (l < r) arr[l] = arr[r];
            //找到arr[l]大于等于temp的
            while (l < r && arr[l] < temp) l++;
            //交换
            if (l < r) arr[r] = arr[l];
        }
        //l==r的时候退出循环
        arr[l] = temp;
        return l;
    }

}
