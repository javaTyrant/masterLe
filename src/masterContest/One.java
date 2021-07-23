package masterContest;

/**
 * @author lufengxiang
 * @since 2021/6/28
 **/
public class One {
    //1911. 最大子序列交替和
    //输入：nums = [4,2,5,3]
    //输出：7
    //解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
    public static long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        //
        long ans = 0, now = nums[0];
        //start with 1
        for (int i = 1; i < n; i++) {
            //bigger
            if (now > nums[i]) {
                ans += now - nums[i];
            }
            //
            now = nums[i];
        }
        ans += now;
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 5, 3};
        System.out.println(maxAlternatingSum(arr));
    }
}
