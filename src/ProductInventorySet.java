import java.util.HashSet;
import java.util.Scanner;

public class ProductInventorySet {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfProducts;
        int totalProductsEntered = 0;
        int duplicateProducts = 0;
        String productName;
        String searchProduct;
        String searchResult;
        String inventoryClassification;

        // Create HashSet to store unique product names
        HashSet<String> productInventory = new HashSet<>();

        // Prompt user to enter the number of products
        System.out.print("Enter the number of products: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid number of products.");
            input.next();
            System.out.print("Enter the number of products: ");
        }

        numberOfProducts = input.nextInt();
        input.nextLine();

        // Validate the entered number of products
        if (numberOfProducts <= 0) {
            System.out.println("Invalid number of products.");
        } else {

            // Use a loop to read and store product names
            for (int i = 0; i < numberOfProducts; i++) {
                System.out.print("Enter product name: ");
                productName = input.nextLine().trim();

                totalProductsEntered++;

                if (productName.isEmpty()) {
                    System.out.println("Product name cannot be empty.");
                    continue;
                }

                // Add product to HashSet and check for duplicates
                if (!productInventory.add(productName)) {
                    duplicateProducts++;
                    System.out.println("Product already exists. Duplicate entries are not allowed.");
                }
            }

            // Prompt user to search for a product
            System.out.print("Enter product name to search: ");
            searchProduct = input.nextLine().trim();

            // Check whether the product exists using contains()
            if (productInventory.contains(searchProduct)) {
                searchResult = "Product found in inventory.";
            } else {
                searchResult = "Product not found in inventory.";
            }

            // Classify inventory size based on unique products
            if (productInventory.size() < 5) {
                inventoryClassification = "Small Inventory";
            } else if (productInventory.size() <= 10) {
                inventoryClassification = "Medium Inventory";
            } else {
                inventoryClassification = "Large Inventory";
            }

            // Display inventory information
            System.out.println("Total products entered: " + totalProductsEntered);
            System.out.println("Total unique products: " + productInventory.size());
            System.out.println("All products in the inventory: " + productInventory);
            System.out.println("Search result: " + searchResult);
            System.out.println("Inventory classification: " + inventoryClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}