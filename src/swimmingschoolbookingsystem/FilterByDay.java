package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FilterByDay implements TimetableFilter{
    private Scanner scanner;
    
    public FilterByDay(Scanner scanner){
        this.scanner=scanner;
    }

    @Override
    public List<TimetableManager> filterTimetable(List<TimetableManager> timetableList) {
        String day;
        //create filtetLecture to return
        List<TimetableManager> filterLecture = new ArrayList<>();
        //show day name to select
        System.out.println("\nMonday\t\t\tWednesday\nFriday\t\t\tSaturday\n");
        do {
            //get day name from user
            System.out.print("Enter the day to filter the timetable: ");
            day = scanner.nextLine().trim();
            if (validDay(day)) {
                //filter timetable by day
                for (TimetableManager timetable : timetableList) {
                    if (timetable.getLectureDay().equalsIgnoreCase(day)||timetable.getLectureDay().substring(0, 3).equalsIgnoreCase(day)) {
                        filterLecture.add(timetable);
                    }
                }

            } else {
                System.out.println("* "+day + " does not exist in the timetable.");
            }

        } while (!validDay(day));
        //return filter lecture
        return filterLecture;

    }
    
    
    //check for valid day
    private boolean validDay(String day) {
        return day.equalsIgnoreCase("Mon") || day.equalsIgnoreCase("Wed")
                || day.equalsIgnoreCase("Fri") || day.equalsIgnoreCase("Monday")
                || day.equalsIgnoreCase("Wednesday") || day.equalsIgnoreCase("Friday")
                || day.equalsIgnoreCase("Saturday") || day.equalsIgnoreCase("Sat");
    }
  
}


