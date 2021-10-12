package masterDesign.state;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
