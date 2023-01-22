/**
 * Creates and maintains a 20hr clock from 6:00AM - 2:00AM
 * within the Stardew Valley Universe.  Provides a time
 * management system to do daily tasks within 20 hours.
 *
 * @author CS321 instructors
 */
public class StardewDailyClock {

    private int currentClockNumber;

    /**
     * Constructor for Stardew Daily Clock
     */
    public StardewDailyClock() {
        this.currentClockNumber = 0;
    }

    /**
     * Returns a boolean for if the day is over (i.e. 20 hours)
     *
     * @return  true when hour is 20, false when less than 20
     */
    public boolean getNight() {
        if (currentClockNumber >= 20) { return true; }
        else { return false; }
    }

    /**
     * Increases the hour of the clock by
     * incrementing the current clock number by 1
     */
    public void incrementCurrentClockNumber()
    {
        currentClockNumber++;
    }

    /**
     * Resets the clock at the end of the day by
     * setting the current clock number to 0
     */
    public void resetCurrentClockNumber() { currentClockNumber = 0;}

    /**
     * Sets the current clock number
     * @param newHour - the current clock's hour
     */
    public void setCurrentClockNumber(int newHour) { currentClockNumber = newHour;}

    /**
     * Creates a readable time value of the clock based on the
     * clock's current hour.
     *
     * @param currentClockNumber - the current clock's hour
     * @return clockTime - a String of the clock's current time
     */
    public String toString(int currentClockNumber) {
        String clockTime = "";
        if(currentClockNumber == 0 || currentClockNumber == 12) { clockTime = "6:00"; }
        if(currentClockNumber == 1 || currentClockNumber == 13) { clockTime = "7:00"; }
        if(currentClockNumber == 2 || currentClockNumber == 14) { clockTime = "8:00"; }
        if(currentClockNumber == 3 || currentClockNumber == 15) { clockTime = "9:00"; }
        if(currentClockNumber == 4 || currentClockNumber == 16) { clockTime = "10:00"; }
        if(currentClockNumber == 5 || currentClockNumber == 17) { clockTime = "11:00"; }
        if(currentClockNumber == 6 || currentClockNumber == 18) { clockTime = "12:00"; }
        if(currentClockNumber == 7 || currentClockNumber == 19) { clockTime = "1:00"; }
        if(currentClockNumber == 8 || currentClockNumber == 20) { clockTime = "2:00"; }
        if(currentClockNumber == 9) { clockTime = "3:00"; }
        if(currentClockNumber == 10) { clockTime = "4:00"; }
        if(currentClockNumber == 11) { clockTime = "5:00"; }
        if(currentClockNumber < 6 || currentClockNumber > 17) { clockTime = clockTime + "AM"; }
        if(currentClockNumber > 5 && currentClockNumber < 18) { clockTime = clockTime + "PM"; }
        return clockTime;
    }
}
