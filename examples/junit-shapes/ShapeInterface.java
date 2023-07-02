/**
 * an interface to be used by objects which would like
 * to represent a shape.
 *
 * @author andrekeys
 */
public interface ShapeInterface {

    /**
     * Shapes have a perimeter. Difficult for ellipses!
     * @return the perimeter of a shape
     */
    public int perimeter();

    /**
     * Shapes have an area
     * @return the area of a shape
     */
    public int area();
}
