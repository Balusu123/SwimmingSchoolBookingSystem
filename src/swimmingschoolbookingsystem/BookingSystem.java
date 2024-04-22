package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookingSystem {

    private Scanner scanner;
    private Trainee trainee;
    public TimetableManager timetable;
    private int bookingId;
    private String lectureId;
    private int traineeId;
    private String bookingStatus;
    public List<BookingSystem> bookingList;

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getLectureId() {
        return lectureId;
    }

    public void setLectureId(String lectureId) {
        this.lectureId = lectureId;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public List<BookingSystem> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<BookingSystem> bookingList) {
        this.bookingList = bookingList;
    }

    public BookingSystem(int bookingId, String lectureId, int traineeId, String bookingStatus) {
        this.bookingId = bookingId;
        this.lectureId = lectureId;
        this.traineeId = traineeId;
        this.bookingStatus = bookingStatus;
    }

    public BookingSystem(Trainee trainee) {
        scanner = new Scanner(System.in);
        bookingList = new ArrayList<>();
        this.trainee = trainee;

    }

    public boolean manageBooking(TimetableManager timetable1, ReviewSystem review, int traineeId) {
        this.timetable = timetable1;
        String lectureId = null;
        boolean validLectureId = false;
        String status = "Booked";
        String action;
        int bookingId = bookingList.size() + 1;
        //show timetable to book clas
        action = timetable.updateTimetable(review);
        if (!action.equalsIgnoreCase("Back")) {
            //select lecture for booking
            do {
                if (lectureId == null || !lectureId.matches("\\d+") || !validLectureId) {
                    System.out.print("\nEnter lecture S.N for booking class : ");
                    lectureId = scanner.nextLine().trim();
                    if (lectureId.matches("\\d+")) {
                        if (lectureId.matches("[1-9]")) {
                            lectureId = "L0" + lectureId;
                        } else {
                            lectureId = "L" + lectureId;
                        }
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
                    if (duplicateBooking(traineeId, lectureId)) {
                        System.out.println("* " + lectureId + " already booked by you ");
                        //check selected lecture suitable for trainee level
                    } else if (traineeWalletAmount < lectureFees) {
                        System.out.println("* You have not available amount to book this class.Your wallet amount : $ " + traineeWalletAmount);
                        System.out.println("Lecture Fees : $ " + lectureFees);
                        //check trainee does not select duplicate booking lecture
                    } else if (!((traineeLevel + 1) == lectureLevel || traineeLevel == lectureLevel)) {
                        System.out.println("* " + lectureId + " is not suitable for your level");
                        System.out.println("Your Level : " + traineeLevel);
                    } else if (!(lectureAvailablity > 0 && lectureAvailablity < 5)) {
                        System.out.println("lecture availablity" + lectureAvailablity);
                        System.out.println("* " + lectureId + " has not availablity to booking");
                    } else {
                        //create bookingSystem object
                        BookingSystem booking1 = new BookingSystem(bookingId, lectureId, traineeId, status);
                        bookingList.add(booking1);
                        //update wallet
                        trainee.updateWallet(traineeId, status, lectureFees);
                        //update availablity
                        timetable.updateLectureAvailablity(lectureId, status);
                        return true;
                    }
                } else {
                    System.out.println("Lecture id " + lectureId + " does not exist in the timetable");
                    validLectureId = true;

                }
            } while (!validLectureId);
        }
        return false;
    }

    //update booking
    public void updateBooking(ReviewSystem review, TimetableManager timetable, int traineeId) {
        this.timetable = timetable;
        String selectMenu;
        boolean attendLecture = false;
        boolean cancelLecture = false;
        boolean changeLecture = false;
        //show all booking
        filterBooking(traineeId);
        System.out.println("a. Attend booking");
        System.out.println("b. Change booking");
        System.out.println("c. Cancel booking");
        System.out.println("d. Previous Menu");
        do {
            System.out.print("Select one from above menu : ");
            selectMenu = scanner.nextLine().trim();
            if (selectMenu.matches("[a-d]")) {
                if (selectMenu.equals("a")) {
                    attendLecture = attendLecture(review, traineeId);
                    if (attendLecture) {
                        System.out.println("You have attended this class successfully.");
                    }
                } else if (selectMenu.equals("b")) {
                    changeLecture = changeLecture(review, traineeId);
                    if (changeLecture) {
                        System.out.println("Lecture has been changed successfully.");
                    }
                } else if (selectMenu.equals("c")) {
                    cancelLecture = cancelLecture(traineeId);
                    if (cancelLecture) {
                        System.out.println("The lecture has been canceled successfully.");
                    }
                } else {
                    return;
                }
            }
        } while (!selectMenu.matches("[a-d]"));

    }

    //attend class
    public boolean attendLecture(ReviewSystem reviewSystem, int traineeId) {
        String selectBooking;
        int bookingId;
        String lectureId = null;
        String getRating;
        int rating = 0;
        String review;
        String status;
        int lectureLevel;
        boolean correctBooking = false;
        // Display booking details to select for change class
        filterBooking(traineeId);
        do {
            System.out.print("Enter the booking ID to attend the class: ");
            selectBooking = scanner.nextLine().trim();
            if (selectBooking.matches("\\d+")) {
                bookingId = Integer.parseInt(selectBooking);
                correctBooking = correctBooking(bookingId);
                if (correctBooking) {
                    status = bookingStatus(bookingId);
                    if (status.equalsIgnoreCase("Attend")) {
                        System.out.println("* You have already attended this class.");
                    } else if (status.equalsIgnoreCase("Cancel")) {
                        System.out.println("* This class has been canceled.");
                    } else {
                        //get lectureId from selected booking id to attend
                        lectureId = getBookingById(bookingId).getLectureId();
                        //show attended booking details
                        displaySelectedBooking(bookingId);
                        //get rating for attended lecture
                        do {
                            System.out.print("Enter Rating for attended lecture (1-5) : ");
                            getRating = scanner.nextLine().trim();
                            if (getRating.matches("[1-5]")) {
                                rating = Integer.parseInt(getRating);
                            } else {
                                System.out.println("* Please enter correct rating 1 to 5");
                            }
                        } while (!getRating.matches("[1-5]"));
                        //get Review for attended lecture
                        do {
                            System.out.print("Enter review : ");
                            review = scanner.nextLine().trim();
                        } while (review == null);

                        //save review 
                        reviewSystem.addReviewList(traineeId, bookingId, lectureId, rating, review);
                        //update seatAvailablity
                        timetable.updateLectureAvailablity(lectureId, "Attend");
                        //get attend lecture level
                        lectureLevel = timetable.getTimetableById(lectureId).getLectureLevel();
                        //update Trainee Level
                        trainee.updateTraineeLevel(traineeId, lectureLevel);
                        //change selected booking status
                        for (BookingSystem booking : bookingList) {
                            if (booking.getBookingId() == bookingId) {
                                booking.setBookingStatus("Attend");
                            }
                        }
                        return true;
                    }
                } else {
                    System.out.println("* This booking ID does not exist.");
                    correctBooking = true;
                }
            } else {
                System.out.println("* Please enter a valid numeric booking Id.");
                correctBooking = true;
            }
        } while (!correctBooking);
        return false;
    }

    //change class
    public boolean changeLecture(ReviewSystem review, int traineeId) {
        String selectBooking;
        String selectLecture = null;
        String bookingStatus;
        int bookingId;
        String action;
        String lastLecture;
        double lastLectureFees;
        double changeLectureFees;
        String lectureId = null;
        boolean validLectureId = false;
        boolean correctBookingId = false;
        // Display booking details to select for change class
        filterBooking(traineeId);
        do {
            System.out.print("Enter booking id to change lecture : ");
            selectBooking = scanner.nextLine().trim();
            if (selectBooking.matches("\\d+")) {
                bookingId = Integer.parseInt(selectBooking);
                correctBookingId = correctBooking(bookingId);
                if (correctBookingId) {
                    bookingStatus = bookingStatus(bookingId);
                    if (bookingStatus.equalsIgnoreCase("Cancel")) {
                        System.out.println("* This lecture has been canceled.");
                    } else if (bookingStatus.equalsIgnoreCase("Attend")) {
                        System.out.println("* You have already attended this lecture.");
                    } else {
                        // Store last lecture
                        lastLecture = getBookingById(bookingId).getLectureId();
                        // Get new lecture to change lecture
                        action = timetable.updateTimetable(review);
                        if (!action.equalsIgnoreCase("Back")) {
                            do {
                                System.out.print("\nEnter lecture S.N for booking class : ");
                                lectureId = scanner.nextLine().trim();
                                if (lectureId.matches("\\d+")) {
                                    if (lectureId.matches("[1-9]")) {
                                        lectureId = "L0" + lectureId;
                                    } else {
                                        lectureId = "L" + lectureId;
                                    }
                                    validLectureId = lectureIdExist(lectureId);
                                    if (validLectureId) {
                                        // Check trainee wallet amount is available for booking class
                                        double traineeWalletAmount = trainee.getTraineeById(traineeId).getWalletAmount() + timetable.getTimetableById(lastLecture).getLectureFees();
                                        int traineeLevel = trainee.getTraineeById(traineeId).getLevel();
                                        double lectureFees = timetable.getTimetableById(lectureId).getLectureFees();
                                        int lectureLevel = timetable.getTimetableById(lectureId).getLectureLevel();
                                        int lectureAvailablity = lectureAvailablity(lectureId);
                                        if (traineeWalletAmount < lectureFees) {
                                            System.out.println("* You have not available amount to book this class. Your wallet amount : $ " + traineeWalletAmount);
                                            System.out.println("Lecture Fees : $ " + lectureFees);
                                        } else if (duplicateBooking(traineeId, lectureId)) {
                                            System.out.println("* You have already booked this lecture: " + lectureId);
                                        } else if (!((traineeLevel + 1) == lectureLevel || traineeLevel == lectureLevel)) {
                                            System.out.println("* This lecture is not suitable for your level. ");
                                            System.out.println("Your level : " + traineeLevel);
                                        } else if (!(lectureAvailablity > 0 && lectureAvailablity < 5)) {
                                            System.out.println("* This lecture is fully booked. Availability: " + lectureAvailablity);
                                        } else {
                                            //chnage lecture
                                            for (BookingSystem booking : bookingList) {
                                                if (booking.getBookingId() == bookingId) {
                                                    booking.setLectureId(lectureId);
                                                    booking.setBookingStatus("Change");
                                                    break;
                                                }
                                            }
                                            //update last lecture availablity
                                            timetable.updateLectureAvailablity(lastLecture, "Cancel");
                                            //update change lecture availablity
                                            timetable.updateLectureAvailablity(lectureId, "Booked");
                                            //get last lecture fees
                                            lastLectureFees = timetable.getTimetableById(lastLecture).getLectureFees();
                                            //update traineWallet for last lecture
                                            trainee.updateWallet(traineeId, "Cancel", lastLectureFees);
                                            //get change lecture fees
                                            changeLectureFees = timetable.getTimetableById(lectureId).getLectureFees();
                                            //update traineeWalledt for change lecture
                                            trainee.updateWallet(traineeId, "Booked", changeLectureFees);
                                            return true;
                                        }
                                    } else {
                                        System.out.println("Lecture with Id " + lectureId + " does not exist in the timetable.");
                                    }

                                } else {
                                    System.out.println("* Please enter currect lecture S.N for changing lecture");
                                }

                            } while (!validLectureId);
                        }
                    }
                }
            } else {
                System.out.println("* Please enter a valid numeric booking Id.");
                correctBookingId = true;
            }
        } while (!correctBookingId);
        return false;
    }

    //cancel class
    public boolean cancelLecture(int traineeId) {
        String selectBooking;
        int bookingId;
        String status = "Cancel";
        String lectureId = null;
        double lectureFees;
        boolean correctBookingId = false;
        // Display booking details to select for change class
        filterBooking(traineeId);
        do {
            System.out.print("Enter booking id to cancel class : ");
            selectBooking = scanner.nextLine().trim();
            if (selectBooking.matches("\\d+")) {
                bookingId = Integer.parseInt(selectBooking);
                correctBookingId = correctBooking(bookingId);
                if (correctBookingId) {
                    bookingStatus = bookingStatus(bookingId);
                    if (bookingStatus.equalsIgnoreCase("Cancel")) {
                        System.out.println("* This class has been canceled.");
                    } else if (bookingStatus.equalsIgnoreCase("Attend")) {
                        System.out.println("* You have already attended this class.");
                    } else {
                        //cancel booking id
                        for (BookingSystem booking : bookingList) {
                            if (booking.getBookingId() == bookingId) {
                                //get lecture id from selected booking
                                lectureId = booking.getLectureId();
                                booking.setBookingStatus(status);
                                break;
                            }
                        }
                        //get lecture fees from cancel lecture
                        lectureFees = timetable.getTimetableById(lectureId).getLectureFees();
                        //update trainee wallet after cancel lecture
                        trainee.updateWallet(traineeId, status, lectureFees);
                        //update lecture availablity after cancel lecture
                        timetable.updateLectureAvailablity(lectureId, "Cancel");
                        return true;
                    }
                }
            } else {
                System.out.println("* Please enter a valid numeric booking Id.");
                correctBookingId = true;
            }
        } while (!correctBookingId);
        return false;
    }

    //get booking by booking id
    public BookingSystem getBookingById(int bookingId) {
        return bookingList.stream()
                .filter(booking -> booking.getBookingId() == bookingId)
                .findFirst()
                .orElse(null);
    }

    //display trainee booking details
    public void displayBooking(List<BookingSystem> filterBooking) {
        //create timetable object to get lecture details by id
        TimetableManager timetable1;
        //create trainee object to get trainee details 
        Trainee trainee1;
        if (!filterBooking.isEmpty()) {
            System.out.println("=========================================================================================================================================");
            System.out.printf(" %-5s  %-10s  %-8s  %-10s  %-10s  %-12s  %-12s  %-5s  %-15s %-15s %-10s \n", "BookingId", "Trainee ", "Trainee Level", "Tutor Name", "LectureId", "Lecture Level", "Lecture Name", "Fees", "Booking Day", "Booking Date", "Status");
            System.out.println("=========================================================================================================================================");
            for (BookingSystem booking : filterBooking) {
                int bookingId = booking.getBookingId();
                int traineeId = booking.getTraineeId();
                String lectureId = booking.getLectureId();
                timetable1 = timetable.getTimetableById(lectureId);
                int tutorId = timetable1.getTutorId();
                trainee1 = trainee.getTraineeById(traineeId);
                //create timetable
                int lectureLevel = timetable1.getLectureLevel();
                int traineeLevel = trainee1.getLevel();
                String tutorName = new Tutor().getTutorById(tutorId).getTutorName();
                String lectureName = timetable1.getLectureName();
                double lectureFees = timetable1.getLectureFees();
                String lectureDate = timetable1.getLectureDate();
                String lectureDay = timetable1.getLectureDay();
                String status = booking.getBookingStatus();
                String traineeName = trainee1.getTraineeName();
                System.out.printf(" %-9s  %-12s  %-11s  %-10s  %-10s  %-12s  %-12s  %-5s  %-15s %-15s %-10s \n",
                        bookingId, traineeName, "Level " + traineeLevel, tutorName, lectureId,
                        "Level " + lectureLevel, lectureName, lectureFees, lectureDay, lectureDate, status);
            }
            System.out.println("=========================================================================================================================================");
        }

    }

    //display selected booking 
    public void displaySelectedBooking(int bookingId) {
        // Iterate through bookings to find the matching ID
        for (BookingSystem booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                System.out.println("\n\t\t\tAttend Class Details");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Booking ID     : " + booking.getBookingId());
                System.out.println("Trainee Name   : " + trainee.getTraineeById(booking.getTraineeId()).getTraineeName());
                System.out.println("Trainee Level  : " + trainee.getTraineeById(booking.getTraineeId()).getLevel());
                System.out.println("Lecture ID     : " + booking.getLectureId());
                System.out.println("Booking Status : " + booking.getBookingStatus());
                System.out.println("Lecture Name   : " + timetable.getTimetableById(booking.getLectureId()).getLectureName());
                System.out.println("Lecture Date   : " + timetable.getTimetableById(booking.getLectureId()).getLectureDate());
                System.out.println("Lecture Day    : " + timetable.getTimetableById(booking.getLectureId()).getLectureDay());
                System.out.println("Lecture Level  : " + timetable.getTimetableById(booking.getLectureId()).getLectureLevel());
                System.out.println("Lecture Fees   : " + timetable.getTimetableById(booking.getLectureId()).getLectureFees());
                System.out.println("Tutor Name     : " + new Tutor().getTutorById(timetable.getTimetableById(booking.getLectureId()).getTutorId()).getTutorName());

                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            }

        }
    }

    //filter Booking details by one trainee or all
    public void filterBooking(int traineeId) {
        // For trainee ID 1

        List<BookingSystem> filterBooking = new ArrayList<>();
        for (BookingSystem booking : bookingList) {
            if (booking.getTraineeId() == 0 || booking.getTraineeId() == traineeId) {
                filterBooking.add(booking);
            }
        }
        //display booking details 
        displayBooking(filterBooking);

    }

    //get studentBookingList
    public List<BookingSystem> traineeBookingList(int traineeId) {
        //create arrayList to return trainee booking list
        List<BookingSystem> traineeBookings = new ArrayList<>();
        //fetch bookins by trainee id
        for (BookingSystem booking : bookingList) {
            if (booking.getTraineeId() == traineeId) {
                traineeBookings.add(booking);
            }
        }
        //return traineeBookins
        return traineeBookings;
    }

    //check valid lectureId
    public boolean lectureIdExist(String lectureId) {
        for (TimetableManager timetable1 : timetable.getLectureList()) {
            if (timetable1.getLectureId().equalsIgnoreCase(lectureId)) {
                return true;
            }
        }
        return false;
    }

    //check duplicate booking
    public boolean duplicateBooking(int traineeId, String lectureId) {
        for (BookingSystem booking : bookingList) {
            if (booking.getLectureId().equalsIgnoreCase(lectureId) && booking.getTraineeId() == traineeId) {
                if (booking.getBookingStatus().equalsIgnoreCase("Booked") || booking.getBookingStatus().equalsIgnoreCase("Changed")) {
                    return true;
                }
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

    //check for select booking id correct or not
    public boolean correctBooking(int bookingId) {
        for (BookingSystem booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                return true;
            }
        }
        return false;
    }

    //get booking status by bookingId
    public String bookingStatus(int bookingId) {
        String status = null;
        for (BookingSystem booking : bookingList) {
            if (booking.getBookingId() == bookingId) {
                status = booking.getBookingStatus();
            }
        }
        return status;
    }

    //check booking availble for trainee id
    public boolean bookingAvailable(int traineeId) {
        for (BookingSystem booking : bookingList) {
            if (booking.getTraineeId() == traineeId) {
                return true;
            }
        }
        return false;
    }

    //check booking available this month
    public boolean bookingAvailableThisMonth(int month) {
        for (BookingSystem booking : bookingList) {
            if (Integer.parseInt(timetable.getTimetableById(booking.getLectureId()).getLectureDate().substring(3, 5)) == month) {
                return true;
            }
        }
        return false;
    }

}
