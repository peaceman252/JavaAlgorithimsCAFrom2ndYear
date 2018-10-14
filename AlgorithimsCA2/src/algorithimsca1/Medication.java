package algorithimsca1;

/**
 *
 * @author n00143569
 */
//class for medication
public class Medication {
    //instance variables
    private int medId;
    private String medicationName;
    private double cost;
    private String bestBeforeDate;
    //blank constructor
    public Medication(){
        this.medId=0;
        this.medicationName="";
        this.cost=0;
        this.bestBeforeDate="";
        }
    //constructor with values
    public Medication(int medId,String medicationName,double cost, String bestBeforeDate){
        this.medId=medId;
        this.medicationName=medicationName;
        this.cost=cost;
        this.bestBeforeDate=bestBeforeDate;
    }
    //get and set methods
    public int getMedId() {
        return medId;
    }
    public void setMedId(int medId) {
        this.medId = medId;
    }
    public String getMedicationName() {
        return medicationName;
    }
    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getBestBeforeDate() {
        return bestBeforeDate;
    }
    public void setBestBeforeDate(String bestBeforeDate) {
        this.bestBeforeDate = bestBeforeDate;
    } 
}
