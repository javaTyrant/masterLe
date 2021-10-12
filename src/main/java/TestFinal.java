/**
 * @author lufengxiang
 * @since 2021/9/29
 **/
public class TestFinal {
    static class P {
        final B b;

        P(B b) {
            this.b = b;
        }
    }

    static class B {
        int age;

        B(int age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {
        B b = new B(12);
        P p = new P(b);
        b.age = 15;
        System.out.println(p.b.age);
    }
}
