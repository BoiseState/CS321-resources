/**
 * A simple class that represents a web site page, which 
 * consists of the  web site page URL and the contents as a string.
 *
 * @author CS321 instructors
 */
public class Webpage implements KeyInterface<String> {

    private String webpageURL;
    private String webpageContent;

    /**
     * Constructor for Webpage with null content.
     *
     * @param webpageURL - name of Webpage
     */
    public Webpage(String webpageURL) {
        this.webpageURL = webpageURL;
        webpageContent = null;
    }

    /**
     * Overloaded constructor for Webpage.
     *
     * @param webpageURL     - name of Webpage
     * @param webpageContent - content of Webpage
     */
    public Webpage(String webpageURL, String webpageContent) {

        this.webpageURL = webpageURL;
        this.webpageContent = webpageContent;
    }

    /**
     * Gets the Webpage URL.
     *
     * @return webpageURL - the Webpage's URL
     */
    public String getWebpageURL() {
        return this.webpageURL;
    }

    /**
     * Gets the Webpage's summarized contents (returns the first 200 characters).
     *
     * @return String containing the summarized contents
     */
    public String getSummarizedWebpageContent() {
        return webpageContent.substring(0, 200) + "...";
    }


    /**
     * Gets the entire content of the Webpage.
     *
     * @return String containing the entire contents
     */
    public String getWholeWebpageContent() {
        return webpageContent;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return webpageURL + " [...]";
    }

    /**
     * Find if the given Webpage is equal to the current one
     * by comparing the URLs of the two Webpages.
     *
     * @param other - the Webpage to compare to
     * @return  - true if equal, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        Webpage webby = (Webpage) other;
        return webpageURL.equals(webby.webpageURL);
    }

    /**
     * Returns this Webpages Key
     */
    @Override
    public String getKey() {
        return webpageURL;
    }
}
