package masterBit;

/**
 * &:位于
 *
 * @author lufengxiang
 * @since 2021/6/23
 **/
public class DayOne {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(2));
    }

    //1.二的幂
    public static boolean isPowerOfTwo(int n) {
        //如何证明:负数的二进制是怎么表现的.取反+1.
        return (n > 0) && (n & -n) == n;
    }
    //2.
    public boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }
}
