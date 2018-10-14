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
//This class is the gateway for patients
public class PatientTableGateway {
    //These variables are related to the table headings in my database
    private static final String TABLE_NAME = "patients";
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
    //creates a connection
    private Connection mConnection;
    //creates a gateway
    public PatientTableGateway(Connection connection) {
        mConnection = connection;
    }
    //method for inserting a patient into the database
    public Patient insertPatient(String fName, String lName, int pNum, int addressNumber, String addressEstate, String addressCity, String addressCounty, /*int phoneNumber, String email, */String dob, int wardId, String doctor) throws SQLException {
        //create a patient
        Patient p = null;
        
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
                COLUMN_DOCTORNAME +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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


        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
       
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            id = keys.getInt(1);
            //changes the patient to have attributes
            p = new Patient(fName, lName, pNum, addressNumber, addressEstate, addressCity, addressCounty,/* phoneNumber, email,*/ dob, wardId, doctor);
        }

        //returns the patient
        return p;
    }
    //method for removing a patient from the database
    public void removePatient(Patient p, int i) throws SQLException {
        

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
    public boolean updatePatient(Patient p)throws SQLException
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
                //COLUMN_PHONENUMBER      + " = ?, " +
                //COLUMN_EMAIL            + " = ?, " +
                COLUMN_WARDID           + " = ?, " +
                COLUMN_DOCTORNAME       + " = ? " +
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
        stmt.setInt(9, p.getPatientNumber());
        numRowsAffected = stmt.executeUpdate();
        return (numRowsAffected == 1);
        
    }
    //method for viewing all the patients
    public List<Patient> getPatients() throws SQLException {
        String query;                
        Statement stmt;                
        ResultSet rs;                  
        List<Patient> patients;   
                                        

        String patientFName,patientLName, addressEstate,addressCity,addressCounty, email, dob, doctorName;
        int patientNumber, addressNumber,phoneNumber,wardId;

        Patient p;                   

        //query that selects all data
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        
        patients = new ArrayList<Patient>();
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

            p = new Patient(patientFName, patientLName, patientNumber,addressNumber,addressEstate,addressCity,addressCounty, /*phoneNumber,email,*/ 
                        dob, wardId, doctorName);
            patients.add(p);
        }

        //return arraylist of patients
        return patients;
    }

}