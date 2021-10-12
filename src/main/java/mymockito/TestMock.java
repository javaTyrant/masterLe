package mymockito;

import org.junit.Assert;
import org.junit.Test;

import static mymockito.Mockito.mock;
import static mymockito.Mockito.when;

/**
 * @author lufengxiang
 * @since 2021/10/8
 **/
public class TestMock {
    @Test
    public void test() {
        //
        Calculate calculate = mock(Calculate.class);
        when(calculate.add(1, 1)).thenReturn(1);
        when(calculate.add(2, 2)).thenReturn(3);
        Assert.assertEquals(1, calculate.add(1, 1));
        Assert.assertEquals(3, calculate.add(2, 2));
    }

    @Test
    public void test1() {

    }
}
