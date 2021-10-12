package day.day;

/**
 * @author lufengxiang
 * @since 2021/8/31
 **/
public class Day0831 {
    //1109. 航班预订统计
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        for (int[] booking : bookings) {
            nums[booking[0] - 1] += booking[2];
            if (booking[1] < n) {
                nums[booking[1]] -= booking[2];
            }
        }
        for (int i = 1; i < n; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(Math.abs("db2a01dd70564f3488ec5e364af6f137" .hashCode()) % 10));
    }
}
