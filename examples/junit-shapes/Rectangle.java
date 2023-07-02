/**
 * Simple representation of a Rectangle for the purposes of demonstrating
 * junit unit testing
 *
 * @author andrekeys
 */
public class Rectangle implements ShapeInterface {

    private int x;
    private int y;

    public Rectangle(int sideX, int sideY) {
        if (sideX <= 0  || sideY <= 0) {
            throw new IllegalArgumentException("Side length cannot be 0 or below");
        }
        this.x = sideX;
        this.y = sideY;
    }

    /**
     * Calculates the perimeter of this Shape
     * @return the perimeter
     */
    public int perimeter() {
        return 2 * x + 2 * y;
    }

    /**
     * Calculates the area
     * @return the area
     */
    public int area() {
        return x * y;
    }

    /**
     * Some rectangles can also be considered Squares.
     * @return whether this rectangle is a square too
     */
    public boolean isSquare() {
        return x == y;
    }
}
