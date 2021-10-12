package masterDesign.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class Subject {
    //持有观察者集合
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        //通知观察者
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
