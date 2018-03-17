package Calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dylan Galea on 17/03/2018.
 */
public class SimpleCalculatorTest {
    SimpleCalculator calc ;

    @Before
    public void setup(){
        calc = new SimpleCalculator();
    }

    @Test
    public void testAdd2Positives(){
        assertEquals(7,calc.add(5,2));
    }

    @Test
    public void testSubtractPositiveAnswer(){
        assertEquals(3,calc.subtract(5,2));
    }

    @Test
    public void testSubtractNegativeAnswer(){
        assertEquals(-7,calc.subtract(-5,2));
    }

    @Test
    public void testAddNegativeAnswer(){
        assertEquals(-3,calc.add(-5,2));
    }

    @Test
    public void testMultiply(){
        assertEquals(10,calc.multiply(5,2));
    }

    @After
    public void teardown(){
        calc = null;
    }
}
