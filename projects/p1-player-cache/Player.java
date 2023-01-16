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
     * @param name
     * @param balance
     */
    public Player(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }
    

    @Override
    public String toString() {
        return "Player " + getName() + " balance = " + balance;
    }
}
