package algorithimsca1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author n00143569
 */
//class for doctor(model)
public class Doctor {
    
    private static Doctor instance = null;
    
    public static synchronized Doctor getInstance() {
        if (instance == null) {
            instance = new Doctor();
        }
        return instance;
    }
    //arraylists and gateways
    private List<Patient> patients;
    private PatientTableGateway gateway;
    private List<Prescription> prescriptions;
    private PrescriptionTableGateway gateway2;
    private List<Ward> wards;
    private WardTableGateway gateway3;
    private List<Medication>meds;
    private MedicationTableGateway gateway4;
    private List<ChildPatient> cPatients;
    private CPatientTableGateway gateway5;
     private List<AdultPatient> aPatients;
    private APatientTableGateway gateway6;
    //constructor
    private Doctor() {
            try{
                //creates a connection
                Connection conn = DBConnection.getInstance();
                //creates 6 gateways
                PatientTableGateway gateway = new PatientTableGateway(conn);
                PrescriptionTableGateway gateway2 = new PrescriptionTableGateway(conn);
                WardTableGateway gateway3 = new WardTableGateway(conn);
                MedicationTableGateway gateway4 = new MedicationTableGateway(conn);
                CPatientTableGateway gateway5 = new CPatientTableGateway(conn);
                APatientTableGateway gateway6 = new APatientTableGateway(conn);
                this.gateway = new PatientTableGateway(conn);
                this.gateway2 = new PrescriptionTableGateway(conn);
                this.gateway3 = new WardTableGateway(conn);
                this.gateway4 = new MedicationTableGateway(conn);
                this.gateway5 = new CPatientTableGateway(conn);
                this.gateway6 = new APatientTableGateway(conn);
                //puts data inside arraylists
                this.patients = gateway.getPatients();
                this.prescriptions = gateway2.getPrescriptions();   
                this.wards = gateway3.getWards();
                this.meds = gateway4.getMeds();
                this.cPatients = gateway5.getCPatients();
                this.aPatients = gateway6.getAPatients();
            }
            catch (ClassNotFoundException ex) {
               Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            } 
            catch (SQLException ex) {
                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    //methods for getting the data out of the database
    public List<Patient> getPatients() {
        return new ArrayList<Patient>(this.patients);
    }
     public List<Prescription> getPrescriptions() {
        return new ArrayList<Prescription>(this.prescriptions);
    }
     public List<Medication> getMeds() {
        return new ArrayList<Medication>(this.meds);
    }
     public List<Ward> getWards() {
        return new ArrayList<Ward>(this.wards);
    }
    public List<ChildPatient> getCPatients() {
        return new ArrayList<ChildPatient>(this.cPatients);
    }
     public List<AdultPatient> getAPatients() {
        return new ArrayList<AdultPatient>(this.aPatients);
    }
    //methods for adding data to the database
    public void addPatient(Patient p) {
        try {
            this.gateway.insertPatient(
                    p.getPatientFName(), p.getPatientLName(), p.getPatientNumber(),
                    p.getAddressNumber(), p.getAddressEstate(), p.getAddressCity(), 
                    p.getAddressCounty(), /*p.getPhoneNumber(), p.getEmail(), */p.getDob(),
                    p.getWardId(), p.getDoctorName());
            
            this.patients.add(p);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addPrescription(Prescription pn) {
       try {
            this.gateway2.insertPrescription(pn.getPatientId(),pn.getPrescriptionNumber(), 
                    pn.getMedId(), pn.getDatePrescribed(), pn.getDosage());
            
            this.prescriptions.add(pn);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addWard(Ward wd) {
       try {
            this.gateway3.insertWard(wd.getWardId(),wd.getWardName(),wd.getNumBeds(),wd.getNurse());
            
            this.wards.add(wd);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addMedication(Medication md) {
       try {
            this.gateway4.insertMedication(md.getMedId(),md.getMedicationName(),md.getCost(),md.getBestBeforeDate());
            
            this.meds.add(md);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addChildPatient(ChildPatient p) {
        try {
          this.gateway5.insertCPatient(
                    p.getPatientFName(), p.getPatientLName(), p.getPatientNumber(),
                    p.getAddressNumber(), p.getAddressEstate(), p.getAddressCity(), 
                    p.getAddressCounty(), /*p.getPhoneNumber(), p.getEmail(),*/ p.getDob(),
                    p.getWardId(), p.getDoctorName(), p.getParentName(), p.getParentMobile(), p.getParentEmail());
            
            this.cPatients.add(p);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void addAdultPatient(AdultPatient p) {
        try {
          this.gateway6.insertAPatient(
                    p.getPatientFName(), p.getPatientLName(), p.getPatientNumber(),
                    p.getAddressNumber(), p.getAddressEstate(), p.getAddressCity(), 
                    p.getAddressCounty(), /*p.getPhoneNumber(), p.getEmail(),*/ p.getDob(),
                    p.getWardId(), p.getDoctorName(),p.getPhone(), p.getEmail());
            
            this.aPatients.add(p);
        } 
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     //methods for removing data from the database
    public boolean removePatient(Patient p,int i) {
        try {
            this.gateway.removePatient(p,i);
            this.patients.remove(p);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean removePrescription(Prescription pn,int i) {
        try {
            this.gateway2.removePrescription(pn,i);
            this.prescriptions.remove(pn);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean removeWard(Ward w,int i) {
        try {
            this.gateway3.removeWard(w,i);
            this.wards.remove(w);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean removeMedication(Medication m,int i) {
        try {
            this.gateway4.removeMedication(m,i);
            this.meds.remove(m);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean removeCPatient(ChildPatient p,int i) {
        try {
            this.gateway5.removeCPatient(p,i);
            this.cPatients.remove(p);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
     public boolean removeAPatient(AdultPatient p,int i) {
        try {
            this.gateway6.removeAPatient(p,i);
            this.aPatients.remove(p);
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
     //methods for updating the data in the database
    public boolean updatePatients(Patient p){
        boolean update = false;
        try {
        update = this.gateway.updatePatient(p); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }
    public boolean updatePrescriptions(Prescription p){
        boolean update2 = false;
        try {
        update2 = this.gateway2.updatePrescription(p); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update2;
    }
    public boolean updateWard(Ward w){
        boolean update3 = false;
        try {
        update3 = this.gateway3.updateWard(w); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update3;
    }
    public boolean updateMedication(Medication m){
        boolean update4 = false;
        try {
        update4 = this.gateway4.updateMedication(m);  
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update4;
    }
     public boolean updateCPatients(ChildPatient p){
        boolean update = false;
        try {
        update = this.gateway5.updateCPatient(p); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }
      public boolean updateAPatients(AdultPatient p){
        boolean update = false;
        try {
        update = this.gateway6.updateAPatient(p); 
        }
        catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }
    //methods for finding an object by a certain value (id)
    public Patient findPatientByPatientNumber(int patientNumber) {
        Patient p = null;
        int i = 0;
        boolean found = false;
        while (i < this.patients.size() && !found) {
            p = this.patients.get(i);
            if (p.getPatientNumber() == patientNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
    
    public Prescription findPrescriptionByPrescriptionNumber(int prescriptionNumber) {
        Prescription pn = null;
        int i = 0;
        boolean found = false;
        while (i < this.prescriptions.size() && !found) {
            pn = this.prescriptions.get(i);
            if (pn.getPrescriptionNumber() == prescriptionNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            pn = null;
        }
        return pn;
    }
    public Ward findWardByWardId(int wardId) {
        Ward w = null;
        int i = 0;
        boolean found = false;
        while (i < this.wards.size() && !found) {
            w = this.wards.get(i);
            if (w.getWardId() == wardId) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            w = null;
        }
        return w;
    }
    public Medication findMedicationByMedId(int medId) {
        Medication m = null;
        int i = 0;
        boolean found = false;
        while (i < this.meds.size() && !found) {
            m = this.meds.get(i);
            if (m.getMedId() == medId) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            m = null;
        }
        return m;
    }
    public ChildPatient findCPatientByPatientNumber(int patientNumber) {
        ChildPatient p = null;
        int i = 0;
        boolean found = false;
        while (i < this.cPatients.size() && !found) {
            p = this.cPatients.get(i);
            if (p.getPatientNumber() == patientNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
    public AdultPatient findAPatientByPatientNumber(int patientNumber) {
        AdultPatient p = null;
        int i = 0;
        boolean found = false;
        while (i < this.aPatients.size() && !found) {
            p = this.aPatients.get(i);
            if (p.getPatientNumber() == patientNumber) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            p = null;
        }
        return p;
    }
    //search algorithim for finding a medication by its name
    public static int linearSearch(List<Medication> meds, String a)
    {
        int i = 0;
        boolean found = false;
        while (!found && i != meds.size()){
            if((meds.get(i)).getMedicationName().equalsIgnoreCase(a)){
                found = true;
            }
            else{
                i++;
            }
        }
        if (!found){
            i =-1;
        }
        return i;
    }
}