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
 * @author n00143569
 */
//This class is the gateway for adult patients
public class APatientTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "adultpatients";
    private static final String COLUMN_PATIENTFNAME = "patientFName";
    private static final String COLUMN_PATIENTLNAME = "patientLName";
    private static final String COLUMN_PATIENTNUMBER = "patientNumber";
    private static final String COLUMN_ADDRESSNUMBER = "addressNumber";
    private static final String COLUMN_ADDRESSESTATE = "addressEstate";
    private static final String COLUMN_ADDRESSCITY = "addressCity";
    private static final String COLUMN_ADDRESSCOUNTY = "addressCounty";
    private static final String COLUMN_DOB = "dob";
    private static final String COLUMN_WARDID = "wardId";
    private static final String COLUMN_DOCTORNAME = "doctorName";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_EMAIL = "email";
    
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public APatientTableGateway(Connection connection) {
        mConnection = connection;
    }
    //method for inserting an adult patient into the database
    public AdultPatient insertAPatient(String fName, String lName, int pNum, int addressNumber, String addressEstate, String addressCity, String addressCounty,
        String dob, int wardId, String doctor, int phone, String email) throws SQLException {
        //create an adult patient
        AdultPatient p = null;
        
        String query;       
        PreparedStatement stmt;        
        int numRowsAffected;
        int id;

        //creates a query that inserts into database 
        query = "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_PATIENTFNAME + ", " +
                COLUMN_PATIENTLNAME + ", " +
                COLUMN_PATIENTNUMBER + ", " +
                COLUMN_ADDRESSNUMBER + ", " +
                COLUMN_ADDRESSESTATE + ", " +
                COLUMN_ADDRESSCITY + ", " +
                COLUMN_ADDRESSCOUNTY + ", " +
                COLUMN_DOB + ", " +
                COLUMN_WARDID + ", " +
                COLUMN_DOCTORNAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        //statement returns the keys
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, fName); 
        stmt.setString(2, lName);
        stmt.setInt(3, pNum);
        stmt.setInt(4, addressNumber);
        stmt.setString(5, addressEstate);
        stmt.setString(6, addressCity);
        stmt.setString(7, addressCounty);
        stmt.setString(8, dob);
        stmt.setInt(9, wardId);
        stmt.setString(10, doctor);
        stmt.setInt(11, phone);
        stmt.setString(12, email);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            //id = keys.getInt(1);
            //changes the patient to have attributes
            p = new AdultPatient(fName, lName, pNum, addressNumber, addressEstate, addressCity, addressCounty, dob, wardId, doctor, phone, email);
        }

        //returns the patient
        return p;
    }
    //method for removing a patient from the database
    public void removeAPatient(AdultPatient p, int i) throws SQLException {
        

        String query;  
        
        PreparedStatement stmt=null;        
        int numRowsAffected;
        

        //creates query for deleting a row relating to the patient number
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_PATIENTNUMBER + " = ?";
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1,i);
        numRowsAffected = stmt.executeUpdate();
        
       
        
    }
    //method for updating a patient's data in the database
    public boolean updateAPatient(AdultPatient p)throws SQLException
    {
        String query;                
        PreparedStatement stmt;                
        int numRowsAffected;    

       //creates a query that updates for changing the data
        query = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_PATIENTFNAME     + " = ?, " +
                COLUMN_PATIENTLNAME     + " = ?, " +
                COLUMN_ADDRESSNUMBER    + " = ?, " +
                COLUMN_ADDRESSESTATE    + " = ?, " +
                COLUMN_ADDRESSCITY      + " = ?, " +
                COLUMN_ADDRESSCOUNTY    + " = ?, " +
                COLUMN_WARDID           + " = ?, " +
                COLUMN_DOCTORNAME       + " = ?, " +
                COLUMN_PHONE            + " = ?, " +
                COLUMN_EMAIL             + " = ?  "  +
                " WHERE " + COLUMN_PATIENTNUMBER + " = ? ";
        //statement for updating the data
        stmt = mConnection.prepareStatement(query); 
        stmt.setString(1, p.getPatientFName());
        stmt.setString(2, p.getPatientLName());
        stmt.setInt(3, p.getAddressNumber());
        stmt.setString(4, p.getAddressEstate());
        stmt.setString(5, p.getAddressCity());
        stmt.setString(6, p.getAddressCounty());
        stmt.setInt(7, p.getWardId()); 
        stmt.setString(8, p.getDoctorName());
        stmt.setInt(9, p.getPhone()); 
        stmt.setString(10, p.getEmail());
        stmt.setInt(11, p.getPatientNumber());
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
    //method for viewing all the adult patients
    public List<AdultPatient> getAPatients() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<AdultPatient> aPatients;   
                                        

        String patientFName,patientLName, addressEstate,addressCity,addressCounty, email, dob, doctorName;
        int patientNumber, addressNumber,phone,wardId;

        AdultPatient p;                   

       //query that selects all data
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        
        aPatients = new ArrayList<AdultPatient>();
        while (rs.next()) {
            patientFName = rs.getString(COLUMN_PATIENTFNAME);
            patientLName = rs.getString(COLUMN_PATIENTLNAME);
            patientNumber = rs.getInt(COLUMN_PATIENTNUMBER);
            addressNumber = rs.getInt(COLUMN_ADDRESSNUMBER);
            addressEstate = rs.getString(COLUMN_ADDRESSESTATE);
            addressCity = rs.getString(COLUMN_ADDRESSCITY);
            addressCounty = rs.getString(COLUMN_ADDRESSCOUNTY);
            dob = rs.getString(COLUMN_DOB);
            wardId = rs.getInt(COLUMN_WARDID);
            doctorName = rs.getString(COLUMN_DOCTORNAME);
            phone = rs.getInt(COLUMN_PHONE);
            email = rs.getString(COLUMN_EMAIL);

            p = new AdultPatient(patientFName, patientLName, patientNumber,addressNumber,addressEstate,addressCity,addressCounty, 
                        dob, wardId, doctorName, phone, email);
            aPatients.add(p);
        }

        //return arraylist of adult patients
        return aPatients;
    }

}
