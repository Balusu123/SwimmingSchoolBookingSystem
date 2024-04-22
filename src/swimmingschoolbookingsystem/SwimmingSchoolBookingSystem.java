package swimmingschoolbookingsystem;


import java.util.Scanner;

public class SwimmingSchoolBookingSystem {

    private Scanner scanner;
    private Trainee trainee;
    private BookingSystem booking;
    private ReportSystem report;
    private ReviewSystem review;
    private TimetableManager timetable;
    private String adminEmail = "admin@gmail.com";
    private String adminPassword = "admin";

    public SwimmingSchoolBookingSystem() {
        trainee = new Trainee();
        //save pre registered trainee details
        trainee.SaveTraineeDetails(trainee);
        timetable = new TimetableManager();
        review = new ReviewSystem();
        booking = new BookingSystem(trainee);
        report = new ReportSystem(trainee);

    }
    
    public void info(){
         System.out.println("\tSwimming School Booking System");
        System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public void openApp() {

        String selectMenu;
        boolean register;
        scanner = new Scanner(System.in);
        System.out.println("\na. Already have an accout");
        System.out.println("b. Create an account");
        System.out.println("c. Exit");
        System.out.print("Select one from above menu : ");
        selectMenu = scanner.nextLine();
        if (!selectMenu.matches("[a-c]")) {
            System.out.println("Please select menu option in alphbatical order ");
            openApp();
        } else {
            switch (selectMenu) {
                case "a" -> {
                    login();

                }
                case "b" -> {
                    register = trainee.trainneRegister();
                    if (register) {
                        System.out.println("You are now a registered trainee.");
                    }

                }
                default -> {
                }
            }
            if (!selectMenu.equals("c")) {
                openApp();
            }
        }

    }

    //user login form
    public void login() {
        boolean isLogin = false;
        String email;
        String password;
        System.out.println("\n\t\tLogin Form");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //get email from user
        System.out.print("Email : ");
        email = scanner.nextLine().trim();
        // get password from user
        if (email.matches("^(.+)@(\\S+)$")) {
            System.out.print("Password : ");
            password = scanner.nextLine().trim();

            // Validate user email and password
            for (Trainee trainee1 : trainee.getTraineeList()) {
                if (trainee1.getEmail().equalsIgnoreCase(email) && trainee1.getPassword().equals(password)) {
                    int traineeId = trainee1.getTraineeId();
                    System.out.println("You are now logged in.");
                    trainee.traineePanel(booking, review, timetable, traineeId);
                    isLogin = true;
                    break;
                    //validate email or password if admin enter email or password
                } else if (adminEmail.equalsIgnoreCase(email) && adminPassword.equals(password)) {
                    System.out.println("You are now logged in.");
                    new Admin().adminPanel(timetable, trainee, report, review, booking);
                    isLogin = true;
                    break;
                }
            }
        } else {
            System.out.println("* Please enter a valid email address.");
        }

        if (!isLogin) {
            System.out.println("* Invalid email or password. Please try again.");
        }
    }
    public static void main(String[] args) {
        SwimmingSchoolBookingSystem school = new SwimmingSchoolBookingSystem();
        school.info();
        school.openApp();

    }

}
