import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class UniversityCourseRegistrationSystem {
    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner scanner = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfStudents;
        int studentId;
        int numberOfCourses;
        int menuChoice;
        int searchStudentId;
        int courseCount;
        String courseName;
        String studentCoursesResult;

        // Create HashSet to store unique student IDs
        HashSet<Integer> studentIds = new HashSet<>();

        // Create HashMap to store student IDs and their course sets
        HashMap<Integer, HashSet<String>> studentCourses = new HashMap<>();

        // Prompt user to enter number of students
        System.out.print("Enter the number of students: ");
        numberOfStudents = scanner.nextInt();

        // Validate number of students
        if (numberOfStudents <= 0) {
            System.out.println("Invalid number of students.");
        } else {

            // Enter student records
            for (int i = 0; i < numberOfStudents; i++) {

                System.out.print("Enter student ID: ");
                studentId = scanner.nextInt();

                // Check if student ID already exists
                if (studentIds.contains(studentId)) {
                    System.out.println("Student ID already exists. Record skipped.");
                } else {

                    // Add student ID to Set
                    studentIds.add(studentId);

                    // Create Set for student courses
                    HashSet<String> courses = new HashSet<>();

                    System.out.print("Enter number of courses: ");
                    numberOfCourses = scanner.nextInt();

                    // Enter courses for student
                    for (int j = 0; j < numberOfCourses; j++) {

                        System.out.print("Enter course name: ");
                        courseName = scanner.next();

                        if (!courses.add(courseName)) {
                            System.out.println("Duplicate course. Course not added.");
                        }
                    }

                    // Store student courses in Map
                    studentCourses.put(studentId, courses);
                }
            }

            // Menu loop
            do {

                System.out.println("\n===== University Course Registration Menu =====");
                System.out.println("1. Search Student");
                System.out.println("2. Add Course to Student");
                System.out.println("3. Remove Course from Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Display Registration Statistics");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                menuChoice = scanner.nextInt();

                // Handle menu options using switch-case
                switch (menuChoice) {

                    case 1:
                        // Search student option
                        System.out.print("Enter student ID to search: ");
                        searchStudentId = scanner.nextInt();

                        if (studentCourses.containsKey(searchStudentId)) {
                            System.out.println("Student ID: " + searchStudentId);
                            System.out.println("Registered Courses: "
                                    + studentCourses.get(searchStudentId));
                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 2:
                        // Add course option
                        System.out.print("Enter student ID: ");
                        searchStudentId = scanner.nextInt();

                        if (studentCourses.containsKey(searchStudentId)) {

                            System.out.print("Enter course name: ");
                            courseName = scanner.next();

                            HashSet<String> courses =
                                    studentCourses.get(searchStudentId);

                            if (courses.add(courseName)) {
                                studentCourses.replace(searchStudentId, courses);
                                System.out.println("Course added successfully.");
                            } else {
                                System.out.println(
                                        "Course already registered. Duplicate not allowed.");
                            }

                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 3:
                        // Remove course option
                        System.out.print("Enter student ID: ");
                        searchStudentId = scanner.nextInt();

                        if (studentCourses.containsKey(searchStudentId)) {

                            System.out.print("Enter course name to remove: ");
                            courseName = scanner.next();

                            HashSet<String> courses =
                                    studentCourses.get(searchStudentId);

                            if (courses.remove(courseName)) {
                                studentCourses.replace(searchStudentId, courses);
                                System.out.println("Course removed successfully.");
                            } else {
                                System.out.println("Course not found.");
                            }

                        } else {
                            System.out.println("Student not found.");
                        }
                        break;

                    case 4:
                        // Display all students option
                        System.out.println("\nAll Students:");

                        for (Integer id : studentCourses.keySet()) {
                            System.out.println("Student ID: " + id);
                            System.out.println("Courses: "
                                    + studentCourses.get(id));
                        }
                        break;

                    case 5:
                        // Display registration statistics option
                        int totalRegistrations = 0;
                        int highestCourses = -1;
                        int lowestCourses = Integer.MAX_VALUE;
                        int highestStudent = 0;
                        int lowestStudent = 0;

                        for (Integer id : studentCourses.keySet()) {

                            courseCount = studentCourses.get(id).size();
                            totalRegistrations += courseCount;

                            if (courseCount > highestCourses) {
                                highestCourses = courseCount;
                                highestStudent = id;
                            }

                            if (courseCount < lowestCourses) {
                                lowestCourses = courseCount;
                                lowestStudent = id;
                            }
                        }

                        double averageCourses =
                                (double) totalRegistrations / studentCourses.size();

                        System.out.println("\nRegistration Statistics:");
                        System.out.println("Total number of students: "
                                + studentIds.size());
                        System.out.println("Total course registrations: "
                                + totalRegistrations);
                        System.out.println("Student with highest courses: "
                                + highestStudent);
                        System.out.println("Student with lowest courses: "
                                + lowestStudent);
                        System.out.println("Average courses per student: "
                                + averageCourses);

                        break;

                    case 6:
                        // Exit option
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } while (menuChoice != 6);

            // Display final registration summary
            System.out.println("\n===== Final Registration Summary =====");

            for (Integer id : studentCourses.keySet()) {
                System.out.println("Student ID: " + id
                        + " Courses: " + studentCourses.get(id));
            }
        }

        // Close Scanner before program ends
        scanner.close();
    }
}