package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimetableManager {

    private Scanner scanner;
    private String lectureId;
    private String lectureDay;
    private String lectureName;
    private String lectureTime;
    private int tutorId;
    private int lectureAvailablity;
    private int lectureLevel;
    private String lectureDate;
    private double lectureFees;
    private double avgRating;
    private int viewAllTimetable;
    private List<TimetableManager> lectureList;

    public String getLectureId() {
        return lectureId;
    }

    public String getLectureDay() {
        return lectureDay;
    }

    public String getLectureName() {
        return lectureName;
    }

    public String getLectureTime() {
        return lectureTime;
    }

    public int getTutorId() {
        return tutorId;
    }

    public int getLectureAvailablity() {
        return lectureAvailablity;
    }

    public void setLectureAvailablity(int lectureAvailablity) {
        this.lectureAvailablity = lectureAvailablity;
    }

    public int getLectureLevel() {
        return lectureLevel;
    }

    public void setLectureLevel(int lectureLevel) {
        this.lectureLevel = lectureLevel;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public double getLectureFees() {
        return lectureFees;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public List<TimetableManager> getLectureList() {
        return lectureList;
    }

    public void setLectureList(List<TimetableManager> lectureList) {
        this.lectureList = lectureList;
    }

    public TimetableManager(String lectureId, String lectureDay, String lectureTime, String lectureDate, String lectureName, int tutorId, int lectureLevel, int lectureAvailablity, double lectureFees, double avgRating) {
        this.lectureId = lectureId;
        this.lectureDay = lectureDay;
        this.lectureName = lectureName;
        this.lectureTime = lectureTime;
        this.tutorId = tutorId;
        this.lectureAvailablity = lectureAvailablity;
        this.lectureLevel = lectureLevel;
        this.lectureDate = lectureDate;
        this.lectureFees = lectureFees;
        this.avgRating = avgRating;
    }

    public TimetableManager() {
        lectureList = new ArrayList<>();
        scanner = new Scanner(System.in);
        storeLectureDetails();
    }

    //add lecture in lecturelist
    public void storeLectureDetails() {
        TimetableManager L01 = new TimetableManager("L01", "Monday", "4:00pm - 5:00pm", "08-04-2024", "Toddler Splash", 1, 1, 4, 500.00, 0);
        lectureList.add(L01);
        TimetableManager L02 = new TimetableManager("L02", "Monday", "5:00pm - 6:00pm", "08-04-2024", "Toddler Splash", 3, 1, 4, 460.00, 0);
        lectureList.add(L02);
        TimetableManager L03 = new TimetableManager("L03", "Monday", "6:00pm - 7:00pm", "08-04-2024", "Toddler Splash", 4, 5, 4, 550.00, 0);
        lectureList.add(L03);
        TimetableManager L04 = new TimetableManager("L04", "Wednesday", "4:00pm - 5:00pm", "10-04-2024", "Dive Skills", 6, 3, 4, 600.00, 0);
        lectureList.add(L04);
        TimetableManager L05 = new TimetableManager("L05", "Wednesday", "5:00pm - 6:00pm", "10-04-2024", "Dive Skills", 5, 4, 4, 650.00, 0);
        lectureList.add(L05);
        TimetableManager L06 = new TimetableManager("L06", "Wednesday", "6:00pm - 7:00pm", "10-04-2024", "Dive Skills", 2, 3, 4, 700.00, 0);
        lectureList.add(L06);
        TimetableManager L07 = new TimetableManager("L07", "Friday", "4:00pm - 5:00pm", "12-04-2024", "Swim Strong", 4, 2, 4, 550.00, 0);
        lectureList.add(L07);
        TimetableManager L08 = new TimetableManager("L08", "Friday", "5:00pm - 6:00pm", "12-04-2024", "Swim Strong", 6, 3, 4, 600.00, 0);
        lectureList.add(L08);
        TimetableManager L09 = new TimetableManager("L09", "Friday", "6:00pm - 7:00pm", "12-04-2024", "Swim Strong", 5, 2, 4, 650.00, 0);
        lectureList.add(L09);
        TimetableManager L10 = new TimetableManager("L10", "Saturday", "2:00pm - 3:00pm", "13-04-2024", "Water Polo", 1, 4, 4, 700.00, 0);
        lectureList.add(L10);
        TimetableManager L11 = new TimetableManager("L11", "Saturday", "3:00pm - 4:00pm", "13-04-2024", "Water Polo", 4, 4, 4, 650.00, 0);
        lectureList.add(L11);
        TimetableManager L12 = new TimetableManager("L12", "Monday", "4:00pm - 5:00pm", "15-04-2024", "Toddler Splash", 3, 1, 4, 500.00, 0);
        lectureList.add(L12);
        TimetableManager L13 = new TimetableManager("L13", "Monday", "5:00pm - 6:00pm", "15-04-2024", "Toddler Splash", 1, 4, 4, 550.00, 0);
        lectureList.add(L13);
        TimetableManager L14 = new TimetableManager("L14", "Monday", "6:00pm - 7:00pm", "15-04-2024", "Toddler Splash", 4, 1, 4, 460.00, 0);
        lectureList.add(L14);
        TimetableManager L15 = new TimetableManager("L15", "Wednesday", "4:00pm - 5:00pm", "17-04-2024", "Dive Skills", 3, 1, 4, 500.00, 0);
        lectureList.add(L15);
        TimetableManager L16 = new TimetableManager("L16", "Wednesday", "5:00pm - 6:00pm", "17-04-2024", "Dive Skills", 2, 5, 4, 600.00, 0);
        lectureList.add(L16);
        TimetableManager L17 = new TimetableManager("L17", "Wednesday", "6:00pm - 7:00pm", "17-04-2024", "Dive Skills", 2, 5, 4, 650.00, 0);
        lectureList.add(L17);
        TimetableManager L18 = new TimetableManager("L18", "Friday", "4:00pm - 5:00pm", "19-04-2024", "Swim Strong", 2, 4, 4, 650.00, 0);
        lectureList.add(L18);
        TimetableManager L19 = new TimetableManager("L19", "Friday", "5:00pm - 6:00pm", "19-04-2024", "Swim Strong", 1, 5, 4, 700.00, 0);
        lectureList.add(L19);
        TimetableManager L20 = new TimetableManager("L20", "Friday", "6:00pm - 7:00pm", "19-04-2024", "Swim Strong", 4, 4, 4, 650.00, 0);
        lectureList.add(L20);
        TimetableManager L21 = new TimetableManager("L21", "Saturday", "2:00pm - 3:00pm", "20-04-2024", "Water Polo", 1, 1, 4, 550.00, 0);
        lectureList.add(L21);
        TimetableManager L22 = new TimetableManager("L22", "Saturday", "3:00pm - 4:00pm", "20-04-2024", "Water Polo", 4, 5, 4, 750.00, 0);
        lectureList.add(L22);
        TimetableManager L23 = new TimetableManager("L23", "Monday", "4:00pm - 5:00pm", "22-04-2024", "Toddler Splash", 2, 5, 4, 550.00, 0);
        lectureList.add(L23);
        TimetableManager L24 = new TimetableManager("L24", "Monday", "5:00pm - 6:00pm", "22-04-2024", "Toddler Splash", 3, 2, 4, 550.00, 0);
        lectureList.add(L24);
        TimetableManager L25 = new TimetableManager("L25", "Monday", "6:00pm - 7:00pm", "22-04-2024", "Toddler Splash", 3, 1, 4, 500.00, 0);
        lectureList.add(L25);
        TimetableManager L26 = new TimetableManager("L26", "Wednesday", "4:00pm - 5:00pm", "24-04-2024", "Dive Skills", 6, 3, 4, 600.00, 0);
        lectureList.add(L26);
        TimetableManager L27 = new TimetableManager("L27", "Wednesday", "5:00pm - 6:00pm", "24-04-2024", "Dive Skills", 4, 2, 4, 550.00, 0);
        lectureList.add(L27);
        TimetableManager L28 = new TimetableManager("L28", "Wednesday", "6:00pm - 7:00pm", "24-04-2024", "Dive Skills", 3, 1, 4, 500.00, 0);
        lectureList.add(L28);
        TimetableManager L29 = new TimetableManager("L29", "Friday", "4:00pm - 5:00pm", "26-04-2024", "Swim Strong", 3, 1, 4, 500.00, 0);
        lectureList.add(L29);
        TimetableManager L30 = new TimetableManager("L30", "Friday", "5:00pm - 6:00pm", "26-04-2024", "Swim Strong", 6, 1, 4, 460.00, 0);
        lectureList.add(L30);
        TimetableManager L31 = new TimetableManager("L31", "Friday", "6:00pm - 7:00pm", "26-04-2024", "Swim Strong", 5, 5, 4, 550.00, 0);
        lectureList.add(L31);
        TimetableManager L32 = new TimetableManager("L32", "Saturday", "2:00pm - 3:00pm", "27-04-2024", "Water Polo", 1, 3, 4, 600.00, 0);
        lectureList.add(L32);
        TimetableManager L33 = new TimetableManager("L33", "Saturday", "3:00pm - 4:00pm", "27-04-2024", "Water Polo", 5, 4, 4, 650.00, 0);
        lectureList.add(L33);
        TimetableManager L34 = new TimetableManager("L34", "Monday", "4:00pm - 5:00pm", "29-04-2024", "Toddler Splash", 2, 2, 4, 700.00, 0);
        lectureList.add(L34);
        TimetableManager L35 = new TimetableManager("L35", "Monday", "5:00pm - 6:00pm", "29-04-2024", "Toddler Splash", 2, 2, 4, 650.00, 0);
        lectureList.add(L35);
        TimetableManager L36 = new TimetableManager("L36", "Monday", "6:00pm - 7:00pm", "29-04-2024", "Toddler Splash", 5, 3, 4, 550.00, 0);
        lectureList.add(L36);
        TimetableManager L37 = new TimetableManager("L37", "Wednesday", "4:00pm - 5:00pm", "01-05-2024", "Dive Skills", 1, 4, 4, 600.00, 0);
        lectureList.add(L37);
        TimetableManager L38 = new TimetableManager("L38", "Wednesday", "5:00pm - 6:00pm", "01-05-2024", "Dive Skills", 6, 4, 4, 650.00, 0);
        lectureList.add(L38);
        TimetableManager L39 = new TimetableManager("L39", "Wednesday", "6:00pm - 7:00pm", "01-05-2024", "Dive Skills", 5, 5, 4, 700.00, 0);
        lectureList.add(L39);
        TimetableManager L40 = new TimetableManager("L40", "Friday", "4:00pm - 5:00pm", "03-05-2024", "Swim Strong", 6, 2, 4, 500.00, 0);
        lectureList.add(L40);
        TimetableManager L41 = new TimetableManager("L41", "Friday", "5:00pm - 6:00pm", "03-05-2024", "Swim Strong", 6, 3, 4, 550.00, 0);
        lectureList.add(L41);
        TimetableManager L42 = new TimetableManager("L42", "Friday", "6:00pm - 7:00pm", "03-05-2024", "Swim Strong", 5, 1, 4, 600.00, 0);
        lectureList.add(L42);
        TimetableManager L43 = new TimetableManager("L43", "Saturday", "2:00pm - 3:00pm", "04-05-2024", "Water Polo", 2, 2, 4, 650.00, 0);
        lectureList.add(L43);
        TimetableManager L44 = new TimetableManager("L44", "Saturday", "3:00pm - 4:00pm", "04-05-2024", "Water Polo", 1, 3, 4, 700.00, 0);
        lectureList.add(L44);
    }

    // Display timetable
    public void displayTimetable(ReviewSystem review, List<TimetableManager> timetableList) {
        String averageRating;
        if (!timetableList.isEmpty()) {
            System.out.println("=================================================================================================================================================");
            System.out.printf("%-5s %-5s  %-12s  %-10s  %-12s  %-14s  %-12s  %-12s  %-8s  %-8s %-4s %-10s \n", "S.N", "LectureId", "Lecture Name", "Lecture Day", "Lecture Date", "Lecture Time", "Tutor Name", "Tutor Exp.", "Level", "Fees", "Seat", "Avg. Rating");
            System.out.println("=================================================================================================================================================");
            //get timetable details
            for (TimetableManager timetable : timetableList) {
                String lectureId = timetable.getLectureId();
                String lectureDay = timetable.getLectureDay();
                String lectureName = timetable.getLectureName();
                String lectureTime = timetable.getLectureTime();
                int tutorId = timetable.getTutorId();
                Tutor tutor1 = new Tutor().getTutorById(tutorId);
                String tutorName = tutor1.getTutorName();
                String tutorExperience = tutor1.getExperience();
                int lectureLevel = timetable.getLectureLevel();
                String lectureDate = timetable.getLectureDate();
                double lectureFees = timetable.getLectureFees();
                int availablity = timetable.getLectureAvailablity();
                //get averating of each lecture
                averageRating = String.format("%.1f", review.calculateAverageRating(lectureId));
                //print timetable details in table
                System.out.printf("%-5s %-5s  %-15s  %-10s  %-12s  %-17s  %-10s  %-10s  %-10s  %-10s %-5s %-10s \n", Integer.parseInt(lectureId.substring(1)), lectureId, lectureName, lectureDay, lectureDate, lectureTime, tutorName, tutorExperience, "Level " + lectureLevel, " $" + lectureFees, availablity, averageRating);
            }
            System.out.println("=================================================================================================================================================");
        }

    }

    //filter time table
    public String updateTimetable(ReviewSystem review) {
        String selectMenu;
        String action = null;

        // Display timetable without filter 
        if (viewAllTimetable == 0) {
            viewAllTimetable++;
            displayTimetable(review, lectureList);
        }
        //display menu to select
        System.out.println("\na. Timetable by Level");
        System.out.println("b. Timetable by Day ");
        System.out.println("c. Timetable by Tutor");
        System.out.println("d. All timetable");
        System.out.println("e. Select class for booking");
        System.out.println("f. Previous Menu\n");

        do {
            System.out.print("Select one from above menu : ");
            selectMenu = scanner.nextLine().trim();

            switch (selectMenu) {
                case "a" ->
                    //filter timetable by level
                    displayTimetable(review, new FilterByLevel(scanner).filterTimetable(lectureList));
                case "b" ->
                    //filter timetable by day
                    displayTimetable(review, new FilterByDay(scanner).filterTimetable(lectureList));
                case "c" ->
                    //filter timetable by tutor
                    displayTimetable(review, new FilterByTutor(scanner).filterTimetable(lectureList));
                case "d" ->
                    //display all timetable
                    displayTimetable(review, lectureList);
                case "e" ->
                    action = "Select Lecture";
                case "f" ->
                    action = "Back";
                default ->
                    System.out.println("Invalid option. Please select again.");
            }

        } while (!selectMenu.matches("[a-f]"));

        if (action == null || !(action.equalsIgnoreCase("Back") || action.equalsIgnoreCase("Select Lecture"))) {
            return updateTimetable(review);
        } else {
            // set veis all timetable 0
            viewAllTimetable = 0;
            return action;
        }
    }

    //get lecture details by lecture id
    public TimetableManager getTimetableById(String lectureId) {
        return lectureList.stream()
                .filter(lecture -> lecture.getLectureId().equalsIgnoreCase(lectureId))
                .findFirst()
                .orElse(null);
    }

    //update lectureAvailablity
    public void updateLectureAvailablity(String lectureId, String status) {
        int currentAvailablity;
        int updateAvailablity;
        //find lecture details by id
        for (TimetableManager timetable : lectureList) {
            if (timetable.getLectureId().equalsIgnoreCase(lectureId)) {
                //get currentAvailablity
                currentAvailablity = timetable.getLectureAvailablity();
                //update availablity by  status
                if (status.equalsIgnoreCase("Booked")) {
                    //update Availablity
                    updateAvailablity = currentAvailablity - 1;
                    //set updated availablity
                    timetable.setLectureAvailablity(updateAvailablity);
                    break;
                } else {
                    updateAvailablity = currentAvailablity + 1;
                    timetable.setLectureAvailablity(updateAvailablity);
                    break;
                }
            }
        }
    }

}
