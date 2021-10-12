package masterDesign.state;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class StartState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString() {
        return "Start State";
    }
}
