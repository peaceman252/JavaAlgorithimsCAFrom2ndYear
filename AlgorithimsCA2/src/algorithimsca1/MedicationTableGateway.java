package algorithimsca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author N00143569
 */
//This class is the gateway for medication
public class MedicationTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "meds";
    private static final String COLUMN_MEDID = "medId";
    private static final String COLUMN_MEDICATIONNAME = "medicationName";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_BESTBEFOREDATE="bestBeforeDate";
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public MedicationTableGateway(Connection connection) {
        mConnection = connection;
    }
    //method for inserting a medication into the database
    public Medication insertMedication(int medId,String medicationName,double cost, String bestBeforeDate) throws SQLException {
       //create a medication
        Medication md = null;

        String query;       
        PreparedStatement stmt;        
        int numRowsAffected;
        int id;

        //creates a query that inserts into database 
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_MEDID + ", " +
                COLUMN_MEDICATIONNAME + ", " +
                COLUMN_COST + ", " +
                COLUMN_BESTBEFOREDATE +
                ") VALUES (?, ?, ?, ?)";

        //statement returns the keys
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, medId);
        stmt.setString(2, medicationName);
        stmt.setDouble(3, cost);
        stmt.setString(4, bestBeforeDate);


        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            //id = keys.getInt(1);
            //changes the medication to have attributes
            md = new Medication(medId, medicationName, cost, bestBeforeDate);
        }

        //returns the medication
        return md;
    }
    //method for removing a medication from the database
    public void removeMedication(Medication m, int i) throws SQLException {
        

        String query;  
        
        PreparedStatement stmt=null;        
        int numRowsAffected;
        

        //creates query for deleting a row relating to the medication id
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_MEDID + " = ?";
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,i);
        numRowsAffected = stmt.executeUpdate();
        
       
        
    }
    //method for updating a medication's data in the database
    public boolean updateMedication(Medication m)throws SQLException
    {
        String query;                
        PreparedStatement stmt;                
        int numRowsAffected;    

       //creates a query that updates for changing the data
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_MEDICATIONNAME     + " = ?, " +
                COLUMN_COST     + " = ?, " +
                COLUMN_BESTBEFOREDATE    + " = ?, " +
                " WHERE " + COLUMN_MEDID + " = ? ";
        //statement for updating the data
        stmt = mConnection.prepareStatement(query); 
        stmt.setString(1, m.getMedicationName());
        stmt.setDouble(2, m.getCost());
        stmt.setString(3, m.getBestBeforeDate());
        stmt.setInt(4, m.getMedId());
        
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
    //method for viewing all the medications
    public List<Medication> getMeds() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<Medication> meds;   
                                        

        String medicationName,bestBeforeDate;
        int medId;
        double cost;


        Medication md;                   

       
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        //query that selects all data
        meds = new ArrayList<Medication>();
        while (rs.next()) {
            medId = rs.getInt(COLUMN_MEDID);
            medicationName = rs.getString(COLUMN_MEDICATIONNAME);
            cost = rs.getDouble(COLUMN_COST);
            bestBeforeDate= rs.getString(COLUMN_BESTBEFOREDATE);
          

            md = new Medication(medId, medicationName, cost, bestBeforeDate);
            meds.add(md);
        }

       //return arraylist of medications
        return meds;
    }
}
