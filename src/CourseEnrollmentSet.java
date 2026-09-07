import java.util.HashSet;
import java.util.Scanner;

public class CourseEnrollmentSet {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfCourses;
        int totalCoursesEntered = 0;
        String courseName;
        String courseToRemove;
        String removalResult;
        String catalogClassification;

        // Create HashSet to store unique course names
        HashSet<String> courseCatalog = new HashSet<>();

        // Prompt user to enter the number of courses
        System.out.print("Enter the number of courses: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of courses.");
            input.next();
            System.out.print("Enter the number of courses: ");
        }

        numberOfCourses = input.nextInt();
        input.nextLine();

        // Validate the entered number of courses
        if (numberOfCourses <= 0) {
            System.out.println("Invalid number of courses.");
        } else {

            // Use a loop to read and store course names
            for (int i = 0; i < numberOfCourses; i++) {
                System.out.print("Enter course name: ");
                courseName = input.nextLine().trim();

                totalCoursesEntered++;

                if (courseName.isEmpty()) {
                    System.out.println("Course name cannot be empty.");
                    continue;
                }

                // Add course to HashSet and check for duplicates
                if (!courseCatalog.add(courseName)) {
                    System.out.println("Course already exists. Duplicate entries are not allowed.");
                }
            }

            // Prompt user to enter a course to remove
            System.out.print("Enter course name to remove: ");
            courseToRemove = input.next();

            // Remove course using remove() method
            if (courseCatalog.remove(courseToRemove)) {
                removalResult = "Course removed successfully.";
            } else {
                removalResult = "Course not found.";
            }

            // Classify course catalog based on remaining courses
            if (courseCatalog.size() < 4) {
                catalogClassification = "Small Course Catalog";
            } else if (courseCatalog.size() <= 8) {
                catalogClassification = "Medium Course Catalog";
            } else {
                catalogClassification = "Large Course Catalog";
            }

            // Display course enrollment information
            System.out.println("Total courses entered: " + totalCoursesEntered);
            System.out.println("Total unique courses remaining: " + courseCatalog.size());
            System.out.println("All remaining course names: " + courseCatalog);
            System.out.println("Removal result: " + removalResult);
            System.out.println("Course catalog classification: " + catalogClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}
