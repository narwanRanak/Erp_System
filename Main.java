package org.example;

import java.util.*;
import java.time.LocalDate;






import java.util.Date;




class Course {
    private String courseCode;
    private String courseName;
    private String syllabus;
    private String classTimings;
    private int credits;
    private String prerequisites;
    private int enrollmentLimit;
    private String officeHours;
    private List<String> enrolledStudents = new ArrayList<>();
    private List<String> availableSemesters = new ArrayList<>();
    private LocalDate dropDeadline; // Add the drop deadline field
    private List<Feedback<?>> feedbackList = new ArrayList<>();

    // Constructor with drop deadline
    public Course(String courseCode, String courseName, int credits, LocalDate dropDeadline) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.dropDeadline = dropDeadline; // Initialize drop deadline

    }

    // Method to get the drop deadline
    public LocalDate getDropDeadline() {
        return dropDeadline;
    }

    // Add a student to the course, only if the course is not full
    public void enrollStudent(String studentEmail) {
        if (!isFull()) {
            enrolledStudents.add(studentEmail);
            System.out.println("Student " + studentEmail + " enrolled in course " + courseCode);
        } else {
            System.out.println("Enrollment limit reached for " + courseCode);
        }
    }

    // Check if the course is full
    public boolean isFull() {
        return enrolledStudents.size() >= enrollmentLimit;
    }

    // Other getters, setters, and methods
    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }
    public <T> void addFeedback(Feedback<T> feedback) {
        feedbackList.add(feedback);
    }

    public List<Feedback<?>> getFeedbackList() {
        return feedbackList;
    }

    public int getCredits() {
        return credits;
    }

    public List<String> getPrerequisites() {
        return Arrays.asList(prerequisites.split(","));
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public void setClassTimings(String classTimings) {
        this.classTimings = classTimings;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setEnrollmentLimit(int enrollmentLimit) {
        this.enrollmentLimit = enrollmentLimit;
    }

    public void setOfficeHours(String officeHours) {
        this.officeHours = officeHours;
    }

    public String getSchedule() {
        return "Course: " + courseName + ", Timings: " + classTimings + ", Professor: TBD";
    }

    public boolean isAvailableInSemester(String semester) {
        return availableSemesters.contains(semester);
    }

    public void addAvailableSemester(String semester) {
        availableSemesters.add(semester);
    }

    public boolean isStudentEnrolled(String studentEmail) {
        return enrolledStudents.contains(studentEmail);
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public static double getGradePoints(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }

    public static double getCreditsForCourse(String courseCode) {
        return 4; // Example: All courses are 4 credits
    }

    @Override
    public String toString() {
        return courseCode + ": " + courseName + " (Credits: " + credits + ", Prerequisites: " + prerequisites + ")";
    }
}



class Complaint {


    private String studentEmail;
    private String text;
    private String status;

    public Complaint(String studentEmail, String text) {
        this.studentEmail = studentEmail;
        this.text = text;
        this.status = "Pending";
    }

    public String getStatus() {
        return status;
    }

    public void resolve() {
        status = "Resolved";
    }

    @Override
    public String toString() {
        return "Complaint from " + studentEmail + ": " + text + " (Status: " + status + ")";
    }
}







// Base class for all users
//abstract class User {
//    protected String email;
//    protected String password;
//
//    public User(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
//
//    public abstract void login(String email, String password);
//
//    public abstract void showMenu(Scanner scanner);
//}

// Student class extending User
//class Student extends User {
//    private String semester = "1"; // Start from semester one
//    HashMap<String, String> courseGrades = new HashMap<>(); // Stores course and grade
//    private HashSet<String> completedCourses = new HashSet<>();
//    private List<Complaint> complaints = new ArrayList<>();
//    private final static int MAX_CREDITS = 20;
//    private String contactInfo;
//    private String academicStanding = "Good";
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
//            System.out.println("8. Logout");
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
//                    System.out.println("Logging out...");
//                    return;
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
////     View available courses
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
//    // Other methods (viewAvailableCourses, registerForCourse, etc.) remain the same
//}

// Professor class extending User
//class Professor extends User {
//    private List<Course> assignedCourses = new ArrayList<>();
//
//    public Professor(String email, String password) {
//        super(email, password);
//    }
//
//    @Override
//    public void login(String email, String password) {
//        if (this.email.equals(email) && this.password.equals(password)) {
//            System.out.println("Professor logged in successfully.");
//        } else {
//            System.out.println("Invalid login for professor.");
//        }
//    }
//
//    @Override
//    public void showMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("\nProfessor Menu:");
//            System.out.println("1. View and Manage Courses");
//            System.out.println("2. View Enrolled Students");
//            System.out.println("3. Logout");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    viewAndManageCourses(scanner);
//                    break;
//                case 2:
//                    System.out.println("Enter course code to view enrolled students:");
//                    String courseCode = scanner.nextLine();
//                    viewEnrolledStudents(courseCode, Main.studentDatabase);
//                    break;
//                case 3:
//                    System.out.println("Logging out...");
//                    return;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    public void viewAndManageCourses(Scanner scanner) {
//        if (assignedCourses.isEmpty()) {
//            System.out.println("No courses assigned.");
//            return;
//        }
//
//        System.out.println("Assigned courses:");
//        for (int i = 0; i < assignedCourses.size(); i++) {
//            System.out.println((i + 1) + ". " + assignedCourses.get(i));
//        }
//
//        System.out.println("Enter course number to manage or 0 to exit:");
//        int courseNumber = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        if (courseNumber > 0 && courseNumber <= assignedCourses.size()) {
//            Course selectedCourse = assignedCourses.get(courseNumber - 1);
//            manageCourseDetails(selectedCourse, scanner);
//        }
//    }
//
//    private void manageCourseDetails(Course course, Scanner scanner) {
//        System.out.println("Managing course: " + course);
//        System.out.println("Choose detail to update: 1. Syllabus 2. Class Timings 3. Credits 4. Prerequisites 5. Enrollment Limit 6. Office Hours 7. Exit");
//        int option = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        switch (option) {
//            case 1:
//                System.out.println("Enter new syllabus:");
//                course.setSyllabus(scanner.nextLine());
//                break;
//            case 2:
//                System.out.println("Enter new class timings:");
//                course.setClassTimings(scanner.nextLine());
//                break;
//            case 3:
//                System.out.println("Enter new credits:");
//                course.setCredits(scanner.nextInt());
//                scanner.nextLine(); // Consume newline
//                break;
//            case 4:
//                System.out.println("Enter new prerequisites:");
//                course.setPrerequisites(scanner.nextLine());
//                break;
//            case 5:
//                System.out.println("Enter new enrollment limit:");
//                course.setEnrollmentLimit(scanner.nextInt());
//                scanner.nextLine(); // Consume newline
//                break;
//            case 6:
//                System.out.println("Enter new office hours:");
//                course.setOfficeHours(scanner.nextLine());
//                break;
//            case 7:
//                return;
//            default:
//                System.out.println("Invalid option.");
//        }
//        System.out.println("Course updated successfully.");
//    }
//
//
//    public void viewEnrolledStudents(String courseCode, HashMap<String, Student> studentDatabase) {
//        boolean courseFound = false;
//        for (Course course : assignedCourses) {
//            if (course.getCourseCode().equals(courseCode)) {
//                courseFound = true;
//                System.out.println("Enrolled students for " + courseCode + ":");
//                for (String studentEmail : course.getEnrolledStudents()) {
//                    Student student = studentDatabase.get(studentEmail);
//                    if (student != null) {
//                        System.out.println("Student: " + studentEmail + ", Academic Standing: " + student.getAcademicStanding() + ", Contact: " + student.getContactInfo());
//                    }
//                }
//                break;
//            }
//        }
//        if (!courseFound) {
//            System.out.println("No such course assigned.");
//        }
//    }
//
//    public void assignCourse(Course course) {
//        assignedCourses.add(course);
//        System.out.println("Course " + course.getCourseCode() + " assigned to Professor " + email);
//    }
//
//    // Other methods (viewAndManageCourses, viewEnrolledStudents, etc.) remain the same
//}

// Administrator class with fixed password
//class Administrator extends User {
//    private static final String ADMIN_PASSWORD = "admin123"; // Fixed password for admin
//    private List<Course> courseCatalog = new ArrayList<>();
//    private HashMap<String, Student> studentRecords = new HashMap<>();
//    private List<Complaint> complaints = new ArrayList<>();
//
//    public Administrator(String email) {
//        super(email, ADMIN_PASSWORD);
//    }
//
//    @Override
//    public void login(String email, String password) {
//        if (this.email.equals(email) && this.password.equals(password)) {
//            System.out.println("Administrator logged in successfully.");
//        } else {
//            System.out.println("Invalid login for administrator.");
//        }
//    }
//
//    @Override
//    public void showMenu(Scanner scanner) {
//        while (true) {
//            System.out.println("\nAdministrator Menu:");
//            System.out.println("1. Manage Course Catalog");
//            System.out.println("2. Assign Professor to Course");
//            System.out.println("3. Manage Student Records");
//            System.out.println("4. View Complaints");
//            System.out.println("5. Resolve Complaints");
//            System.out.println("6. Logout");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter action (add/delete) for course:");
//                    String action = scanner.nextLine();
//                    System.out.println("Enter course code:");
//                    String courseCode = scanner.nextLine();
//                    System.out.println("Enter course name:");
//                    String courseName = scanner.nextLine();
//                    System.out.println("Enter course credits:");
//                    int credits = scanner.nextInt();
//                    scanner.nextLine(); // Consume newline
//                    Course course = new Course(courseCode, courseName, credits);
//                    manageCourseCatalog(action, course);
//                    break;
//                case 2:
//                    System.out.println("Enter course code to assign:");
//                    String assignCourseCode = scanner.nextLine();
//                    System.out.println("Enter professor email:");
//                    String professorEmail = scanner.nextLine();
//                    Professor professor = Main.professorDatabase.get(professorEmail);
//                    assignProfessorToCourse(assignCourseCode, professor);
//                    break;
//                case 3:
//                    System.out.println("Enter student email:");
//                    String studentEmail = scanner.nextLine();
//                    System.out.println("Enter action (update):");
//                    String studentAction = scanner.nextLine();
//                    System.out.println("Enter detail to update (e.g., grade):");
//                    String detail = scanner.nextLine();
//                    System.out.println("Enter value:");
//                    String value = scanner.nextLine();
//                    manageStudentRecords(studentEmail, studentAction, detail, value);
//                    break;
//                case 4:
//                    viewComplaints("all");
//                    break;
//                case 5:
//                    System.out.println("Enter complaint index to resolve:");
//                    int complaintIndex = scanner.nextInt();
//                    resolveComplaint(complaintIndex);
//                    break;
//                case 6:
//                    System.out.println("Logging out...");
//                    return;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        }
//    }
//
//    public void manageCourseCatalog(String action, Course course) {
//        switch (action) {
//            case "add":
//                courseCatalog.add(course);
//                System.out.println("Course added to catalog: " + course);
//                break;
//            case "delete":
//                courseCatalog.remove(course);
//                System.out.println("Course removed from catalog: " + course);
//                break;
//            default:
//                System.out.println("Invalid action.");
//        }
//    }
//
//    public void manageStudentRecords(String studentEmail, String action, String detail, String value) {
//        Student student = studentRecords.get(studentEmail);
//        if (student != null) {
//            switch (action) {
//                case "update":
//                    if (detail.equals("grade")) {
//                        student.courseGrades.put(value, "A"); // Example grade
//                        System.out.println("Grade updated for student " + studentEmail);
//                    }
//                    // Additional record updates can be handled here
//                    break;
//                default:
//                    System.out.println("Invalid action.");
//            }
//        } else {
//            System.out.println("Student not found.");
//        }
//    }
//
//    public void assignProfessorToCourse(String courseCode, Professor professor) {
//        for (Course course : courseCatalog) {
//            if (course.getCourseCode().equals(courseCode)) {
//                professor.assignCourse(course);
//                System.out.println("Professor assigned to course: " + courseCode);
//                break;
//            }
//        }
//    }
//
//    public void viewComplaints(String filter) {
//        System.out.println("Complaints:");
//        for (Complaint complaint : complaints) {
//            if (filter.equals("all") || complaint.getStatus().equals(filter)) {
//                System.out.println(complaint);
//            }
//        }
//    }
//
//    public void resolveComplaint(int index) {
//        if (index >= 0 && index < complaints.size()) {
//            complaints.get(index).resolve();
//            System.out.println("Complaint resolved.");
//        } else {
//            System.out.println("Invalid complaint index.");
//        }
//    }
//
//    // Other methods (manageCourseCatalog, assignProfessorToCourse, etc.) remain the same
//}

// Main class to manage users and simulate login/signup
public class Main {


    public static HashMap<String, TA> taDatabase = new HashMap<>();
    public static List<Course> courseCatalog = new ArrayList<>();
    public static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static HashMap<String, TA> TADatabase = new HashMap<>();
//    studentDatabase.

    public static HashMap<String, Professor> professorDatabase = new HashMap<>();
//    public static List<Course> courseCatalog = new ArrayList<>();
    private static Administrator admin = new Administrator("admin@example.com");


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Course c1 = new Course("MTH101", "Linear Algebra", 4, LocalDate.of(2024, 10, 10));  // Drop deadline: 10th Oct 2024
        Course c2 = new Course("CSE101", "Intro to Programming", 4, LocalDate.of(2024, 11, 15)); // Drop deadline: 15th Nov 2024
        Course c3 = new Course("DC101", "Digital Circuits", 4, LocalDate.of(2024, 10, 20)); // Drop deadline: 20th Oct 2024
        Course c4 = new Course("HCI101", "Human Computer Interaction", 4, LocalDate.of(2024, 12, 1)); // Drop deadline: 1st Dec 2024
        Course c5 = new Course("COM101", "Communication Skills", 4, LocalDate.of(2024, 11, 5)); // Drop deadline: 5th Nov 2024
        Course c6 = new Course("TCOM201","Technical Communication Skills",4,LocalDate.of(2024,11,16));

        c1.addAvailableSemester("1");
        c2.addAvailableSemester("1");
        c3.addAvailableSemester("1");
        c4.addAvailableSemester("1");
        c5.addAvailableSemester("1");
        c6.addAvailableSemester("1");
        c6.addAvailableSemester("2");

        c1.setEnrollmentLimit(20);
        c2.setEnrollmentLimit(20);
        c3.setEnrollmentLimit(20);
        c4.setEnrollmentLimit(20);
        c5.setEnrollmentLimit(20);
        c6.setEnrollmentLimit(20);

        TA ta1=new TA("ta1@gmail.com","123","999");
        TA ta2=new TA("ta2@gmail.com","123","999");
        TA ta3=new TA("ta3@gmail.com","123","999");

        ta1.setAssigned_course(c1);
        ta2.setAssigned_course(c2);
        ta3.setAssigned_course(c3);




        courseCatalog.add(c1);
        courseCatalog.add(c2);
        courseCatalog.add(c3);
        courseCatalog.add(c4);
        courseCatalog.add(c5);
        courseCatalog.add(c6);


        Student s1=new Student("kar@gmail.com","123","888");
        Student s2=new Student("iso@gmail.com","456","999");
        Student s3=new Student("ranak@gmail.com","789","222");

        s1.flag=true;
        s2.flag=true;
        s2.flag=true;

        s1.courseGrades.put("MTH101","");
        s1.courseGrades.put("CSE101","");
        s1.courseGrades.put("COM101","");
        s1.courseGrades.put("DC101","");
        s1.courseGrades.put("HCI101","");



        s2.courseGrades.put("MTH101","");
        s2.courseGrades.put("CSE101","");
        s2.courseGrades.put("DC101","");
        s2.courseGrades.put("HCI101","");
        s2.courseGrades.put("COM101","");

        s3.courseGrades.put("MTH101","");
        s3.courseGrades.put("CSE101","");
        s3.courseGrades.put("DC101","");
        s3.courseGrades.put("COM101","");
        s3.courseGrades.put("HCI101","");

        c1.enrollStudent("kar@gmail.com");
        c1.enrollStudent("iso@gmail.com");
        c1.enrollStudent("ranak@gmail.com");

        c2.enrollStudent("kar@gmail.com");
        c2.enrollStudent("iso@gmail.com");
        c2.enrollStudent("ranak@gmail.com");


        c3.enrollStudent("kar@gmail.com");
        c3.enrollStudent("iso@gmail.com");
        c3.enrollStudent("ranak@gmail.com");


        c4.enrollStudent("kar@gmail.com");
        c4.enrollStudent("iso@gmail.com");
        c4.enrollStudent("ranak@gmail.com");

        c5.enrollStudent("kar@gmail.com");
        c5.enrollStudent("iso@gmail.com");
        c5.enrollStudent("ranak@gmail.com");







        studentDatabase.put("kar@gmail.com",s1);
        studentDatabase.put("iso@gmail.com",s2);
        studentDatabase.put("ranak@gmail.com",s3);


        studentDatabase.put("ta1@gmail.com",ta1);
        studentDatabase.put("ta2@gmail.com",ta2);
        studentDatabase.put("ta3@gmail.com",ta3);

        TADatabase.put("ta1@gmail.com",ta1);
        TADatabase.put("ta2@gmail.com",ta1);
        TADatabase.put("ta3@gmail.com",ta2);

        admin.put_student("kar@gmail.com",s1);
        admin.put_student("iso@gmail.com",s2);
        admin.put_student("ranak@gmail.com",s3);


        Professor p1=new Professor("prof1","123");
        Professor p2=new Professor("prof2","456");


        p1.assignCourse(c2);
        p1.assignCourse(c3);
        p1.assignCourse(c4);
        p2.assignCourse(c1);
        p2.assignCourse(c5);

        professorDatabase.put("prof1",p1);
        professorDatabase.put("prof2",p2);


        while (true) {
            System.out.println("Choose action: 1. Sign Up 2. Log In 3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                signUp(scanner);
            } else if (choice == 2) {
                logIn(scanner);
            } else {
                break;
            }
        }
        scanner.close();
    }

//    private static void signUp(Scanner scanner) {
//        System.out.println("Choose role to sign up: 1. Student 2. Professor");
//        int role = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.println("Enter your email:");
//        String email = scanner.nextLine();
//
//        System.out.println("Create your password:");
//        String password = scanner.nextLine();
//
//        if (role == 1) {
//            System.out.println("Enter contact info:");
//            String contactInfo = scanner.nextLine();
//            studentDatabase.put(email, new Student(email, password, contactInfo));
//            System.out.println("Student signed up successfully.");
//        } else if (role == 2) {
//            professorDatabase.put(email, new Professor(email, password));
//            System.out.println("Professor signed up successfully.");
//        } else {
//            System.out.println("Invalid role.");
//        }
//    }
//
//    private static void logIn(Scanner scanner) {
//        System.out.println("Enter your email:");
//        String email = scanner.nextLine();
//
//        System.out.println("Enter your password:");
//        String password = scanner.nextLine();
//
//        // Check if user is student
//        if (studentDatabase.containsKey(email)) {
//            Student student = studentDatabase.get(email);
//            student.login(email, password);
//            student.showMenu(scanner);
//
//            // Check if user is professor
//        } else if (professorDatabase.containsKey(email)) {
//            Professor professor = professorDatabase.get(email);
//            professor.login(email, password);
//            professor.showMenu(scanner);
//
//            // Check if user is admin
//        } else if (admin.email.equals(email)) {
//            admin.login(email, password);
//            admin.showMenu(scanner);
//        } else {
//            System.out.println("Invalid email or password.");
//        }
//    }

    private static void signUp(Scanner scanner) {
        System.out.println("Choose role to sign up: 1. Student 2. Professor 3. Teaching Assistant");
        int role = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Create your password:");
        String password = scanner.nextLine();

        if (role == 1) {
            System.out.println("Enter contact info:");
            String contactInfo = scanner.nextLine();
            studentDatabase.put(email, new Student(email, password, contactInfo));
            System.out.println("Student signed up successfully.");
        } else if (role == 2) {
            professorDatabase.put(email, new Professor(email, password));
            System.out.println("Professor signed up successfully.");
        } else if (role == 3) {
            System.out.println("Enter contact info:");
            String contactInfo = scanner.nextLine();
            taDatabase.put(email, new TA(email, password, contactInfo));
            System.out.println("Teaching Assistant signed up successfully.");
        } else {
            System.out.println("Invalid role.");
        }
    }

    private static void logIn(Scanner scanner) {
        System.out.println("Enter your email:");
        String email = scanner.nextLine();

        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (studentDatabase.containsKey(email)) {
            Student student = studentDatabase.get(email);
            boolean flag=student.login(email, password);
            if (!flag) {
                return;
            }
            student.showMenu(scanner);
        } else if (professorDatabase.containsKey(email)) {
            Professor professor = professorDatabase.get(email);
            boolean flag=professor.login(email, password);
            if (!flag) {
                return;
            }
            professor.showMenu(scanner);
        } else if (taDatabase.containsKey(email)) {
            TA ta = taDatabase.get(email);
            boolean flag=ta.login(email, password);
            if (!flag) {
                return;
            }
            ta.showMenu(scanner);
        } else if (admin.email.equals(email)) {
            boolean flag=admin.login(email, password);
            if (!flag) {
                return;
            }
            admin.showMenu(scanner);
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public static Course findCourse(String courseCode) {
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

