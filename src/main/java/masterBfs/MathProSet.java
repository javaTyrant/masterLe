package masterBfs;

import java.util.Scanner;

/**
 * 快速幂
 *
 * @author lufengxiang
 * @since 2021/7/2
 **/
public class MathProSet {
    //2的幂
    public boolean isPowerOfTwo(int n) {
        // return (n > 0) && (n & -n) == n;
        if (n == 0) return false;
        while (n % 2 == 0) n /= 2;
        return n == 1;
    }

    //64位整数乘法
    public static void main(String[] args) {
        //
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long p = in.nextLong();
        long res = 0;
        //
        while (b > 0) {
            //奇数
            if ((b & 1) > 0) {
                res = (res + a) % p;
            }
            //偶数,先处以二
            b >>= 1;
            //a * 2 模 p
            a = a * 2 % p;
        }
        System.out.println(res);
    }
}
