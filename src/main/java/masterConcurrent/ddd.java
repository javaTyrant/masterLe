package masterConcurrent;

/**
 * @author lufengxiang
 * @since 2021/10/14
 **/
public class ddd {
    public static void main(String[] args) {
        System.out.println(Math.abs("15139ee90cec41a78339faabd3fa8f7b".hashCode() % 10));
        int h = 0;
        String orderCode = "SHZL-2021-001753882";
        System.out.println((((h = orderCode.hashCode()) ^ (h >>> 16)) & 63));
    }
}
