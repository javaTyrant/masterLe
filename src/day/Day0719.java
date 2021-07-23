package day;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lufengxiang
 * @since 2021/7/19
 **/
public class Day0719 {
    public static void main(String[] args) {
        int[] arr = {1, 4, 8, 12, 13, 14};
        System.out.println(maxFrequency(arr, 7));
        int[] arr1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        System.out.println(longestOnes(arr1, 2));
        int[] arr3 = {0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(removeDuplicates2(arr3));
    }

    //1838. 最高频元素的频数
    public static int maxFrequency(int[] nums, int k) {
        //先排序
        Arrays.sort(nums);
        //
        int n = nums.length;
        //作用:是将窗口所有的值都转换成窗口最后的那个值所需要的操作次数
        //因为窗口1次滑动1个单位，所以每次判断当前最右值和前一个值的差值，然后乘以窗口中除去最后一个元素的前面的元素个数。
        long total = 0;
        //
        int l = 0, res = 1;
        for (int r = 1; r < n; ++r) {
            //
            total += (long) (nums[r] - nums[r - 1]) * (r - l);
            //什么时候更新l.
            //当total大于k时，需要将左边界右移，操作是：total减去最左值与最右值的差值
            //然后将左边界右移
            while (total > k) {
                total -= nums[r] - nums[l];
                ++l;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }

    //1004. 最大连续1的个数 III:
    //给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
    //返回仅包含 1 的最长（连续）子数组的长度。
    public static int longestOnes(int[] nums, int k) {
        int n = nums.length;
        //
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            //右窗口0的个数
            rsum += (1 - nums[right]);
            //rsum - k.rsum=3说明遇到了三个0.
            //
            while (lsum < rsum - k) {//要移动左指针了.
                //窗口左边0的个数.
                lsum += (1 - nums[left]);
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    //删除有序数组中的重复项:nums = [0,0,1,1,1,2,2,3,3,4]
    //5, nums = [0,1,2,3,4]
    public int removeDuplicates(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            //
            if (nums[i] != nums[start]) {
                start++;
                nums[start] = nums[i];
            }
        }
        return start + 1;
    }

    //最长连续递增序列
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        int res = 0;
        int i = 0;
        int j = 0;
        // 循环不变量 [i..j) 严格单调递增
        while (j < len) {
            //如果前一个大于当前的,更换位置.
            if (j > 0 && nums[j - 1] >= nums[j]) {
                i = j;
            }
            //j++
            j++;
            //取最大
            res = Math.max(res, j - i);
        }
        return res;
    }

    public int findLengthOfLCIS2(int[] nums) {
        int len = nums.length;
        int res = 0;
        int i = 0;
        int j = 0;
        // 循环不变量 [i..j] 严格单调递增
        while (j < len) {
            if (j > 0 && nums[j - 1] >= nums[j]) {
                i = j;
            }
            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }

    //移除元素输入：nums = [0,1,2,2,3,0,4,2], val = 2
    //输出：5, nums = [0,1,4,0,3]
    public int removeElement(int[] nums, int val) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val)
                nums[res++] = nums[i];
        }
        return res;
    }

    //给你一个有序数组 nums
    //删除排序数组中的重复项 II:请你 原地 删除重复出现的元素，使每个元素 最多出现两次
    public static int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            //
            if (i < 2 || nums[j] > nums[i - 2]) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    //移动零:给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    //输入: [0,1,0,3,12]
    //输出: [1,3,12,0,0]
    public static void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        //
        while (right < n) {
            //如果right不等于0.left
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            //
            right++;
        }
    }

    //颜色分类:荷兰国旗问题.
    public static void sortColors(int[] nums) {
        //双指针
        int low = 0, high = nums.length - 1;
        int i = 0;
        //i为什么要==high
        while (i <= high) {
            if (nums[i] == 0) {
                swap(nums, low, i);
                ++low;
                ++i;
            } else if (nums[i] == 1) {
                ++i;
            } else if (nums[i] == 2) {
                swap(nums, high, i);
                --high;
            }
        }
    }

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

    Random random = new Random();

    @Test
    public void testFindKthLargest0() {
        int[] arr = {3, 1, 2, 4, 5};
        System.out.println(findKthLargest0(arr, 3));
    }

    //第k大的,也就是第nums.length - k小的.
    public int findKthLargest0(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = partition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            //基准小于index.
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    @SuppressWarnings("unused")
    public int randomPartition(int[] a, int l, int r) {
        int i = l;
        swap(a, i, r);
        return partition0(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    @Test
    public void testPartition() {
        int[] arr = {3, 3, 3, 3, 3};
        int[] arr1 = {2, 1, 3, -1, 5};
        partition0(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        partition0(arr1, 0, arr1.length - 1);
        System.out.println(Arrays.toString(arr1));
    }

    private static int partition0(int[] arr, int l, int r) {
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

    @Test
    public void test_par() {
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        par(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private void par(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right && arr[right] >= temp) {
            right--;
        }
        if (left < right) arr[left] = arr[right];
        while (left < right && arr[left] < temp) {
            left++;
        }
        if (left < right) arr[right] = arr[left];
        arr[left] = temp;
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}