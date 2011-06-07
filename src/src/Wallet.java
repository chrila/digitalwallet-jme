package src;

import java.util.Date;
import java.util.Vector;

/**
 * Describes a "wallet", which contains expenses.
 * 
 * @see Expense
 * @author Christian Lampl
 * @version 2011.05.25
 */
public class Wallet
{
    /**
     * budget is per-day
     */
    public static final byte BUDGET_TYPE_DAILY = 0;
    
    /**
     * budget is per month
     */
    public static final byte BUDGET_TYPE_MONTHLY = 1;
    
    /**
     * budget is per year
     */
    public static final byte BUDGET_TYPE_YEARLY = 2;
    
    /**
     * budget is per week
     */
    public static final byte BUDGET_TYPE_WEEKLY = 3;

    private float budget;
    private byte budgetType;
    private String name;
    private String owner;
    private Vector expenses;

    /**
     * Creates a new wallet
     * @param name the name of the wallet
     * @param owner the owner of the wallet
     * @param budget the value of the budget
     * @param budgetType the type of the budget (see above)
     */
    public Wallet(String name, String owner, float budget, byte budgetType)
    {
        this.name= name;
        this.owner= owner;
        this.budget= budget;
        this.budgetType= budgetType;
        expenses= new Vector();
    }

    /**
     * Creates a wallet from a dataset string
     * @param str a string, containing the data comma-separated
     * @see #toString()
     */
    public Wallet(String str)
    {
        this(Util.splitString(str, ',')[0],
             Util.splitString(str, ',')[1],
             Float.parseFloat(Util.splitString(str, ',')[2]),
             Byte.parseByte(Util.splitString(str, ',')[3])
            );
    }

    /**
     * Creates a new Wallet with default values
     */
    public Wallet()
    {
        this.budget= 0;
        this.budgetType= Wallet.BUDGET_TYPE_MONTHLY;
        this.name= "";
        this.owner= "";
    }

    /**
     * Creates a String containing all information of the wallet
     * @return a String of the form: name,owner,budget,budgetType
     */
    public String toString()
    {
        return name+","+owner+","+String.valueOf(budget)+","+String.valueOf(budgetType);
    }

    /**
     * Adds a Expense to the collection of the Wallet.
     * @param ex the Expense to be added
     */
    public void addExpense(Expense ex)
    {
        expenses.addElement(ex);
    }

    /**
     * Returns the total sum of expenses
     * @return the total sum of all expenses
     */
    public float getTotalExpenses()
    {
        float sum= 0;

        for (int i=0; i<expenses.size(); i++)
        {
            sum+= ((Expense)expenses.elementAt(i)).getValue();
        }

        return sum;
    }

    /**
     * Returns the spent money according to the budget type, e.g. if budget
     * type is BUDGET_TYPE_MONTHLY, this function returns how much money was
     * spent this month.
     * @return a String containing the information
     */
    public float getExpenseThisPeriod()
    {
        // The date of the expenses is stored in the form: YYYY-MM-dd hh:mm

        switch (budgetType)
        {
            case BUDGET_TYPE_DAILY:
                return sumPeriodValues(8, 10);
            case BUDGET_TYPE_WEEKLY:
                return sumPeriodValues(4, 6);
            case BUDGET_TYPE_MONTHLY:
                return sumPeriodValues(5, 7);
            case BUDGET_TYPE_YEARLY:
                return sumPeriodValues(0, 4);
        }

        // if nothing matches (should not ever happen)
        return 0;
    }

    /**
     * Sums up the values of the expenses, where the specified portions of the
     * date match the current date.
     * @param startIndex the start index of the equation-string
     * @param endIndex the end index of the equation-string
     * @return the sum
     */
    private float sumPeriodValues(int startIndex, int endIndex)
    {
        float sum= 0;
        String now= Util.dateToString(new Date(System.currentTimeMillis()));
        String elemDate;

        // debugging output
        //System.out.println("Current date: "+now);
        //System.out.println("Criterium: start "+startIndex+", end "+endIndex);

        for (int i=0; i<expenses.size(); i++) {
            elemDate= ((Expense)(expenses.elementAt(i))).getDate();
            if (elemDate.substring(startIndex, endIndex).equals(now.substring(startIndex, endIndex))) {
                sum+= ((Expense)(expenses.elementAt(i))).getValue();
                //System.out.println(elemDate.substring(startIndex, endIndex)+" = "+now.substring(startIndex, endIndex));
            }
        }

        return sum;
    }

    /**
     * Returns, how much money there is left in the current period
     * @return a float value
     */
    public float getBudgetLeft()
    {
        return budget-getExpenseThisPeriod();
    }

    /**
     * Removes a given expense from the collection
     * @param expString the value of the expense.toString()
     * @return true, if the expense was found and removed, false if not.
     */
    public boolean removeExpense(String expString)
    {
        for (int i=0; i<expenses.size(); i++)
        {
            if (((Expense)expenses.elementAt(i)).toString().equals(expString))
            {
                expenses.removeElementAt(i);
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all expenses
     * @return a Vector including all records
     */
    public Vector getExpenses()
    {
        return expenses;
    }

    /**
     * Returns the name of the Wallet
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the owner of the Wallet
     * @return
     */
    public String getOwner()
    {
        return owner;
    }

    /**
     * Returns the budget
     * @return
     */
    public float getBudget()
    {
        return budget;
    }

    /**
     * Converts the budget type to a String
     * @return a String version of the budget type (e.g. day, month, etc)
     */
    public String getBudgetType()
    {
        switch (budgetType)
        {
            case BUDGET_TYPE_DAILY:
                return "day";
            case BUDGET_TYPE_WEEKLY:
                return "week";
            case BUDGET_TYPE_MONTHLY:
                return "month";
            case BUDGET_TYPE_YEARLY:
                return "year";
        }

        return null;
    }

    /**
     * Setter for budget
     * @param budget value of the budget
     */
    public void setBudget(float budget)
    {
        this.budget = budget;
    }

    /**
     * Setter for budgetType
     * @param budgetType the type of the budget
     */
    public void setBudgetType(byte budgetType)
    {
        this.budgetType = budgetType;
    }

    /**
     * Setter for name
     * @param name the name of the wallet
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Setter for owner
     * @param owner the owner of the wallet
     */
    public void setOwner(String owner)
    {
        this.owner = owner;
    }


}
