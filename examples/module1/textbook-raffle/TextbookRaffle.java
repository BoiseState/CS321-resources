import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * A program that simulates a textbook raffle along with some special effects.
 * @author amit
 *
 */
public class TextbookRaffle
{
    private static Random generator = new Random();

    /**
     * Run a raffle even.
     * @param raffle An list of raffle participants
     * @throws InterruptedException
     */
    private static void raffleIt(ArrayList<String> raffle) throws InterruptedException {
	System.out.println(" Starting the raffle! <low rumbling drum roll> ");

	for (int i = 0; i < 10; i++) {
	    System.out.print(" $ ");
	    Thread.sleep(500); //1/2 sec
	}
	System.out.print(" The lucky winner is <drum roll crescendo>" );
	Thread.sleep(5000); // the suspense
	System.out.println(":  " + raffle.remove(generator.nextInt(raffle.size())));
	System.out.println();
    }
    
    public static void main(String[] args) throws InterruptedException {		
	ArrayList<String> raffle = new ArrayList<String>();
	raffle.add("Austin");
	raffle.add("Eric");
	raffle.add("Emily");
	raffle.add("Sawyer");
	raffle.add("Jacob");
	raffle.add("Brandon");
	raffle.add("Akayou");
	raffle.add("Raymundo");

	raffleIt(raffle);
	raffleIt(raffle);
    }
}
