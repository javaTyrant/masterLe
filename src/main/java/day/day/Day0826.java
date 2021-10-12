package day.day;

import java.util.Arrays;

/**
 * @author lufengxiang
 * @since 2021/8/26
 **/
public class Day0826 {
    //881. 救生艇
    public int numRescueBoats(int[] people, int limit) {
        int res = 0;
        int right = people.length - 1;
        int left = 0;
        Arrays.sort(people);
        while (left <= right) {
            if (left == right) {
                res++;      // 只剩下最后一个,直接一个走,结束
                break;
            }
            if (people[left] + people[right] > limit) {
                res++;
                right--;        // 先载最重的, 而且最小的也无法一起载,那么就最重的单独走
            } else {
                res++;
                right--;        // 最重的与最轻的一起走
                left++;
            }
        }
        return res;
    }
}
