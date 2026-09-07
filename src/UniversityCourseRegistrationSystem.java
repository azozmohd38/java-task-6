import java.util.*;

public class UniversityCourseRegistrationSystem {

    public static void main(String[] args) {

        // Create Scanner object
        Scanner scanner = new Scanner(System.in);

        // Store unique student IDs
        HashSet<Integer> studentIds = new HashSet<>();

        // Store student ID and registered courses
        HashMap<Integer, HashSet<String>> studentCourses = new HashMap<>();

        // Ask for number of students
        System.out.print("Enter number of students: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid number of students.");
            scanner.next();
            System.out.print("Enter number of students: ");
        }

        int numberOfStudents = scanner.nextInt();
        scanner.nextLine();

        // Validate number of students
        if (numberOfStudents <= 0) {
            System.out.println("Invalid number of students.");
            scanner.close();
            return;
        }

        // Enter student records
        for (int i = 1; i <= numberOfStudents; i++) {

            System.out.println("\nStudent " + i);

            System.out.print("Enter Student ID: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid Student ID. Student ID must be a positive number.");
                scanner.next();
                System.out.print("Enter Student ID: ");
            }

            int studentId = scanner.nextInt();
            scanner.nextLine();

            // Validate positive student ID
            if (studentId <= 0) {
                System.out.println("Invalid Student ID. Student ID must be a positive number.");
                continue;
            }

            // Check duplicate student ID
            if (studentIds.contains(studentId)) {
                System.out.println("Student ID already exists. Record skipped.");
                continue;
            }

            // Add student ID
            studentIds.add(studentId);

