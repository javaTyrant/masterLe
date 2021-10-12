package masterDesign.decorator;

/**
 * @author lufengxiang
 * @since 2021/10/12
 **/
public abstract class ShapeDecorator {
    //
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}
