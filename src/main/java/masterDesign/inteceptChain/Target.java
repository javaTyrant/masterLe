package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class Target {
    public void execute(String request) {
        System.out.println("execute target" + request);
    }
}
