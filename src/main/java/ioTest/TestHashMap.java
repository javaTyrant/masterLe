package ioTest;

/**
 * @author lufengxiang
 * @since 2021/12/17
 **/
public class TestHashMap {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static int tableSizeFor(int cap) {
        System.out.println(Integer.toBinaryString(cap));
        int n = cap - 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        tableSizeFor(MAXIMUM_CAPACITY);
    }
}
