package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class Client {
    //一个Client有一个Manager,可以直接持有filterChain吗?
    FilterManager filterManager;

    //
    public void setFilterManager(FilterManager filterManager) {
        this.filterManager = filterManager;
    }

    //
    public void sendRequest(String request) {
        filterManager.filterRequest(request);
    }
}
