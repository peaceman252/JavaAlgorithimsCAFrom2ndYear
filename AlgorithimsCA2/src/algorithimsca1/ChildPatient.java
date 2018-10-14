package algorithimsca1;

/**
 *
 * @author n00143569
 */
//class for child patient extends to the patient super class and implements the report interface
public class ChildPatient extends Patient implements Report{
    //instance variables that only the adult patient uses
    private String parentName;
    private int parentMobile;
    private String parentEmail;
    //a blank constructor
    public ChildPatient() {
    }
    
    //a constructor that uses the super class
    public ChildPatient(String newFName, String newLName,int newPNum, int newAddressNumber, String newAddressEstate, String newAddressCity,
            String newAddressCounty, String newDob, int wardId, String newDoctor, String newParentName, int newParentMobile, String newParentEmail)
            {
                super(newFName, newLName, newPNum, newAddressNumber, newAddressEstate, newAddressCity, newAddressCounty, newDob, wardId, newDoctor);
                this.parentName = newParentName;
                this.parentMobile = newParentMobile;
                this.parentEmail = newParentEmail;
                
            }
    //get and set methods
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public int getParentMobile() {
        return parentMobile;
    }

    public void setParentMobile(int parentMobile) {
        this.parentMobile = parentMobile;
    }

    public String getParentEmail() {
        return parentEmail;
    }

    public void setParentEmail(String parentEmail) {
        this.parentEmail = parentEmail;
    }
     //an override that prints contact information of the child patient
    @Override
    public String printContactInfo(){
        return "Patient's contact details " + "          "+
                "Patient Name: " + this.getPatientFName() + " "+this.getPatientLName() +"          "+
                "Parent's Name: " + this.parentName + "          "+
                "Parent's Phone Number: " + this.parentMobile + "          "+
                "Parent's Email Address: " + this.parentEmail;
    }
}

