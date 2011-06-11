package src;

import java.io.*;
import java.util.*;
import javax.microedition.io.*;
import javax.bluetooth.*;
import javax.obex.*;

/**
 * Used to send a wallet via OBEX push to a bluetooth device
 * @author Christian Lampl
 * @version 2011.06.08
 */

public class ObjectPusher extends Thread
{

    String connectionURL = null;
    Connection connection = null;
    DigitalWallet client = null;
    Wallet wallet = null;

    /**
     * Creates a new instance of ObjectPusher.
     * @param client the DigitalWallet, used for notification
     * @param connectionURL the URL of the device, where the wallet shall be pushed
     * @param wallet the wallet to be pushed
     */
    public ObjectPusher(DigitalWallet client, String connectionURL, Wallet wallet)
    {

        this.connectionURL= connectionURL;
        this.client= client;
        this.wallet= wallet;
    }

    /**
     * Pushing is done in a separate thread
     */
    public void run()
    {

        try
        {
            connection= Connector.open(connectionURL);
            client.updateStatus("Connection obtained");


            ClientSession cs= (ClientSession) connection;
            HeaderSet hs= cs.createHeaderSet();

            cs.connect(hs);
            client.updateStatus("OBEX session created");

            /* To get all expenses into one byte-array, we iterate over the
             * vector we get and append all the elements.toString() to a
             * String(Buffer), which we then convert to byte[].
             */
            Vector exp= wallet.getExpenses();
            StringBuffer buf= new StringBuffer();
            buf.append(Util.FILE_HEADER).append("\n");
            for (int i= 0; i<exp.size(); i++)
            {
                buf.append(exp.elementAt(i).toString()).append("\n");
            }
            
            byte[] fbytes= buf.toString().getBytes();
            String fname= wallet.getOwner()+"_"+wallet.getName()+".csv";
            
            hs= cs.createHeaderSet();
            hs.setHeader(HeaderSet.NAME, fname);
            hs.setHeader(HeaderSet.TYPE, "text/csv");
            hs.setHeader(HeaderSet.LENGTH, new Long(fbytes.length));

            Operation putOperation= cs.put(hs);
            client.updateStatus("Pushing file: " + fname);
            client.updateStatus("Total file size: " + fbytes.length + " bytes");

            OutputStream outputStream= putOperation.openOutputStream();
            outputStream.write(fbytes);
            client.updateStatus("File push complete");

            outputStream.close();
            putOperation.close();

            cs.disconnect(null);

            connection.close();
        } catch (Exception e)
        {
            client.updateStatus(e.getMessage());
        }
    }
}