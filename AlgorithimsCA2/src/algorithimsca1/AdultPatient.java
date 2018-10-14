package algorithimsca1;

/**
 *
 * @author n00143569
 */
//class for adult patient extends to the patient super class and implements the report interface
public class AdultPatient extends Patient implements Report{
    //instance variables that only the adult patient uses
    private int phone;
    private String email;
    //a blank constructor
    public AdultPatient() {
    }
    //a constructor that uses the super class
    public AdultPatient(String newFName, String newLName,int newPNum, int newAddressNumber, String newAddressEstate, String newAddressCity,
            String newAddressCounty, String newDob, int wardId, String newDoctor, int phone, String email)
            {
                super(newFName, newLName, newPNum, newAddressNumber, newAddressEstate, newAddressCity, newAddressCounty, newDob, wardId, newDoctor);
                this.phone = phone;
                this.email = email;
                
            }
    //get and set methods
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //an override that prints contact information of the adult patient
    @Override
    public String printContactInfo(){
        return "Patient's contact details " + "          "+
                "Patient Name: " + this.getPatientFName() + " "+this.getPatientLName() +"          "+
                "Phone Number: " + this.phone + "          "+
                "Email Address: " + this.email;
    }
    
}
