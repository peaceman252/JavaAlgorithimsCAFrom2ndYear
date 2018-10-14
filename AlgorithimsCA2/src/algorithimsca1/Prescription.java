package algorithimsca1;

/**
 *
 * @author n00143569
 */
//class for Prescription
public class Prescription {
    //instance variables
    private int patientId;
    private int prescriptionNumber;
    private int medId;
    private String datePrescribed;
    private String dosage;
    //blank constructor
    public Prescription() {
        this.patientId=0;
        this.prescriptionNumber = 0;
        this.medId=0;
        this.datePrescribed = "";
        this.dosage = "";
    }
    //constructor with values
    public Prescription(int pId,int newPNum, int medId, String date, String dosage) {
        this.patientId=pId;
        this.prescriptionNumber = newPNum;
        this.medId=medId;
        this.datePrescribed = date;
        this.dosage = dosage;
    }
    //get and set methods
    public int getPatientId() {
        return patientId;
    }
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    public int getPrescriptionNumber() {
        return prescriptionNumber;
    }
    public void setPrescriptionNumber(int prescriptionNumber) {
        this.prescriptionNumber = prescriptionNumber;
    }
    public int getMedId() {
        return medId;
    }
    public void setMedId(int medId) {
        this.medId = medId;
    }
    public String getDatePrescribed() {
        return datePrescribed;
    }
    public void setDatePrescribed(String datePrescribed) {
        this.datePrescribed = datePrescribed;
    }
    public String getDosage() {
        return dosage;
    }
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}

