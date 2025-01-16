package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Professor extends User {
    private List<Course> assignedCourses = new ArrayList<>();

    public Professor(String email, String password) {
        super(email, password);
    }

    @Override
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            System.out.println("Professor logged in successfully.");
            return true;
        } else {
            System.out.println("Invalid login for professor.");
//            return false;
        }
        return false;
    }

    @Override
    public void showMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nProfessor Menu:");
            System.out.println("1. View and Manage Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. View Feedback for Courses");
            System.out.println("4. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAndManageCourses(scanner);
                    break;
                case 2:
                    System.out.println("Enter course code to view enrolled students:");
                    String courseCode = scanner.nextLine();
                    viewEnrolledStudents(courseCode, Main.studentDatabase);
                    break;
                case 3:
                    System.out.println("Enter course code to view feedback:");
                    String feedbackCourseCode = scanner.nextLine();
                    viewFeedbackForCourse(feedbackCourseCode);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public void viewAndManageCourses(Scanner scanner) {
        if (assignedCourses.isEmpty()) {
            System.out.println("No courses assigned.");
            return;
        }

        System.out.println("Assigned courses:");
        for (int i = 0; i < assignedCourses.size(); i++) {
            System.out.println((i + 1) + ". " + assignedCourses.get(i));
        }

        System.out.println("Enter course number(the serial number) to manage or 0 to exit:");
        int courseNumber=0;
        try {
            courseNumber=scanner.nextInt();
            scanner.nextLine(); // Consume newline
        } catch (Exception e) {
            System.out.println("Enter valid input");
            Scanner sc=new Scanner(System.in);
            showMenu(sc);
            return;
        }



        if (courseNumber > 0 && courseNumber <= assignedCourses.size()) {
            Course selectedCourse = assignedCourses.get(courseNumber - 1);
            manageCourseDetails(selectedCourse, scanner);
        } else {
            System.out.println("Enter valid input");
            showMenu(scanner);
            return;
        }
    }

    private void manageCourseDetails(Course course, Scanner scanner) {
        System.out.println("Managing course: " + course);
        System.out.println("Choose detail to update: 1. Syllabus 2. Class Timings 3. Credits 4. Prerequisites 5. Enrollment Limit 6. Office Hours 7. Exit");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (option) {
            case 1:
                System.out.println("Enter new syllabus:");
                course.setSyllabus(scanner.nextLine());
                break;
            case 2:
                System.out.println("Enter new class timings:");
                course.setClassTimings(scanner.nextLine());
                break;
            case 3:
                System.out.println("Enter new credits:");
                course.setCredits(scanner.nextInt());
                scanner.nextLine(); // Consume newline
                break;
            case 4:
                System.out.println("Enter new prerequisites:");
                course.setPrerequisites(scanner.nextLine());
                break;
            case 5:
                System.out.println("Enter new enrollment limit:");
                course.setEnrollmentLimit(scanner.nextInt());
                scanner.nextLine(); // Consume newline
                break;
            case 6:
                System.out.println("Enter new office hours:");
                course.setOfficeHours(scanner.nextLine());
                break;
            case 7:
                return;
            default:
                System.out.println("Invalid option.");
        }
        System.out.println("Course updated successfully.");
    }

    public void viewEnrolledStudents(String courseCode, HashMap<String, Student> studentDatabase) {
        boolean courseFound = false;
        System.out.println("inside the prof");
        System.out.println(studentDatabase);
        for (Course course : assignedCourses) {
            if (course.getCourseCode().equals(courseCode)) {
                courseFound = true;
                System.out.println("Enrolled students for " + courseCode + ":");
                for (String studentEmail : course.getEnrolledStudents()) {
                    Student student = studentDatabase.get(studentEmail);
                    if (student != null) {
                        System.out.println("Student: " + studentEmail + ", Academic Standing: " + student.getAcademicStanding() + ", Contact: " + student.getContactInfo());
                    }
                }
                break;
            }
        }
        if (!courseFound) {
            System.out.println("No such course assigned.");
        }
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
        System.out.println("Course " + course.getCourseCode() + " assigned to Professor " + email);
    }

    public void viewFeedbackForCourse(String courseCode) {
        boolean courseFound = false;
        for (Course course : assignedCourses) {  // Iterate over the courses assigned to the professor
            if (course.getCourseCode().equals(courseCode)) {
                courseFound = true;
                System.out.println("Feedback for course " + courseCode + ":");
                for (Feedback<?> feedback : course.getFeedbackList()) {  // Get the feedback list from the course
                    System.out.println(feedback);  // Print each feedback entry
                }
                break;
            }
        }
        if (!courseFound) {
            System.out.println("No such course assigned.");
        }
    }



}
