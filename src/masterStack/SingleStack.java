package masterStack;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/13
 **/
public class SingleStack {
    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(dailyTemperatures(arr)));
        int[] a = {4, 1, 2};
        int[] b = {1, 3, 4, 2};
        System.out.println(Arrays.toString(nextGreaterElement(a, b)));
        int[] c = {1, 2, 3, 4, 3};
        System.out.println(Arrays.toString(nextGreaterElements(c)));
        int[] water = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap2(water));
    }

    //接雨水 双指针.
    public int trap(int[] height) {
        int res = 0, maxLeft = 0, maxRight = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            if (maxLeft < height[right]) {
                if (maxLeft < height[left]) {
                    maxLeft = height[left];
                } else {
                    res += maxLeft - height[left];
                    left++;
                }
            } else {
                if (maxRight < height[right]) {
                    maxRight = height[right];
                } else {
                    res += maxRight - height[right];
                    right--;
                }
            }
        }
        return res;
    }

    //单调栈:
    //思路:维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组height中的元素递减。
    //装水的条件是什么?
    public static int trap2(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                //
                if (stack.isEmpty()) {
                    break;
                }
                //左边的高度
                int left = stack.peek();
                //
                int currWidth = i - left - 1;
                //选最小的
                int currHeight = Math.min(height[left], height[i]) - height[top];
                //
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }

    //下一个更大的元素:
    //输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
    //输出: [-1,3,-1]
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //
        Deque<Integer> stack = new ArrayDeque<>();
        //对应的比他大的值.
        HashMap<Integer, Integer> map = new HashMap<>();
        //
        int[] res = new int[nums1.length];
        //nums2放入map
        for (int j : nums2) {
            while (!stack.isEmpty() && j > stack.peek())
                //放入map
                map.put(stack.pop(), j);
            //push
            stack.push(j);
        }
        //处理-1
        while (!stack.isEmpty())
            map.put(stack.pop(), -1);
        //放入数组
        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

    //739每日温度:请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，
    //至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
    //[73, 74, 75, 71, 69, 72, 76, 73] -> [1, 1, 4, 2, 1, 1, 0, 0]
    public static int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] ans = new int[length];
        Deque<Integer> stack = new ArrayDeque<>();
        //循环
        for (int i = 0; i < length; i++) {
            //
            int temperature = T[i];
            //如果当前大于栈里最大的.
            while (!stack.isEmpty() && temperature > T[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            //
            stack.push(i);
        }
        return ans;
    }

    //503. 下一个更大元素 II
    //输入: [1,2,1]
    //输出: [2,-1,2]
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        //n * 2 - 1
        for (int i = 0; i < n * 2 - 1; i++) {
            //i % n
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i % n]) {
                ret[stack.pop()] = nums[i % n];
            }
            stack.push(i % n);
        }
        return ret;
    }

    //901. 股票价格跨度:
    //编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
    //今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
    //例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
    class StockSpanner {
        //双栈
        Stack<Integer> prices, weights;

        public StockSpanner() {
            prices = new Stack<>();
            weights = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && prices.peek() <= price) {
                prices.pop();
                w += weights.pop();
            }
            prices.push(price);
            weights.push(w);
            return w;
        }
    }


    //239. 滑动窗口最大值


}
