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
//This class is the gateway for ward
public class WardTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "wards";
    private static final String COLUMN_WARDID = "wardId";
    private static final String COLUMN_WARDNAME = "wardName";
    private static final String COLUMN_NUMBEDS = "numBeds";
    private static final String COLUMN_NURSE = "nurse";
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public WardTableGateway(Connection connection) {
        mConnection = connection;
    }
    //method for inserting a ward into the database
    public Ward insertWard(int wardId,String wardName, int numBeds, String nurse) throws SQLException {
        //create a ward
        Ward wd = null;

        String query;       
        PreparedStatement stmt;        
        int numRowsAffected;
        int id;

        //creates a query that inserts into database
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_WARDID + ", " +
                COLUMN_WARDNAME + ", " +
                COLUMN_NUMBEDS + ", " +
                COLUMN_NURSE +
                ") VALUES (?, ?, ?, ?)";

        //statement returns the keys
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, wardId);
        stmt.setString(2, wardName);
        stmt.setInt(3, numBeds);
        stmt.setString(4, nurse);


        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            //id = keys.getInt(1);
            //changes the ward to have attributes
            wd = new Ward(wardId, wardName, numBeds, nurse);
        }

        //returns the ward
        return wd;
    }
    //method for removing a ward from the database
    public void removeWard(Ward w, int i) throws SQLException {
        

        String query;  
        
        PreparedStatement stmt=null;        
        int numRowsAffected;
        

        //creates a query for deleting a row relating to the ward id
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_WARDID + " = ?";
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,i);
        numRowsAffected = stmt.executeUpdate();
        
       
        
    }
    //method for updating ward data in the database
    public boolean updateWard(Ward w)throws SQLException
    {
        String query;                
        PreparedStatement stmt;                
        int numRowsAffected;    

       //creates a query that updates for changes in the data
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_WARDNAME     + " = ?, " +
                COLUMN_NUMBEDS     + " = ?, " +
                COLUMN_NURSE    + " = ?, " +
                " WHERE " + COLUMN_WARDID + " = ? ";
        //statement for updating data
        stmt = mConnection.prepareStatement(query); 
        stmt.setString(1, w.getWardName());
        stmt.setInt(2, w.getNumBeds());
        stmt.setString(3, w.getNurse());
        stmt.setInt(4, w.getWardId());
        
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
    //method for viewing all wards
    public List<Ward> getWards() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<Ward> wards;   
                                        

        String wardName,nurse;
        int wardId, numBeds;
        


        Ward wd;                   

        //query selects all data
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        
        wards = new ArrayList<Ward>();
        while (rs.next()) {
            wardId = rs.getInt(COLUMN_WARDID);
            wardName = rs.getString(COLUMN_WARDNAME);
            numBeds = rs.getInt(COLUMN_NUMBEDS);
            nurse= rs.getString(COLUMN_NURSE);
          

            wd = new Ward(wardId, wardName, numBeds, nurse);
            wards.add(wd);
        }

        //returns arraylist of wards
        return wards;
    }
}
