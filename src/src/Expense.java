package src;

import java.util.Date;

/**
 * Describes an "expense", which represents an event where money was spent.
 * @author Christian Lampl
 * @version 2011.05.25
 */
public class Expense
{
    private float value;
    private String category;
    private String location;
    /**
     * The date in the form: YYYYMMddhhmm
     */
    private String date;

    /**
     * Creates a new Expense with default values.
     */
    public Expense()
    {
        value= 0.0f;
        category= "";
        location= "";
        date= Util.dateToString(new Date(System.currentTimeMillis()));
    }

    /**
     * Creates a new Expense with the given values.
     * @param value
     * @param category
     * @param location
     * @param date
     */
    public Expense(float value, String category, String location, String date)
    {
        this.value= value;
        this.category= category;
        this.location= location;
        this.date= date;
    }

    /**
     * Creates a new Expense from a dataset string
     * @param str a dataset string
     * @see #toString()
     */
    public Expense(String str)
    {
        this(Float.parseFloat(Util.splitString(str, ',')[0]),
             Util.splitString(str, ',')[1],
             Util.splitString(str, ',')[2],
             Util.splitString(str, ',')[3]
            );
    }

    /**
     * Checks if the date of the given expense is before this one
     * @param otherExpense the expense to be compared
     * @return true, if this expense is dated before the given one
     */
    public boolean isBefore(Expense otherExpense)
    {
        return true;
    }

    /**
     * Converts the record into a String to be written into the text file;
     * form: value,category,location,date
     * @return a String with comma-separated values (--> CSV)
     */
    public String toString()
    {    
        return String.valueOf(value) + "," + category + "," + location + "," + date;
    }
    
    /**
     * Converts the record into a formatted String
     * Fields: %v (value), %c (category), %l (location), %d (date)
     * @param format the format string containing fields (see above)
     * @return nicely formatted String
     */
    public String toString(String format)
    {
        StringBuffer result= new StringBuffer();
        format.trim();
        char c;
        
        // go through format-String
        for (int i= 0; i<format.length(); i++)
        {
            c= format.charAt(i);
            if ((c == '%') && (i < format.length()-1))
            {
                switch (format.charAt(i+1))
                {
                    // if a reasonable field is found, replace it with the corresponding value and skip the next character
                    case 'v':
                        result.append(value);
                        i++;
                        break;
                        
                    case 'c':
                        result.append(category);
                        i++;
                        break;
                        
                    case 'l':
                        result.append(location);
                        i++;
                        break;
                        
                    case 'd':
                        result.append(date);
                        i++;
                        break;
                        
                    default:
                        result.append(format.charAt(i));
                        break;
                }
            } else
            {
                result.append(format.charAt(i));
            }
        }
        
        return result.toString();
    }

    /**
     * Getter for value
     * @return the value of the expense
     */
    public float getValue()
    {
        return value;
    }
    
    /**
     * Getter for category
     * @return the category of the expense
     */
    public String getCategory()
    {
        return category;
    }
    
    /**
     * Getter for location
     * @return the location the expense was taken at
     */
    public String getLocation()
    {
        return location;
    }
    
    /**
     * Getter for date
     * @return the date on which the expense was taken
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Setter for value
     * @param value the value of the expense
     */
    public void setValue(float value)
    {
        this.value= value;
    }
    
    /**
     * Setter for category
     * @param category the category of the expense
     */
    public void setCategory(String category)
    {
        this.category= category;
    }
    
    /**
     * Setter for location
     * @param location the location the expense was taken at
     */
    public void setLocation(String location)
    {
        this.location= location;
    }
    
    /**
     * Setter for date
     * @param date the date the expense was taken on
     */
    public void setDate(String date)
    {
        this.date= date;
    }
}
