package masterDesign.decorator;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
