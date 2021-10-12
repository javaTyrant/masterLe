package mymockito.cglib;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author lufengxiang
 * @since 2021/10/9
 **/
public class CglibTest {

    @Test
    public void testCglib() {
        //
        DaoProxy daoProxy = new DaoProxy();

        Enhancer enhancer = new Enhancer();
        //setSuperclass表示设置要代理的类
        enhancer.setSuperclass(Dao.class);
        //setCallback表示设置回调即MethodInterceptor的实现类
        enhancer.setCallback(daoProxy);
        //使用create()方法生成一个代理对象，注意要强转一下，因为返回的是Object
        Dao dao = (Dao) enhancer.create();
        dao.update();
        dao.select();
    }
}
