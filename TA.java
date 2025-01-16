package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TA extends Student {


    private Course assigned_course;
    public TA(String email, String password, String contactInfo) {
        super(email, password, contactInfo);
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nTeaching Assistant Menu:");
            System.out.println("1. View Available Courses");
            System.out.println("2. View and Manage Grades");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    List<Course> availableCourses = Main.courseCatalog;
                    viewAvailableCourses(availableCourses);
                    break;
                case 2:
                    manageGrades(scanner);
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Additional method to manage grades
    public void manageGrades(Scanner scanner) {
        System.out.println("Enter student email to view and manage grades:");
        String studentEmail = scanner.nextLine();

        Student student = Main.studentDatabase.get(studentEmail);
        if (student != null) {
            System.out.println("Grades for student " + studentEmail + ":");
            for (HashMap.Entry<String, String> entry : student.courseGrades.entrySet()) {
                System.out.println("Course: " + entry.getKey() + ", Grade: " + entry.getValue());
            }

            System.out.println("Enter course code to update grade (or press Enter to skip):");
            String courseCode = scanner.nextLine();
            if (!courseCode.isEmpty()) {
                if (!courseCode.equals(assigned_course.getCourseCode())) {
                    System.out.println("Cannot do this for this course");
//                    showMenu(scanner);
                    return;
                }
                System.out.println("Enter new grade:");
                String newGrade = scanner.nextLine();
                student.courseGrades.put(courseCode, newGrade); // Updating the grade
                System.out.println("Grade updated for course: " + courseCode);
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public Course getAssigned_course() {
        return assigned_course;
    }

    public void setAssigned_course(Course assigned_course) {
        this.assigned_course = assigned_course;
    }
}
