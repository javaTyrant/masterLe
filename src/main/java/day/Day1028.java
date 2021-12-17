package day;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/10/28
 **/
public class Day1028 {
    //1.枚举可能包含重复字符的数组的全排列，读者可参考「47. 全排列 II」的官方题解；
    //2.判断一个整数是否为 2 的幂，读者可参考「231. 2 的幂」的官方题解。
    boolean[] vis;

    public boolean reorderedPowerOf2(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        Arrays.sort(nums);
        vis = new boolean[nums.length];
        return backtrack(nums, 0, 0);
    }

    public boolean backtrack(char[] nums, int idx, int num) {
        if (idx == nums.length) {
            return isPowerOfTwo(num);
        }
        for (int i = 0; i < nums.length; ++i) {
            // 不能有前导零
            if ((num == 0 && nums[i] == '0') || vis[i] || (i > 0 && !vis[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            vis[i] = true;
            if (backtrack(nums, idx + 1, num * 10 + nums[i] - '0')) {
                return true;
            }
            vis[i] = false;
        }
        return false;
    }

    //
    public boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    @Test
    public void test() {
        System.out.println(reorderedPowerOf2(61));
        Integer a = 10;
    }

    //全排列
    public List<List<Integer>> permute(int[] nums) {
        return null;
    }
    //
}

