package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class DebugFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("request Log" + request);
    }
}
