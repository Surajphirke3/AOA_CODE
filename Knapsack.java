import java.util.Scanner;

class Item {
    int weight, profit;
    double ratio;
    
    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}

public class Knapsack
 {
    public static double findOptimalSolution(Item[] items, int M) {
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j].ratio < items[j + 1].ratio) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        
        double maxProfit = 0.0;
        int i = 0;
        int itemCount = 0;
        int[] selectedWeights = new int[items.length];
        int[] selectedProfits = new int[items.length];
        int[] selectedItems = new int[items.length];
        
        while (M > 0 && i < items.length) {
            if (items[i].weight <= M) {
                selectedItems[itemCount] = i + 1;
                selectedWeights[itemCount] = items[i].weight;
                selectedProfits[itemCount] = items[i].profit;
                maxProfit += items[i].profit;
                M -= items[i].weight;
            } else {
                selectedItems[itemCount] = i + 1;
                selectedWeights[itemCount] = M;
                selectedProfits[itemCount] = (int) (M * items[i].ratio);
                maxProfit += selectedProfits[itemCount];
                M = 0;
            }
            itemCount++;
            i++;
        }
        System.out.println("\n*********************************************");
        System.out.println("Items added to the knapsack (item numbers and With Weight and profits):");
        System.out.println("----------------------------------------------------------------");

        for (int j = 0; j < itemCount; j++) {
            System.out.println("Item number: " + selectedItems[j] + " Added item with " +selectedWeights[j]+ " with profit: " + selectedProfits[j]);
        }
        System.out.println("*********************************************");

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of objects (n): ");
        int n = scanner.nextInt();
        
        System.out.print("Enter knapsack capacity (Mw): ");
        int M = scanner.nextInt();
        
        int[] P = new int[n];
        int[] W = new int[n];
        
        System.out.println("Enter profits: ");
        for (int i = 0; i < n; i++) {
            P[i] = scanner.nextInt();
        }
        System.out.println("\n*********************************************");

        System.out.println("Enter weights: ");

        for (int i = 0; i < n; i++) {
            W[i] = scanner.nextInt();
        }
        System.out.println("\n*********************************************");

        System.out.println("Entered items:");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("Item " + (i + 1) + " -> Weight: " + W[i] + ", Profit: " + P[i]);
        }

        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(W[i], P[i]);
        }
        
        double maxProfit = findOptimalSolution(items, M);
        System.out.println("----------------------------------------------------------------");

        System.out.println("Optimal solution (MAX_profit): " + maxProfit);
        System.out.println("Maximum profit we get after fitting: " + maxProfit);
        System.out.println("----------------------------------------------------------------");
        scanner.close();
    }
}
