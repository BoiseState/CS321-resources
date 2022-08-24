import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Player Generator class generate an ArrayList of Players
 * objects that are then dumped to an output file
 *
 * @author CS321 instructors
 */
public class PlayerGenerator {

    private int numberOfPlayers;
    private double standardDeviation;
    private long seed;
    private boolean seeded;

    private Random rand;
    private ArrayList<Player> players;
    private boolean[] playerNames;
    private Player[] playerObjects;

    /**
     * Show usage for the program.
     */
    public void showUsage() {
        System.out.println("$ java PlayerGenerator " +
                "<number-of-players> " +
                "<standard-deviation> " +
                "[<seed>]");
        System.exit(1);
    }

    /**
     * Processes the command line arguments
     *
     *  @param args - String arguments:
     *  args[0]: <number-of-players>
     *  args[1]: <standard-deviation>
     *  args[2]: [<seed>]
     */
    private void processArguments(String[] args) {
        numberOfPlayers = Integer.parseInt(args[0]);
        if (numberOfPlayers < 1) {
            throw new IllegalArgumentException("Illegal argument: number-of-players must >= 1.");
        }

        standardDeviation = Double.parseDouble(args[1]);

        if(args.length == 3) { //optional seed
            seed = Long.parseLong(args[2]);
            seeded = true;
        }
    }

    /**
     * Creates a dump file containing an ArrayList of Player objects to be
     * put in the Cache using a Gaussian Distribution with an alterable
     * standard deviation and mean depending on the middle player number
     */
    public void dumpOutputFile() throws FileNotFoundException {
        int playerMiddle = numberOfPlayers/2;
        try {
            FileOutputStream outputFile = new FileOutputStream("Player-List" + numberOfPlayers + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(outputFile);

            players = new ArrayList<Player>(); //Players-List in dump file
            playerNames = new boolean[numberOfPlayers+1]; //ArrayList for checking if a Player already exists
            playerObjects = new Player[numberOfPlayers+1]; //holds the Players to eventually be referenced to

            int countPlayer = 0;
            if (seeded == true) {
                rand = new Random(seed);
            } else {
                rand = new Random();
            }
            double tempNumber;
            int playerInt;
            Player tempPlayer;

            while(countPlayer < numberOfPlayers) {
                tempNumber = (rand.nextGaussian() * standardDeviation) + playerMiddle; //the Standard Deviation and Mean can be altered
                playerInt = (int) tempNumber;
                if(tempNumber > 0 && tempNumber < numberOfPlayers) {
                    if (!playerNames[playerInt]) { //if not already present
                        tempPlayer = new Player(playerInt + "");
                        playerNames[playerInt] = true; //adds true to that player's index
                        playerObjects[playerInt] = tempPlayer; //adds player to index
                    } else { //if already present
                        tempPlayer = playerObjects[playerInt]; //retrieves player if found
                    }
                    countPlayer++;
                    players.add(tempPlayer); //adds to Player ArrayList
                }
            }
            out.writeObject(players); //writes array list to file
            out.close(); //closes stream
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Main driver of the program
     */
    public static void main(String[] args) {
        PlayerGenerator generator = new PlayerGenerator();
        if (args.length < 2 || args.length > 3) { generator.showUsage(); }
        generator.processArguments(args);
        try {
            generator.dumpOutputFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Players dumped to output file:\n" + "Player-List" + args[0] + ".txt");
        System.out.println("----------------------------------------------------");
    }
}