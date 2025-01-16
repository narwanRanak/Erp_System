package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class Administrator extends User {
    private static final String ADMIN_PASSWORD = "admin123";
    private List<Course> courseCatalog = new ArrayList<>();
    private HashMap<String, Student> studentRecords = new HashMap<>();
    private List<Complaint> complaints = new ArrayList<>();

    public Administrator(String email) {
        super(email, ADMIN_PASSWORD);
    }

    @Override
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Administrator logged in successfully.");
            return true;
        } else {
            System.out.println("Invalid login for administrator.");
        }
        return false;
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Manage Course Catalog");
            System.out.println("2. Assign Professor to Course");
            System.out.println("3. Manage Student Records");
            System.out.println("4. View Complaints");
            System.out.println("5. Resolve Complaints");
            System.out.println("6. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    manageCourseCatalog(scanner);
                    break;
                case 2:
                    System.out.println("Enter course code to assign:");
                    String assignCourseCode = scanner.nextLine();
                    System.out.println("Enter professor email:");
                    String professorEmail = scanner.nextLine();
                    Professor professor = Main.professorDatabase.get(professorEmail);
                    assignProfessorToCourse(assignCourseCode, professor);
                    break;
                case 3:
                    System.out.println("Enter student email:");
                    String studentEmail = scanner.nextLine();
                    System.out.println("Enter grade for the course:");
                    String detail = scanner.nextLine();
                    System.out.println("Enter the course code:");
                    String value = scanner.nextLine();
                    manageStudentRecords(studentEmail, detail, value);
                    break;
                case 4:
                    viewComplaints("all");
                    break;
                case 5:
                    System.out.println("Enter complaint index to resolve:");
                    int complaintIndex = scanner.nextInt();
                    resolveComplaint(complaintIndex);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void manageCourseCatalog(Scanner scanner) {
        System.out.println("Enter action (add/delete) for course:");
        String action = scanner.nextLine();
        System.out.println("Enter course code:");
        String courseCode = scanner.nextLine();
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter course credits:");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        LocalDate dropDeadline = null;
        if (action.equals("add")) {
            System.out.println("Enter drop deadline (yyyy-MM-dd):");
            String deadlineStr = scanner.nextLine();
            try {
                //dropDeadline = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(deadlineStr);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dropDeadline = LocalDate.parse(deadlineStr, formatter);

            } catch (Exception e) {
                System.out.println("Invalid date format. Setting no deadline.");
            }
        }

        Course course = new Course(courseCode, courseName, credits, dropDeadline);
        switch (action) {
            case "add":
                courseCatalog.add(course);
                System.out.println("Course added to catalog: " + course);
                break;
            case "delete":
                courseCatalog.removeIf(c -> c.getCourseCode().equals(courseCode));
                System.out.println("Course removed from catalog: " + courseCode);
                break;
            default:
                System.out.println("Invalid action.");
        }
    }

    public void manageStudentRecords(String studentEmail, String detail, String value) {
        Student student = studentRecords.get(studentEmail);
        if (student != null) {
            student.courseGrades.put(value, detail); // Example grade
            System.out.println("Grade updated for student " + studentEmail);
        } else {
            System.out.println("Student not found.");
        }
    }

    public void assignProfessorToCourse(String courseCode, Professor professor) {
        for (Course course : courseCatalog) {
            if (course.getCourseCode().equals(courseCode)) {
                professor.assignCourse(course);
                System.out.println("Professor assigned to course: " + courseCode);
                break;
            }
        }
    }

    public void viewComplaints(String filter) {
        System.out.println("Complaints:");
        for (Complaint complaint : complaints) {
            if (filter.equals("all") || complaint.getStatus().equals(filter)) {
                System.out.println(complaint);
            }
        }
    }

    public void resolveComplaint(int index) {
        if (index >= 0 && index < complaints.size()) {
            complaints.get(index).resolve();
            System.out.println("Complaint resolved.");
        } else {
            System.out.println("Invalid complaint index.");
        }
    }

    public void put_student(String value, Student s) {
        studentRecords.put(value, s);
    }
}
