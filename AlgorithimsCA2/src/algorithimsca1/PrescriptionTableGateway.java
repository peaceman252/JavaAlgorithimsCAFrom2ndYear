package algorithimsca1;

/**
 *
 * @author n00143569
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//This class is the gateway for prescription
public class PrescriptionTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "prescriptions";
    private static final String COLUMN_PATIENTID = "patientId";
    private static final String COLUMN_PRESCRIPTIONNUMBER = "prescriptionNumber";
    private static final String COLUMN_MEDID="medId";
    private static final String COLUMN_DATEPRESCRIBED = "datePrescribed";
    private static final String COLUMN_DOSAGE = "dosage";
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public PrescriptionTableGateway(Connection connection) {
        mConnection = connection;
    }
    //method for inserting a prescription into the database
    public Prescription insertPrescription(int pId, int pNum, int medId, String datePrescribed, String dosage) throws SQLException {
        //create an prescription
        Prescription p = null;

        String query;       
        PreparedStatement stmt;        
        int numRowsAffected;
        int id;

        //creates a query that inserts into database 
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_PATIENTID + ", " +
                COLUMN_PRESCRIPTIONNUMBER + ", " +
                COLUMN_MEDID + ", " +
                COLUMN_DATEPRESCRIBED + ", " +
                COLUMN_DOSAGE +
                ") VALUES (?, ?, ?, ?,?)";

        //statement returns the keys
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, pId);
        stmt.setInt(2, pNum);
        stmt.setInt(3, medId);
        stmt.setString(4, datePrescribed);
        stmt.setString(5, dosage);


        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

           // id = keys.getInt(1);
            //changes the prescription to have attributes
            p = new Prescription(pId,pNum, medId,datePrescribed,dosage);
        }

        //returns the prescription
        return p;
    }
    //method for removing a prescription from the database
    public void removePrescription(Prescription p, int i) throws SQLException {
        

        String query;  
        
        PreparedStatement stmt=null;        
        int numRowsAffected;
        

        //creates query for deleting a row relating to the prescription number
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PRESCRIPTIONNUMBER + " = ?";
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,i);
        numRowsAffected = stmt.executeUpdate();
        
       
        
    }
     //method for updating a prescription's data in the database
    public boolean updatePrescription(Prescription p)throws SQLException
    {
        String query;                
        PreparedStatement stmt;                
        int numRowsAffected;    

       //creates a query that updates for changing the data
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_MEDID     + " = ?, " +
                COLUMN_DATEPRESCRIBED     + " = ?, " +
                COLUMN_DOSAGE    + " = ?, " +
                " WHERE " + COLUMN_PRESCRIPTIONNUMBER + " = ? ";
        //statement for updating the data
        stmt = mConnection.prepareStatement(query); 
        stmt.setInt(1, p.getMedId());
        stmt.setString(2, p.getDatePrescribed());
        stmt.setString(3, p.getDosage());
        stmt.setInt(4, p.getPrescriptionNumber());
        
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
    //method for viewing all the prescriptions
    public List<Prescription> getPrescriptions() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<Prescription> prescriptions;   
                                        

        String datePrescribed, dosage;
        int pNum, medId,pId;


        Prescription p;                   

       //query that selects all data
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        
        prescriptions = new ArrayList<Prescription>();
        while (rs.next()) {
            pId = rs.getInt(COLUMN_PATIENTID);
            pNum = rs.getInt(COLUMN_PRESCRIPTIONNUMBER);
            medId= rs.getInt(COLUMN_MEDID);
            datePrescribed = rs.getString(COLUMN_DATEPRESCRIBED);
            dosage = rs.getString(COLUMN_DOSAGE);
          

            p = new Prescription(pId,pNum, medId,datePrescribed,dosage);
            prescriptions.add(p);
        }

       //return arraylist of prescriptions
        return prescriptions;
    }

}