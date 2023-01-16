import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * Player Generator class generates an ArrayList of Players objects that are
 * then serialized to an output file for use by other programs.
 * 
 * The usage for PlayerGenerator.java is as follows: 
 *     <pre>java PlayerGenerator &lt;number-of-players&gt; &lt;standard-deviation&gt; &lt;debug-level&gt; [&lt;seed&gt;]</pre>
 *     where:
 *     <ul>
 *     <li>&lt;number-of-players&gt;: The number of Players generated to the output file.</li>
 *     <li>&lt;standard-deviation&gt;: The amount of standard deviation from the median Player.</li>
 *     <li>&lt;debug-level&gt;: 
 *                        <ul>
 *                        0 is no debug output <br>
 *                        1 shows all the players that were generated<br>
 *                        2 shows the actual distribution of player names as well as all the players<br>
 *                        </ul></li>
 *     <li>&lt;seed&gt;: A seed for a random number generator for testing to ensure simulation can be 
 *             repeated. This argument can be skipped -- then the seed will be random.
 *     </ul>
 *
 *  The idea of adding varying debug levels to be output debugging information is a 
 *  commonly used technique in most large scale software projects. This is an example 
 *  of <b>code instrumentation</b>.
 *  
 * @author CS321 instructors
 */
public class PlayerGenerator
{
    private int numberOfPlayers;
    private double standardDeviation;
    private long seed;
    private boolean seeded;
   
    private final int SHOW_NO_DEBUG_INFO = 0;
    private final int SHOW_PLAYERS = 1;
    private final int SHOW_DISTRIBUTION = 2;
    private int debugLevel = SHOW_NO_DEBUG_INFO;
    
    private Random rand;
    private ArrayList<Player> players;
    private int[] playerNames;
    private Player[] playerObjects;

    /**
     * Show usage for the program.
     */
    public void showUsage() {
        System.out.println("java PlayerGenerator " + "<number-of-players> " + 
                           "<standard-deviation> " + "<debug-level = 0, 1, 2>" + " [<seed>]");
        System.exit(1);
    }


    /**
     * Processes the command line arguments
     *
     * @param args  String arguments: 
     *              args[0]: <number-of-players> 
     *              args[1]: <standard-deviation> 
     *              args[2]: <debug-level>
     *              args[3]: [<seed>]
     */
    private void processArguments(String[] args) {   
        numberOfPlayers = Integer.parseInt(args[0]);
        if (numberOfPlayers < 1) {
            throw new IllegalArgumentException("Illegal argument: number-of-players must >= 1.");
        }

        standardDeviation = Double.parseDouble(args[1]);
        debugLevel = Integer.parseInt(args[2]);

        if (args.length == 4) { // optional seed
            seed = Long.parseLong(args[3]);
            seeded = true;
        }
    }


    /**
     * Creates a dump file containing a serialized ArrayList of random Player objects to be put. 
     * Uses a Gaussian distribution with an alterable standard deviation and mean depending on 
     * the median player number.
     */
    public void dumpOutputFile() throws FileNotFoundException {
        
        int playerMiddle = numberOfPlayers / 2;
        try {
            FileOutputStream outputFile = new FileOutputStream("Player-List" + numberOfPlayers + ".data");
            ObjectOutputStream out = new ObjectOutputStream(outputFile);

            players = new ArrayList<Player>(); 
            
            // ArrayList for checking if a Player already exists
            playerNames = new int[numberOfPlayers + 1]; 
            
            // holds the Players to eventually be referenced to
            playerObjects = new Player[numberOfPlayers + 1]; 

            int countPlayer = 0;
            if (seeded == true) {
                rand = new Random(seed);
            } else {
                rand = new Random();
            }
            double nextRandom;
            int playerInt;
            Player nextPlayer;

            while (countPlayer < numberOfPlayers) {
                nextRandom = (rand.nextGaussian() * standardDeviation) + playerMiddle; 

                playerInt = (int) nextRandom;
                if (nextRandom > 0 && nextRandom < numberOfPlayers) {
                    if (playerNames[playerInt] == 0) { // if not already present
                        nextPlayer = new Player(playerInt + "");
                        playerObjects[playerInt] = nextPlayer; //adds player to index
                    } else { // if already present
                        
                        nextPlayer = playerObjects[playerInt]; //use the existing player
                    }
                    playerNames[playerInt]++;
                    countPlayer++;
                    players.add(nextPlayer);
                }
            }
            
            showDebugInfo();
            out.writeObject(players); 
            out.close();
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    private void showDebugInfo() {
        if (debugLevel >= SHOW_DISTRIBUTION) {
            System.out.println("Printing the full distribution");
            for (int i = 0; i < playerNames.length; i++) {
                System.out.println("playerNames[" + i + "] = " + playerNames[i]);
            }
            System.out.println();
        }

        if (debugLevel >= SHOW_PLAYERS) {
            System.out.println("Generated and serialized players");
            System.out.println();
            for (Player p : players) {
                System.out.println(p);
            }
        }
    }


    /**
     * Main driver of the program
     */
    public static void main(String[] args) {
        
        PlayerGenerator generator = new PlayerGenerator();
        if (args.length < 2 || args.length > 4) {
            generator.showUsage();
        }
        generator.processArguments(args);
        try {
            generator.dumpOutputFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Randomly generated " + generator.numberOfPlayers + " serialized to output file: " + 
                           "Player-List" + args[0] + ".data");
        System.out.println("--------------------------------------------------------------------------------");
    }
}