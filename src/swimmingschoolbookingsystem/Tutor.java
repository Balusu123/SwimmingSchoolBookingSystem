package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;

public class Tutor {

    private int tutorId;
    private String tutorName;
    private String tutorAge;
    private String experience;
    private String tutorGender;
    private String tutorEmail;
    private String tutorContact;
    private List<Tutor> tutorList;

    public int getTutorId() {
        return tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getTutorAge() {
        return tutorAge;
    }

    public String getExperience() {
        return experience;
    }

    public String getTutorGender() {
        return tutorGender;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public String getTutorContact() {
        return tutorContact;
    }

    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    public Tutor(int tutorId, String tutorName, String tutorAge, String experience, String tutorGender, String tutorEmail, String tutorContact) {
        this.tutorId = tutorId;
        this.tutorName = tutorName;
        this.tutorAge = tutorAge;
        this.experience = experience;
        this.tutorGender = tutorGender;
        this.tutorEmail = tutorEmail;
        this.tutorContact = tutorContact;
    }

    public Tutor() {
        tutorList = new ArrayList<>();
        storeTutorDetails();
    }

    //add pre registered tutor details
    public void storeTutorDetails() {
        Tutor tutor1 = new Tutor(1, "John", "35", "10 years", "Male", "john@gmail.com", "+44 1234567890");
        tutorList.add(tutor1);
        Tutor tutor2 = new Tutor(2, "Emma", "28", "8 years", "Female", "emma@gmail.com", "+44 1234567891");
        tutorList.add(tutor2);
        Tutor tutor3 = new Tutor(3, "William", "30", "6 years", "Male", "william@gmail.com", "+44 1234567892");
        tutorList.add(tutor3);
        Tutor tutor4 = new Tutor(4, "Sophie", "40", "12 years", "Female", "sophie@gmail.com", "+44 1234567893");
        tutorList.add(tutor4);
        Tutor tutor5 = new Tutor(5, "James", "32", "7 years", "Male", "james@gmail.com", "+44 1234567894");
        tutorList.add(tutor5);
        Tutor tutor6 = new Tutor(6, "Daniel", "29", "5 years", "Male", "daniel@gmail.com", "+44 1234567896");
        tutorList.add(tutor6);
    }

    //get tutordetails by id
    public Tutor getTutorById(int tutorId) {
        //create tutor object to return
        Tutor tutor1 = null;
        for (Tutor tutor : tutorList) {
            if (tutor.getTutorId() == tutorId) {
                tutor1 = tutor;
            }
        }
        //return tutur object
        return tutor1;

    }

    //show tutor details
    public void displayTutorDetails() {
    System.out.println("================================================================================================");
    System.out.printf("%-9s  %-11s  %-10s  %-11s  %-7s  %-20s  %-12s%n","Tutor ID","Tutor Name","Tutor Age","Experience","Gender","Email","Contact");
    System.out.println("================================================================================================");
    for (Tutor tutor : tutorList) {
        System.out.printf("%-9d  %-11s  %-10s  %-11s  %-7s  %-20s  %-12s%n",
                tutor.getTutorId(), tutor.getTutorName(), tutor.getTutorAge(),
                tutor.getExperience(), tutor.getTutorGender(), tutor.getTutorEmail(),
                tutor.getTutorContact());
    }
    System.out.println("================================================================================================");
}


}
