package algorithimsca1;

/**
 *
 * @author n00143569
 */
//class for ward
public class Ward {
    //instance variables
    private int wardId;
    private String wardName;
    private int numBeds;
    private String nurse;
    //blank constructor
    public Ward() {
        this.wardId =0;
        this.wardName = "";
        this.numBeds = 0;
        this.nurse = "";
    }
    //constructor with values
    public Ward(int wardId, String wardName, int numBeds, String nurse) {
        this.wardId =wardId;
        this.wardName = wardName;
        this.numBeds = numBeds;
        this.nurse = nurse;
    }
    //get and set methods
    public int getWardId() {
        return wardId;
    }
    public void setWardId(int wardId) {
        this.wardId = wardId;
    }
    public String getWardName() {
        return wardName;
    }
    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
    public int getNumBeds() {
        return numBeds;
    }
    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }
    public String getNurse() {
        return nurse;
    }
    public void setNurse(String nurse) {
        this.nurse = nurse;
    }
}
