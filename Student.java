//package org.example;
//
//import java.util.*;
//
//public class Student extends User {
//    private String semester = "1"; // Start from semester one
//    HashMap<String, String> courseGrades = new HashMap<>(); // Stores course and grade
//
//    private HashSet<String> completedCourses = new HashSet<>();
//    private List<Complaint> complaints = new ArrayList<>();
//    private final static int MAX_CREDITS = 20;
//    private String contactInfo;
//    private String academicStanding = "Good";
//
//    private static int count=0;
//    public static boolean flag=false;
//
//    public Student(String email, String password, String contactInfo) {
//        super(email, password);
//        this.contactInfo = contactInfo;
//    }
//
//    @Override
//    public void login(String email, String password) {
//        if (this.email.equals(email) && this.password.equals(password)) {
//            System.out.println("Student logged in successfully.");
////            showMenu(new Scanner(System.in));
//        } else {
//            System.out.println("Invalid login for student.");
//        }
//    }
//
//    @Override
//    public void showMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("\nStudent Menu:");
//            System.out.println("1. View Available Courses");
//            System.out.println("2. Register for a Course");
//            System.out.println("3. View Schedule");
//            System.out.println("4. Track Academic Progress");
//            System.out.println("5. Drop a Course");
//            System.out.println("6. Submit Complaint");
//            System.out.println("7. View Complaints");
//            System.out.println("8. View Registered Courses");
//            System.out.println("9. Logout");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    // Sample list of courses (in reality, this should be fetched from a catalog)
//                    List<Course> availableCourses = Main.courseCatalog;
//                    viewAvailableCourses(availableCourses);
//                    break;
//                case 2:
//                    System.out.println("Enter course code to register:");
//                    String courseCode = scanner.nextLine();
//                    Course courseToRegister = Main.findCourse(courseCode);
//                    if (courseToRegister != null) {
//                        registerForCourse(courseToRegister);
//                    } else {
//                        System.out.println("Course not found.");
//                    }
//                    break;
//                case 3:
//                    viewSchedule(Main.courseCatalog);
//                    break;
//                case 4:
//                    trackAcademicProgress();
//                    break;
//                case 5:
//                    System.out.println("Enter course code to drop:");
//                    String courseToDrop = scanner.nextLine();
//                    dropCourse(courseToDrop);
//                    break;
//                case 6:
//                    System.out.println("Enter complaint text:");
//                    String complaintText = scanner.nextLine();
//                    submitComplaint(complaintText);
//                    break;
//                case 7:
//                    viewComplaints();
//                    break;
//                case 8:
//                    see_courses();
//                    break;
//                case 9:
//                    System.out.println("Logging out...");
//                    return;
//
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    public String getAcademicStanding() {
//        return academicStanding;
//    }
//
//    public String getContactInfo() {
//        return contactInfo;
//    }
//
//    //     View available courses
//    public void viewAvailableCourses(List<Course> courses) {
//        System.out.println("Available courses for semester " + semester + ":");
//        for (Course course : courses) {
//            if (course.isAvailableInSemester(semester)) {
//                System.out.println(course);
//            }
//        }
//    }
//
//    // Register for courses
//    public void registerForCourse(Course course) {
//        if (course.isAvailableInSemester(semester)) {
//            int totalCredits = courseGrades.values().stream().mapToInt(gr -> course.getCredits()).sum();
//            if (totalCredits + course.getCredits() <= MAX_CREDITS) {
//                if (completedCourses.containsAll(course.getPrerequisites())) {
//                    course.enrollStudent(email);
//                    System.out.println("Registered for course: " + course.getCourseCode());
//                } else {
//                    System.out.println("Prerequisites not met for course: " + course.getCourseCode());
//                }
//            } else {
//                System.out.println("Credit limit exceeded.");
//            }
//        } else {
//            System.out.println("Course not available in current semester.");
//        }
//    }
//
//    // View schedule
//    public void viewSchedule(List<Course> courses) {
//        System.out.println("Weekly schedule for semester " + semester + ":");
//        for (Course course : courses) {
//            if (course.isStudentEnrolled(email)) {
//                System.out.println(course.getSchedule());
//            }
//        }
//    }
//
//    // Track academic progress
//    public void trackAcademicProgress() {
//        System.out.println("Academic progress for " + email + ":");
//        double totalPoints = 0;
//        double totalCredits = 0;
//
//        for (Map.Entry<String, String> entry : courseGrades.entrySet()) {
//            String courseCode = entry.getKey();
//            String grade = entry.getValue();
//            double credits = Course.getCreditsForCourse(courseCode);
//            double gradePoints = Course.getGradePoints(grade);
//
//            totalPoints += gradePoints * credits;
//            totalCredits += credits;
//        }
//
//        double sgpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
//        double cgpa = totalCredits > 0 ? sgpa : 0; // For simplicity, CGPA calculation is the same as SGPA
//
//        System.out.println("SGPA: " + sgpa);
//        System.out.println("CGPA: " + cgpa);
//    }
//
//    // Drop courses
//    public void dropCourse(String courseCode) {
//        System.out.println("Dropping course: " + courseCode);
//        completedCourses.remove(courseCode);
//        System.out.println("Course dropped successfully.");
//    }
//
//    // Submit complaints
//    public void submitComplaint(String complaintText) {
//        complaints.add(new Complaint(email, complaintText));
//        System.out.println("Complaint submitted.");
//    }
//
//    public void viewComplaints() {
//        System.out.println("Your complaints:");
//        for (Complaint complaint : complaints) {
//            System.out.println(complaint);
//        }
//    }
//
//    public HashMap<String, String> get_hash_map() {
//        return courseGrades;
//    }
//
//    public void see_courses() {
//        for (Map.Entry<String, String> entry : courseGrades.entrySet()) {
//            System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
//        }
//    }
//}




package org.example;

import java.time.LocalDate;
import java.util.*;

public class Student extends User {
    private String semester = "1"; // Start from semester one
    HashMap<String, String> courseGrades = new HashMap<>(); // Stores course and grade

    private HashSet<String> completedCourses = new HashSet<>();
    private List<Complaint> complaints = new ArrayList<>();
    private final static int MAX_CREDITS = 20;
    private String contactInfo;
    private String academicStanding = "Good";
    private List<Feedback<?>> feedbackList = new ArrayList<>(); // Feedback system

    private static int count = 0;
    public static boolean flag = false;

    public Student(String email, String password, String contactInfo) {
        super(email, password);
        this.contactInfo = contactInfo;
    }

    @Override
    public boolean login(String email, String password) {
        try {
            if (!this.email.equals(email) || !this.password.equals(password)) {
                throw new InvalidLoginException("Invalid login credentials for student.");
            }
            System.out.println("Student logged in successfully.");
            return true;
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. View Schedule");
            System.out.println("4. Track Academic Progress");
            System.out.println("5. Drop a Course");
            System.out.println("6. Submit Complaint");
            System.out.println("7. View Complaints");
            System.out.println("8. View Registered Courses");
            System.out.println("9. Give Feedback");
            System.out.println("10. View Feedback");
            System.out.println("11. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Course> availableCourses = Main.courseCatalog;
//                    System.out.println(availableCourses);
                    viewAvailableCourses(availableCourses);
                    break;
                case 2:
                    System.out.println("Enter course code to register:");
                    String courseCode = scanner.nextLine();
                    Course courseToRegister = Main.findCourse(courseCode);
                    if (courseToRegister != null) {
                        registerForCourse(courseToRegister);
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    viewSchedule(Main.courseCatalog);
                    break;
                case 4:
                    trackAcademicProgress();
                    break;
                case 5:
                    System.out.println("Enter course code to drop:");
                    String courseToDrop = scanner.nextLine();
                    dropCourse(courseToDrop);
                    break;
                case 6:
                    System.out.println("Enter complaint text:");
                    String complaintText = scanner.nextLine();
                    submitComplaint(complaintText);
                    break;
                case 7:
                    viewComplaints();
                    break;
                case 8:
                    seeCourses();
                    break;
                case 9:
                    System.out.println("Enter course code to give feedback:");
                    String feedbackCourse = scanner.nextLine();
                    System.out.println("Enter numeric feedback or text feedback:");
                    String feedback = scanner.nextLine();
                    try {
                        Integer numericFeedback = Integer.parseInt(feedback);
                        giveFeedback(feedbackCourse, numericFeedback);
                    } catch (NumberFormatException e) {
                        giveFeedback(feedbackCourse, feedback);
                    }
                    break;
                case 10:
                    viewFeedback();
                    break;
                case 11:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public String getAcademicStanding() {
        return academicStanding;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // View available courses
    public void viewAvailableCourses(List<Course> courses) {
        System.out.println("Available courses for semester " + semester + ":");
        for (Course course : courses) {
            if (course.isAvailableInSemester(semester)) {
                System.out.println(course);
            }
        }
    }

    // Register for courses with exception handling for full courses
    public void registerForCourse(Course course) {
        try {
            if (course.isFull()) {
                throw new CourseFullException("Course " + course.getCourseCode() + " is full.");
            }
            int totalCredits = courseGrades.values().stream().mapToInt(gr -> course.getCredits()).sum();
            if (totalCredits + course.getCredits() <= MAX_CREDITS) {
                if (completedCourses.containsAll(course.getPrerequisites())) {
                    course.enrollStudent(email);
                    System.out.println("Registered for course: " + course.getCourseCode());
                } else {
                    System.out.println("Prerequisites not met for course: " + course.getCourseCode());
                }
            } else {
                System.out.println("Credit limit exceeded.");
            }
        } catch (CourseFullException e) {
            System.out.println(e.getMessage());
        }
    }

    // View schedule
    public void viewSchedule(List<Course> courses) {
        System.out.println("Weekly schedule for semester " + semester + ":");
        for (Course course : courses) {
            if (course.isStudentEnrolled(email)) {
                System.out.println(course.getSchedule());
            }
        }
    }

    // Track academic progress
    public void trackAcademicProgress() {
        System.out.println("Academic progress for " + email + ":");
        double totalPoints = 0;
        double totalCredits = 0;

        for (Map.Entry<String, String> entry : courseGrades.entrySet()) {
            String courseCode = entry.getKey();
            String grade = entry.getValue();
            double credits = Course.getCreditsForCourse(courseCode);
            double gradePoints = Course.getGradePoints(grade);

            totalPoints += gradePoints * credits;
            totalCredits += credits;
        }

        double sgpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
        double cgpa = totalCredits > 0 ? sgpa : 0;

        System.out.println("SGPA: " + sgpa);
        System.out.println("CGPA: " + cgpa);
    }

    // Drop courses with exception handling for missed deadline
    public void dropCourse(String courseCode) {
        try {
            // Find the course based on the course code
            Course course = Main.findCourse(courseCode);
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }

            // Check if the drop deadline has passed
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isAfter(course.getDropDeadline())) {
                throw new DropDeadlinePassedException("Drop deadline for " + courseCode + " has passed.");
            }

            // Remove the course from the student's completed courses
            completedCourses.remove(courseCode);
            System.out.println("Course dropped successfully.");
        } catch (DropDeadlinePassedException e) {
            System.out.println(e.getMessage());
        }
    }


    // Submit complaints
    public void submitComplaint(String complaintText) {
        complaints.add(new Complaint(email, complaintText));
        System.out.println("Complaint submitted.");
    }

    public <T> void giveFeedback(Course course, T feedback) {
        Feedback<T> newFeedback = new Feedback<>(this.email, course.getCourseCode(), feedback);
        course.addFeedback(newFeedback); // Add feedback to the course's feedback list
        System.out.println("Feedback submitted for course: " + course.getCourseCode());
    }

    // View complaints
    public void viewComplaints() {
        System.out.println("Your complaints:");
        for (Complaint complaint : complaints) {
            System.out.println(complaint);
        }
    }

    // Feedback system
    public <T> void giveFeedback(String courseCode, T feedback) {
        Feedback<T> newFeedback = new Feedback<>(this.email, courseCode, feedback);
        feedbackList.add(newFeedback);
        System.out.println("Feedback submitted for course: " + courseCode);
    }

    public void viewFeedback() {
        System.out.println("Feedback for student " + email + ":");
        for (Feedback<?> feedback : feedbackList) {
            System.out.println(feedback);
        }
    }

    // See registered courses
    public void seeCourses() {
        for (Map.Entry<String, String> entry : courseGrades.entrySet()) {
            System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
        }
    }
}

// Generic feedback class
class Feedback<T> {
    private T feedback;
    private String studentEmail;
    private String courseCode;

    public Feedback(String studentEmail, String courseCode, T feedback) {
        this.studentEmail = studentEmail;
        this.courseCode = courseCode;
        this.feedback = feedback;
    }

    public T getFeedback() {
        return feedback;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return "Feedback for course: " + courseCode + " by " + studentEmail + ": " + feedback;
    }
}

// Custom exception classes
class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}

class DropDeadlinePassedException extends Exception {
    public DropDeadlinePassedException(String message) {
        super(message);
    }
}
