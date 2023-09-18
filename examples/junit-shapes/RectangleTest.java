import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class that contains unit tests for Rectangle.
 *
 * @author andrekeys
 */
class RectangleTest {

    private Rectangle r;

    /**
     * The annotation BeforeEach runs the attached method
     * before each test case.
     */
    @BeforeEach
    public void setUp() {
        //method executes before each test case executes
        r = new Rectangle(2, 3);
    }

    /**
     * The annotation AfterEach runs the attached method
     * after each test case.
     */
    @AfterEach
    public void tearDown() {
        //method executes after each test case executes
        //nothing to do here
    }

    /**
     * The annotation Test will cause the junit runner
     * to execute the attached method as part of the
     * test cases and code coverage.
     *
     * Tests the perimeter calculation of Rectangle.
     */
    @Test
    public void testPerimeter() {
        //test case with the parameters from setUp
        assertEquals(10, r.perimeter());

        //try another set of parameters;
        r = new Rectangle(1, 5);

        assertEquals(12, r.perimeter());

        //reverse params and test
        r = new Rectangle(5, 1);

        assertEquals(12, r.perimeter());
    }

    /**
     * Tests the area calculation of the Rectangle.
     */
    @Test
    public void testArea() {

        //test with parameters from setUp
        assertEquals(6, r.area());

        //try another set of parameters;
        r = new Rectangle(1, 5);

        assertEquals(5, r.area());

        //reverse params and test
        r = new Rectangle(5, 1);

        assertEquals(5, r.area());
    }

    /**
     * Tests various Rectangles for the isSquare condition.
     */
    @Test
    public void testIsSquare() {

        assertFalse(r.isSquare());

        r = new Rectangle(2, 2);

        assertTrue(r.isSquare());

    }

    /**
     * Tests that a rectangle cannot be created with zero or negative
     * side lengths.
     *
     * JUnit5 provides the assertThrows() method for this purpose.
     *
     * More on lambda expressions:
     * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html">here.</a>
     */
    @Test()
    public void testNoNegatives() {

        //use Lambda syntax in Junit5
        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(-2, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(10, -3);
        });

        //specify the boundary condition of 0
        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(0, 10);
        });

        //specify the boundary condition of 0
        assertThrows(IllegalArgumentException.class, () -> {
            r = new Rectangle(10, 0);
        });
    }
}
