package masterDesign.state;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class StopState implements State {
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString() {
        return "Stop State";
    }
}
