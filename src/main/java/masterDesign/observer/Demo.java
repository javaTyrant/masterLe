package masterDesign.observer;

/**
 * guava是怎么实现的呢?
 *
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class Demo {
    public static void main(String[] args) {
        Subject subject = new Subject();
        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);
        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
