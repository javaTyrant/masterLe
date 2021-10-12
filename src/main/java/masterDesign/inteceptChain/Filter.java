package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public interface Filter {
    void execute(String request);
}
