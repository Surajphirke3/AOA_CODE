import java.util.Scanner; // Import Scanner class for user input

// Define the Item class to store the weight, profit, and profit-to-weight ratio of an item
class Item {
    int weight, profit; // Variables for weight and profit of an item
    double ratio; // Variable to store the profit-to-weight ratio
    
    // Constructor to initialize weight, profit, and calculate the ratio
    Item(int weight, int profit) {
        this.weight = weight; // Set the item's weight
        this.profit = profit; // Set the item's profit
        this.ratio = (double) profit / weight; // Calculate the profit-to-weight ratio
    }
}

public class Knapsack1 {
    // Function to find the optimal solution (maximum profit) for the knapsack problem
    public static double findOptimalSolution(Item[] items, int M) {
        // Sort items in descending order based on their profit-to-weight ratio using bubble sort
        for (int i = 0; i < items.length - 1; i++) { // Loop through all items
            for (int j = 0; j < items.length - i - 1; j++) { // Compare adjacent items
                if (items[j].ratio < items[j + 1].ratio) { // If the current item has lower ratio than the next
                    // Swap the items to sort them in descending order of ratio
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        
        double maxProfit = 0.0; // Variable to store the maximum profit
        int i = 0; // Index for iterating over the sorted items
        int itemCount = 0; // Variable to store the count of items added to the knapsack
        int[] selectedWeights = new int[items.length]; // Array to store the weights of selected items
        int[] selectedProfits = new int[items.length]; // Array to store the profits of selected items
        int[] selectedItems = new int[items.length]; // Array to store the item numbers (indices) of selected items
        
        while (M > 0 && i < items.length) { // While there is capacity left in the knapsack and items are available
            if (items[i].weight <= M) { // If the entire item fits in the knapsack
                selectedItems[itemCount] = i + 1; // Store the item number (index + 1)
                selectedWeights[itemCount] = items[i].weight; // Store the weight of the selected item
                selectedProfits[itemCount] = items[i].profit; // Store the profit of the selected item
                maxProfit += items[i].profit; // Add the full profit of the item
                M -= items[i].weight; // Decrease the knapsack's capacity by the taken weight
            } else { // If the item doesn't fully fit, add the fraction of it that fits
                selectedItems[itemCount] = i + 1; // Store the item number (index + 1)
                selectedWeights[itemCount] = M; // Store the remaining weight that fits in the knapsack
                selectedProfits[itemCount] = (int) (M * items[i].ratio); // Calculate the proportional profit
                maxProfit += selectedProfits[itemCount]; // Add the proportional profit of the item
                M = 0; // The knapsack is full
            }
            itemCount++; // Increment the count of selected items
            i++; // Move to the next item
        }
        
        // Display the items added to the knapsack with their item numbers and profits
        System.out.println("Items added to the knapsack (item numbers and profits):");
        for (int j = 0; j < itemCount; j++) {
            System.out.println("Item number: " + selectedItems[j] + " with profit: " + selectedProfits[j]);
        }
        
        // Return the total profit
        return maxProfit;
    }

    // Main function to execute the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a scanner object to take input
        
        // Prompt user for number of items
        System.out.print("Enter number of objects (n): ");
        int n = scanner.nextInt();
        
        // Prompt user for the knapsack's maximum capacity
        System.out.print("Enter knapsack capacity (Mw): ");
        int M = scanner.nextInt();
        
        int[] P = new int[n]; // Array to store the profits of the items
        int[] W = new int[n]; // Array to store the weights of the items
        
        // Prompt user to enter profits of the items
        System.out.println("Enter profits: ");
        for (int i = 0; i < n; i++) { // Loop to input profits for each item
            P[i] = scanner.nextInt();
        }
        
        // Prompt user to enter weights of the items
        System.out.println("Enter weights: ");
        for (int i = 0; i < n; i++) { // Loop to input weights for each item
            W[i] = scanner.nextInt();
        }
        
        // Display the entered items (weights and profits) in a list
        System.out.println("Entered items:");
        for (int i = 0; i < n; i++) {
            System.out.println("Item " + (i + 1) + " -> Weight: " + W[i] + ", Profit: " + P[i]);
        }

        // Create an array of Item objects and initialize them with weights and profits
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(W[i], P[i]); // Create an Item object for each item
        }
        
        // Call the function to calculate the optimal profit and store the result
        double maxProfit = findOptimalSolution(items, M);
        
        // Output the result: optimal solution (maximum profit)
        System.out.println("Optimal solution (MAX_profit): " + maxProfit);
        System.out.println("Maximum profit we get after fitting: " + maxProfit);
        
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
