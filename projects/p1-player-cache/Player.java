import java.io.Serializable;

/**
 * This Player class implements Serializable
 * and represents a Player object.
 *
 * @author CS321 instructors
 */
public class Player implements Serializable
{
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
