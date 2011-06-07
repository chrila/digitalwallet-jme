package src;

import java.util.Date;

/**
 * This class contains several static methods that are often used
 * @author Christian Lampl
 * @version 2011.05.25
 */

public final class Util
{
    /**
     * An array defining the standard categories to be used by default
     */
    public static final String[] DEFAULT_CATEGORIES = {"Food", "Clothes", "Transport", "School", "Office"};
    
    /**
     * An array defining the standard settings values
     * { Active wallet, currency }
     */
    public static final String[] DEFAULT_SETTINGS = {"User_default", "EUR"};

    /**
     * A default wallet to be stored in the DAL if none exists
     */
    public static final Wallet DEFAULT_WALLET = new Wallet("default", "User", 100, Wallet.BUDGET_TYPE_MONTHLY);

    /**
     * A String defining the header of the export-file (CSV)
     */
    public static final String FILE_HEADER = "Value,Category,Location,Date/Time";
    
    /**
     * Converts a date to a String of the form YYYYMMddhhmm
     * @param date a Date object
     * @return a String in the form YYYYMMddhhmm
     */
    public static String dateToString(Date date)
    {
        String str, dat, month;

        // Date.toString() gives a String in the form:
        // dow mon dd hh:mm:ss zzz yyyy

        // year
        dat= date.toString().substring(date.toString().length()-4, date.toString().length());

        str= date.toString().substring(4, 7);
        // Converting 3-char month name to number
        if (str.equals("Jan"))
            month= "01";
        else if (str.equals("Feb"))
            month= "02";
        else if (str.equals("Mar"))
            month= "03";
        else if (str.equals("Apr"))
            month= "04";
        else if (str.equals("May"))
            month= "05";
        else if (str.equals("Jun"))
            month= "06";
        else if (str.equals("Jul"))
            month= "07";
        else if (str.equals("Aug"))
            month= "08";
        else if (str.equals("Sep"))
            month= "09";
        else if (str.equals("Oct"))
            month= "10";
        else if (str.equals("Nov"))
            month= "11";
        else if (str.equals("Dec"))
            month= "12";
        else month= "00";

        // month
        dat+= "-"+month;
        // day
        dat+= "-"+date.toString().substring(8, 10);
        // hour
        dat+= " "+date.toString().substring(11, 13);
        // minute
        dat+= ":"+date.toString().substring(14, 16);
        // second
        //dat+= date.toString().substring(17, 18);

        return dat;
    }

    /**
     * Regular String.split(char), which is not existent in standard J2ME.
     * Splits a String into the parts separated by @param separator.
     * @param str the String to be split
     * @param separator the separator
     * @return a String array containing the separated part-Strings
     */
    public static String[] splitString(String str, char separator)
    {
        int c= 0;
        int pre;
        int i;
        String[] split;

        // finding out how many parts we will get
        for (i=0; i<str.length(); i++)
        {
            if (str.charAt(i) == separator)
                c++;
        }
        // e.g. if there are 5 separators, there have to be 6 parts.
        split= new String[c+1];

        pre= -1;
        c= 0;
        for (i=0; i<str.length(); i++)
        {
            if (str.charAt(i) == separator)
            {
                split[c]= str.substring(pre+1, i);
                pre= i;
                c++;
            }
        }

        split[c]= str.substring(pre+1, i);

        return split;
    }

    /**
     * Creates a store name out of the owner and the name of the Wallet
     * @param w a Wallet object
     * @return a suitable store name
     */
    public static String createStoreName(Wallet w)
    {
        return w.getOwner() + "_" + w.getName();
    }
}
