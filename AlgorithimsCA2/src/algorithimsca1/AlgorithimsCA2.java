package algorithimsca1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.List;
//Main method
public class AlgorithimsCA2 {

    public static void main(String[] args) {
        //Scanner in made
        Scanner in = new Scanner(System.in);
        //instance of doctor created
        Doctor doc = Doctor.getInstance();
        //patient created
        Patient p;
        //prescription created
        Prescription pn;
        //ward created
        Ward wd;
        //medication created
        Medication md;
        //variable for while loop
        int end=0;
        //while loop used for displaying the menu
        while (end==0)
        {
            //menu with 18 options
            System.out.println("1.  Input new data.");
            System.out.println("2.  Print all current patients.");
            System.out.println("3.  Print patients contact information");
            System.out.println("4.  Update a child patient's file.");
            System.out.println("5.  Delete a child patient's file.");
            System.out.println("6.  Update a adult patient's file.");
            System.out.println("7.  Delete an adult patient's file.");
            System.out.println("8.  Print current prescriptions.");
            System.out.println("9.  Update a patient's prescription.");
            System.out.println("10.  Delete a prescription.");
            System.out.println("11.  Print current wards.");
            System.out.println("12. Update a ward.");
            System.out.println("13. Delete a ward.");
            System.out.println("14. Print current medications.");
            System.out.println("15. Update a medication.");
            System.out.println("16. Delete a medication.");
            System.out.println("17. Search database for a medication.");
            System.out.println("18. Exit.");
            System.out.println();
            System.out.println("Please select an option.");
            //variable to take the users response
            int ans; 
            ans= in.nextInt();
            if (ans==1)
            {
                //option for adding new data to the database
                System.out.println("Adding new information");
                readFile(in, doc);
            }
            else if (ans==2)
            {
                //option for reading the data that is in the child and adult patient tables
                System.out.println("Viewing child patients");
                viewChildPatients(in,doc);
                System.out.println("Viewing adult patients");
                viewAdultPatients(in,doc);
            }
            else if (ans==3)
            {
                //option for viewing patient contact information
                System.out.println("Viewing patients contact information");
                viewContactInfo(in,doc);
            }
            else if (ans==4)
            {
                //option for updating the child patient table
                System.out.println("Updating child patient");
                updateChildPatient(in, doc);
            }
            else if (ans==5)
            {
                //option for deleting child patient from the database
                System.out.println("Deleting child patient");
                deleteChildPatient(in, doc);
            }
            else if (ans==6)
            {
                //option for updating the adult patient table
                System.out.println("Updating adult patient");
                updateAdultPatient(in, doc);
            }
            else if (ans==7)
            {
                //option for deleting adult patient from the database
                System.out.println("Deleting adult patient");
                deleteAdultPatient(in, doc);
            }
            else if (ans==8)
            {
                //option to view the prescription data
                System.out.println("Viewing prescriptions");
                viewPrescription(in,doc);
            }
            else if (ans==9)
            {
                //option for updating the prescription data
                System.out.println("Which file would you like to update?");
                pn = updatePrescription(in, doc);
            }
            else if (ans==10)
            {
                //option for deleting prescriptions from the database
                System.out.println("Deleting prescription");
                deletePrescription(in, doc);   
            }
            else if (ans==11)
            {
                //option to view the wards in the database
                System.out.println("Viewing wards");
                viewWards(in,doc);
            }
            else if (ans==12)
            {
                //option for updating the ward data
                System.out.println("Which file would you like to update?");
                wd = updateWard(in, doc);
            }
            else if (ans==13)
            {
                //option for deleting wards from the database
                System.out.println("Deleting ward");
                deleteWard(in, doc);
            }
            else if (ans==14)
            {
                //option for viewing medications in the database
                System.out.println("Viewing medications");
                viewMedications(in,doc);
            }
            else if (ans==15)
            {
                //option for updating medication data
                System.out.println("Which file would you like to update?");
                md = updateMedication(in, doc);
            }
            else if (ans==16)
            {
                //option for deleting medications from the database
                System.out.println("Deleting medication");
                deleteMedication(in, doc);
            }
            else if (ans==17)
            {
                //option for finding out where a certain medication is in a database
                System.out.println("Searching for a medication");
                int medreturn = searchMedications(in, doc);
                if (medreturn == -1)
                {
                    System.out.println("The medication is not in the database.");
                }
                else{
                    System.out.println("The medication is at " + (medreturn+1) + " in the database");
                }
            }
            else if (ans==18)
            {
                //option for exiting program
                System.out.println("Thank you for using this program");
                end =1;
            }
            else
            {
                //what happens if users enter an unassigned int
                System.out.println("Invalid entry");
            }
        }
    }
    //method for inputting data
    private static void readFile(Scanner kb, Doctor doc) 
    {
        String x = kb.nextLine();
        System.out.println("Enter file name:");
        String inputFileName = kb.nextLine();
        //inputfile code
        File inputFile = new File(inputFileName);
        try{
            Scanner in = new Scanner(inputFile);
            while (in.hasNextLine())
            {
                //checks what letter is a the start of each block and sorts so each thing is created with the rigth method
                String line = in.nextLine();
                if(line.equalsIgnoreCase("C"))
                {
                    createChildPatient(in, doc);
                }
                else if(line.equalsIgnoreCase("A"))
                {
                    createAdultPatient(in, doc);
                }
                else if(line.equalsIgnoreCase("W"))
                {
                    createWard(in, doc);
                }
                else if(line.equalsIgnoreCase("P"))
                {
                    createPrescription(in, doc);
                }
                else if(line.equalsIgnoreCase("M"))
                {
                    createMedication(in, doc);
                }
            }
            //ends the input
            in.close();
        }
        catch (FileNotFoundException ex)
        {
            //error message
            System.out.println("No such file exists");   
        }   
    }
    //method for viewing contact information using the interface
    private static void viewContactInfo(Scanner kb,Doctor doc)
    {
        String x = kb.nextLine();
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try{
            //output file code
            PrintWriter out = new PrintWriter(outputFileName);
            //arraylists for child and adult patients
            List<ChildPatient> cpatients = doc.getCPatients();
            List<AdultPatient> apatients = doc.getAPatients();
            out.println("Child Patients");
            //outputs child patients contact information
            for (ChildPatient pt : cpatients) {
                out.println(pt.printContactInfo());
            }
            //outputs adult patients contact information
            out.println("Adult Patients");
            for (AdultPatient pt : apatients) {
                out.println(pt.printContactInfo());
            }
            //ends output
            out.close();
        }
        catch(FileNotFoundException e){
            //error message
            System.err.println("File not found");
        }
    }
    //method for creating an adult patient
    private static void createAdultPatient(Scanner in, Doctor doc)
    { 
        String fname = in.nextLine();
        String lname = in.nextLine();
        int pnum = in.nextInt();
        String x = in.nextLine();
        int an = in.nextInt();
        x = in.nextLine();
        String ae = in.nextLine();
        String ac = in.nextLine();
        String aco = in.nextLine();
        String dob = in.nextLine();
        int w = in.nextInt();
        x = in.nextLine();
        String d = in.nextLine();
        int phone = in.nextInt();
        x = in.nextLine();
        String em = in.nextLine();    
        AdultPatient p = new AdultPatient(fname, lname, pnum, an, ae, ac, aco, /*num, em,*/ dob, w, d, phone, em);
        doc.addAdultPatient(p); 
    }
    //method for creating a child patient
    private static void createChildPatient(Scanner in, Doctor doc)
    {
        String fname = in.nextLine();
        String lname = in.nextLine();
        int pnum = in.nextInt();
        String x = in.nextLine();
        int an = in.nextInt();
        x = in.nextLine();
        String ae = in.nextLine();
        String ac = in.nextLine();
        String aco = in.nextLine();
        String dob = in.nextLine();
        int w = in.nextInt();
        x = in.nextLine();
        String d = in.nextLine();
        String pname = in.nextLine();
        int pmob = in.nextInt();
        x = in.nextLine();
        String pem = in.nextLine();  
        ChildPatient p = new ChildPatient(fname, lname, pnum, an, ae, ac, aco, /*num, em,*/ dob, w, d, pname, pmob, pem);
        doc.addChildPatient(p);   
    }
    //method for creating a ward
    private static void createWard(Scanner in, Doctor doc)
    {
        int wardId = in.nextInt();
        String x = in.nextLine();
        String wardName = in.nextLine();
        int numBeds = in.nextInt();
        x = in.nextLine();
        String nurse = in.nextLine();
        Ward wd = new Ward(wardId, wardName, numBeds, nurse);
        doc.addWard(wd);
    }
    //method for creating a prescription
    private static void createPrescription(Scanner in, Doctor doc)
    {
        int pId = in.nextInt();
        String x = in.nextLine();
        int prescriptionNumber = in.nextInt();
        x = in.nextLine();
        int medId = in.nextInt();
        x = in.nextLine();
        String datePrescribed = in.nextLine();
        String dosage = in.nextLine();
        Prescription pn = new Prescription(pId,prescriptionNumber,medId,datePrescribed,dosage);
        doc.addPrescription(pn);
    }
    //method for creating a medication
    private static void createMedication(Scanner in, Doctor doc)
    {
        int medId = in.nextInt();
        String x = in.nextLine();
        String medicationName = in.nextLine();
        double cost = in.nextDouble();
        x = in.nextLine();
        String bestBeforeDate = in.nextLine();
        Medication md = new Medication(medId, medicationName, cost,bestBeforeDate);
        doc.addMedication(md);
    }
    //method for updating child patient
    private static Patient updateChildPatient(Scanner in4,Doctor doc) 
    {
        System.out.print("Enter the Patient Number of the patient to update:");
        int pNumber  = in4.nextInt();
        ChildPatient p;
        String line;
        String addressEstate,addressCity,addressCounty,  doctorName, pFname,pLname,parentName,parentEmail;
        int  addressNumber,wardId, parentMobile;
        //gets patient by using inserted number
        p = doc.findCPatientByPatientNumber(pNumber);
        if (p != null) 
        {
            pFname = getString(in4, "Enter patient's first name: ");
            p.setPatientFName(pFname);
            pLname = getString(in4, "Enter patient's last name: ");
            p.setPatientLName(pLname);
            line = getString(in4, "Enter patient's address number: ");
            addressNumber=Integer.parseInt(line);
            p.setAddressNumber(addressNumber);
            addressEstate = getString(in4, "Enter patient's address estate: ");
            p.setAddressEstate(addressEstate);
            addressCity = getString(in4, "Enter patient's address city: ");
            p.setAddressCity(addressCity);
            addressCounty = getString(in4, "Enter patient's address county: ");
            p.setAddressCounty(addressCounty);
            line = getString(in4, "Enter patient's ward: ");
            wardId= Integer.parseInt(line);
            p.setWardId(wardId);
            doctorName = getString(in4, "Enter patient's doctor: ");
            p.setDoctorName(doctorName);
            parentName = getString(in4, "Enter patient's parent's name");
            p.setParentName(parentName);
            line = getString(in4, "Enter patient's parent's mobile number");
            parentMobile=Integer.parseInt(line);
            p.setParentMobile(parentMobile);
            parentEmail = getString(in4, "Enter patient's parent's email");
            p.setParentEmail(parentEmail);
            doc.updateCPatients(p);
            System.out.println("Patient updated");
        }
        else 
        {
            System.out.println("Patient not found");
        }    
        return p;
    }
    //method for updating adult patient
    private static Patient updateAdultPatient(Scanner in4,Doctor doc) 
    {
        System.out.print("Enter the Patient Number of the patient to update:");
        int pNumber  = in4.nextInt();
        AdultPatient p;
        String line;
        String addressEstate,addressCity,addressCounty,  doctorName, pFname,pLname, email;
        int  addressNumber,wardId, phone;
        p = doc.findAPatientByPatientNumber(pNumber);
        if (p != null) 
        {
            pFname = getString(in4, "Enter patient's first name: ");
            p.setPatientFName(pFname);
            pLname = getString(in4, "Enter patient's last name: ");
            p.setPatientLName(pLname);
            line = getString(in4, "Enter patient's address number: ");
            addressNumber=Integer.parseInt(line);
            p.setAddressNumber(addressNumber);
            addressEstate = getString(in4, "Enter patient's address estate: ");
            p.setAddressEstate(addressEstate);
            addressCity = getString(in4, "Enter patient's address city: ");
            p.setAddressCity(addressCity);
            addressCounty = getString(in4, "Enter patient's address county: ");
            p.setAddressCounty(addressCounty);
            line = getString(in4, "Enter patient's ward: ");
            wardId= Integer.parseInt(line);
            p.setWardId(wardId);
            doctorName = getString(in4, "Enter patient's doctor: ");
            p.setDoctorName(doctorName);
            line = getString(in4, "Enter patient's phone: ");
            phone = Integer.parseInt(line);
            p.setPhone(phone);
            email = getString(in4, "Enter patient's email: ");
            doc.updateAPatients(p);
            System.out.println("Patient updated");
        }
        else 
        {
            System.out.println("Patient not found");
        }    
        return p;
    }
    //method for updating prescription
    private static Prescription updatePrescription(Scanner in4,Doctor doc) 
    {
        System.out.print("Enter the prescription Number of the prescription to update:");
        int pNumber  = in4.nextInt();
        String line;
        String dosage;
        int medId;
        Prescription pn;
        pn = doc.findPrescriptionByPrescriptionNumber(pNumber);
        if (pn != null) 
        {
            line = getString(in4, "Enter medication id: ");
            medId= Integer.parseInt(line);
            pn.setMedId(medId);
            dosage = getString(in4, "Enter dosage: ");
            pn.setDosage(dosage);
            doc.updatePrescriptions(pn);
            System.out.println("Prescription updated");
        }
        else 
        {
            System.out.println("Prescription not found");
        }    
        return pn;
    }
    //method for updating medication
    private static Medication updateMedication(Scanner in4,Doctor doc) 
    {
        System.out.print("Enter the medication id of the medication to update:");
        int pNumber  = in4.nextInt();
        String line;
        String medName,bestBeforeDate;
        double cost;
        Medication m;
        m = doc.findMedicationByMedId(pNumber);
        if (m != null) 
        {
            medName = getString(in4, "Enter medication name: ");
            m.setMedicationName(medName);
            line = getString(in4, "Enter cost: ");
            cost= Double.parseDouble(line);
            m.setCost(cost);
            bestBeforeDate = getString(in4, "Enter best before date: ");
            m.setBestBeforeDate(bestBeforeDate);
            doc.updateMedication(m);
            System.out.println("medication updated");
        }
        else 
        {
            System.out.println("medication not found");
        }    
        return m;
    }
    //method for updating wards
    private static Ward updateWard(Scanner in4,Doctor doc) 
    {
        System.out.print("Enter the ward id of the ward to update:");
        int pNumber  = in4.nextInt();
        String line;
        String wardName,nurse;
        int numbeds;
        Ward w;
        w = doc.findWardByWardId(pNumber);
        if (w != null) 
        {
            wardName = getString(in4, "Enter ward name: ");
            w.setWardName(wardName);
            line = getString(in4, "Enter number of beds: ");
            numbeds= Integer.parseInt(line);
            w.setNumBeds(numbeds);
            nurse = getString(in4, "Enter nurse's name: ");
            w.setNurse(nurse);
            doc.updateWard(w);
            System.out.println("Ward updated");
        }
        else 
        {
            System.out.println("Ward not found");
        }    
        return w;
    }
    //method for deleting child patients
    private static void deleteChildPatient(Scanner in3, Doctor doc) 
    {
        System.out.print("Enter the Patient Number of the patient to delete:");
        int pNumber  = in3.nextInt();
        ChildPatient p;
        p = doc.findCPatientByPatientNumber(pNumber);
        if (p != null) 
        {
            if (doc.removePatient(p, pNumber)) 
            {
                System.out.println("Patient deleted");
            }
            else 
            {
                System.out.println("Patient not deleted");
            }
        }
        else 
        {
            System.out.println("Patient not found");
        }
    }
    //method for deleting adult patients
    private static void deleteAdultPatient(Scanner in3, Doctor doc) 
    {
        System.out.print("Enter the Patient Number of the patient to delete:");
        int pNumber  = in3.nextInt();
        AdultPatient p;
        p = doc.findAPatientByPatientNumber(pNumber);
        if (p != null) 
        {
            if (doc.removePatient(p, pNumber)) 
            {
                System.out.println("Patient deleted");
            }
            else 
            {
                System.out.println("Patient not deleted");
            }
        }
        else 
        {
            System.out.println("Patient not found");
        }
    }
    //method for deleting prescriptions
    private static void deletePrescription(Scanner in3, Doctor doc) 
    {
        System.out.print("Enter the prescription Number of the prescription to delete:");
        int pNumber  = in3.nextInt();
        Prescription pn;
        pn = doc.findPrescriptionByPrescriptionNumber(pNumber);
        if (pn != null) 
        {
            if (doc.removePrescription(pn,pNumber)) 
            {
                System.out.println("Prescription deleted");
            }
            else 
            {
                System.out.println("Prescription not deleted");
            }
        }
        else 
        {
            System.out.println("Prescription not found");
        }
    }
    //method for deleting medications
    private static void deleteMedication(Scanner in3, Doctor doc) 
    {
        System.out.print("Enter the Medication id of the Medication to delete:");
        int pNumber  = in3.nextInt();
        Medication m;
        m = doc.findMedicationByMedId(pNumber);
        if (m != null) 
        {
            if (doc.removeMedication(m,pNumber)) 
            {
                System.out.println("Medication deleted");
            }
            else 
            {
                System.out.println("Medication not deleted");
            }
        }
        else 
        {
            System.out.println("Medication not found");
        }
    }
    //method for deleting a ward
    private static void deleteWard(Scanner in3, Doctor doc) 
    {
        System.out.print("Enter the ward id of the ward to delete:");
        int pNumber  = in3.nextInt();
        Ward w;
        w = doc.findWardByWardId(pNumber);
        if (w != null) 
        {
            if (doc.removeWard(w,pNumber)) 
            {
                System.out.println("Ward deleted");
            }
            else 
            {
                System.out.println("Ward not deleted");
            }
        }
        else 
        {
            System.out.println("Ward not found");
        }
    }
    //method for outputting child patient data
    private static void viewChildPatients(Scanner kb,Doctor doc) 
    {
        String x = kb.nextLine();
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try{
            //code for output
            PrintWriter out = new PrintWriter(outputFileName);
            List<ChildPatient> cpatients = doc.getCPatients();
            for (ChildPatient pt : cpatients) 
            {
                out.println("Name: " + pt.getPatientFName() +"" + pt.getPatientLName());
                out.println("Patient Number: " + pt.getPatientNumber());
            }
            //end of output
            out.close();
        }
        catch(FileNotFoundException e){
            System.err.println("File not found");
        }       
    }
    //method for outputting adult patients
    private static void viewAdultPatients(Scanner kb,Doctor doc) 
    {
        
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try
        {
            PrintWriter out = new PrintWriter(outputFileName);
            List<AdultPatient> apatients = doc.getAPatients();
            for (AdultPatient pt : apatients) 
            {
                out.println("Name: " + pt.getPatientFName() +"" + pt.getPatientLName());
                out.println("Patient Number: " + pt.getPatientNumber());
            }
            out.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
    //method for outputting prescription
    private static void viewPrescription(Scanner kb,Doctor doc) 
    {
        String x = kb.nextLine();
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try
        {
            PrintWriter out = new PrintWriter(outputFileName);
            List<Prescription> prescriptions = doc.getPrescriptions();
            for (Prescription pn : prescriptions) 
            {
                out.println("Id: " + pn.getPatientId());
                out.println("Prescription: " + pn.getDosage());
                out.println("Prescription Number: " + pn.getPrescriptionNumber());
            }
            out.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
    //method for outputting wards
    private static void viewWards(Scanner kb,Doctor doc) 
    {
        String x = kb.nextLine();
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try
        {
            PrintWriter out = new PrintWriter(outputFileName);
            List<Ward> wards = doc.getWards();
            for (Ward wd : wards) 
            {
                out.println("Name: " + wd.getWardName());
                out.println("Nurse: " + wd.getNurse());
            }
            out.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
    //method for outputting medications
    private static void viewMedications(Scanner kb,Doctor doc) 
    {
        String x = kb.nextLine();
        System.out.println("Enter file name: ");
        String outputFileName = kb.nextLine();
        try
        {
            PrintWriter out = new PrintWriter(outputFileName);
            List<Medication> meds = doc.getMeds();
            for (Medication md : meds) 
            {
                out.println("Name: " + md.getMedicationName());
                out.println("Cost: " + md.getCost());
            }
            out.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("File not found");
        }
    }
    //method which activates a search algorithim to find a user named medication
    private static int searchMedications(Scanner kb,Doctor doc)
    {
        List<Medication> meds = doc.getMeds();
        System.out.println("Please enter the name of the medication you want to find:");
        String a = kb.next();
        return doc.linearSearch(meds,a);
    }
    //method for getstring
    private static String getString(Scanner in, String prompt) 
    {
        System.out.print(prompt);
    return in.next();
    }
}
