import java.io.Serializable;

/**
 * Represents a Player that also implements the Serializable interface so it can be serialized 
 * to a file on disk.
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
    /**
     * The name of the player.
     */
    private String name;
    /**
     * The balance (represents their bank balance).
     */
    private double balance;

    /**
     * A simple constructor for the Player object.
     *
     * @param name - the name of Player
     */
    public Player(String name) {
        this.name = name;
        this.balance = 0.0;
    }
    
    /**
     * An overloaded constructor for the Player object.
     * 
     * @param name  the name of the player
     * @param balance  the initial balance they have
     */
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    
    /**
     * Getter for the name.
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Getter for the balance.
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Player " + getName() + " balance = " + balance;
    }
}
