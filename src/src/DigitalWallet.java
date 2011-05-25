package src;

import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.rms.RecordStoreNotFoundException;

/**
 * Forms of Digital Wallet.
 *
 * @author Christian Lampl
 * @version 2011.05.25
 */
public class DigitalWallet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private Wallet wallet;
    private DAL dal;
    private String[] settings;
    private boolean editMode = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private java.util.Hashtable __previousDisplayables = new java.util.Hashtable();
    private Form frmMain;
    private StringItem lbBudget;
    private StringItem lbSpent;
    private StringItem lbActiveWallet;
    private StringItem lbDifference;
    private Form frmExpense;
    private TextField tfExpenseValue;
    private TextField tfExpenseLocation;
    private DateField dfExpenseDate;
    private ChoiceGroup choiceExpenseCategory;
    private Form frmWallet;
    private TextField tfWalletName;
    private ChoiceGroup choiceWalletBudgetType;
    private TextField tfWalletBudgetValue;
    private TextField tfWalletOwner;
    private List listPrintWallet;
    private Alert alert;
    private List listManageCategories;
    private TextBox tbEditValue;
    private List listSettings;
    private List listManageWallets;
    private Form frmAbout;
    private StringItem stringItem;
    private Form frmTimezone;
    private Command cmdNewExpense;
    private Command cmdSettings;
    private Command cmdOk;
    private Command cmdCancel;
    private Command exitCommand;
    private Command cmdEditCategory;
    private Command cmdDeleteCategory;
    private Command cmdNewCategory;
    private Command cmdNewWallet;
    private Command cmdEditWallet;
    private Command cmdDeleteWallet;
    private Command cmdActivateWallet;
    private Command cmdBack;
    private Command okCommand;
    private Command cmdShowWallet;
    private Command cmdReset;
    private Image imgOk;
    private Image imgError;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The DigitalWallet constructor.
     */
    public DigitalWallet()
    {

    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    /**
     * Switches a display to previous displayable of the current displayable.
     * The <code>display</code> instance is obtain from the <code>getDisplay</code> method.
     */
    private void switchToPreviousDisplayable() {
        Displayable __currentDisplayable = getDisplay().getCurrent();
        if (__currentDisplayable != null) {
            Displayable __nextDisplayable = (Displayable) __previousDisplayables.get(__currentDisplayable);
            if (__nextDisplayable != null) {
                switchDisplayable(null, __nextDisplayable);
            }
        }
    }
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initializes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        dal= new DAL();

        if ((dal.getWalletList() == null) || (dal.getWalletList().length == 0))
            resetDAL();
        else {
            try
            {
                settings= dal.getSettings();
                activateWallet(settings[0]);
            } catch (Exception e)
            {
                displayError(e.getMessage(), getFrmMain());
            }
        }
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getFrmMain());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        Displayable __currentDisplayable = display.getCurrent();
        if (__currentDisplayable != null  &&  nextDisplayable != null) {
            __previousDisplayables.put(nextDisplayable, __currentDisplayable);
        }
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmMain ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of frmMain component.
     * @return the initialized component instance
     */
    public Form getFrmMain() {
        if (frmMain == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            frmMain = new Form("DigitalWallet", new Item[] { getLbActiveWallet(), getLbBudget(), getLbSpent(), getLbDifference() });//GEN-BEGIN:|14-getter|1|14-postInit
            frmMain.addCommand(getCmdNewExpense());
            frmMain.addCommand(getCmdSettings());
            frmMain.addCommand(getExitCommand());
            frmMain.addCommand(getCmdShowWallet());
            frmMain.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return frmMain;
    }
    //</editor-fold>//GEN-END:|14-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmExpense ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of frmExpense component.
     * @return the initialized component instance
     */
    public Form getFrmExpense() {
        if (frmExpense == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            frmExpense = new Form("New Expense", new Item[] { getTfExpenseValue(), getChoiceExpenseCategory(), getTfExpenseLocation(), getDfExpenseDate() });//GEN-BEGIN:|15-getter|1|15-postInit
            frmExpense.addCommand(getCmdOk());
            frmExpense.addCommand(getCmdCancel());
            frmExpense.setCommandListener(this);//GEN-END:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return frmExpense;
    }
    //</editor-fold>//GEN-END:|15-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfExpenseValue ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of tfExpenseValue component.
     * @return the initialized component instance
     */
    public TextField getTfExpenseValue() {
        if (tfExpenseValue == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            tfExpenseValue = new TextField("Value [EUR]", null, 32, TextField.DECIMAL);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return tfExpenseValue;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == alert) {//GEN-BEGIN:|7-commandAction|1|72-preAction
            if (command == cmdOk) {//GEN-END:|7-commandAction|1|72-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|2|72-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|173-preAction
        } else if (displayable == frmAbout) {
            if (command == cmdBack) {//GEN-END:|7-commandAction|3|173-preAction
                // write pre-action user code here
                switchDisplayable(null, getListSettings());//GEN-LINE:|7-commandAction|4|173-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|29-preAction
        } else if (displayable == frmExpense) {
            if (command == cmdCancel) {//GEN-END:|7-commandAction|5|29-preAction
                clearExpenseForm();
                switchDisplayable(null, getFrmMain());//GEN-LINE:|7-commandAction|6|29-postAction
                // write post-action user code here
            } else if (command == cmdOk) {//GEN-LINE:|7-commandAction|7|27-preAction
                if (checkExpenseForm()) {
                    // create new Expense
                    if (!editMode)
                        createExpense();
                    // clear Form
                    clearExpenseForm();
                    editMode= false;
                    switchDisplayable(null, getFrmMain());
                }
//GEN-LINE:|7-commandAction|8|27-postAction
                // Save the entered Record
            }//GEN-BEGIN:|7-commandAction|9|21-preAction
        } else if (displayable == frmMain) {
            if (command == cmdNewExpense) {//GEN-END:|7-commandAction|9|21-preAction
                switchDisplayable(null, getFrmExpense());//GEN-LINE:|7-commandAction|10|21-postAction
                dfExpenseDate.setDate(new Date(System.currentTimeMillis()));
                displayCategoryList(getChoiceExpenseCategory());
                getDisplay().setCurrentItem(tfExpenseValue);
            } else if (command == cmdSettings) {//GEN-LINE:|7-commandAction|11|23-preAction
                // write pre-action user code here
                switchDisplayable(null, getListSettings());//GEN-LINE:|7-commandAction|12|23-postAction
                // write post-action user code here
            } else if (command == cmdShowWallet) {//GEN-LINE:|7-commandAction|13|162-preAction
                printWallet(getListPrintWallet());
                switchDisplayable(null, getListPrintWallet());//GEN-LINE:|7-commandAction|14|162-postAction
                // write post-action user code here
            } else if (command == exitCommand) {//GEN-LINE:|7-commandAction|15|67-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|16|67-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|17|135-preAction
        } else if (displayable == frmWallet) {
            if (command == cmdCancel) {//GEN-END:|7-commandAction|17|135-preAction
                clearWalletForm();
                switchDisplayable(null, getListManageWallets());//GEN-LINE:|7-commandAction|18|135-postAction
                // write post-action user code here
            } else if (command == cmdOk) {//GEN-LINE:|7-commandAction|19|134-preAction
                if (checkWalletForm()) {
                    // create new Wallet
                    createWallet();
                    clearWalletForm();
                    switchDisplayable(null, getListManageWallets());
                    displayWalletList(getListManageWallets());
                }
//GEN-LINE:|7-commandAction|20|134-postAction

            }//GEN-BEGIN:|7-commandAction|21|75-preAction
        } else if (displayable == listManageCategories) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|21|75-preAction
                // write pre-action user code here
                listManageCategoriesAction();//GEN-LINE:|7-commandAction|22|75-postAction
                // write post-action user code here
            } else if (command == cmdBack) {//GEN-LINE:|7-commandAction|23|167-preAction
                writeCategories();
                switchDisplayable(null, getListSettings());//GEN-LINE:|7-commandAction|24|167-postAction
                // write post-action user code here
            } else if (command == cmdDeleteCategory) {//GEN-LINE:|7-commandAction|25|82-preAction
                listManageCategories.delete(listManageCategories.getSelectedIndex());
//GEN-LINE:|7-commandAction|26|82-postAction
                // write post-action user code here
            } else if (command == cmdEditCategory) {//GEN-LINE:|7-commandAction|27|86-preAction
                tbEditValue= getTbEditValue();
                tbEditValue.setTitle("Enter new category name: ");
                tbEditValue.setConstraints(javax.microedition.lcdui.TextField.ANY);
                tbEditValue.setString(listManageCategories.getString(listManageCategories.getSelectedIndex()));

                switchDisplayable(null, tbEditValue);
//GEN-LINE:|7-commandAction|28|86-postAction
                // write post-action user code here
            } else if (command == cmdNewCategory) {//GEN-LINE:|7-commandAction|29|84-preAction
                listManageCategories.setSelectedIndex(listManageCategories.append("", null), true);
                tbEditValue= getTbEditValue();
                tbEditValue.setTitle("Enter new category name: ");
                tbEditValue.setConstraints(javax.microedition.lcdui.TextField.ANY);
                tbEditValue.setString("");
                switchDisplayable(null, tbEditValue);
//GEN-LINE:|7-commandAction|30|84-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|126-preAction
        } else if (displayable == listManageWallets) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|31|126-preAction
                // write pre-action user code here
                listManageWalletsAction();//GEN-LINE:|7-commandAction|32|126-postAction
                // write post-action user code here
            } else if (command == cmdActivateWallet) {//GEN-LINE:|7-commandAction|33|144-preAction
                activateWallet(listManageWallets.getString(listManageWallets.getSelectedIndex()));
//GEN-LINE:|7-commandAction|34|144-postAction
                // write post-action user code here
            } else if (command == cmdBack) {//GEN-LINE:|7-commandAction|35|168-preAction
                // write pre-action user code here
                switchDisplayable(null, getListSettings());//GEN-LINE:|7-commandAction|36|168-postAction
                // write post-action user code here
            } else if (command == cmdDeleteWallet) {//GEN-LINE:|7-commandAction|37|141-preAction
                try
                {
                    dal.deleteWallet(listManageWallets.getString(listManageWallets.getSelectedIndex()));
                } catch (Exception e)
                {
                    displayError(e.getMessage(), getListManageWallets());
                }
                displayWalletList(listManageWallets);
//GEN-LINE:|7-commandAction|38|141-postAction
                // write post-action user code here
            } else if (command == cmdEditWallet) {//GEN-LINE:|7-commandAction|39|139-preAction
                // write pre-action user code here
//GEN-LINE:|7-commandAction|40|139-postAction
                // write post-action user code here
            } else if (command == cmdNewWallet) {//GEN-LINE:|7-commandAction|41|137-preAction
                getDisplay().setCurrent(getFrmWallet());
//GEN-LINE:|7-commandAction|42|137-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|43|59-preAction
        } else if (displayable == listPrintWallet) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|43|59-preAction
                // write pre-action user code here
                listPrintWalletAction();//GEN-LINE:|7-commandAction|44|59-postAction
                // write post-action user code here
            } else if (command == cmdBack) {//GEN-LINE:|7-commandAction|45|171-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmMain());//GEN-LINE:|7-commandAction|46|171-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|47|110-preAction
        } else if (displayable == listSettings) {
            if (command == List.SELECT_COMMAND) {//GEN-END:|7-commandAction|47|110-preAction
                // write pre-action user code here
                listSettingsAction();//GEN-LINE:|7-commandAction|48|110-postAction
                // write post-action user code here
            } else if (command == cmdBack) {//GEN-LINE:|7-commandAction|49|155-preAction
                // write pre-action user code here
                switchDisplayable(null, getFrmMain());//GEN-LINE:|7-commandAction|50|155-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|51|87-preAction
        } else if (displayable == tbEditValue) {
            if (command == cmdOk) {//GEN-END:|7-commandAction|51|87-preAction
                // write pre-action user code here
                switchToPreviousDisplayable();//GEN-LINE:|7-commandAction|52|87-postAction
                listManageCategories.set(listManageCategories.getSelectedIndex(), tbEditValue.getString(), null);
            }//GEN-BEGIN:|7-commandAction|53|7-postCommandAction
        }//GEN-END:|7-commandAction|53|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|54|
    //</editor-fold>//GEN-END:|7-commandAction|54|




    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdNewExpense ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of cmdNewExpense component.
     * @return the initialized component instance
     */
    public Command getCmdNewExpense() {
        if (cmdNewExpense == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            cmdNewExpense = new Command("New Expense", "New Expense", Command.SCREEN, 1);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return cmdNewExpense;
    }
    //</editor-fold>//GEN-END:|20-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdSettings ">//GEN-BEGIN:|22-getter|0|22-preInit
    /**
     * Returns an initiliazed instance of cmdSettings component.
     * @return the initialized component instance
     */
    public Command getCmdSettings() {
        if (cmdSettings == null) {//GEN-END:|22-getter|0|22-preInit
            // write pre-init user code here
            cmdSettings = new Command("Settings", "Settings", Command.SCREEN, 3);//GEN-LINE:|22-getter|1|22-postInit
            // write post-init user code here
        }//GEN-BEGIN:|22-getter|2|
        return cmdSettings;
    }
    //</editor-fold>//GEN-END:|22-getter|2|
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>


    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfExpenseLocation ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of tfExpenseLocation component.
     * @return the initialized component instance
     */
    public TextField getTfExpenseLocation() {
        if (tfExpenseLocation == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            tfExpenseLocation = new TextField("Location", null, 32, TextField.ANY);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return tfExpenseLocation;
    }
    //</editor-fold>//GEN-END:|25-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdOk ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of cmdOk component.
     * @return the initialized component instance
     */
    public Command getCmdOk() {
        if (cmdOk == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            cmdOk = new Command("Ok", "Ok", Command.OK, 1);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return cmdOk;
    }
    //</editor-fold>//GEN-END:|26-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdCancel ">//GEN-BEGIN:|28-getter|0|28-preInit
    /**
     * Returns an initiliazed instance of cmdCancel component.
     * @return the initialized component instance
     */
    public Command getCmdCancel() {
        if (cmdCancel == null) {//GEN-END:|28-getter|0|28-preInit
            // write pre-init user code here
            cmdCancel = new Command("Cancel", "Cancel", Command.CANCEL, 2);//GEN-LINE:|28-getter|1|28-postInit
            // write post-init user code here
        }//GEN-BEGIN:|28-getter|2|
        return cmdCancel;
    }
    //</editor-fold>//GEN-END:|28-getter|2|
    //</editor-fold>
    //</editor-fold>


    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmWallet ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of frmWallet component.
     * @return the initialized component instance
     */
    public Form getFrmWallet() {
        if (frmWallet == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            frmWallet = new Form("New Wallet", new Item[] { getTfWalletName(), getTfWalletOwner(), getChoiceWalletBudgetType(), getTfWalletBudgetValue() });//GEN-BEGIN:|35-getter|1|35-postInit
            frmWallet.addCommand(getCmdOk());
            frmWallet.addCommand(getCmdCancel());
            frmWallet.setCommandListener(this);//GEN-END:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return frmWallet;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lbBudget ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of lbBudget component.
     * @return the initialized component instance
     */
    public StringItem getLbBudget() {
        if (lbBudget == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            lbBudget = new StringItem("Your budget: ", " ", Item.PLAIN);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return lbBudget;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lbSpent ">//GEN-BEGIN:|37-getter|0|37-preInit
    /**
     * Returns an initiliazed instance of lbSpent component.
     * @return the initialized component instance
     */
    public StringItem getLbSpent() {
        if (lbSpent == null) {//GEN-END:|37-getter|0|37-preInit
            // write pre-init user code here
            lbSpent = new StringItem("You already spent: ", " ");//GEN-LINE:|37-getter|1|37-postInit
            // write post-init user code here
        }//GEN-BEGIN:|37-getter|2|
        return lbSpent;
    }
    //</editor-fold>//GEN-END:|37-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfWalletName ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of tfWalletName component.
     * @return the initialized component instance
     */
    public TextField getTfWalletName() {
        if (tfWalletName == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            tfWalletName = new TextField("Wallet name", null, 32, TextField.ANY);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return tfWalletName;
    }
    //</editor-fold>//GEN-END:|38-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceWalletBudgetType ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of choiceWalletBudgetType component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceWalletBudgetType() {
        if (choiceWalletBudgetType == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            choiceWalletBudgetType = new ChoiceGroup("Budget Type", Choice.POPUP);//GEN-BEGIN:|39-getter|1|39-postInit
            choiceWalletBudgetType.append("daily", null);
            choiceWalletBudgetType.append("monthly", null);
            choiceWalletBudgetType.append("yearly", null);
            choiceWalletBudgetType.setSelectedFlags(new boolean[] { true, false, false });//GEN-END:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return choiceWalletBudgetType;
    }
    //</editor-fold>//GEN-END:|39-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfWalletBudgetValue ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of tfWalletBudgetValue component.
     * @return the initialized component instance
     */
    public TextField getTfWalletBudgetValue() {
        if (tfWalletBudgetValue == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            tfWalletBudgetValue = new TextField("Budget value", null, 32, TextField.DECIMAL);//GEN-LINE:|43-getter|1|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|2|
        return tfWalletBudgetValue;
    }
    //</editor-fold>//GEN-END:|43-getter|2|
    //</editor-fold>
    //</editor-fold>


    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: dfExpenseDate ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of dfExpenseDate component.
     * @return the initialized component instance
     */
    public DateField getDfExpenseDate() {
        if (dfExpenseDate == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            dfExpenseDate = new DateField("Date", DateField.DATE_TIME, java.util.TimeZone.getTimeZone("Europe/Vienna"));//GEN-BEGIN:|52-getter|1|52-postInit
            dfExpenseDate.setDate(new java.util.Date(System.currentTimeMillis()));//GEN-END:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return dfExpenseDate;
    }
    //</editor-fold>//GEN-END:|52-getter|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listPrintWallet ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of listPrintWallet component.
     * @return the initialized component instance
     */
    public List getListPrintWallet() {
        if (listPrintWallet == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            listPrintWallet = new List("Contents of the wallet", Choice.IMPLICIT);//GEN-BEGIN:|57-getter|1|57-postInit
            listPrintWallet.addCommand(getCmdBack());
            listPrintWallet.setCommandListener(this);
            listPrintWallet.setFitPolicy(Choice.TEXT_WRAP_ON);
            listPrintWallet.setSelectCommand(null);//GEN-END:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return listPrintWallet;
    }
    //</editor-fold>//GEN-END:|57-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listPrintWalletAction ">//GEN-BEGIN:|57-action|0|57-preAction
    /**
     * Performs an action assigned to the selected list element in the listPrintWallet component.
     */
    public void listPrintWalletAction() {//GEN-END:|57-action|0|57-preAction
        // enter pre-action user code here
        String __selectedString = getListPrintWallet().getString(getListPrintWallet().getSelectedIndex());//GEN-LINE:|57-action|1|57-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|57-action|2|
    //</editor-fold>//GEN-END:|57-action|2|



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|66-getter|0|66-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|66-getter|0|66-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", "Exit", Command.EXIT, 4);//GEN-LINE:|66-getter|1|66-postInit
            // write post-init user code here
        }//GEN-BEGIN:|66-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|66-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            alert = new Alert("alert");//GEN-BEGIN:|69-getter|1|69-postInit
            alert.addCommand(getCmdOk());
            alert.setCommandListener(this);
            alert.setTimeout(Alert.FOREVER);//GEN-END:|69-getter|1|69-postInit
            // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return alert;
    }
    //</editor-fold>//GEN-END:|69-getter|2|
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listManageCategories ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of listManageCategories component.
     * @return the initialized component instance
     */
    public List getListManageCategories() {
        if (listManageCategories == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            listManageCategories = new List("Categories", Choice.IMPLICIT);//GEN-BEGIN:|74-getter|1|74-postInit
            listManageCategories.addCommand(getCmdDeleteCategory());
            listManageCategories.addCommand(getCmdNewCategory());
            listManageCategories.addCommand(getCmdEditCategory());
            listManageCategories.addCommand(getCmdBack());
            listManageCategories.setCommandListener(this);//GEN-END:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return listManageCategories;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listManageCategoriesAction ">//GEN-BEGIN:|74-action|0|74-preAction
    /**
     * Performs an action assigned to the selected list element in the listManageCategories component.
     */
    public void listManageCategoriesAction() {//GEN-END:|74-action|0|74-preAction
        // enter pre-action user code here
        String __selectedString = getListManageCategories().getString(getListManageCategories().getSelectedIndex());//GEN-LINE:|74-action|1|74-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|74-action|2|
    //</editor-fold>//GEN-END:|74-action|2|
    //</editor-fold>



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tbEditValue ">//GEN-BEGIN:|80-getter|0|80-preInit
    /**
     * Returns an initiliazed instance of tbEditValue component.
     * @return the initialized component instance
     */
    public TextBox getTbEditValue() {
        if (tbEditValue == null) {//GEN-END:|80-getter|0|80-preInit
            // write pre-init user code here
            tbEditValue = new TextBox("textBox", null, 100, TextField.ANY);//GEN-BEGIN:|80-getter|1|80-postInit
            tbEditValue.addCommand(getCmdOk());
            tbEditValue.setCommandListener(this);//GEN-END:|80-getter|1|80-postInit
            // write post-init user code here
        }//GEN-BEGIN:|80-getter|2|
        return tbEditValue;
    }
    //</editor-fold>//GEN-END:|80-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdDeleteCategory ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of cmdDeleteCategory component.
     * @return the initialized component instance
     */
    public Command getCmdDeleteCategory() {
        if (cmdDeleteCategory == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            cmdDeleteCategory = new Command("Delete", "Delete", Command.ITEM, 4);//GEN-LINE:|81-getter|1|81-postInit
            // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return cmdDeleteCategory;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdNewCategory ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of cmdNewCategory component.
     * @return the initialized component instance
     */
    public Command getCmdNewCategory() {
        if (cmdNewCategory == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            cmdNewCategory = new Command("New", "New", Command.ITEM, 3);//GEN-LINE:|83-getter|1|83-postInit
            // write post-init user code here
        }//GEN-BEGIN:|83-getter|2|
        return cmdNewCategory;
    }
    //</editor-fold>//GEN-END:|83-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdEditCategory ">//GEN-BEGIN:|85-getter|0|85-preInit
    /**
     * Returns an initiliazed instance of cmdEditCategory component.
     * @return the initialized component instance
     */
    public Command getCmdEditCategory() {
        if (cmdEditCategory == null) {//GEN-END:|85-getter|0|85-preInit
            // write pre-init user code here
            cmdEditCategory = new Command("Edit", "Edit", Command.ITEM, 2);//GEN-LINE:|85-getter|1|85-postInit
            // write post-init user code here
        }//GEN-BEGIN:|85-getter|2|
        return cmdEditCategory;
    }
    //</editor-fold>//GEN-END:|85-getter|2|
    //</editor-fold>



    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: choiceExpenseCategory ">//GEN-BEGIN:|93-getter|0|93-preInit
    /**
     * Returns an initiliazed instance of choiceExpenseCategory component.
     * @return the initialized component instance
     */
    public ChoiceGroup getChoiceExpenseCategory() {
        if (choiceExpenseCategory == null) {//GEN-END:|93-getter|0|93-preInit
            // write pre-init user code here
            choiceExpenseCategory = new ChoiceGroup("Category", Choice.POPUP);//GEN-LINE:|93-getter|1|93-postInit
            // write post-init user code here
        }//GEN-BEGIN:|93-getter|2|
        return choiceExpenseCategory;
    }
    //</editor-fold>//GEN-END:|93-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listSettings ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initiliazed instance of listSettings component.
     * @return the initialized component instance
     */
    public List getListSettings() {
        if (listSettings == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            listSettings = new List("Settings", Choice.IMPLICIT);//GEN-BEGIN:|109-getter|1|109-postInit
            listSettings.append("Manage categories", null);
            listSettings.append("Manage wallets", null);
            listSettings.append("Set timezone", null);
            listSettings.append("-RESET-", null);
            listSettings.append("About", null);
            listSettings.addCommand(getCmdBack());
            listSettings.setCommandListener(this);
            listSettings.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            listSettings.setSelectedFlags(new boolean[] { false, false, false, false, false });//GEN-END:|109-getter|1|109-postInit
            // write post-init user code here
        }//GEN-BEGIN:|109-getter|2|
        return listSettings;
    }
    //</editor-fold>//GEN-END:|109-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listSettingsAction ">//GEN-BEGIN:|109-action|0|109-preAction
    /**
     * Performs an action assigned to the selected list element in the listSettings component.
     */
    public void listSettingsAction() {//GEN-END:|109-action|0|109-preAction
        // enter pre-action user code here
        String __selectedString = getListSettings().getString(getListSettings().getSelectedIndex());//GEN-BEGIN:|109-action|1|112-preAction
        if (__selectedString != null) {
            if (__selectedString.equals("Manage categories")) {//GEN-END:|109-action|1|112-preAction
                // Manage categories
                getDisplay().setCurrent(getListManageCategories());
                displayCategoryList(getListManageCategories());
//GEN-LINE:|109-action|2|112-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Manage wallets")) {//GEN-LINE:|109-action|3|113-preAction
                // Manage wallets
                getDisplay().setCurrent(getListManageWallets());
                displayWalletList(getListManageWallets());
//GEN-LINE:|109-action|4|113-postAction
                // write post-action user code here
            } else if (__selectedString.equals("Set timezone")) {//GEN-LINE:|109-action|5|114-preAction
                // write pre-action user code here
//GEN-LINE:|109-action|6|114-postAction
                // write post-action user code here
            } else if (__selectedString.equals("-RESET-")) {//GEN-LINE:|109-action|7|179-preAction
                resetDAL();
//GEN-LINE:|109-action|8|179-postAction
                // write post-action user code here
            } else if (__selectedString.equals("About")) {//GEN-LINE:|109-action|9|115-preAction
                // About dialog
                getDisplay().setCurrent(getFrmAbout());
//GEN-LINE:|109-action|10|115-postAction
                // write post-action user code here
            }//GEN-BEGIN:|109-action|11|109-postAction
        }//GEN-END:|109-action|11|109-postAction
        // enter post-action user code here
    }//GEN-BEGIN:|109-action|12|
    //</editor-fold>//GEN-END:|109-action|12|







    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: listManageWallets ">//GEN-BEGIN:|125-getter|0|125-preInit
    /**
     * Returns an initiliazed instance of listManageWallets component.
     * @return the initialized component instance
     */
    public List getListManageWallets() {
        if (listManageWallets == null) {//GEN-END:|125-getter|0|125-preInit
            // write pre-init user code here
            listManageWallets = new List("Manage Wallets", Choice.IMPLICIT);//GEN-BEGIN:|125-getter|1|125-postInit
            listManageWallets.addCommand(getCmdNewWallet());
            listManageWallets.addCommand(getCmdEditWallet());
            listManageWallets.addCommand(getCmdDeleteWallet());
            listManageWallets.addCommand(getCmdActivateWallet());
            listManageWallets.addCommand(getCmdBack());
            listManageWallets.setCommandListener(this);//GEN-END:|125-getter|1|125-postInit
            // write post-init user code here
        }//GEN-BEGIN:|125-getter|2|
        return listManageWallets;
    }
    //</editor-fold>//GEN-END:|125-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: listManageWalletsAction ">//GEN-BEGIN:|125-action|0|125-preAction
    /**
     * Performs an action assigned to the selected list element in the listManageWallets component.
     */
    public void listManageWalletsAction() {//GEN-END:|125-action|0|125-preAction
        // enter pre-action user code here
        String __selectedString = getListManageWallets().getString(getListManageWallets().getSelectedIndex());//GEN-LINE:|125-action|1|125-postAction
        activateWallet(__selectedString);
    }//GEN-BEGIN:|125-action|2|
    //</editor-fold>//GEN-END:|125-action|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmTimezone ">//GEN-BEGIN:|128-getter|0|128-preInit
    /**
     * Returns an initiliazed instance of frmTimezone component.
     * @return the initialized component instance
     */
    public Form getFrmTimezone() {
        if (frmTimezone == null) {//GEN-END:|128-getter|0|128-preInit
            // write pre-init user code here
            frmTimezone = new Form("form");//GEN-LINE:|128-getter|1|128-postInit
            // write post-init user code here
        }//GEN-BEGIN:|128-getter|2|
        return frmTimezone;
    }
    //</editor-fold>//GEN-END:|128-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: frmAbout ">//GEN-BEGIN:|129-getter|0|129-preInit
    /**
     * Returns an initiliazed instance of frmAbout component.
     * @return the initialized component instance
     */
    public Form getFrmAbout() {
        if (frmAbout == null) {//GEN-END:|129-getter|0|129-preInit
            // write pre-init user code here
            frmAbout = new Form("About DigitalWallet", new Item[] { getStringItem() });//GEN-BEGIN:|129-getter|1|129-postInit
            frmAbout.addCommand(getCmdBack());
            frmAbout.setCommandListener(this);//GEN-END:|129-getter|1|129-postInit
            // write post-init user code here
        }//GEN-BEGIN:|129-getter|2|
        return frmAbout;
    }
    //</editor-fold>//GEN-END:|129-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|131-getter|0|131-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|131-getter|0|131-preInit
            // write pre-init user code here
            stringItem = new StringItem("DigitalWallet v0.1", "Copyright 2010 by Christian J. Lampl, dedicated to Paula N\u00F6hrer", Item.PLAIN);//GEN-BEGIN:|131-getter|1|131-postInit
            stringItem.setLayout(ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER | ImageItem.LAYOUT_NEWLINE_BEFORE | ImageItem.LAYOUT_NEWLINE_AFTER);//GEN-END:|131-getter|1|131-postInit
            // write post-init user code here
        }//GEN-BEGIN:|131-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|131-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tfWalletOwner ">//GEN-BEGIN:|132-getter|0|132-preInit
    /**
     * Returns an initiliazed instance of tfWalletOwner component.
     * @return the initialized component instance
     */
    public TextField getTfWalletOwner() {
        if (tfWalletOwner == null) {//GEN-END:|132-getter|0|132-preInit
            // write pre-init user code here
            tfWalletOwner = new TextField("Owner", null, 32, TextField.ANY);//GEN-LINE:|132-getter|1|132-postInit
            // write post-init user code here
        }//GEN-BEGIN:|132-getter|2|
        return tfWalletOwner;
    }
    //</editor-fold>//GEN-END:|132-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdNewWallet ">//GEN-BEGIN:|136-getter|0|136-preInit
    /**
     * Returns an initiliazed instance of cmdNewWallet component.
     * @return the initialized component instance
     */
    public Command getCmdNewWallet() {
        if (cmdNewWallet == null) {//GEN-END:|136-getter|0|136-preInit
            // write pre-init user code here
            cmdNewWallet = new Command("New", "New", Command.ITEM, 2);//GEN-LINE:|136-getter|1|136-postInit
            // write post-init user code here
        }//GEN-BEGIN:|136-getter|2|
        return cmdNewWallet;
    }
    //</editor-fold>//GEN-END:|136-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdEditWallet ">//GEN-BEGIN:|138-getter|0|138-preInit
    /**
     * Returns an initiliazed instance of cmdEditWallet component.
     * @return the initialized component instance
     */
    public Command getCmdEditWallet() {
        if (cmdEditWallet == null) {//GEN-END:|138-getter|0|138-preInit
            // write pre-init user code here
            cmdEditWallet = new Command("Edit", "Edit", Command.ITEM, 3);//GEN-LINE:|138-getter|1|138-postInit
            // write post-init user code here
        }//GEN-BEGIN:|138-getter|2|
        return cmdEditWallet;
    }
    //</editor-fold>//GEN-END:|138-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdDeleteWallet ">//GEN-BEGIN:|140-getter|0|140-preInit
    /**
     * Returns an initiliazed instance of cmdDeleteWallet component.
     * @return the initialized component instance
     */
    public Command getCmdDeleteWallet() {
        if (cmdDeleteWallet == null) {//GEN-END:|140-getter|0|140-preInit
            // write pre-init user code here
            cmdDeleteWallet = new Command("Delete", "Delete", Command.ITEM, 4);//GEN-LINE:|140-getter|1|140-postInit
            // write post-init user code here
        }//GEN-BEGIN:|140-getter|2|
        return cmdDeleteWallet;
    }
    //</editor-fold>//GEN-END:|140-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdActivateWallet ">//GEN-BEGIN:|143-getter|0|143-preInit
    /**
     * Returns an initiliazed instance of cmdActivateWallet component.
     * @return the initialized component instance
     */
    public Command getCmdActivateWallet() {
        if (cmdActivateWallet == null) {//GEN-END:|143-getter|0|143-preInit
            // write pre-init user code here
            cmdActivateWallet = new Command("Activate", "Activate", Command.ITEM, 1);//GEN-LINE:|143-getter|1|143-postInit
            // write post-init user code here
        }//GEN-BEGIN:|143-getter|2|
        return cmdActivateWallet;
    }
    //</editor-fold>//GEN-END:|143-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imgOk ">//GEN-BEGIN:|145-getter|0|145-preInit
    /**
     * Returns an initiliazed instance of imgOk component.
     * @return the initialized component instance
     */
    public Image getImgOk() {
        if (imgOk == null) {//GEN-END:|145-getter|0|145-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|145-getter|1|145-@java.io.IOException
                imgOk = Image.createImage("/ok.png");
            } catch (java.io.IOException e) {//GEN-END:|145-getter|1|145-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|145-getter|2|145-postInit
            // write post-init user code here
        }//GEN-BEGIN:|145-getter|3|
        return imgOk;
    }
    //</editor-fold>//GEN-END:|145-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdBack ">//GEN-BEGIN:|154-getter|0|154-preInit
    /**
     * Returns an initiliazed instance of cmdBack component.
     * @return the initialized component instance
     */
    public Command getCmdBack() {
        if (cmdBack == null) {//GEN-END:|154-getter|0|154-preInit
            // write pre-init user code here
            cmdBack = new Command("Back", "Back", Command.BACK, 0);//GEN-LINE:|154-getter|1|154-postInit
            // write post-init user code here
        }//GEN-BEGIN:|154-getter|2|
        return cmdBack;
    }
    //</editor-fold>//GEN-END:|154-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: okCommand ">//GEN-BEGIN:|157-getter|0|157-preInit
    /**
     * Returns an initiliazed instance of okCommand component.
     * @return the initialized component instance
     */
    public Command getOkCommand() {
        if (okCommand == null) {//GEN-END:|157-getter|0|157-preInit
            // write pre-init user code here
            okCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|157-getter|1|157-postInit
            // write post-init user code here
        }//GEN-BEGIN:|157-getter|2|
        return okCommand;
    }
    //</editor-fold>//GEN-END:|157-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lbActiveWallet ">//GEN-BEGIN:|163-getter|0|163-preInit
    /**
     * Returns an initiliazed instance of lbActiveWallet component.
     * @return the initialized component instance
     */
    public StringItem getLbActiveWallet() {
        if (lbActiveWallet == null) {//GEN-END:|163-getter|0|163-preInit
            // write pre-init user code here
            lbActiveWallet = new StringItem("Active wallet: ", "");//GEN-LINE:|163-getter|1|163-postInit
            // write post-init user code here
        }//GEN-BEGIN:|163-getter|2|
        return lbActiveWallet;
    }
    //</editor-fold>//GEN-END:|163-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdShowWallet ">//GEN-BEGIN:|161-getter|0|161-preInit
    /**
     * Returns an initiliazed instance of cmdShowWallet component.
     * @return the initialized component instance
     */
    public Command getCmdShowWallet() {
        if (cmdShowWallet == null) {//GEN-END:|161-getter|0|161-preInit
            // write pre-init user code here
            cmdShowWallet = new Command("Show wallet", "Show wallet", Command.SCREEN, 2);//GEN-LINE:|161-getter|1|161-postInit
            // write post-init user code here
        }//GEN-BEGIN:|161-getter|2|
        return cmdShowWallet;
    }
    //</editor-fold>//GEN-END:|161-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imgError ">//GEN-BEGIN:|175-getter|0|175-preInit
    /**
     * Returns an initiliazed instance of imgError component.
     * @return the initialized component instance
     */
    public Image getImgError() {
        if (imgError == null) {//GEN-END:|175-getter|0|175-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|175-getter|1|175-@java.io.IOException
                imgError = Image.createImage("/error.png");
            } catch (java.io.IOException e) {//GEN-END:|175-getter|1|175-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|175-getter|2|175-postInit
            // write post-init user code here
        }//GEN-BEGIN:|175-getter|3|
        return imgError;
    }
    //</editor-fold>//GEN-END:|175-getter|3|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cmdReset ">//GEN-BEGIN:|177-getter|0|177-preInit
    /**
     * Returns an initiliazed instance of cmdReset component.
     * @return the initialized component instance
     */
    public Command getCmdReset() {
        if (cmdReset == null) {//GEN-END:|177-getter|0|177-preInit
            // write pre-init user code here
            cmdReset = new Command("-RESET-", "-RESET-", Command.ITEM, 5);//GEN-LINE:|177-getter|1|177-postInit
            // write post-init user code here
        }//GEN-BEGIN:|177-getter|2|
        return cmdReset;
    }
    //</editor-fold>//GEN-END:|177-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lbDifference ">//GEN-BEGIN:|180-getter|0|180-preInit
    /**
     * Returns an initiliazed instance of lbDifference component.
     * @return the initialized component instance
     */
    public StringItem getLbDifference() {
        if (lbDifference == null) {//GEN-END:|180-getter|0|180-preInit
            // write pre-init user code here
            lbDifference = new StringItem("Left:", null);//GEN-LINE:|180-getter|1|180-postInit
            // write post-init user code here
        }//GEN-BEGIN:|180-getter|2|
        return lbDifference;
    }
    //</editor-fold>//GEN-END:|180-getter|2|



    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay () {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        try
        {
            dal.updateSettings(settings);
        } catch (Exception e)
        {
            //displayError(e.getMessage(), getFrmMain());
            //return;
        }

        switchDisplayable (null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet ();
        } else {
            initialize ();
            startMIDlet ();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     * Create a new Expense with the entered information
     */
    private void createExpense()
    {
        wallet.addExpense(new Expense(Float.parseFloat(tfExpenseValue.getString()),
                choiceExpenseCategory.getString(choiceExpenseCategory.getSelectedIndex()),
                tfExpenseLocation.getString(),
                Util.dateToString(dfExpenseDate.getDate())));

        try
        {
            dal.updateWallet(wallet);
            displayWallet();
        } catch (Exception e)
        {
            displayError(e.getMessage(), getFrmExpense());
        }
    }

    /**
     * Create a new Expense with the entered information
     */
    private void createWallet()
    {
        Wallet w= new Wallet(getTfWalletName().getString(),
                getTfWalletOwner().getString(),
                Float.parseFloat(getTfWalletBudgetValue().getString()),
                (byte)getChoiceWalletBudgetType().getSelectedIndex()
                );

        try
        {
            dal.createWallet(w);
            displayWalletList(getListManageWallets());
        } catch (Exception e)
        {
            displayError(e.getMessage(), getFrmWallet());
        }
    }

    /**
     * Clear the input form for a new expense
     */
    private void clearExpenseForm()
    {
        tfExpenseLocation.setString("");
        tfExpenseValue.setString("");
        dfExpenseDate.setDate(new Date(System.currentTimeMillis()));
    }

    /**
     * Checks if all the required fields in the form are filled
     * @return true if its OK, else if it is not
     */
    private boolean checkExpenseForm()
    {
        if (tfExpenseValue.getString().equals("")) {
            displayError("Please enter a value!", getFrmExpense());
            return false;
        }
        if (tfExpenseLocation.getString().equals("")) {
            displayError("Please enter a location!", getFrmExpense());
            return false;
        }
        // if everything is ok
        return true;
    }

    /**
     * Clear the input form for a new wallet
     */
    private void clearWalletForm()
    {
        tfWalletName.setString("");
        tfWalletOwner.setString("");
        tfWalletBudgetValue.setString("");
        choiceWalletBudgetType.setSelectedIndex(0, true);
    }

    /**
     * Checks if all the required fields in the form are filled
     * @return true if its OK, else if it is not
     */
    private boolean checkWalletForm()
    {
        if (tfWalletName.getString().equals("")) {
            displayError("Please enter a name!", getFrmWallet());
            return false;
        }
        if (tfWalletOwner.getString().equals("")) {
            displayError("Please enter an owner!", getFrmWallet());
            return false;
        }
        if (tfWalletBudgetValue.getString().equals("")) {
            displayError("Please enter a budget value!", getFrmWallet());
            return false;
        }
        // if everything is ok
        return true;
    }

    /**
     * Print out the contents of the wallet (all the Records)
     */
    private void printWallet(Choice ch)
    {
        ch.deleteAll();

        for (int i=0; i<wallet.getExpenses().size(); i++)
        {
            ch.append(wallet.getExpenses().elementAt(i).toString(), null);
        }
    }

    /**
     * Print out the categories
     * @param ch The Item implementing the Choice interface
     * @see Choice
     */
    private void displayCategoryList(Choice ch)
    {
        ch.deleteAll();
        try
        {
            String[] cats= dal.getCategories();
            for (int i=0; i<cats.length; i++)
            {
                ch.append(cats[i], null);
            }
        } catch (Exception e)
        {
            displayError(e.getMessage(), getFrmMain());
        }
    }

    /**
     * Print out the categories
     * @param ch The Item implementing the Choice interface
     * @see Choice
     */
    private void displayWalletList(Choice ch)
    {
        ch.deleteAll();
        try
        {
            String[] walls= dal.getWalletList();
            for (int i=0; i<walls.length; i++) {
                if (walls[i].equals(settings[0]))
                    ch.append(walls[i]+" [active]", getImgOk());
                else
                    ch.append(walls[i], null);
            }
        } catch (Exception e)
        {
            //displayError(e.getMessage());
            System.out.println(e.getMessage());
        }
    }

    /**
     * Displays the important values of the wallet in frmMain
     */
    private void displayWallet()
    {
        getLbActiveWallet().setText(wallet.getOwner()+"s " + wallet.getName());
        getLbBudget().setText(wallet.getBudget()+" per "+wallet.getBudgetType());
        getLbSpent().setLabel("Spent this "+wallet.getBudgetType()+": ");
        getLbSpent().setText(String.valueOf(wallet.getExpenseThisPeriod()));
        getLbDifference().setText(String.valueOf(wallet.getBudgetLeft()));
    }

    /**
     * For handling error messages
     * @param msg The error to be displayed
     */
    private void displayError(String msg, Displayable nextDisplayable)
    {
        alert= new Alert("Error!", msg, getImgError(), AlertType.ERROR);
        alert.setTimeout(Alert.FOREVER);
        switchDisplayable(alert, nextDisplayable);
        System.out.println("ERROR: "+msg);
    }

    /**
     * Tries to activate a wallet (=read wallet from DAL)
     * @param walletString the unique identifier of the wallet
     */
    private void activateWallet(String walletString)
    {
        // we don't activate the already active wallet
        if (walletString.equals(settings[0]+" [active]")) {
            return;
        } else {
            settings[0]= walletString;
            try
            {
                wallet = dal.readWallet(settings[0]);
            } catch (Exception e)
            {
                displayError(e.getMessage(), getListManageWallets());
            }
            displayWalletList(getListManageWallets());
            displayWallet();
        }
    }

    /**
     * Resets the DATA to default values
     */
    private void resetDAL()
    {
        try
        {
            dal.resetDataStore();
            settings= dal.getSettings();
            activateWallet(settings[0]);
        } catch (Exception e) {
            displayError(e.getMessage(), getListSettings());
        }
    }

    /**
     * Write the categories form the listCategories into the store
     */
    private void writeCategories()
    {
        try
        {
            String[] cats= new String[getListManageCategories().size()];
            for (int i=0; i<cats.length; i++)
            {
                cats[i]= getListManageCategories().getString(i);
            }

            dal.updateCategories(cats);
        } catch (Exception e) {
            displayError(e.getMessage(), getListSettings());
        }
    }
}