
package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FilterByTutor implements TimetableFilter{
    private  Scanner scanner;
    
    public FilterByTutor(Scanner scanner){
        this.scanner=scanner;
    }

    @Override
    public List<TimetableManager> filterTimetable(List<TimetableManager> timetableList) {
        String selectTutor;
        int tutorId = 0;
        boolean validTutor = false;
        //create filter lecture to store filter timetable
        List<TimetableManager> filterLecture = new ArrayList<>();
        //show tutor details for select tutor id
        new Tutor().displayTutorDetails();
        do {
            //get tutor id from user
            System.out.print("Enter TutorId to show timetable by tutor : ");
            selectTutor = scanner.nextLine();
            if (selectTutor.matches("\\d+")) {
                tutorId = Integer.parseInt(selectTutor);
                validTutor = tutorIdExist(tutorId);
                if (validTutor) {
                    //filter timetable by tutor id
                    for (TimetableManager timetable : timetableList) {
                        if (timetable.getTutorId() == tutorId) {
                            filterLecture.add(timetable);
                        }
                    }
                } else {
                    System.out.println("Tutor id " + tutorId + " does not exist");
                }

            } else {
                System.out.println("* Please enter correct tutor id to show timetable");
            }

        } while (!validTutor);

        return filterLecture;
    }
    
    
    
    
    
    //check valid tutorId
    public boolean tutorIdExist(int tutorId) {
        for (Tutor tutor : new Tutor().getTutorList()) {
            if (tutor.getTutorId() == tutorId) {
                return true;
            }
        }
        return false;
    }
    
}
