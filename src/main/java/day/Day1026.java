package day;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;

/**
 * @author lufengxiang
 * @since 2021/10/26
 **/
public class Day1026 {
    //496. 下一个更大元素 I
    //
    public int[] nextGreaterElementBruteForce(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = 0;
            while (j < n && nums2[j] != nums1[i]) {
                ++j;
            }
            int k = j + 1;
            while (k < n && nums2[k] < nums2[j]) {
                ++k;
            }
            res[i] = k < n ? nums2[k] : -1;
        }
        return res;
    }

    //背后的思想.
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        //
        Deque<Integer> stack = new ArrayDeque<>();
        //
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[findNums.length];
        //
        for (int num : nums) {
            while (!stack.isEmpty() && num > stack.peek())
                map.put(stack.pop(), num);
            stack.push(num);
        }
        while (!stack.isEmpty())
            map.put(stack.pop(), -1);
        for (int i = 0; i < findNums.length; i++) {
            res[i] = map.get(findNums[i]);
        }
        return res;
    }

    @Test
    public void testNextGreaterElement() {
        int[] nums1 = {4, 1, 2}, nums2 = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(nums1, nums2)));
    }
}
