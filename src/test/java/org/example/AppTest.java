package org.example;

import org.ToPinYin;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        ToPinYin toPinYin = new ToPinYin();
        System.out.println(toPinYin.evaluate("暗杀说的话"));
//        assertTrue( true );
    }
}
