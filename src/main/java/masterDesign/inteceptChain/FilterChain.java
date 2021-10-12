package masterDesign.inteceptChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class FilterChain {
    //可以是list,可以是链表,可以是数组
    private final List<Filter> filters = new ArrayList<>();
    //真实的对象.
    private Target target;

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void execute(String request) {
        for (Filter filter : filters) {
            filter.execute(request);
        }
        target.execute(request);
    }

    public void setTarget(Target target) {
        this.target = target;
    }
}