            System.out.print("Enter number of courses: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid number of courses.");
                scanner.next();
                System.out.print("Enter number of courses: ");
            }

            int numberOfCourses = scanner.nextInt();
            scanner.nextLine();

            if (numberOfCourses < 0) {
                System.out.println("Invalid number of courses.");
                numberOfCourses = 0;
            }

            HashSet<String> courses = new HashSet<>();

            // Read courses
            for (int j = 1; j <= numberOfCourses; j++) {

                if (courses.size() >= 6) {
                    System.out.println("Maximum of 6 courses allowed.");
                    break;
                }

                System.out.print("Enter course " + j + ": ");
                String course = scanner.nextLine().trim();

                if (course.isEmpty()) {
                    System.out.println("Course name cannot be empty.");
                } else if (courses.add(course)) {
                    System.out.println("Course added.");
                } else {
                    System.out.println("Duplicate course. Not added.");
                }
            }

            // Save student courses
            studentCourses.put(studentId, courses);
        }

        int choice;

        // Menu
        do {

            System.out.println("\n==============================");
            System.out.println("University Registration System");
            System.out.println("==============================");
            System.out.println("1. Search Student");
            System.out.println("2. Add Course to Student");
            System.out.println("3. Remove Course from Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Display Registration Statistics");
            System.out.println("6. Register New Student");
            System.out.println("7. Display Students in a Course");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid choice.");
                scanner.next();
                System.out.print("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                // Search Student
                case 1:

                    System.out.print("Enter Student ID: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Student not found.");
                        scanner.next();
                        System.out.print("Enter Student ID: ");
                    }

                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    if (studentCourses.containsKey(searchId)) {
                        System.out.println("Registered Courses:");
                        System.out.println(studentCourses.get(searchId));
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                // Add Course
                case 2:

                    System.out.print("Enter Student ID: ");
                    int addId = scanner.nextInt();
                    scanner.nextLine();

                    if (studentCourses.containsKey(addId)) {

                        HashSet<String> courses = studentCourses.get(addId);

                        if (courses.size() >= 6) {
                            System.out.println("Student already has maximum (6) courses.");
                            break;
                        }

                        System.out.print("Enter Course Name: ");
                        String newCourse = scanner.nextLine().trim();

                        if (newCourse.isEmpty()) {
                            System.out.println("Course name cannot be empty.");
                        } else if (courses.contains(newCourse)) {
                            System.out.println("Duplicate course registration.");
                        } else {
                            courses.add(newCourse);
                            studentCourses.replace(addId, courses);
                            System.out.println("Course added successfully.");
                        }

                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                // Remove Course
                case 3:

                    System.out.print("Enter Student ID: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();

                    if (studentCourses.containsKey(removeId)) {

                        System.out.print("Enter Course Name: ");
                        String removeCourse = scanner.nextLine().trim();

                        HashSet<String> courses = studentCourses.get(removeId);

                        if (removeCourse.isEmpty()) {
                            System.out.println("Course not found.");
                        } else if (courses.remove(removeCourse)) {
                            studentCourses.replace(removeId, courses);
                            System.out.println("Course removed successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }

                    } else {
                        System.out.println("Student not found.");
                    }

                    break;

                // Display All Students
                case 4:

                    if (studentCourses.isEmpty()) {
                        System.out.println("No students found.");
                    } else {

                        TreeSet<Integer> sortedIds = new TreeSet<>(studentCourses.keySet());

                        System.out.println("\nAll Students");

                        for (Integer id : sortedIds) {
                            System.out.println("Student ID: " + id);
                            System.out.println("Courses: " + studentCourses.get(id));
                            System.out.println();
                        }
                    }

                    break;

                // Statistics
                case 5:

                    int totalStudents = studentCourses.size();
                    int totalCourseRegistrations = 0;

                    int highestStudent = -1;
                    int lowestStudent = -1;

                    int highestCourses = Integer.MIN_VALUE;
                    int lowestCourses = Integer.MAX_VALUE;

                    for (Integer id : studentCourses.keySet()) {

                        int count = studentCourses.get(id).size();

                        totalCourseRegistrations += count;

                        if (count > highestCourses) {
                            highestCourses = count;
                            highestStudent = id;
                        }

                        if (count < lowestCourses) {
                            lowestCourses = count;
                            lowestStudent = id;
                        }
                    }

                    double average = 0;

                    if (totalStudents > 0) {
                        average = (double) totalCourseRegistrations / totalStudents;
                    }

                    System.out.println("\nRegistration Statistics");
                    System.out.println("----------------------------");
                    System.out.println("Total Students: " + totalStudents);
                    System.out.println("Total Course Registrations: " + totalCourseRegistrations);

                    if (totalStudents > 0) {
                        System.out.println("Student with Highest Courses: " + highestStudent);
                        System.out.println("Student with Lowest Courses: " + lowestStudent);
                    }

                    System.out.printf("Average Courses per Student: %.2f%n", average);

                    break;

                // Register New Student
                case 6:

                    System.out.print("Enter Student ID: ");
                    int newId = scanner.nextInt();
                    scanner.nextLine();

                    // Validate positive student ID
                    if (newId <= 0) {
                        System.out.println("Invalid Student ID. Student ID must be a positive number.");
                    } else if (studentIds.contains(newId)) {
                        System.out.println("Student ID already exists.");
                    } else {

                        studentIds.add(newId);

                        System.out.print("Enter number of courses: ");
                        int numCourses = scanner.nextInt();
                        scanner.nextLine();

                        HashSet<String> newCourses = new HashSet<>();

                        for (int i = 1; i <= numCourses; i++) {

                            if (newCourses.size() >= 6) {
                                System.out.println("Maximum of 6 courses allowed.");
                                break;
                            }

                            System.out.print("Enter course " + i + ": ");
                            String course = scanner.nextLine();

                            if (newCourses.add(course)) {
                                System.out.println("Course added.");
                            } else {
                                System.out.println("Duplicate course.");
                            }
                        }

                        studentCourses.put(newId, newCourses);
                        System.out.println("Student registered successfully.");
                    }

                    break;

                // Display students registered in a course
                case 7:

                    System.out.print("Enter course name: ");
                    String searchCourse = scanner.nextLine().trim();

                    boolean found = false;

                    if (searchCourse.isEmpty()) {
                        System.out.println("No students found.");
                        break;
                    }

                    System.out.println("Students registered in " + searchCourse + ":");

                    TreeSet<Integer> ids = new TreeSet<>(studentCourses.keySet());

                    for (Integer id : ids) {

                        if (studentCourses.get(id).contains(searchCourse)) {
                            System.out.println(id);
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("No students found.");
                    }

                    break;

                // Exit
                case 8:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        // Display complete registration summary
        System.out.println("\n========== REGISTRATION SUMMARY ==========");

        TreeSet<Integer> summaryIds = new TreeSet<>(studentCourses.keySet());

        for (Integer id : summaryIds) {
            System.out.println("Student ID: " + id);
            System.out.println("Courses: " + studentCourses.get(id));
            System.out.println("------------------------------------------");
        }

        // Close Scanner
        scanner.close();
    }
}