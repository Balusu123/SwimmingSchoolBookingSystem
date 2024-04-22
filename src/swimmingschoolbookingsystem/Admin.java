package swimmingschoolbookingsystem;

import java.util.Scanner;

public class Admin {

    private Scanner scanner;

    public Admin() {
        scanner = new Scanner(System.in);
    }

    public void adminPanel(TimetableManager timetable, Trainee trainee, ReportSystem report, ReviewSystem review, BookingSystem booking) {
        String selectMenu;
        //show menu to select one 
        System.out.println("\n\t\t\tAdmin Dashboard");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        do {
            System.out.println("a. Timetable");
            System.out.println("b. Trainee Details");
            System.out.println("c. Tutor Details");
            System.out.println("d. Trainee Monthly Report");
            System.out.println("e. Tutor Monthly Report");
            System.out.println("f. Booking Details");
            System.out.println("g. Lecture Reviews");
            System.out.println("h. Logout");
            System.out.print("Select one from above Menu : ");
            selectMenu = scanner.nextLine().trim();
            System.out.println();
            //call method by selectMenu by 
            switch (selectMenu) {
                case "a" -> {
                    timetable.displayTimetable(review, timetable.getLectureList());
                }
                case "b" -> {
                    trainee.displayTraineeDetails();
                }
                case "c" -> {
                    new Tutor().displayTutorDetails();
                }
                case "d" -> {
                    report.filterReportByMonth(booking, timetable, review, "Trainee");
                }
                case "e" -> {
                    report.filterReportByMonth(booking, timetable, review, "Tutor");
                }
                case "f" -> {
                    booking.displayBooking(booking.getBookingList());
                }
                case "g" -> {
                    review.displayReview(timetable, trainee, 0);
                }
                case "h" -> {
                    System.out.println("Logging out...");
                    //return
                    return;
                }
                default ->
                    System.out.println("Invalid selection. Please try again.");
            }
        } while (!selectMenu.equals("h"));

    }

}
