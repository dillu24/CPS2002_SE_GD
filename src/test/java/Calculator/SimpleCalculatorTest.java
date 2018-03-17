package Calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dylan Galea on 17/03/2018.
 */

/**
 * This class contains the test classes of the SimpleCalculator class ,testing all the methods
 */

public class SimpleCalculatorTest {
    SimpleCalculator calc ;

    @Before
    public void setup(){
        calc = new SimpleCalculator();
    }

    @Test
    public void testAdd2Positives(){
        assertEquals(7,calc.IntegerAddition(5,2));
    }

    @Test
    public void testSubtractPositiveAnswer(){
        assertEquals(3,calc.IntegerSubtraction(5,2));
    }

    @Test
    public void testSubtractNegativeAnswer(){
        assertEquals(-7,calc.IntegerSubtraction(-5,2));
    }

    @Test
    public void testAddNegativeAnswer(){
        assertEquals(-3,calc.IntegerAddition(-5,2));
    }

    @Test
    public void testMultiply(){
        assertEquals(10,calc.IntegerMultiplication(5,2));
    }

    @Test
    public void testDivide2NonZeros(){
        assertEquals(2,calc.IntegerDivision(10,5));
    }

    @Test
    public void testDividebyZero(){
        assertEquals(-1,calc.IntegerDivision(5,0));
    }

    @After
    public void teardown(){
        calc = null;
    }
}
