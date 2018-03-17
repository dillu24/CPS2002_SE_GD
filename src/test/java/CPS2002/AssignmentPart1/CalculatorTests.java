package CPS2002.AssignmentPart1;

/**
 * Created by Dylan Galea on 16/03/2018.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTests {
    public Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @After
    public void tearDown() {
        calculator = null;
    }

    @Test
    public void testAdd2PositiveIntegers() {
        assertEquals(7, calculator.add(5,2));
    }

}
