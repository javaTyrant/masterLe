package mymockito;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lufengxiang
 * @since 2021/10/8
 **/
public class Mockito {
    /**
     * 根据class对象创建该对象的代理对象
     * 1、设置父类；2、设置回调
     * 本质：动态创建了一个class对象的子类
     */
    //
    private static Map<Invocation, Object> results = new HashMap<>();
    //
    private static Invocation lastInvocation;

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> clazz) {
        //
        Enhancer enhancer = new Enhancer();
        //
        enhancer.setSuperclass(clazz);
        //
        enhancer.setCallback(new MockInterceptor());
        //
        return (T) enhancer.create();
    }

    private static class MockInterceptor implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
            //生成一个新的invocation对象.
            Invocation invocation = new Invocation(proxy, method, args, proxy);
            lastInvocation = invocation;
            //
            if (results.containsKey(invocation)) {
                return results.get(invocation);
            }
            return null;
        }
    }

    //返回一个When对象.
    @SuppressWarnings("unused")
    public static <T> When<T> when(T o) {
        return new When<>();
    }

    public static class When<T> {
        public void thenReturn(T retObj) {
            //
            results.put(lastInvocation, retObj);
        }
    }
}
