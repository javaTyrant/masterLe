package masterDesign.observer;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public abstract class Observer {
    
    protected Subject subject;

    public abstract void update();
}
