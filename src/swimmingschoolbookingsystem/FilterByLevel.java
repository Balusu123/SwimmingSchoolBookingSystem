package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FilterByLevel implements TimetableFilter{
   private Scanner scanner; 
   
   public FilterByLevel(Scanner scanner){
       this.scanner=scanner;
   }
   
   @Override
    public List<TimetableManager> filterTimetable(List<TimetableManager> timetableList) {
        int level;
        String selectLevel;
        //create filter lecture to return
        List<TimetableManager> filterLecture = new ArrayList<>();
        do {
            //get level from user
            System.out.print("Enter Level (1-5) to show timetable : ");
            selectLevel = scanner.nextLine().trim();
            if (selectLevel.matches("[1-5]")) {
                level = Integer.parseInt(selectLevel);
                //filter timetable by level
                for (TimetableManager timetable : timetableList) {
                    if (timetable.getLectureLevel() == level) {
                        filterLecture.add(timetable);
                    }
                }
            } else {
                System.out.println("* Please enter correct level for show timetable");
            }
        } while (!selectLevel.matches("[1-5]"));
        //return filter lecture
        return filterLecture;
    }

    
  
}
