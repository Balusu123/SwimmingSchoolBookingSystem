package swimmingschoolbookingsystem;

import java.util.List;
import java.util.Scanner;

public class ReportSystem {

    private Scanner scanner;
    public TimetableManager timetable;
    private Trainee trainee;
    private ReviewSystem review;
    public BookingSystem booking;
    private String getMonth;

    public ReportSystem(Trainee trainee) {
        scanner = new Scanner(System.in);
        this.trainee = trainee;
    }

    public void filterReportByMonth(BookingSystem booking, TimetableManager timetable, ReviewSystem review, String reportShow) {
        this.timetable = timetable;
        this.booking = booking;
        this.review = review;
        boolean correctMonth = false;
        int month;
        do {
            //get month name from user
            System.out.print("Enter month number to view report : ");
            getMonth = scanner.nextLine().trim();
            //validate for correct name enter
            if (getMonth.matches("\\d+")) {
                month = Integer.parseInt(getMonth);
                if (month >= 1 && month <= 12) {
                    //display name by month
                    if (reportShow.equalsIgnoreCase("Trainee")) {
                        displayTraineeReport(month);
                    } else {
                        displayTutorReport(month);
                    }

                    correctMonth = true;
                }
            }

        } while (!correctMonth);
    }

    //display trainee report by month 
    public void displayTraineeReport(int month) {
        //create timetable object to get lecture details by id
        TimetableManager timetable1;
        //create trainee object to get trainee details 
        Trainee trainee2;
        boolean bookingAvailable = booking.bookingAvailableThisMonth(month);
        List<Trainee> traineeList = trainee.getTraineeList();

        if (bookingAvailable) {
            System.out.println("\n\t\t\tTrainee Bookings Reports");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (Trainee trainee1 : traineeList) {
                List<BookingSystem> bookingList = booking.traineeBookingList(trainee1.getTraineeId());
                int totalBookings = 0;
                int totalAttended = 0;
                int totalCancelled = 0;
                for (BookingSystem booking1 : bookingList) {
                    String lectureId = booking1.getLectureId();
                    int bookingMonth = Integer.parseInt(timetable.getTimetableById(lectureId).getLectureDate().substring(3, 5));
                    if (bookingMonth == month) {
                        if (booking1.getBookingStatus().equalsIgnoreCase("Booked") || booking1.getBookingStatus().equalsIgnoreCase("Change")) {
                            totalBookings++;
                        } else if (booking1.getBookingStatus().equalsIgnoreCase("Attend")) {
                            totalAttended++;
                        } else if (booking1.getBookingStatus().equalsIgnoreCase("Cancel")) {
                            totalCancelled++;
                        }
                    }
                }
                if (!bookingList.isEmpty()) {
                    System.out.println("Trainee ID            : " + trainee1.getTraineeId());
                    System.out.println("Trainee FullName      : " + trainee1.getTraineeName());
                    System.out.println("Email                 : " + trainee1.getEmail());
                    System.out.println("Date Of Birth         : " + trainee1.getDateOfBirth());
                    System.out.println("Emergency Contact     : " + trainee1.getEmergencyContact());
                    System.out.println("Trainee Level         : " + trainee1.getLevel());
                    System.out.println("Trainee Gender        : " + trainee1.getGender());
                    System.out.println("Trainee Age           : " + trainee1.getTraineeAge());
                    System.out.println("Total Booked Lectures : " + totalBookings);
                    System.out.println("Total Attend Lectures : " + totalAttended);
                    System.out.println("Total Cancel Lectures : " + totalCancelled);
                    //show trainee booking report by month
                    System.out.println("=========================================================================================================================================");
                    System.out.printf(" %-5s  %-10s  %-8s  %-10s  %-10s  %-12s  %-12s  %-5s  %-15s %-15s %-10s \n", "BookingId", "Trainee ", "Trainee Level", "Tutor Name", "LectureId", "Lecture Level", "Lecture Name", "Fees", "Booking Day", "Booking Date", "Status");
                    System.out.println("=========================================================================================================================================");
                    for (BookingSystem booking2 : bookingList) {
                        if (Integer.parseInt(timetable.getTimetableById(booking2.getLectureId()).getLectureDate().substring(3, 5)) == month) {
                            int bookingId = booking2.getBookingId();
                            int traineeId = booking2.getTraineeId();
                            String lectureId = booking2.getLectureId();
                            timetable1 = timetable.getTimetableById(lectureId);
                            int tutorId = timetable1.getTutorId();
                            trainee2 = trainee.getTraineeById(traineeId);
                            //create timetable
                            int lectureLevel = timetable1.getLectureLevel();
                            int traineeLevel = trainee1.getLevel();
                            String tutorName = new Tutor().getTutorById(tutorId).getTutorName();
                            String lectureName = timetable1.getLectureName();
                            double lectureFees = timetable1.getLectureFees();
                            String lectureDate = timetable1.getLectureDate();
                            String lectureDay = timetable1.getLectureDay();
                            String status = booking2.getBookingStatus();
                            String traineeName = trainee2.getTraineeName();
                            System.out.printf(" %-9s  %-12s  %-11s  %-10s  %-10s  %-12s  %-12s  %-5s  %-15s %-15s %-10s \n",
                                    bookingId, traineeName, "Level " + traineeLevel, tutorName, lectureId,
                                    "Level " + lectureLevel, lectureName, lectureFees, lectureDay, lectureDate, status);

                        }

                    }
                     System.out.println("=========================================================================================================================================");
                }

            }
        } else {
            System.out.println("* Booking Report for this month is not available.");
        }
    }

    //display tutor average rating by id
    public void displayTutorReport(int month) {
        boolean reportAvailable = review.RatingAvailableThisMonth(month);
        double averageRating;
        //get tutor list
        List<Tutor> tutorList = new Tutor().getTutorList();
        if (reportAvailable) {
            System.out.println("\n\t\t\tTutor Average Rating Report");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (Tutor tutor : tutorList) {
                //get average rating 
                averageRating = review.tutorAverageRating(tutor.getTutorId());
                if (averageRating != 0) {
                    System.out.println("Tutor Id       : " + tutor.getTutorId());
                    System.out.println("Tutor Name     : " + tutor.getTutorName());
                    System.out.println("Tutor Age      : " + tutor.getTutorAge());
                    System.out.println("Experience     : " + tutor.getExperience());
                    System.out.println("Tutor Gender   : " + tutor.getTutorGender());
                    System.out.println("Tutor Email    : " + tutor.getTutorEmail());
                    System.out.println("Tutor Contact  : " + tutor.getTutorContact());
                    System.out.println("Average Rating : " + averageRating);
                    review.displayReview(timetable, trainee, tutor.getTutorId());
                }
            }
        } else {
            System.out.println("* Average rating for this month is not available.");
        }

    }

}
