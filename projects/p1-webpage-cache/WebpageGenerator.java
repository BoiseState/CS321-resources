import java.util.Random;

/**
 * Webpage Generator creates a list of Webpages with a Gaussian distribution.
 *
 * @author CS321 instructors
 */
public class WebpageGenerator {

    private Random rand;
    private int numberWebpages;
    private double standardDeviation;
    private Webpage[] webpageDatabase;
    private int[] webpageDatabasePings;
    private LoremIpsumGenerator lorem;

    private final int PAGE_NUMBER_INDEX = 27;
    private final int MS_DELAY = 2;

    /**
     * Generates the Webpages.
     *
     * @param numberWebpages    - number of Webpages to generate
     * @param standardDeviation - standard deviation to use
     */
    public WebpageGenerator(int numberWebpages, double standardDeviation) {
        this.numberWebpages = numberWebpages;
        this.standardDeviation = standardDeviation;

        // All generated Webpages stored in Webpage array
        webpageDatabase = new Webpage[numberWebpages + 1];
        // Tally counter
        webpageDatabasePings = new int[numberWebpages + 1];

        rand = new Random();
        lorem = new LoremIpsumGenerator();
    }

    /**
     * Generates the Webpages.
     *
     * @param numberWebpages    - number of websites to generate
     * @param standardDeviation - standard deviation to use
     * @param seed              - seed (optional)
     */
    public WebpageGenerator(int numberWebpages, double standardDeviation, long seed) {
	//call the other constructor first
        this(numberWebpages, standardDeviation);
        rand = new Random(seed);
    }

    /**
     * Gets a URL to load.
     *
     * @return websiteURL - URL to load
     */
    public String getURL() {
        double tempNumber = (rand.nextGaussian() * standardDeviation) + (numberWebpages / 2);
        int webInt = (int) tempNumber;
        String websiteURL = "https://someserver.com/page" + webInt;
        // Tallies ping for the Webpage occurrence
        webpageDatabasePings[webInt]++;
        return websiteURL;
    }

    /**
     * Reads a Webpage.
     *
     * @param url - the URL of new Webpage
     */
    public Webpage readPage(String url) {
        // 2ms delay for reading a new Webpage to simulate reading a file
        try {
            Thread.sleep(MS_DELAY);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Generates a new Webpage
        int pageNum = getPage(url);
        Webpage tempWebby = new Webpage(url, loadContent(pageNum));

        if (webpageDatabasePings[pageNum] == 1) {
            // Adds Webpage to the Database, i.e. https://someserver.com/page4
            webpageDatabase[pageNum] = tempWebby;
        }
        return tempWebby;
    }

    /**
     * Gets the page number of that Webpage.
     *
     * @param url - a Webpage's URL
     * @return the page number of that Webpage
     */
    public int getPage(String url) {
        String pageNumString = url.substring(PAGE_NUMBER_INDEX);
        return Integer.parseInt(pageNumString);
    }

    /**
     * Gets the content of a Webpage based on its page number.
     *
     * @param pageNum - a Webpage's number
     * @return Lorem Ipsum content for that Webpage
     */
    public String loadContent(int pageNum) {
        return lorem.getLoremIpsum(pageNum);
    }

    /**
     * Prints out each Webpage.
     */
    public void printWebpages(int debugLevel) {
        for (int i = 1; i < webpageDatabase.length; i++) {
            // Skips non-existent entries
            if (webpageDatabase[i] != null) {
                Webpage temp = webpageDatabase[i];
                if (debugLevel == 2) {
                    System.out.println(temp.getWebpageURL() + ":" + temp.getSummarizedWebpageContent());
                }
                if (debugLevel == 3) {
                    System.out.println(temp.getWebpageURL() + ":" + temp.getWholeWebpageContent());
                }
            }
        }
    }

    /**
     * Prints out the frequency of the Webpages for debugging.
     */
    public void getWebpageDatabasePings() {
        for (int i = 1; i < webpageDatabasePings.length; i++) {
            // Skips non-pinged entries
            if (webpageDatabasePings[i] != 0) {
                System.out.println("[../page" + i + "]: " + webpageDatabasePings[i]);
            }
        }
    }
}
