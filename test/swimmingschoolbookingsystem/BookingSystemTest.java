package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class BookingSystemTest {

    private Trainee trainee;
    private BookingSystem booking;
    private TimetableManager timetable;
    private ReviewSystem reviewSystem;
    private ReportSystem report;

    public BookingSystemTest() {
        intializeObject();
    }

   @Test
    public void testAll() {
        testAttendLecture();
        testCancelLecture();
        testDisplayTraineeReport();
    }

    
    public void intializeObject() {
        timetable = new TimetableManager();
        trainee = new Trainee();
        trainee.SaveTraineeDetails(null);
        booking = new BookingSystem(trainee);
        reviewSystem = new ReviewSystem();
        report=new ReportSystem(trainee);
        //one lecture default booked to attend
        trainee.getTraineeById(1).setWalletAmount(5000.0);
        booking.bookingList.add(new BookingSystem(1, "L01", 1, "Booked"));
        booking.bookingList.add(new BookingSystem(2, "L02", 1, "Booked"));
        booking.timetable=timetable;
    }
    
     /**
     * Test of filterTimetable method, of class FilterByLevel.
     */
    @Test
    public void testFilterTimetable() {
        System.out.println("Test case : 1");
        System.out.println("Filter timetable by level\n");
        int level = 1;
        //create filter lecture to return
        List<TimetableManager> result = new ArrayList<>();
        if (level >= 1 && level <= 5) {
            //filter timetable by level
            for (TimetableManager timetable1 : timetable.getLectureList()) {
                if (timetable1.getLectureLevel() == level) {
                    result.add(timetable1);
                }
            }
        } else {
            System.out.println("Level should be 1 to 5");
        }
        assertNotNull(result);
        //show filter timetable
        timetable.displayTimetable(reviewSystem, result);
        System.out.println("\n");
    }
    
    
     /**
     * Test of traineeBookingList method, of class BookingSystem.
     */
    @Test
    public void testTraineeBookingList() {
        System.out.println("Test case : 2");
        System.out.println("Get Trainne all booking details\n");
        System.out.println("traineeBookingList");
        int traineeId = 1;
        List<BookingSystem> result = booking.traineeBookingList(traineeId);
        assertNotNull(result);
        //show traineeBooking list
        booking.displayBooking(booking.bookingList);
        System.out.println("\n");
    }
    
     /**
     * Test of attendLecture method, of class BookingSystem.
     */
    
    public void testAttendLecture() {
        System.out.println("Test case : 3");
        System.out.println("test attend lecture by trainee\n");
        int bookingId = 1;
        int traineeId = 1;
        String lectureId;
        int rating = 5;
        String review = "best class";
        String status;
        String expResult = "Attend";
        int lectureLevel;
        boolean correctBooking;
        correctBooking = booking.correctBooking(bookingId);
        if (correctBooking) {
            status = booking.bookingStatus(bookingId);
            if (status.equalsIgnoreCase("Attend")) {
                System.out.println("You have already attended this class.");
            } else if (status.equalsIgnoreCase("Cancel")) {
                System.out.println("This class has been canceled.");
            } else {
                // Get lectureId from selected booking id to attend
                lectureId = booking.getBookingById(bookingId).getLectureId();
                if (rating >= 1 && rating <= 5) {
                    if (review != null) {
                        // Save review 
                        reviewSystem.addReviewList(traineeId, bookingId, lectureId, rating, review);
                    }
                }

                // Update seat availability
                timetable.updateLectureAvailablity(lectureId, "Attend");
                // Get attend lecture level
                lectureLevel = timetable.getTimetableById(lectureId).getLectureLevel();
                // Update Trainee Level
                trainee.updateTraineeLevel(traineeId, lectureLevel);
                // Change selected booking status
                for (BookingSystem booking1 : booking.bookingList) {
                    if (booking1.getBookingId() == bookingId) {
                        booking1.setBookingStatus("Attend");
                        break;
                    }
                }

            }
        } else {
            System.out.println("This booking ID does not exist.");
        }
         //display attend lecture details
        booking.displaySelectedBooking(bookingId);
        String result = booking.getBookingById(bookingId).getBookingStatus();
        assertEquals(expResult, result);
        if (result.equalsIgnoreCase("Attend")) {
            System.out.println("You have attended this class successfully.");
        }
       
        System.out.println("\n");

    }
    
    
     /**
     * Test of cancelLecture method, of class BookingSystem.
     */
    
    public void testCancelLecture() {
        System.out.println("Test case : 4");
        System.out.println("test cancel lecture by trainee\n");
        int bookingId = 2;
        int traineeId = 1;
        String status = "Cancel";
        String expResult = "Cancel";
        String lectureId = null;
        double lectureFees;
        boolean correctBookingId;
        String bookingStatus;
        correctBookingId = booking.correctBooking(bookingId);
        if (correctBookingId) {
            bookingStatus = booking.bookingStatus(bookingId);
            if (bookingStatus.equalsIgnoreCase("Cancel")) {
                System.out.println("This class has been canceled.");
            } else if (bookingStatus.equalsIgnoreCase("Attend")) {
                System.out.println("You have already attended this class.");
            } else {
                //cancel booking id
                for (BookingSystem booking1 : booking.bookingList) {
                    if (booking1.getBookingId() == bookingId) {
                        //get lecture id from selected booking
                        lectureId = booking1.getLectureId();
                        booking1.setBookingStatus(status);
                        break;
                    }
                }
                //get lecture fees from cancel lecture
                lectureFees = timetable.getTimetableById(lectureId).getLectureFees();
                //update trainee wallet after cancel lecture
                trainee.updateWallet(traineeId, status, lectureFees);
                //update lecture availablity after cancel lecture
                timetable.updateLectureAvailablity(lectureId, "Cancel");
            }
        }
        //get result
        String result = booking.getBookingById(bookingId).getBookingStatus();
        assertEquals(expResult, result);
        if (result.equalsIgnoreCase("Cancel")) {
            System.out.println("The lecture has been canceled successfully.");
        }
        System.out.println("\n");

    }
    
     /**
     * Test of displayStudentReport method, of class ReportSystem.
     */
    
    public void testDisplayTraineeReport() {
        System.out.println("Test case : 5");
        System.out.println("displayTraineeReport");
        int month = 4;
        List<BookingSystem> bookingList = null;
        boolean bookingAvailable = bookingAvailableThisMonth(month);
        List<Trainee> traineeList = trainee.getTraineeList();

        if (bookingAvailable) {
            System.out.println("\n\t\t\tTrainee Bookings Reports");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            for (Trainee trainee1 : traineeList) {
                bookingList = booking.traineeBookingList(trainee1.getTraineeId());
                int totalBookings = 0;
                int totalAttended = 0;
                int totalCancelled = 0;
                for (BookingSystem booking1 : booking.getBookingList()) {
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
                }
            }
        } else {
            System.out.println("Booking Report for this month is not available.");
        }
        assertNotNull(bookingList);
        System.out.println("\n\n");

    }
    

   
    

    @Test
    public void testAddToWallet() {
        System.out.println("Test case : 6");
        System.out.println("Add amount in wallet");
        int traineeId = 1;
        double amount = 5000.0;
        if (amount == 0) {
            System.out.println("Please enter a valid amount.");
        } else {
            for (Trainee trainee1 : trainee.getTraineeList()) {
                if (trainee1.getTraineeId() == traineeId) {
                    double currentWallet = trainee1.getWalletAmount();
                    double updatedWallet = currentWallet + amount;
                    trainee1.setWalletAmount(updatedWallet);
                    System.out.println("Wallet remaining wallet : "+currentWallet);
                    System.out.println("Amount added successfully.\n Current wallet balance : $" + updatedWallet);
                    break;
                }
            }

        }
        double result = trainee.getWalletAmount();
        assertNotNull(result);
        System.out.println("\n\n");
    }
    
    

    @Test
    public void testManageBooking() {
        System.out.println("Test case : 7 ");
        System.out.println("test book lecture by trainee\n");
        int traineeId = 1;
        String lectureId = "1";
        boolean validLectureId = false;
        String status = "Booked";
        int bookingId = 1;
        if (lectureId.matches("\\d+")) {
            if (lectureId.matches("[1-9]")) {
                lectureId = "L0" + lectureId;
            } else {
                lectureId = "L" + lectureId;
            }
        }
        validLectureId = lectureIdExist(lectureId);
        if (validLectureId) {
            //check trainee wallet amount is available for booking class
            //get trainee wallet amount
            double traineeWalletAmount = trainee.getTraineeById(traineeId).getWalletAmount();
            //get trainee level from list
            int traineeLevel = trainee.getTraineeById(traineeId).getLevel();
            //get lecture fees
            double lectureFees = timetable.getTimetableById(lectureId).getLectureFees();
            //get lecture level from list
            int lectureLevel = timetable.getTimetableById(lectureId).getLectureLevel();
            //get lecture availablity 
            int lectureAvailablity = lectureAvailablity(lectureId);
            //amount in trainee wallet to book lecture
            if (traineeWalletAmount < lectureFees) {
                System.out.println("* You have not available amount to book this class $ " + traineeWalletAmount);
                System.out.println("Lecture Fees : " + lectureFees);
                //check trainee does not select duplicate booking lecture
            } else if (booking.duplicateBooking(traineeId, lectureId)) {
                System.out.println("* "+lectureId + " already booked by you ");
                //check selected lecture suitable for trainee level
            } else if (!((traineeLevel + 1) == lectureLevel || traineeLevel == lectureLevel)) {
                System.out.println("* " + lectureId + " is not suitable for your level");
                System.out.println("Your Level : " + traineeLevel);
            } else if (!(lectureAvailablity > 0 && lectureAvailablity < 5)) {
                System.out.println("lecture availablity" + lectureAvailablity);
                System.out.println("* " + lectureId + " has not availablity to booking");
            } else {

                BookingSystem booking1 = new BookingSystem(bookingId, lectureId, traineeId, status);
                booking.bookingList.add(booking1);
                //update wallet
                trainee.updateWallet(traineeId, status, lectureFees);
                //update availablity
                timetable.updateLectureAvailablity(lectureId, status);
            }
        } else {
            System.out.println("Lecture id " + lectureId + " does not exist in the timetable");

        }
        assertNotNull(booking.getBookingList());
        //show booking details after booking lesson
        
    }

   

   

   

   

   

    //check lectureIdExist
    public boolean lectureIdExist(String lectureId) {
        for (TimetableManager timetable1 : timetable.getLectureList()) {
            if (timetable1.getLectureId().equalsIgnoreCase(lectureId)) {
                return true;
            }
        }
        return false;
    }

    //check lecture availablity
    public int lectureAvailablity(String lectureId) {
        int lectureAvailablity = 0;
        for (TimetableManager timetable1 : timetable.getLectureList()) {
            if (timetable1.getLectureId().equalsIgnoreCase(lectureId)) {
                lectureAvailablity = timetable1.getLectureAvailablity();
            }
        }
        return lectureAvailablity;
    }

    //check booking available this month
    public boolean bookingAvailableThisMonth(int month) {
        for (BookingSystem booking1 : booking.getBookingList()) {
            if (Integer.parseInt(timetable.getTimetableById(booking1.getLectureId()).getLectureDate().substring(3, 5)) == month) {
                return true;
            }
        }
        return false;
    }
}
