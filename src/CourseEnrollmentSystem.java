import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CourseEnrollmentSystem {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfStudents;
        int totalStudentRecordsEntered = 0;
        int studentId;
        int updateStudentId;
        String courseName;
        String newCourseName;
        String updateResult;
        String enrollmentClassification;

        // Create HashSet to store unique student IDs
        HashSet<Integer> studentIds = new HashSet<>();

        // Create HashMap to store student IDs and enrolled courses
        HashMap<Integer, String> studentCourses = new HashMap<>();

        // Prompt user to enter the number of students
        System.out.print("Enter the number of students: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of students.");
            input.next();
            System.out.print("Enter the number of students: ");
        }

        numberOfStudents = input.nextInt();
        input.nextLine();

        // Validate the entered number of students
        if (numberOfStudents <= 0) {
            System.out.println("Invalid number of students.");
        } else {

            // Use a loop to read student IDs and course names
            for (int i = 0; i < numberOfStudents; i++) {

                System.out.print("Enter student ID: ");
                studentId = input.nextInt();

                System.out.print("Enter course name: ");
                courseName = input.next();

                totalStudentRecordsEntered++;

                // Check if student ID already exists
                if (studentIds.contains(studentId)) {
                    System.out.println("Student ID already exists. Record not added.");
                } else {

                    // Add student ID and course to collections
                    studentIds.add(studentId);
                    studentCourses.put(studentId, courseName);
                }
            }

            // Prompt user to enter student ID for course update
            System.out.print("Enter student ID to update course: ");
            updateStudentId = input.nextInt();

            // Check if student ID exists and update course
            if (studentCourses.containsKey(updateStudentId)) {

                System.out.print("Enter new course name: ");
                newCourseName = input.next();

                // Update course using replace() method
                studentCourses.replace(updateStudentId, newCourseName);

                updateResult = "Course updated successfully.";

            } else {
                updateResult = "Student ID not found.";
            }

            // Classify enrollment size based on unique students
            if (studentCourses.size() < 5) {
                enrollmentClassification = "Small Enrollment";
            } else if (studentCourses.size() <= 15) {
                enrollmentClassification = "Medium Enrollment";
            } else {
                enrollmentClassification = "Large Enrollment";
            }

            // Display enrollment information
            System.out.println("Total student records entered: " + totalStudentRecordsEntered);
            System.out.println("Total unique students: " + studentIds.size());
            System.out.println("All student IDs: " + studentIds);
            System.out.println("All student IDs with their enrolled courses: " + studentCourses);
            System.out.println("Update result: " + updateResult);
            System.out.println("Enrollment classification: " + enrollmentClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}
