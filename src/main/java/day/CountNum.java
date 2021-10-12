package day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lufengxiang
 * @since 2021/7/16
 **/
public class CountNum {
    //剑指 Offer 53 - I. 在排序数组中查找数字 I
    public int search(int[] nums, int target) {
        //二分法
        int left = 0, right = nums.length - 1;
        //默认结果
        int count = 0;
        //
        while (left < right) {
            //mid
            int mid = (left + right) / 2;
            //如果中间值大于target,放弃右边.
            if (nums[mid] >= target)
                //为什么不mid-1呢
                right = mid;
            if (nums[mid] < target)
                left = mid + 1;
        }
        //开始累加.
        while (left < nums.length && nums[left++] == target)
            count++;
        return count;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
