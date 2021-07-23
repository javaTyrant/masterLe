import java.util.Scanner;

/**
 * @author lufengxiang
 * @since 2021/7/3
 **/
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();
        System.out.println(Math.max(b + c, Math.max(a + b, a + c)));
    }
}
