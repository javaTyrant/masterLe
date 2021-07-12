package masterDp;

/**
 * @author lufengxiang
 * @since 2021/7/11
 **/
public class MaxSubArray {
    static class Divide {
        public static void main(String[] args) {
            Divide solution = new Divide();
            int[] arr = {-4, 2, 1, -5, 6};
            System.out.println(solution.maxSubArray(arr));
        }

        int maxSubArray(int[] nums) {
            //类似寻找最大最小值的题目，初始值一定要定义成理论上的最小最大值
            int result;
            int numsSize = nums.length;
            result = maxSubArrayHelper(nums, 0, numsSize - 1);
            return result;
        }

        int maxSubArrayHelper(int[] nums, int left, int right) {
            //base case
            if (left == right) {
                return nums[left];
            }
            //找中点。
            int mid = (left + right) / 2;
            int leftSum = maxSubArrayHelper(nums, left, mid);
            //注意这里应是mid + 1，否则left + 1 = right时，会无限循环
            int rightSum = maxSubArrayHelper(nums, mid + 1, right);
            //
            int midSum = findMaxCrossingSubarray(nums, left, mid, right);
            int result = Math.max(leftSum, rightSum);
            result = Math.max(result, midSum);
            return result;
        }

        int findMaxCrossingSubarray(int[] nums, int left, int mid, int right) {
            int leftSum = Integer.MIN_VALUE;
            int sum = 0;
            for (int i = mid; i >= left; i--) {
                sum += nums[i];
                leftSum = Math.max(leftSum, sum);
            }

            int rightSum = Integer.MIN_VALUE;
            sum = 0;
            //注意这里i = mid + 1，避免重复用到nums[i]
            for (int i = mid + 1; i <= right; i++) {
                sum += nums[i];
                rightSum = Math.max(rightSum, sum);
            }
            return (leftSum + rightSum);
        }
    }

    public static void main(String[] args) {
        MaxSubArray solution = new MaxSubArray();
        int[] arr = {-4, 2, 1, -5, 6};
        System.out.println(solution.maxSubArray(arr));
    }

    public class Status {

        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        //base case
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        //
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    //
    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
