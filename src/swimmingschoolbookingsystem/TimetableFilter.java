package swimmingschoolbookingsystem;

import java.util.List;


public interface TimetableFilter {
    
     List<TimetableManager> filterTimetable(List<TimetableManager>timetableList);
}
