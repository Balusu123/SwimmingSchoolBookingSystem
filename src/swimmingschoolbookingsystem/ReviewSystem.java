package swimmingschoolbookingsystem;

import java.util.ArrayList;
import java.util.List;

public class ReviewSystem {

    private int reviewId;
    private int bookingId;
    private String lectureId;
    private int traineeId;
    private int rating;
    private String review;
    private List<ReviewSystem> reviewList;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public List<ReviewSystem> getReviewList() {
        return reviewList;
    }

    public ReviewSystem(int reviewId, int bookingId, String lectureId, int traineeId, int rating, String review) {
        this.reviewId = reviewId;
        this.bookingId = bookingId;
        this.lectureId = lectureId;
        this.traineeId = traineeId;
        this.rating = rating;
        this.review = review;
    }

    public ReviewSystem() {
        reviewList = new ArrayList<>();

    }

    public double calculateAverageRating(String lectureId) {
        int totalRating = 0;
        double avgRating;
        int numReviewList = 0;
        //calculate avg review lecture by
        for (ReviewSystem review2 : reviewList) {
            if (review2.getLectureId().equalsIgnoreCase(lectureId)) {
                totalRating += review2.getRating();
                numReviewList++;
            }
        }
        if (numReviewList > 0) {
            avgRating = (double) totalRating / numReviewList;
        } else {
            avgRating = 0;
        }
        //return avg rating
        return avgRating;
    }

    public void addReviewList(int traineeId, int bookingId, String lectureId, int rating, String review) {
        int ratingId = reviewList.size() + 1;
        //create ReviewSystem object
        ReviewSystem review1 = new ReviewSystem(ratingId, bookingId, lectureId, traineeId, rating, review);
        //add review details in reviewList
        reviewList.add(review1);

    }

    //get tutor average rating
    public double tutorAverageRating(int tutorId) {
        int totalRating = 0;
        double avgRating;
        int numReviewList = 0;
        //calculate avg review lecture by
        for (ReviewSystem review2 : reviewList) {
            if (new TimetableManager().getTimetableById(review2.getLectureId()).getTutorId() == tutorId) {
                totalRating += review2.getRating();
                numReviewList++;
            }
        }
        if (numReviewList > 0) {
            avgRating = (double) totalRating / numReviewList;
        } else {
            avgRating = 0;
        }
        //return avg rating
        return avgRating;
    }

    public void displayReview(TimetableManager timetable, Trainee trainee, int tutorId) {
        int headerShow = 1;
        if (!reviewList.isEmpty()) {
            //get review details by using for loop
            for (ReviewSystem review : reviewList) {
                int lectureTutorId = timetable.getTimetableById(review.getLectureId()).getTutorId();
                //filter tutor id by or all reivew details
                if (lectureTutorId == tutorId || tutorId == 0) {
                    if (headerShow == 1) {
                        //show heading
                        System.out.println("===========================================================================================================================================================================");
                        System.out.printf(" %-8s  %-15s  %-15s  %-10s  %-10s  %-12s  %-12s  %-8s  %-10s  %-10s\n",
                                "ReviewId", "Trainee Name", "Tutor Name", "Booking Id", "LectureId", "Lecture Name", "Rating Date", "Rating Day", "Rating", "Review");
                        System.out.println("===========================================================================================================================================================================");
                        headerShow++;
                    }
                    int reviewId = review.getReviewId();
                    int bookingId = review.getBookingId();
                    String lectureId = review.getLectureId();
                    String traineeName = trainee.getTraineeById(review.getTraineeId()).getTraineeName();
                    String tutorName = (lectureTutorId == 0) ? "N/A" : new Tutor().getTutorById(lectureTutorId).getTutorName();
                    String lectureName = timetable.getTimetableById(lectureId).getLectureName();
                    int rating = review.getRating();
                    String reviewText = review.getReview();
                    String ratingDate = timetable.getTimetableById(lectureId).getLectureDate();
                    String ratingDay = timetable.getTimetableById(lectureId).getLectureDay();
                    //pring ratiing details
                    System.out.printf(" %-8s  %-15s  %-15s  %-10s  %-10s  %-12s  %-12s  %-8s  %-10s  %-10s\n",
                            reviewId, traineeName, tutorName, bookingId, lectureId,
                            lectureName, ratingDate, ratingDay,
                            rating, reviewText);
                }
            }

            System.out.println("===========================================================================================================================================================================");
        } else {
            System.out.println("No review details available.");
        }
    }

    //check average rating available for selected month{
    public boolean RatingAvailableThisMonth(int month) {
        for (ReviewSystem review : reviewList) {
            if (Integer.parseInt(new TimetableManager().getTimetableById(review.getLectureId()).getLectureDate().substring(3, 5)) == month) {
                return true;
            }
        }
        return false;
    }

}
