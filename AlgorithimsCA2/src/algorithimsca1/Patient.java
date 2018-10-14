package algorithimsca1;

/**
 *
 * @author n00143569
 */
//patient super class
public class Patient {
    //instance variables
    private String patientFName;
    private String patientLName;
    private int patientNumber;
    private int addressNumber;
    private String addressEstate;
    private String addressCity;
    private String addressCounty;
    private String dob;
    private int wardId;
    private String doctorName;
    //blank constructor
    public Patient()
    {
        this.patientFName = "";
        this.patientLName = "";
        this.patientNumber = 0;
        this.addressNumber = 0;
        this.addressEstate = "";
        this.addressCity = "";
        this.addressCounty = "";
        this.dob = "";
        this.wardId =0;
        this.doctorName = "";
    }
    //constructor with values
    public Patient(String newFName, String newLName,int newPNum, int newAddressNumber, String newAddressEstate, String newAddressCity, String newAddressCounty, /*int newNumber, String newEmail, */ String newDob, int wardId, String newDoctor)
    {
        this.patientFName = newFName;
        this.patientLName = newLName;
        this.patientNumber = newPNum;
        this.addressNumber = newAddressNumber;
        this.addressEstate = newAddressEstate;
        this.addressCity = newAddressCity;
        this.addressCounty = newAddressCounty;
        this.dob = newDob;
        this.wardId =wardId;
        this.doctorName = newDoctor;
    }
    //to string method
    public String toString()
    {
        return "Patient Name: " + patientFName +" "+ patientLName+
                "\nPatient Number: " + patientNumber +
                "\nPatient's Address: " + addressNumber +" "+ addressEstate+" "+addressCity+" "+addressCounty+" "+
                "\nPatient's Date of Birth: "+ dob +
                "\nPatient's Ward: " + wardId + 
                "\nPatient's Doctor's Name: "+doctorName;
    }
    //get and set methods
    public String getPatientFName() {
        return patientFName;
    }
    public void setPatientFName(String patientFName) {
        this.patientFName = patientFName;
    }
    public String getPatientLName() {
        return patientLName;
    }
    public void setPatientLName(String patientLName) {
        this.patientLName = patientLName;
    }
    public int getPatientNumber() {
        return patientNumber;
    }
    public void setPatientNumber(int patientNumber) {
        this.patientNumber = patientNumber;
    }
    public int getAddressNumber() {
        return addressNumber;
    }
    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }
    public String getAddressEstate() {
        return addressEstate;
    }
    public void setAddressEstate(String addressEstate) {
        this.addressEstate = addressEstate;
    }
    public String getAddressCity() {
        return addressCity;
    }
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
    public String getAddressCounty() {
        return addressCounty;
    }
    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }
    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }
    public int getWardId() {
        return wardId;
    }
    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
