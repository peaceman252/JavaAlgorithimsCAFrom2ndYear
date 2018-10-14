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
//This class is the gateway for child patients
public class CPatientTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "childpatients";
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
    private static final String COLUMN_PARENTNAME = "parentName";
    private static final String COLUMN_PARENTMOBILE = "parentMobile";
    private static final String COLUMN_PARENTEMAIL = "parentEmail";
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public CPatientTableGateway(Connection connection) {
        mConnection = connection;
    }
     //method for inserting a child patient into the database
    public ChildPatient insertCPatient(String fName, String lName, int pNum, int addressNumber, String addressEstate, String addressCity, String addressCounty,
        String dob, int wardId, String doctor, String pName, int pMobile, String pEmail) throws SQLException {
        //create a child patient
        ChildPatient p = null;
        
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
                COLUMN_PARENTNAME + ", " +
                COLUMN_PARENTMOBILE + ", " +
                COLUMN_PARENTEMAIL +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        stmt.setString(11, pName);
        stmt.setInt(12, pMobile);
        stmt.setString(13, pEmail);
        
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

//            id = keys.getInt(1);
            //changes the patient to have attributes
            p = new ChildPatient(fName, lName, pNum, addressNumber, addressEstate, addressCity, addressCounty, /*phoneNumber, email,*/ dob, wardId, doctor, pName, pMobile, pEmail);
        }

         //returns the patient
        return p;
    }
    //method for removing a patient from the database
    public void removeCPatient(ChildPatient p, int i) throws SQLException {
        

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
    public boolean updateCPatient(ChildPatient p)throws SQLException
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
                COLUMN_PARENTNAME       + " = ?, " +
                COLUMN_PARENTMOBILE     + " = ?, " +
                COLUMN_PARENTEMAIL      + " = ?  "  +
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
        stmt.setString(9, p.getParentName());
        stmt.setInt(10, p.getParentMobile()); 
        stmt.setString(11, p.getParentEmail());
        stmt.setInt(12, p.getPatientNumber());
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
     //method for viewing all the child patients
    public List<ChildPatient> getCPatients() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<ChildPatient> cPatients;   
                                        

        String patientFName,patientLName, addressEstate,addressCity,addressCounty, dob, doctorName, pName, pEmail;
        int patientNumber, addressNumber,wardId, pMobile;

        ChildPatient p;                   

        //query that selects all data
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        
        cPatients = new ArrayList<ChildPatient>();
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
            pName = rs.getString(COLUMN_PARENTNAME);
            pMobile = rs.getInt(COLUMN_PARENTMOBILE);
            pEmail = rs.getString(COLUMN_PARENTEMAIL);

            p = new ChildPatient(patientFName, patientLName, patientNumber,addressNumber,addressEstate,addressCity,addressCounty,
                        dob, wardId, doctorName, pName, pMobile, pEmail);
            
            cPatients.add(p);
        }

        //return arraylist of adult patients
        return cPatients;
    }

}
