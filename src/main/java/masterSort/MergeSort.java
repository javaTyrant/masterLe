package masterSort;

/**
 * @author lumac
 * @since 2021/7/24
 */
public class MergeSort {
    //归并排序
    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int left, int right) {
        if (right >= left) return;
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        merge(arr, left, right);
    }

    public void merge(int[] arr, int low, int high) {
        int[] aux = new int[arr.length];
        for (int i = 0; i < high; i++) {
            aux[i] = arr[i];
        }

    }
}
