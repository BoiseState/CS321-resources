import java.io.Serializable;

/**
 * This Player class represents a Player and implements the Serializable interface.
 *
 * @author CS321 instructors
 */
public class Player implements Serializable
{
    /**
     * Each serialized object should have an id that depends on the structure of the class.
     * This provides security and prevents programs from reading a version of the class
     * that may be different enough to be compatible. Most IDEs will auto-generate this id for you. 
     */
    private static final long serialVersionUID = -3220479994421851449L;
    private String name;

    /**
     * The constructor for the Player object
     *
     * @param name - the name of Player
     */
    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player " + getName();
    }
}
