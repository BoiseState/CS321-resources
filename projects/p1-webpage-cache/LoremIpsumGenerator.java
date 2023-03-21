import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a Lorem Ipsum Generator for creating random patterns of words to fill a Webpage.
 *
 * @author CS321 instructors
 */
public class LoremIpsumGenerator {

    private Random rand;
    private ArrayList<String> fruitList;
    private Scanner scanner;

    private final int FRUIT_SIZE;

    private final int WORD_MAX = 200;
    private final int UPPER_BOUND_PARAGRAPH = 7;
    private final int BOUND_PARAGRAPH = 4;
    private final int UPPER_BOUND_SENTENCE = 11;
    private final int BOUND_SENTENCE = 5;

    private final int COMMA_FORMATTER = 20;

    /**
     * Parses a text file and places the entries
     * into an ArrayList when generated.
     */
    public LoremIpsumGenerator() {
        // Reads file
        fruitList = new ArrayList<>();
        int fruitCount = 0;
        try {
            scanner = new Scanner(new File("fruits.txt"));
            while(scanner.hasNext()) {
                String fruit = scanner.next();
                fruitList.add(fruit);
                fruitCount++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FRUIT_SIZE = fruitCount;
        scanner.close();
    }

    /**
     * Generate random content for a web page.
     *
     * @param seed - seed
     * @return content - a Webpage's content
     */
    public String getLoremIpsum(long seed) {
        rand = new Random(seed);

        String content = "";

        int wordCount = 0;
        while(wordCount < WORD_MAX) {
            int paragraph = 0;

            // Gets the amount of sentences per paragraph
            int paragraphNum = rand.nextInt(UPPER_BOUND_PARAGRAPH) + BOUND_PARAGRAPH;

            while(paragraph < paragraphNum) {
                // Gets the amount of words per sentence
                int wordSent = rand.nextInt(UPPER_BOUND_SENTENCE) + BOUND_SENTENCE;

                // Gets a specific sentence
                String sentence = getLoremSentence(wordSent);

                content = content + " " + sentence;
                wordCount = wordCount + wordSent;
                paragraph++;
            }
            content = content + "\n";
        }
        return content;
    }

    /**
     * Gets a sentence with specified amount of words
     *
     * @param sentenceLength - length of words within a sentence
     * @return stringSentence - a generated sentence
     */
    public String getLoremSentence(int sentenceLength) {
        String stringSentence = "";
        int counting = 0;
        while(counting < sentenceLength) {

            // Gets a random number
            int numInt = rand.nextInt() & Integer.MAX_VALUE;

            //Gets an index from the array list
            int numFruit = numInt % FRUIT_SIZE; //changed

            String tempFruit;
            // Ensures the capitalization of the first word
            if(counting == 0) { tempFruit = fruitList.get(numFruit); }
            else { tempFruit = fruitList.get(numFruit).toLowerCase(); }

            // Decides if a comma should be present
            if (numInt % COMMA_FORMATTER == 0 && counting != 0) { stringSentence = stringSentence + ","; }

            stringSentence = stringSentence + " " + tempFruit;
            counting++;
        }
        return stringSentence + ".";
    }
}
