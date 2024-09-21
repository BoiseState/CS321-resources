import java.util.Random;

/**
 * Web page generator that creates a list of web pages with a Gaussian (aka
 * <a href="https://www.scribbr.com/statistics/normal-distribution/">Normal</a>) distribution. 
 * This means that some web pages are generated more often than others -- which is just like 
 * some pages on a web site are visited a lot more often.
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
    
    private final int DISPLAY_SUMMARY = 2;
    private final int DISPLAY_FULL = 3;

    /**
     * Generates the web pages.
     *
     * @param numberWebpages    - number of web pages to generate
     * @param standardDeviation - standard deviation to use
     */
    public WebpageGenerator(int numberWebpages, double standardDeviation) {
        this.numberWebpages = numberWebpages;
        this.standardDeviation = standardDeviation;
        
        int mean = numberWebpages/2;
        if (mean < 3 * standardDeviation) {
            System.err.println("Warning! The standard deviation specified is less than 3 times the mean page number,\n"
            	+ "\t which (number of web pages)/2 (because the page number range is [1..number-of-web-pages]).\n"
            	+ "\t This will cause a significant amount of the generated data to not be usable, \n"
            	+ "\t which causes the simulation to be less realistic. \n"
            	+ "\t Please specify a smaller standard deviation to make the results be more useful.\n");
            
        }

        // All generated web pages are stored in the Webpage array
        webpageDatabase = new Webpage[numberWebpages + 1];
        // To keep track of number of times a page is looked up
        webpageDatabasePings = new int[numberWebpages + 1];

        rand = new Random();
        lorem = new LoremIpsumGenerator();
    }

    /**
     * Generates the web pages.
     *
     * @param numberWebpages    - number of web pages to generate
     * @param standardDeviation - standard deviation to use
     * @param seed              - seed (optional)
     */
    public WebpageGenerator(int numberWebpages, double standardDeviation, long seed) {
        //call the other constructor first
        this(numberWebpages, standardDeviation);
        rand = new Random(seed);
    }

    /**
     * Gets a URL to load. URLs are generated using the Gaussian distribution.
     *
     * @return websiteURL - URL to load
     */
    public String getURL() {
	//Note that Gaussian (or normal) distribution has a mean of 0.0 and a standard deviation of 1.0 so
	//we will get negative values. If the page numbers range from 0 to 99, we want to generate
	//page numbers with a mean of 50 and +- stddev. The issue is that we can still get a few outliers
	//on both the negative and positive sides. We will ignore them as it will not affect the overall 
	//distribution in any significant way.
	
	int webInt = -1;
	while (webInt < 0 || webInt > numberWebpages) {
	    double nextRandom = rand.nextGaussian() * standardDeviation;
	    double nextPage = nextRandom + (numberWebpages / 2);
	    webInt = (int) nextPage;
	}
            
        String websiteURL = "https://someserver.com/page" + webInt;
        // Increment lookups for the Webpage occurrence
        webpageDatabasePings[webInt]++;
        return websiteURL;
    }

    /**
     * Reads a web page.
     *
     * @param url - the URL of new Webpage
     */
    public Webpage readPage(String url) {
        // 2ms delay for reading a new Webpage to simulate reading a file from disk
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
     * Get the page number of a web page specified by its URL.
     *
     * @param url - a Webpage's URL
     * @return the page number of that web page
     */
    public int getPage(String url) {
        String pageNumString = url.substring(PAGE_NUMBER_INDEX);
        return Integer.parseInt(pageNumString);
    }
    

    /**
     * Get the content of a web page based on its page number.
     *
     * @param pageNum - a Webpage's number
     * @return Lorem Ipsum content for that Webpage
     */
    public String loadContent(int pageNum) {
        return lorem.getLoremIpsum(pageNum);
    }
    

    
    /**
     * Print out the web pages using the debug level parameter. 
     * @param debugLevel  DISPLAY_SUMMARY prints summary of each web page
     *                    DISPLAY_FULL prints full contents of each web page
     */
    public void printWebpages(int debugLevel) {
        for (int i = 1; i < webpageDatabase.length; i++) {
            // Skips non-existent entries
            if (webpageDatabase[i] != null) {
                Webpage temp = webpageDatabase[i];
                if (debugLevel == DISPLAY_SUMMARY) {
                    System.out.println(temp.getWebpageURL() + ":" + temp.getSummarizedWebpageContent());
                }
                if (debugLevel == DISPLAY_FULL) {
                    System.out.println(temp.getWebpageURL() + ":" + temp.getWholeWebpageContent());
                }
                System.out.println();
            }
        }
    }
    

    /**
     * Prints out the frequency distribution of the web pages for debugging.
     */
    public void getWebpageDatabasePings() {
        for (int i = 1; i < webpageDatabasePings.length; i++) {
            // Skips non-looked up Webpages
            if (webpageDatabasePings[i] != 0) {
                System.out.println("[../page" + i + "]: " + webpageDatabasePings[i]);
            }
        }
    }
}
