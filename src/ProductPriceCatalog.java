import java.util.HashMap;
import java.util.Scanner;

public class ProductPriceCatalog {
    public static void main(String[] args) {

        // Create Scanner object to read user input
        Scanner input = new Scanner(System.in);

        // Declare variables required for the program
        int numberOfProducts;
        int totalProductsEntered = 0;
        String productName;
        String searchProduct;
        String updateChoice;
        double productPrice;
        double newPrice;
        String searchUpdateResult;
        String inventoryClassification;

        // Create HashMap to store product names and prices
        HashMap<String, Double> productCatalog = new HashMap<>();

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

            // Use a loop to read product names and prices
            for (int i = 0; i < numberOfProducts; i++) {
                System.out.print("Enter product name: ");
                productName = input.nextLine().trim();

                System.out.print("Enter product price: ");
                while (!input.hasNextDouble()) {
                    System.out.println("Price must be a number.");
                    input.next();
                    System.out.print("Enter product price: ");
                }

                productPrice = input.nextDouble();
                input.nextLine();

                totalProductsEntered++;

                // Check if product already exists before adding
                if (productCatalog.containsKey(productName)) {
                    System.out.println("Product already exists. Record not added.");
                } else {
                    productCatalog.put(productName, productPrice);
                }
            }

            // Prompt user to search for a product
            System.out.print("Enter product name to search: ");
            searchProduct = input.next();

            // Search product and update price if required
            if (productCatalog.containsKey(searchProduct)) {

                System.out.println("Current price: " + productCatalog.get(searchProduct));

                System.out.print("Do you want to update the price? (Y/N): ");
                updateChoice = input.next();

                if (updateChoice.equalsIgnoreCase("Y")) {
                    System.out.print("Enter new price: ");
                    newPrice = input.nextDouble();

                    // Update product price using replace()
                    productCatalog.replace(searchProduct, newPrice);

                    searchUpdateResult = "Price updated successfully.";
                } else {
                    searchUpdateResult = "Price update skipped.";
                }

            } else {
                searchUpdateResult = "Product not found.";
            }

            // Classify inventory size based on number of products
            if (productCatalog.size() < 5) {
                inventoryClassification = "Small Inventory";
            } else if (productCatalog.size() <= 10) {
                inventoryClassification = "Medium Inventory";
            } else {
                inventoryClassification = "Large Inventory";
            }

            // Display product catalog information
            System.out.println("Total products entered: " + totalProductsEntered);
            System.out.println("Total unique products: " + productCatalog.size());
            System.out.println("All product names and prices: " + productCatalog);
            System.out.println("Search and update result: " + searchUpdateResult);
            System.out.println("Inventory classification: " + inventoryClassification);
        }

        // Close Scanner before program ends
        input.close();
    }
}