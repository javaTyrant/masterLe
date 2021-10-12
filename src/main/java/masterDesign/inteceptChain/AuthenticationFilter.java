package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class AuthenticationFilter implements Filter {
    @Override
    public void execute(String request) {
        System.out.println("认证");
    }
}
