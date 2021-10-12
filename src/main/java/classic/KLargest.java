package classic;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author lufengxiang
 * @since 2021/7/19
 **/
public class KLargest {
    //数组中的第 K 个最大元素
    public int findKthLargest(int[] nums, int k) {
        return quickSort(nums, 0, nums.length - 1, k - 1);
    }

    public int quickSort(int[] arr, int low, int high, int k) {
        if (low >= high) return arr[low];
        int i = low - 1;//i是向后搜索指针，为简化计算先-1
        int j = high + 1;//j是向前搜索指针，为简化计算下+1
        int pivot = arr[(low + high) / 2];
        while (i < j) {
            do {
                i++;
            } while (arr[i] > pivot);//arr[i] 大于基准，不用交换，继续向后搜索
            do {
                j--;
            } while (arr[j] < pivot);//arr[j] 小于基准，不用交换，继续向前搜索
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (j >= k) { //第k大的元素位于基准左侧
            return quickSort(arr, low, j, k);
        } else { //第k大的元素位于右侧
            return quickSort(arr, j + 1, high, k);
        }
    }

    public int findKthLargestQueue(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (queue.peek() < num) {
                queue.poll();
                queue.offer(num);
            }
        }
        return queue.peek();
    }
}
