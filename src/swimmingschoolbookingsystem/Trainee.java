package swimmingschoolbookingsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Trainee {

    private Scanner scanner;
    private int traineeId;
    private String traineeName;
    private String traineeEmail;
    private String traineeContact;
    private String gender;
    private long traineeAge;
    private String dateOfBirth;
    private int level;
    private double walletAmount;
    private String password;
    private final int minimumAge = 4;
    private final int maximumAge = 11;
    private List<Trainee> traineeList;

    public int getTraineeId() {
        return traineeId;
    }

    public String getTraineeName() {
        return traineeName;
    }

    public String getEmail() {
        return traineeEmail;
    }

    public String getEmergencyContact() {
        return traineeContact;
    }

    public String getGender() {
        return gender;
    }

    public long getTraineeAge() {
        return traineeAge;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public int getLevel() {
        return level;
    }
    
    
    public void setLevel(int level) {
        this.level = level;
    }
    
     public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public String getPassword() {
        return password;
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public Trainee(int traineeId, String traineeName, String traineeEmail, String traineeContact, String gender, long traineeAge, String dateOfBirth, int level, double walletAmount, String password) {
        this.traineeId = traineeId;
        this.traineeName = traineeName;
        this.traineeEmail = traineeEmail;
        this.traineeContact = traineeContact;
        this.gender = gender;
        this.traineeAge = traineeAge;
        this.dateOfBirth = dateOfBirth;
        this.level = level;
        this.walletAmount=walletAmount;
        this.password = password;
    }

    public Trainee() {
        traineeList = new ArrayList<>();
        scanner = new Scanner(System.in);
        
      
        
    }
    
   
    
     //trainee panel
    public void traineePanel(BookingSystem booking, ReviewSystem review,TimetableManager timetable, int traineeId) {
        String selectMenu;
        boolean bookLecture;
        do {
            System.out.println("\na. View Profile");
            System.out.println("b. Add Wallet ");
            System.out.println("c. Manage booking");
            System.out.println("d. Update booking");
            System.out.println("e. Logout");
            System.out.println();
            System.out.print("Select one from above Menu : ");
            selectMenu = scanner.nextLine().trim();
            //call method by selectMenu by 
            switch (selectMenu) {
                case "a" -> {
                    traineeProfile(traineeId);
                }
                case "b" -> {
                    addWallet(traineeId);
                }
                case "c" -> {
                    bookLecture = booking.manageBooking(timetable, review, traineeId);
                    if (bookLecture) {
                        System.out.println("Your lecture booking has been confirmed.");
                    }
                }
                case "d" -> {
                    if (booking.bookingAvailable(traineeId)) {
                        booking.updateBooking(review, timetable, traineeId);
                    } else {
                        System.out.println("* There are no bookings available for update at the moment.");
                    }
                }
                case "e" -> {
                    System.out.println("Logging out...");
                    //return
                    return;
                }
                default ->
                    System.out.println("Invalid selection. Please try again.");
            }
        } while (!selectMenu.equals("e"));
    }

    
    //add amount in wallet
    public void addWallet(int traineeId) {
        String amount;
        boolean correctAmount=false;
        double walletAmount = 0;
        do {
            //get amount from trainee to add in wallet
            System.out.print("Enter Amount : $ ");
            amount = scanner.nextLine().trim();
            if (!amount.matches("[0-9]{1,13}(\\.[0-9]*)?")||amount.equals("0")) {
                System.out.println("Wrong amount format. Please enter a valid amount.");
                correctAmount=true;
            } else {
                try {
                   walletAmount = Double.parseDouble(amount);
                    for (Trainee trainee : traineeList) {
                        if (trainee.getTraineeId() == traineeId) {
                            double currentWallet = trainee.getWalletAmount();
                            double updatedWallet = currentWallet + walletAmount;
                            trainee.setWalletAmount(updatedWallet);
                            System.out.println("Amount added successfully.\n Current wallet balance : $" + updatedWallet);
                            break;
                        }
                    }
                    //break loop after amount added
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid number format.");
                }
            }
        } while (!correctAmount);
    }

    
    //update wallet 
    public void updateWallet(int traineeId, String status, double amount) {
        double currentWallet;
        double updateWallet;
        for (Trainee trainee : traineeList) {
            if (trainee.getTraineeId() == traineeId) {
                //get current wallet amount
                currentWallet = trainee.getWalletAmount();
                //update wallet after booking lesson
                if (status.equalsIgnoreCase("booked")) {
                    updateWallet = currentWallet - amount;
                    trainee.setWalletAmount(updateWallet);
                    break;
                } else if (status.equalsIgnoreCase("Cancel")) {
                    updateWallet = currentWallet + amount;
                    trainee.setWalletAmount(updateWallet);
                    break;
                }
            }
        }
        
    }

    public boolean trainneRegister() {
        boolean saveData;
        int id = 0;
        String fullName = null;
        String email = null;
        String emergencyContact = null;
        String dob = null;
        String traineeLevel = null;
        String password = null;
        String gender = null;

        System.out.println("\n\t\tRegistration Form");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Get full name from user
        if (fullName == null || !fullName.matches("[a-zA-Z\\s]+")) {
            do {
                System.out.print("FullName : ");
                fullName = scanner.nextLine().trim();
                // Validate the full name
                if (!fullName.matches("[a-zA-Z\\s]+")) {
                    System.out.println("* Please enter a valid full name.");
                    fullName = null;
                }
                // Check if the name is already exist
                if (uniqueName(fullName)) {
                    System.out.println("* This name is already taken.");
                    //set fullName null 
                    fullName = null;
                }

            } while (fullName == null);
        }

        //get email from user
        if (email == null || !email.matches("^(.+)@(\\S+)$") || uniqueEmail(email)) {
            do {
                System.out.print("Email : ");
                email = scanner.nextLine().trim();
                if (email.isEmpty() || !email.matches("^(.+)@(\\S+)$")) {
                    System.out.println("* Please enter correct email id ");
                } else if (!uniqueEmail(email)) {
                    System.out.println("* This email already exist");
                }
            } while (email.isEmpty() || !email.matches("^(.+)@(\\S+)$") || !uniqueEmail(email));
        }

        //get date of birth from user
        if (dob == null || !dob.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            do {
                System.out.print("Date of birth (DD-MM-YYYY) : ");
                dob = scanner.nextLine().trim();
                if (dob == null || !dob.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                    System.out.println("* Please enter correct date of birth ");
                } else {
                    traineeAge = calculateAge(dob);
                    if (!(traineeAge >= minimumAge && traineeAge <= maximumAge)) {
                        System.out.println("* Your age is greater than maximum age");
                    }
                }
            } while (dob == null || !dob.matches("^\\d{2}-\\d{2}-\\d{4}$") || !(traineeAge >= minimumAge && traineeAge <= maximumAge));
        }

        //get emergency contact from user
        if (emergencyContact == null || !emergencyContact.matches("\\d+")) {
            do {
                System.out.print("Emergency Contact : ");
                emergencyContact = scanner.nextLine().trim();
                if (emergencyContact == null || !emergencyContact.matches("\\d+")) {
                    System.out.println("* Please enter correct emergency contact");
                }
            } while (emergencyContact == null || !emergencyContact.matches("\\d+"));
        }
        //get level from user
        if (traineeLevel == null || !traineeLevel.matches("[1-5]")) {
            do {
                System.out.print("Level (1-5) : ");
                traineeLevel = scanner.nextLine().trim();
                if (traineeLevel == null || !traineeLevel.matches("[1-5]")) {
                    System.out.println("* Please enter correct level");
                } else {
                    level = Integer.parseInt(traineeLevel);
                }
            } while (traineeLevel == null || !traineeLevel.matches("[1-5]"));
        }
        //get gender from user
        if (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")
                || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female"))) {
            do {
                System.out.print("Gender : ");
                gender = scanner.nextLine().trim();
                if (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")
                        || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female"))) {
                    System.out.println("* Please enter correct trainee gender");
                }

            } while (gender == null || !(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")
                    || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")));
        }
        //get password from user
        if (password == null || password.length() < 5) {
            do {
                System.out.print("Password : ");
                password = scanner.nextLine();
                if (password == null || password.length() < 5) {
                    System.out.println("* Password should be greater than 4 character");
                }
            } while (password == null || password.length() < 5);
        }
        //create trainee Object
        if (gender.equalsIgnoreCase("M")) {
            gender = "Male";
        }
        if (gender.equalsIgnoreCase("F")) {
            gender = "Female";
        }
        Trainee trainee1 = new Trainee(id, fullName, email, emergencyContact, gender, traineeAge, dob, level, walletAmount, password);
        //get save data result in boolean
        saveData = SaveTraineeDetails(trainee1);

        return saveData;
    }

    public boolean SaveTraineeDetails(Trainee trainee) {
        //save pre-registered trainee details if traineeList is empty
        if (traineeList.isEmpty()) {
            traineeList.add(new Trainee(1, "John Smith", "john@gmail.com", "123456789", "Male", 11, "01-01-2013", 5, 0, "12345"));
            traineeList.add(new Trainee(2, "Emma Johnson", "emma@gmail.com", "987654321", "Female", 9, "05-03-2015", 4, 0, "12345"));
            traineeList.add(new Trainee(3, "Michael Brown", "michael@gmail.com", "456123789", "Male", 6, "10-07-2017", 3, 0, "12345"));
            traineeList.add(new Trainee(4, "Sophie Wilson", "sophie@gmail.com", "654789123", "Female", 4, "15-09-2019", 2, 0, "12345"));
            traineeList.add(new Trainee(5, "David Taylor", "david@gmail.com", "321654987", "Male", 5, "20-11-2018", 1, 0, "12345"));
            traineeList.add(new Trainee(6, "Olivia Davies", "olivia@gmail.com", "789456123", "Female", 9, "25-04-2015", 5, 0, "12345"));
            traineeList.add(new Trainee(7, "James Evans", "james@gmail.com", "456789123", "Male", 8, "30-08-2016", 4, 0, "12345"));
            traineeList.add(new Trainee(8, "Emily Thomas", "emily@gmail.com", "987123456", "Female", 4, "02-12-2019", 3, 0, "12345"));
            traineeList.add(new Trainee(9, "Thomas Hughes", "thomas@gmail.com", "321987654", "Male", 7, "18-06-2016", 2, 0, "12345"));
            traineeList.add(new Trainee(10, "Charlotte Clark", "charlotte@gmail.com", "654321789", "Female", 6, "08-02-2018", 1, 0, "12345"));
            traineeList.add(new Trainee(11, "Amelia Baker", "amelia@gmail.com", "789321654", "Female", 9, "22-10-2014", 5, 0, "12345"));
        } else {
            if (trainee != null) {
                //get trainee details from object
                traineeId = traineeList.size() + 1;
                traineeName = trainee.getTraineeName();
                traineeContact = trainee.getEmergencyContact();
                level = trainee.getLevel();
                gender = trainee.getGender();
                traineeEmail = trainee.getEmail();
                password = trainee.getPassword();
                dateOfBirth = trainee.getDateOfBirth();
                traineeAge = trainee.getTraineeAge();
                //save trainee details in arrayList
                traineeList.add(new Trainee(traineeId, traineeName, traineeEmail, traineeContact, gender, traineeAge, dateOfBirth, level, walletAmount, password));
                return true;

            }
        }
        return false;
    }
    
    //display trainee details
    public void displayTraineeDetails() {
        if (!traineeList.isEmpty()) {
            //show heading
            System.out.println("=========================================================================================================================================");
            System.out.printf(" %-5s  %-15s  %-20s  %-15s  %-10s  %-8s  %-12s  %-12s  %-6s  \n",
                    "TraineeId", "Trainee Name", "Trainee Email", "Trainee Contact", "Gender", "Age", "Date of Birth", "Level", "Wallet");
            System.out.println("=========================================================================================================================================");
            //get trainee details by for loop
            for (Trainee trainee : traineeList) {
                int traineeId = trainee.getTraineeId();
                String traineeName = trainee.getTraineeName();
                String traineeEmail = trainee.getEmail();
                String traineeContact = trainee.getEmergencyContact();
                String gender = trainee.getGender();
                long traineeAge = trainee.getTraineeAge();
                String dateOfBirth = trainee.getDateOfBirth();
                int level = trainee.getLevel();
                double walletAmount = trainee.getWalletAmount();
                String password = trainee.getPassword();
                //print trainee details
                System.out.printf(" %-9s  %-15s  %-20s  %-15s  %-10s  %-8s  %-12s  %-12s  %-6s \n",
                        traineeId, traineeName, traineeEmail, traineeContact, gender, traineeAge, dateOfBirth, "Level "+level, walletAmount);
            }

            System.out.println("=========================================================================================================================================");
        } else {
            System.out.println("No trainee details available.");
        }
    }

    
    //update trainee level
    public void updateTraineeLevel(int traineeId, int level) {
        for (Trainee trainee : traineeList) {
            if (trainee.getTraineeId() == traineeId) {
                if (trainee.getLevel() < level) {
                    trainee.setLevel(level);
                    break;
                }

            }
        }
    }

    //get trainee object by traineeId
    public Trainee getTraineeById(int traineeId) {
        for (Trainee trainee : traineeList) {
            if (trainee.getTraineeId() == traineeId) {
                return trainee;
            }
        }
        //return null 
        return null;
    }

    
    

    //display trainee profile
    public void traineeProfile(int traineeId) {
        //get trainee object by index
        Trainee trainee1 = getTraineeById(traineeId);
        System.out.println("\n\t\tTrainee Profile");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("Trainee FullName  : " + trainee1.traineeName);
        System.out.println("Email             : " + trainee1.traineeEmail);
        System.out.println("Date Of Birth     : " + trainee1.dateOfBirth);
        System.out.println("Emergency Contact : " + trainee1.traineeContact);
        System.out.println("Trainee Level     : " + trainee1.level);
        System.out.println("Trainee Gender    : " + trainee1.gender);
        System.out.println("Trainee Age       : " + trainee1.getTraineeAge());
        System.out.println("Password          : " + trainee1.password);
        System.out.println("Wallet amount     : $ " + trainee1.getWalletAmount());
        System.out.println();

    }
    

    //check unique traineeEmail id 
    public boolean uniqueEmail(String traineeEmail) {
        for (Trainee trainee : traineeList) {
            if (trainee.getEmail().equalsIgnoreCase(traineeEmail)) {
                return false;
            }
        }
        return true;
    }

    //calculate traineeAge from date of birth
    public long calculateAge(String dateOfBirth1) {
        long traineeAge = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date currentDate = new Date();
            Date dateOfBirth = sdf.parse(dateOfBirth1);
            //find total day from birth to current day
            long diffInMillies = Math.abs(currentDate.getTime() - dateOfBirth.getTime());
            //total days change into year
            traineeAge = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) / 365;

        } catch (ParseException ex) {
            ex.printStackTrace();

        }
        return traineeAge;
    }
    
    //check uniqueName
    public boolean uniqueName(String traineeName) {
        for (Trainee trainee : traineeList) {
            if (trainee.getTraineeName().equalsIgnoreCase(traineeName)) {
                return true;
            }
        }
        return false;
    }

}
