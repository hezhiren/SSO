package cn.hzr0523.DI;

import org.junit.Test;

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
}
