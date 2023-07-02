import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    private Square s;

    /**
     * The annotation BeforeEach runs the attached method
     * before each test case.
     */
    @BeforeEach
    public void setUp() {
        //method executes before each test case executes
        s = new Square(2);
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
     * The annotation Test will cause the JUnit runner
     * to execute the attached method as part of the
     * test cases and code coverage.
     *
     * Tests the perimeter calculation of Square
     */
    @Test
    public void testPerimeter() {
        assertEquals(8, s.perimeter());
    }


    /**
     * Tests the area calculation of the Square.
     */
    @Test
    public void testArea() {
        assertEquals(4, s.area());
    }


    /**
     * Tests the Square for the isSquare condition.
     */
    @Test
    public void testIsSquare() {
        assertTrue(s.isSquare());
    }

    /**
     * Tests that a rectangle cannot be created with zero or negative
     * side lengths.
     *
     * Junit5 provides the assertThrows() method for this purpose.
     *
     * More on lambda expressions:
     * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html">here.</a>
     */
    @Test
    public void testNoNegatives() {

        assertThrows(IllegalArgumentException.class, () -> {
            s = new Square(-10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            s = new Square(0);
        });
    }
}
