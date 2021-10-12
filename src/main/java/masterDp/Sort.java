package masterDp;

/**
 * @author lufengxiang
 * @since 2021/7/17
 **/
public class Sort {
    public static void main(String[] args) {
        int[] arr = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        System.out.println(wiggleMaxLength(arr));
    }

    public int wiggleMaxLengthDiao(int[] nums) {
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);
    }

    //376. 摆动序列 贪心法:
    //思路:观察这个序列可以发现，我们不断地交错选择「峰」与「谷」，可以使得该序列尽可能长。
    //证明非常简单：如果我们选择了一个「过渡元素」，那么在原序列中，这个「过渡元素」的两侧有一个「峰」和一个「谷」。
    //不失一般性，我们假设在原序列中的出现顺序为「峰」「过渡元素」「谷」。如果「过渡元素」在选择的序列中小于其两侧的元素，
    //那么「谷」一定没有在选择的序列中出现，我们可以将「过渡元素」替换成「谷」；
    //同理，如果「过渡元素」在选择的序列中大于其两侧的元素，那么「峰」一定没有在选择的序列中出现，我们可以将「过渡元素」替换成「峰」。
    //这样一来，我们总可以将任意满足要求的序列中的所有「过渡元素」替换成「峰」或「谷」。
    //并且由于我们不断地交错选择「峰」与「谷」的方法就可以满足要求，因此这种选择方法就一定可以达到可选元素数量的最大值。
    //这样，我们只需要统计该序列中「峰」与「谷」的数量即可（注意序列两端的数也是「峰」或「谷」），但需要注意处理相邻的相同元素。
    //在实际代码中，我们记录当前序列的上升下降趋势。每次加入一个新元素时，用新的上升下降趋势与之前对比，如果出现了「峰」或「谷」，答案加一，并更新当前序列的上升下降趋势。
    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        //
        int prevDiff = nums[1] - nums[0];
        //默认答案
        int ret = prevDiff != 0 ? 2 : 1;
        //从第二个开始
        for (int i = 2; i < n; i++) {
            //
            int diff = nums[i] - nums[i - 1];
            //why.确保有峰谷存在.
            if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) {
                ret++;
                //替换prevDiff
                prevDiff = diff;
            }
        }
        return ret;
    }

    public int wiggleMaxLengthDp(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = Math.max(up[i - 1], down[i - 1] + 1);
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) {
                up[i] = up[i - 1];
                down[i] = Math.max(up[i - 1] + 1, down[i - 1]);
            } else {
                up[i] = up[i - 1];
                down[i] = down[i - 1];
            }
        }
        return Math.max(up[n - 1], down[n - 1]);
    }

    public int wiggleMaxLengthDpBetter(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1, down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }
}
