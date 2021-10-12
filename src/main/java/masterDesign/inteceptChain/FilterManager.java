package masterDesign.inteceptChain;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class FilterManager {
    //持有filterChain
    FilterChain filterChain;

    public FilterManager(Target target) {
        filterChain = new FilterChain();
        filterChain.setTarget(target);
    }

    public void setFilter(Filter filter) {
        filterChain.addFilter(filter);
    }

    public void filterRequest(String request) {
        filterChain.execute(request);
    }
}
