package masterDesign.decorator;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
