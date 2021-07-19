package day;

import java.util.*;

/**
 * @author lufengxiang
 * @since 2021/7/13
 **/
public class SkylineProblem {
    //84. 柱状图中最大的矩形
    public static int largestRectangleArea(int[] heights) {
        //栈
        Deque<Integer> stack = new ArrayDeque<>();
        //先放一个-1
        stack.push(-1);
        //保存最大的值
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            //stack.peek()是啥,当前一个比当前的矮,为什么当前的大于之前的就不用进入循环呢?
            //我们先考虑两种极端的情况吧,如果是递减的和是递增的
            //这个处理的是递减的
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                //最大值,保存的和
                //和下面的 只是 i 和 heights.length的区别 高 * 宽
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            //然后把索引push进去
            stack.push(i);
        }
        //再计算一次,相当于把递增的改成递减的,然后计算,牛逼啊
        while (stack.peek() != -1)
            //如果是2,1这种情况,-1刚好把-1给抵消掉了
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        return maxarea;
    }

    //218. 天际线问题
    public List<List<Integer>> getSkyline(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }
}
