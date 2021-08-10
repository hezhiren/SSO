package cn.hzr0523.DI;

import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.*;

/**
 * hezhi
 * 2018/11/17 16:29
 */
public class DITest {

    @Test
    public void test() {
        Quest mockTest = mock(Quest.class);

        BraveKnight knight = new BraveKnight(mockTest);

        knight.save();

        verify(mockTest, times(1)).embark();
    }

    @Test
    public void test2() {
        String a = "123";
        String b = "1" + "23";
        System.out.println(a == b);
    }
}
